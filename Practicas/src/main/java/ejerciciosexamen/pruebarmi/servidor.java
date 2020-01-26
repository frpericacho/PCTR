package ejerciciosexamen.pruebarmi;

//import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class servidor extends UnicastRemoteObject implements interfaz {
    private static final long serialVersionUID = 5434683111456103226L;

    public servidor() throws RemoteException{

    }

    @Override
    public int lanza() throws RemoteException {
        
        return 0;
    }

    @Override
    public int mostrar() throws RemoteException {
        
        return 0;
    }

    public static void main(String[] args) {
        try {
            interfaz objremoto = (interfaz)new servidor();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        //Naming.bind("//localhost:2020/Servidor", objremoto);
        System.out.println("el servidor esta listo");
    }
}