/*package ejerciciosexamen.febrero1314;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Random;

public class ejercicio2s extends UnicastRemoteObject implements ejercicio2i{
    private int ciudad;
    private int[] planeta;

    public boolean misil(int pos) {
        if(planeta[pos]==1){
            ciudad--;
            planeta[pos]=0;
            if(ciudad == 100)
                System.out.println("Armisticio");
                return true;
        }else if(planeta[pos]==2){
            System.out.println("Destruido");
            planeta[pos]=0;
            return true;
        }else{
            return false;
        }

    }

    public ejercicio2s() throws RemoteException{
        planeta = new int[20000];
        ciudad = 2500;
        Arrays.fill(planeta, 0);
        Random r = new Random();
        boolean a = false;
        int cont = 2500;

        while(cont != 0){
            int j = r.nextInt(20000);
            if(j == 0){
                planeta[r.nextInt(20000)] = 1;
                cont--;
            }
        }
        
        while(a){
            int j = r.nextInt(20000);
            if(j == 0){
                planeta[j] = 2;
                a = true;
            }
        }
    }

    public static void main(String[] args) {
        
    }
}*/