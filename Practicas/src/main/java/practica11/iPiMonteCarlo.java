package practica11;
import java.rmi.*;

public interface iPiMonteCarlo extends Remote{
    public void reset() throws RemoteException;
    public void masPuntos(int nPuntos)  throws RemoteException;
}