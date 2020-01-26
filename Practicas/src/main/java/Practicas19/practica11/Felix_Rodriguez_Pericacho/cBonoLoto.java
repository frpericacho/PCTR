
import java.rmi.*;
import java.rmi.registry.*;
import java.util.Scanner;

public class cBonoLoto {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {

        int bono[] = new int[6];
        iBonoLoto RefObRemoto = (iBonoLoto) Naming.lookup("//localhost/Servidor");

        System.out.println("introduzca sus numeros");
        for(int i = 0; i < 6; i++){
            System.out.println("introduzca el numero " + (i+1));
            bono[i] = sc.nextInt();
        }

        boolean res = RefObRemoto.compApuesta(bono);

        if(res)
            System.out.println("enhorabuena");
        else
            System.out.println("mala suerte");
    }
}