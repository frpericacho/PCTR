package practica11;
import java.rmi.*;
import java.rmi.server.*;
import java.util.concurrent.locks.*;
import java.util.*;

public class sPiMonteCarlo extends UnicastRemoteObject implements iPiMonteCarlo{
	private static final long serialVersionUID = 1L;
    Random r = new Random();
	private int cont=0;
	private int puntos=0;
	private double aprox=0;
	static ReentrantLock cerrojo = new ReentrantLock();
	public sPiMonteCarlo()throws RemoteException{super();}
	
	public void reset()throws RemoteException{
		cerrojo.lock();
		try{
			cont=0;
			puntos=0;
		}finally{cerrojo.unlock();}
	}
	public void masPuntos(int nPuntos)throws RemoteException{
		double x,y;
		cerrojo.lock();
		try{
			puntos=puntos+nPuntos;
			for(int i=0;i<nPuntos;i++){
				x=r.nextDouble();
				y=r.nextDouble();
				if((y*y)<(1-(x*x))){
				cont++;
				}
			}
			aprox=(double)4*cont/puntos;
			System.out.println("La aproximacion para pi seria: "+aprox+ " con un numero de puntos: "+puntos);
		}finally{cerrojo.unlock();}
		
		
		
	}
	public static void main(String []arg)throws Exception{
	
		iPiMonteCarlo ORemoto = new sPiMonteCarlo();
		Naming.bind("Servidor", ORemoto);
		System.out.println("Sevidor Remoto Preparado");
		
	
	}

}