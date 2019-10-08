package Practicas18.practica1;
import java.util.Scanner;

class Cesar{
    public static void main(String[] args) {
        int n = 0;
        String cad,cifrada = "";
        System.out.println("introduzca la cadena");
        Scanner S = new Scanner(System.in);
        cad = S.nextLine();
        System.out.println("introduzca el numero para cifrar");
        n = S.nextInt();
        S.close();
        for(int i=0;i<cad.length();++i){
            cifrada += (char) (cad.charAt(i) + n%27);
        }
        System.out.println("la cadena cifrada es: "+cifrada);
    }
}