package seminarios;
import java.util.Scanner;

class cruzafuncion{
    public static void main(String[] args) {
        double a=2,b=3,c=0,error = 0.1;
        Scanner S = new Scanner(System.in);
        System.out.println("introduzca c:");
        c = S.nextDouble();
        while(Math.abs(funcion(a)-funcion(b)) < error || funcion(c) == 0){
            if(funcion(a)*funcion(c) > 0){
                a = c;
            }
            else{
                
            }
        }
        S.close();
    }
    
    public static double funcion(double x){
        return Math.pow(x, 2) - 5;
    }
}