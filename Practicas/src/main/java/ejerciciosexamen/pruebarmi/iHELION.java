package ejerciciosexamen.pruebarmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface iHELION extends Remote {
    public boolean bomba(int pos) throws RemoteException;
}