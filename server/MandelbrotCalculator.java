package server;

import java.rmi.*;

public interface MandelbrotCalculator extends Remote{
	public Subset getWork() throws RemoteException;
	
	public void sendPart(Subset s) throws RemoteException;
	
}
