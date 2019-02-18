package ejerciciosexamen.septiembre1314;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class cJuego{
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        iJuego serv = (iJuego)Naming.lookup("server");
        serv.reset();
    }
}