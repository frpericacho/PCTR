package ejerciciosexamen.septiembre1314;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface iJuego extends Remote {
    public void reset() throws RemoteException;
    public boolean hacerIntento(int numero) throws RemoteException;
}