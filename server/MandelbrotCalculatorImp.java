package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MandelbrotCalculatorImp extends UnicastRemoteObject implements MandelbrotCalculator{
	private Mandelbrot mandel;
	
	public MandelbrotCalculatorImp(Mandelbrot m) throws RemoteException{
		this.mandel=m;
	}
	
	public Subset getWork() throws RemoteException {
		return mandel.getRow();
	}

	public void sendPart(Subset s) throws RemoteException {
		mandel.fillRow(s);
	}

}
