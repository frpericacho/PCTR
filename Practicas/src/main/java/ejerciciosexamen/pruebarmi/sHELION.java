
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class sHELION extends UnicastRemoteObject implements iHELION {

    private static final long serialVersionUID = 1L;
    public int[] planeta = new int[20000];
    public int ciudad = 2500;
    public boolean res = false;
    public Random r = new Random();

    public sHELION(int port) throws RemoteException {
        int cant = 2500;
        while(cant != 0){
            int j = r.nextInt(20000);
            if(planeta[j] == 0){
                planeta[j] = 1;
                cant--;
            }
        }

        boolean a = true;
        
        while(a){
            int j = r.nextInt(20000);
            if(planeta[j] == 0){
                planeta[j] = 2;
                a = false;
            }
        }
    }

    public boolean bomba(int pos) throws RemoteException {
        if(planeta[pos]==2){
            System.out.println("planeta destruido");
            return true;
        }else{
            if(planeta[pos]==1){
                planeta[pos]=0;
                ciudad--;
                if(ciudad==100){
                    System.out.println("armisticio");
                    return true;
                }
                return false;
            }
            else{
                return false;
            }
        }
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
        sHELION server = new sHELION(2020);
        Naming.bind("servidor",server);
        System.out.println("el servidor esta listo");
    }
}