package server;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Mandelbrot {
	private int height, width, zoom;
	private short maxiter;
	private String[] rows;
	private calculationState[] state;
	private boolean end;
	
	public enum calculationState{free,doing,done}
	
	public Mandelbrot(int he, int wi, int zo, short ma){
		this.height=he;
		this.width=wi;
		this.zoom=zo;
		this.maxiter=ma;
		this.rows= new String[height];
		this.state= new calculationState[height];
		Arrays.fill(this.state, calculationState.free);
		this.end=false;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getZoom() {
		return zoom;
	}

	public void setZoom(int zoom) {
		this.zoom = zoom;
	}

	public short getMaxiter() {
		return maxiter;
	}

	public void setMaxiter(short maxiter) {
		this.maxiter = maxiter;
	}

	public String[] getRows() {
		return rows;
	}

	public void setRows(String[] rows) {
		this.rows = rows;
	}
	
	public synchronized Subset getRow(){
		if(!this.isEnd()){
			this.end=true;
			for(int i = 0; i < height ; i++){
				if(this.state[i]==calculationState.free){
					this.end=false;
					this.state[i]=calculationState.doing;
					return new Subset(i,this.width,this.height,this.zoom,this.maxiter,false);
				}
				else if(this.state[i]==calculationState.doing) this.end=false;
				
			}//si recorre todos las filas y todavia no se ha acabado el cálculo, al nuevo cliente se le asigna un trabajo que ya se le asigno a otro cliente
			if(this.end=false){
				for(int i = 0; i < height ; i++){
					if(this.state[i]==calculationState.doing){
						return new Subset(i,this.width,this.height,this.zoom,this.maxiter,false);
					}
				}
			}
		}
		this.end=true;
		this.toFile();
		return new Subset(0,0,0,0,(short)0,true);
	}
	
	public synchronized void fillRow(Subset s){
		if(s.getPosition()<height&&s.getPosition()>=0){
			rows[s.getPosition()]=s.getRow();
			state[s.getPosition()]=calculationState.done;
		}
	}
	private void toFile(){
		PGM p=new PGM(this,(short)2);
		try {
			p.toFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public String getMandelbrotString(){
		String s="";
		for(int i = 0; i < this.height; i++){
			s+=this.rows[i]+"\n";
			this.rows[i]=null;
		}
		
		
		return s;
	}
}
