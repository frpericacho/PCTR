package ejerciciosexamen.pruebarmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;

public class cHELION {
    public static iHELION objremote;

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        cHELION.objremote = (iHELION)Naming.lookup("//localhost/server"); 
        ataque();
    }

    public static void ataque() throws RemoteException {
        boolean guerra = false;
        Random r = new Random();
        while(!guerra){
            guerra = objremote.bomba(r.nextInt(20000));
        }
    }
}