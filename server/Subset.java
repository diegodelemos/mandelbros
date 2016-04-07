package server;

import java.io.Serializable;

public class Subset implements Serializable{
	private int position, width, height, zoom;
	private short maxiter;
	private String row;
	private boolean end;
	public Subset(int pos, int wi, int he, int zo, short ma, boolean e){
		this.position=pos;
		this.width=wi;
		this.height=he;
		this.zoom=zo;
		this.maxiter=ma;
		this.row="";
		this.end=e;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getZoom() {
		return zoom;
	}

	public void setZoom(int zoom) {
		this.zoom = zoom;
	}

	public int getMaxiter() {
		return maxiter;
	}

	public void setMaxiter(short maxiter) {
		this.maxiter = maxiter;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}
	
	public void calculateSubset(){
		double zx,zy,cx,cy,y=this.position,tmp;
        for (int x = 0; x < this.width; x++) {
            zx = zy = 0;
            cx = (x + 2000 - this.width/2) / (double)this.zoom;
            cy = (y - this.height/2) / (double)this.zoom;
            short iter = this.maxiter;
            while (zx * zx + zy * zy < 4 && iter > 0) {
            	double aux = cx*cx+cy*cy;
                tmp = zx*zx - zy*zy + (cx/aux);
                zy = 2*zx*zy - (cy/aux);
                zx = tmp;
                iter--;
            }
            this.row+=iter+" ";
        }
		/*double y=this.position;
		Complex z,c;
		for (int x = 0; x < this.width; x++){
			z=new Complex(0.065,0.122);
			c=new Complex((x-this.width/2)/(double)this.zoom,(y-this.height/2)/(double)this.zoom);
			short iter= this.maxiter;
			while(z.mod()<2 && iter > 0){
				z=z.times(z).plus((new Complex(1,0)).div(c));
				iter--;
			}
			this.row+=(255-iter)+" "+iter+" "+iter+" ";
			switch(iter){
				case 255:
					this.row+="255 100 100";
					break;
				case 254:
					this.row+="255 100 100";
					break;
				default:
					this.row+=iter+" "+iter+" "+iter+" ";
					break;
			}
		}*/
	}
}
