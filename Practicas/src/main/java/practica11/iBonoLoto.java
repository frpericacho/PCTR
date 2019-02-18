package practica11;

import java.rmi.*;

public interface iBonoLoto extends Remote{

    int[] genera() throws RemoteException;
    Boolean acierto(int[] a) throws RemoteException;

}