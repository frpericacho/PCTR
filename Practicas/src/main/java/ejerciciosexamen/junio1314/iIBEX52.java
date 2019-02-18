package ejerciciosexamen.junio1314;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface iIBEX52 extends Remote{
    public int[] consultar() throws RemoteException;
    public int consultar1(int val) throws RemoteException;
}