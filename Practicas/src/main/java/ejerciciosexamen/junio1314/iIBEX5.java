package ejerciciosexamen.junio1314;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface iIBEX5 extends Remote{
    void consultar_todos() throws RemoteException;
    void consultar_uno(int val) throws RemoteException;
}