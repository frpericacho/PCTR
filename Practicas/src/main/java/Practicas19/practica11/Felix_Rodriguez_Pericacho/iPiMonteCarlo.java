import java.rmi.*;
public interface iPiMonteCarlo
  extends Remote
{
  public void reset() throws RemoteException;
  public double masPuntos(int nPuntos)  throws RemoteException;

}