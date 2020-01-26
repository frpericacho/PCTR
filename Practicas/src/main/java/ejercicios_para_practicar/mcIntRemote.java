package ejercicios_para_practicar;
import java.math.BigInteger;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface mcIntRemote extends Remote{
	public BigInteger factorial(int numero) throws RemoteException;
}
