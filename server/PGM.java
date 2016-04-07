package server;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class PGM {
	private final Mandelbrot mandel;
	private final short color;
	
	public PGM( Mandelbrot mandel, short color){
		this.mandel=mandel;
		this.color=color;
	}
	
	public void toFile() throws FileNotFoundException, UnsupportedEncodingException{
		String text="P"+color+"\n"+this.mandel.getWidth()+" "+this.mandel.getHeight()+"\n";
		if(this.color!=1) text+="255\n";
		text+=this.mandel.getMandelbrotString();
		
		PrintWriter w = new PrintWriter("output"+this.getExtension(),"UTF-8");
		w.print(text);
		w.close();
		
	}
	
	private String getExtension(){
		String ext=".pbm";
		if(this.color == 2) ext=".pgm";
		else if(this.color == 3) ext=".ppm";
		return ext;
		
	}

}
