package server;

public class MandelbrotAntiguo {
	private final int height, width;
	private final short MAXITER;
	private short[] set;
	
	public MandelbrotAntiguo(short MAXITER, int height, int width){
		this.MAXITER=MAXITER;
		this.height=height;
		this.width=width;
		this.set=new short[this.height*this.width];
	}
	
	public void calculate(){
		double zx, zy, cx, cy, tmp, zoom;
		zoom = 50;
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                zx = zy = 0;
                cx = (x - this.width/2) / zoom;
                cy = (y - this.height/2) / zoom;
                short iter = this.MAXITER;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = zx * zx - zy * zy + cx;
                    zy = 2 * zx * zy + cy;
                    zx = tmp;
                    iter--;
                }
                this.set[x+this.width*y]=iter;
            }
        }
	}
	
	public short[] getSet(){
		return this.set;
	}
	
	public String toString(){
		String s="";
		for(int i = 0 ; i < this.height; i++){
			for(int j = 0; j < this.width; j++)
				s+=this.set[j+i*this.width]+" ";
			s+="\n";
		}
		return s;
	}

}