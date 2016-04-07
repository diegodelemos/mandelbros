package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
	public static void main(String[] args){
		try {
			int width=Integer.parseInt(args[0]);
			int height=Integer.parseInt(args[1]);
			int zoom=Integer.parseInt(args[2]);
			short maxiter=Short.parseShort(args[3]);
			
			Mandelbrot m= new Mandelbrot(height,width,zoom,maxiter);
			
			//System.setProperty("java.rmi.server.hostname", "172.19.216.165");

			MandelbrotCalculator c = new MandelbrotCalculatorImp(m);
			LocateRegistry.createRegistry(1099);
			Naming.rebind("rmi://localhost:1099/MandelbrotService", c);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

