package practica1;
import java.util.Scanner;

class aleatorios{
    public static void main(String[] args) {
        int a;
        System.out.println("Introduzca la cantidad de numeros aleatorios que desea ver");
        Scanner S = new Scanner(System.in);
        a = S.nextInt();
        for(int i=0 ; i<a ; ++i){
            System.out.println(Math.random());
        }
        S.close();
    }
}