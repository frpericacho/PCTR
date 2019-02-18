package ejercicios_para_practicar;
import java.math.BigInteger;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;

public class serverIntRemote extends UnicastRemoteObject implements mcIntRemote{
	private static final long serialVersionUID = -1013618783650258946L;

	public serverIntRemote() throws RemoteException {}
	
	public BigInteger factorial(int numero)throws RemoteException{
		BigInteger resultado = new BigInteger("1");
		for(int i = 2;i <= numero;++i){
			resultado = resultado.multiply(new BigInteger(Integer.toString(i)));
		}
		return resultado;
	}
	public static void main(String[] args)throws AlreadyBoundException, MalformedURLException,RemoteException{
		serverIntRemote servidor = new serverIntRemote();
		Naming.bind("Server",servidor);
		System.out.println (" Servidor Remoto Preparado ") ;
	}
}
