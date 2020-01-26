import java.rmi.*;
import java.rmi.server.*;
import java.util.concurrent.locks.*;
import java.util.*;

public class SPiMonteCarlo extends UnicastRemoteObject implements iPiMonteCarlo{
    /**
     *
     */
    private static final long serialVersionUID = 3899160662481710257L;
    Random r = new Random();
	private int cont=0;
	private int puntos=0;
	private double aprox=0;
	static ReentrantLock cerrojo = new ReentrantLock();
	public SPiMonteCarlo()throws RemoteException{super();}
	
	public void reset()throws RemoteException{
		cerrojo.lock();
		try{
			cont=0;
			puntos=0;
		}finally{cerrojo.unlock();}
	}
	public double masPuntos(int nPuntos)throws RemoteException{
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
			return aprox;
		}finally{cerrojo.unlock();}
		
		
		
	}
	public static void main(String []arg)throws Exception{
	
		iPiMonteCarlo ORemoto = new SPiMonteCarlo();
		Naming.bind("Servidor", ORemoto);
		System.out.println("Sevidor Remoto Preparado");
		
	
	}

}