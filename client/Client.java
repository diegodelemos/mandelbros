package client;

import java.rmi.Naming;

import server.MandelbrotCalculator;
import server.Subset;

public class Client {
	public static void main(String[] args){
		try{
			while(true){
				String url=args[0];
				String port=args[1];
				MandelbrotCalculator c = (MandelbrotCalculator) Naming.lookup("rmi://"+url+":"+port+"/MandelbrotService");
				Subset subset = c.getWork();
				System.out.println(subset.getPosition());
				if(subset.isEnd()) return;
				else{
					subset.calculateSubset();
					c.sendPart(subset);
				}
			}
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}
}
