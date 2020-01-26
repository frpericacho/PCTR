package ejercicios_para_practicar;

import java.util.Scanner;

public class Ej6H2 {
    public static void main(String[] args) {
        int x=0,y=0;
        double a=5.9,b=2.3;
        Scanner S = new Scanner(System.in);
        
        System.out.println("introduzca x");
        x = S.nextInt();
        System.out.println("introduzca y");
        y = S.nextInt();
        
        System.out.println("la suma de x e y es: "+suma(x,y));
        System.out.println("la suma de a e b es:"+suma(a,b));
        System.out.println("la suma de x, x veces es: "+suma(x));
        S.close();
    }

    public static int suma(int x,int y){
        return x+y;
    }

    public static double suma(double x,double y){
        return x+y;
    }

    public static int suma(int x){
        int n = x,cont = 0;
        
        while(n!=0){
            cont += x;
            n--;
        }

        return cont;
    }
}