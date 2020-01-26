package ejercicios_para_practicar;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;

public class clientIntRemote extends Thread{
	private int numero;
	private BigInteger resultado;
	private static mcIntRemote objRemoto;
	public clientIntRemote(int numero){
		this.numero = numero;
		this.resultado = null;
	}

	public void run(){
		try{
			obtenerResultado();
		}catch(RemoteException e){
			System.out.println("Ha fallado,q chorprecha");
		}
	}

	public void obtenerResultado() throws RemoteException{
		try{
			this.resultado = objRemoto.factorial(this.numero);
		}catch(RemoteException e){
			System.out.println("Ha fallado,q chorprecha");
		}
		System.out.println("El resultado del hilo " + this.numero + " es de = " + this.resultado.toString());
	}

	public static void main(String[] args)throws  NotBoundException,InterruptedException,MalformedURLException,RemoteException{
		clientIntRemote clientes[] = new clientIntRemote[100];
		Thread hilos[] = new Thread[100];
		for(int i = 0;i < 100;++i){
			clientes[i] = new clientIntRemote(i);
			hilos[i] = new Thread(clientes[i]);
		}
		clientIntRemote.objRemoto = (mcIntRemote)Naming.lookup("//localhost/Server");
 		for(int i = 0;i < 100;++i){
			hilos[i].start();
		}
		for(int i = 0;i < 100;++i){
			hilos[i].join();
		}
	}
}
