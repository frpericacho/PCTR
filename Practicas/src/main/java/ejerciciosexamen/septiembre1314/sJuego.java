package ejerciciosexamen.septiembre1314;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class sJuego extends UnicastRemoteObject implements iJuego {
    static Random r = new Random();
    public static int a;

    private static final long serialVersionUID = 1L;

    @Override
    public void reset() throws RemoteException {
        a = r.nextInt(99)+1;
        hacerIntento(a);
    }

    @Override
    public boolean hacerIntento(int numero) throws RemoteException {
        if(a == numero)
            return true;
        else
            return false;
    }
    
    public sJuego() throws RemoteException{}

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        LocateRegistry.createRegistry(1066);
        iJuego servidor = new sJuego();
        Naming.rebind("server", servidor);
        System.out.println("servidor activado");
    }
}