package practica11;

import java.rmi.*;
import java.rmi.server.*;

public class sBonoLoto extends UnicastRemoteObject implements iBonoLoto {
    private static final long serialVersionUID = 1L;
    public static int[] bono = new int[6];

    public sBonoLoto() throws RemoteException{
        genera();
    }

    public int[] genera() throws RemoteException {
        
        for(int i = 0;i < 6; i++){
            bono[i] = (int)(Math.random() + 1);
        }

        return bono;
    }

    
    public Boolean acierto(int[] a) throws RemoteException {
        boolean acertar = true;

        for(int i = 0;i < 6; i++){
            if(a[i] != bono[i])
                acertar = false;
        }

        return acertar;
    }
    
    public static void main (String[] args) throws Exception {
        iBonoLoto objRem1 = new sBonoLoto();
        Naming.bind("Server", objRem1);
        System.out.println("Servidor Remoto Preparado.");
    }

}