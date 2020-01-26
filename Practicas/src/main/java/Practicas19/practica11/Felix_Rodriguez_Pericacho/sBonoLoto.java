
import java.rmi.*;
import java.rmi.server.*;
import java.util.Random;

import java.rmi.registry.*;
import java.net.*;

public class sBonoLoto extends UnicastRemoteObject implements iBonoLoto {

    protected sBonoLoto() throws RemoteException {
        super();
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Random r = new Random();
    private int[] bono = new int[6];

    public static void main(String[] args)
 	  throws Exception 
 	{
 		iBonoLoto ORemoto = new sBonoLoto();
 		
 		Naming.bind("Servidor", ORemoto);
 		  
 		System.out.println("Servidor Remoto Preparado");
 	}

    @Override
    public void resetServidor() throws RemoteException {
        for(int i = 0; i < 6; i++){
            bono[i] = r.nextInt(49)+1;
        }
    }

    @Override
    public boolean compApuesta(int[] apuesta) throws RemoteException {
        for(int i = 0; i < 6; i++){
            if(bono[i] != apuesta[i])
                return false;
        }
        return true;
    }
}
