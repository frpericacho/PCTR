package ejerciciosexamen.junio1314;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class sIBEX52 extends UnicastRemoteObject implements iIBEX52 {

    private static final long serialVersionUID = 6111382833824563268L;
    private static int[] v = new int[5];

    public int[] consultar() throws RemoteException {
        int [] res = new int[5];

        for(int i = 0; i < 5; i++){
            res[i] = v[i];
        }

        return res;
    }

    public int consultar1(int val) throws RemoteException {
        int res = 0;
        switch(val){
            case 1: res = v[0];
            case 2: res = v[1];
            case 3: res = v[2];
            case 4: res = v[3];
            case 5: res = v[4];
        }
        return res;
	}

    public sIBEX52(int server) throws RemoteException{
        super(server);
    }    

    private static void inicializa(){
        for(int i = 0;i < 5; i++){
            v[i] = 1;
        }
    }

    public static void main(String[] args) {
        try {
            sIBEX52 server = new sIBEX52(2020);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        inicializa();
       // Naming.bind("servidor", server);
        System.out.println("servidor listo");
    }
}