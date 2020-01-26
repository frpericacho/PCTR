package ejerciciosexamen.pruebarmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface interfaz extends Remote{
    public int lanza() throws RemoteException;
    public int mostrar() throws RemoteException;
}