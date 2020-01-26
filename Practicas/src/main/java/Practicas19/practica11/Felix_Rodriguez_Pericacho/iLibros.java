
import java.rmi.*;
public interface iLibros extends Remote{

    public void inserta(String nom, String autor,int id) throws RemoteException;
    
	public String[] consulta(String nombre) throws RemoteException;
    
    public void borra(String nombre) throws RemoteException;	
    
}