package ejerciciosexamen.junio1112;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * icinema
 */
public interface iMatriz extends Remote {
  public String buyTicket(String movie, int seat) throws RemoteException;
}