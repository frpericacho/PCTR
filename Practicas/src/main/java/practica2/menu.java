package practica2;
import java.util.*;

class menu{
    static Scanner S = new Scanner(System.in);
    public static void main(String[] args) {
        int a,b=0;

        do{
            do{
                System.out.println("introduzca la opcion deseada");
                System.out.println("1.- Cifrado Cesar");
                System.out.println("2.- Descifrado Cesar");
                System.out.println("3.- lo que sea 3");
                System.out.println("4.- lo que sea 4");
                a = S.nextInt();
                switch(a){
                    case 1: cesar(); break;
                    case 2: desCesar(); break;
                    case 3: System.out.println("Esta es la opcion 3"); break;
                    case 4: System.out.println("Esta es la opcion 4"); break;
                    default: System.out.println("introduzca una opcion valida");
                }
            }while(a > 4 || a < 1);
                System.out.println("Desea hacer otra opercacion?(pulse 1 para seguir)");
                b = S.nextInt();
        }while(b == 1);
    }
    public static void cesar(){
        int n = 0;
        String cad,cifrada = "";
        System.out.println("introduzca la cadena");
        cad = S.nextLine();
        System.out.println("introduzca el numero para cifrar");
        n = S.nextInt();
        for(int i=0;i<cad.length();++i){
            cifrada += (char) (cad.charAt(i) + n%26);
        }
        System.out.println("la cadena cifrada es: "+cifrada);
    }
    public static void desCesar() {
        int k = 0;
        String cad,cifrada = "";
        System.out.println("introduzca la cadena");
        cad = S.nextLine();
        System.out.println("introduzca el numero para cifrar");
        k = S.nextInt();
        for(int i=0;i<cad.length();++i){
            cifrada += (char) (cad.charAt(i) - k%26);
        }
        System.out.println("la cadena cifrada es: "+cifrada);
    }

}