package practica11;
import java.rmi.*;

public interface iLibro extends Remote{
	public void insertar(String[] datos,long id) throws RemoteException;
	public String consultar(String parametro) throws RemoteException;
	public void borrar(String nombre) throws RemoteException;
}