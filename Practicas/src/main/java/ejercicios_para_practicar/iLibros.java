package ejercicios_para_practicar;
import java.rmi.*;
public interface iLibros extends Remote{

	public void insertar(String[] datos,int isbn) throws RemoteException;
	public String consultar(String parametro) throws RemoteException;
	public void borrar(String nombre) throws RemoteException;
	
}