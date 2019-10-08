package Practicas18.practica1;
import java.util.Scanner;

class NewtonRaphson{    
    public static void main(String[] args) {
        fun1.resultado();
        fun2.resultado();
    }
}
class fun1{
    public static double f1(double x){
        return Math.cos(x)-Math.pow(x,3);
    }
    public static double f1d(double x){
        return -Math.sin(x)-3*Math.pow(x,2);
    }
    public static void resultado(){
        double a,e0 = 0.000000001;
        System.out.println("introduzca la primera aproximacion");
        Scanner S = new Scanner(System.in);
        a = S.nextDouble();
        double iter = Math.abs(f1(a));
        System.out.println(" f1 ");
        while(iter >= e0){
            a = a - f1(a)/f1d(a);
            System.out.println(" x = "+a);
            iter = Math.abs(Math.cos(a)-Math.pow(a,3));
            System.out.println(" f(x) = "+(Math.cos(a)-Math.pow(a,3)));
        }
    }
}
class fun2{
    public static double f2(double x){
        return Math.pow(x,2)-5;
    }
    public static double df2(double x){
        return 2*x;
    }
    public static void resultado() {
        double b,e0 = 0.000000001;
        System.out.println("introduzca la primera aproximacion");
        Scanner D = new Scanner(System.in);
        b = D.nextDouble();
        double iter = Math.abs(f2(b));
        while(iter >= e0){
            b = b - f2(b)/df2(b);
            System.out.println(" x = "+b);
            iter = Math.abs(Math.pow(b,2)-5);
            System.out.println(" f(x) ="+(Math.pow(b,2)-5));
        }
    }
}