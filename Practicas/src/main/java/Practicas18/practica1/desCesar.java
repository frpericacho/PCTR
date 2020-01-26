package Practicas18.practica1;
import java.util.Scanner;

class desCesar{
    public static void main(String[] args) {
        int k = 0;
        String cad,cifrada = "";
        System.out.println("introduzca la cadena");
        Scanner S = new Scanner(System.in);
        cad = S.nextLine();
        System.out.println("introduzca el numero para cifrar");
        k = S.nextInt();
        S.close();
        for(int i=0;i<cad.length();++i){
            cifrada += (char) (cad.charAt(i) - k%26);
        }
        System.out.println("la cadena cifrada es: "+cifrada);
    }
}