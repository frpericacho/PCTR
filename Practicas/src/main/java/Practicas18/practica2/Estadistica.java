package Practicas18.practica2;
import java.util.*;

class Estadistica{
    public static void main(String[] args) {
        int n = 0,a = 1,b = 1;
        Scanner S = new Scanner(System.in);
        Random r = new Random();
        System.out.println("Introduzca la cantidad");
        n = S.nextInt();
        System.out.println();
        int []vec = new int[n];
        for(int i = 0;i < n; ++i){
            vec[i] = r.nextInt(n);
        }
        for(int i = 0;i < n; ++i){
            System.out.print(vec[i]+" ,");
        }
        
        do{
            do{
                System.out.println("introduzca la opcion deseada");
                System.out.println("1.- Media");
                System.out.println("2.- Moda");
                System.out.println("3.- Varianza");
                System.out.println("4.- Desviacion tipica");
                System.out.println("5.- Salir");
                a = S.nextInt();
                    switch(a){
                        case 1: System.out.println("La media es: " +media(vec)); break;
                        case 2: moda(vec);  break;
                        case 3: System.out.println("La varianza es: " +varianza(vec, media(vec))); break;
                        case 4: System.out.println("La desviacion tipica es: "+Math.sqrt(varianza(vec, media(vec))));   break;
                        case 5: System.exit(0);
                        default: System.out.println("Opcion no valida");    break;
                    }
                    System.out.println("Desea elegir otra opcion?(0 para salir)");
                    b = S.nextInt();
            }while(b!=0);
            if(b==0) 
                System.exit(0);
        }while(a!=5);
        S.close();
    }

    public static double media(int []vec){
        double med = 0;
        for(int i = 0;i < vec.length; ++i){
            med += vec[i];
        }
        return (double)med/vec.length;
    }
    
    public static void moda(int []vec){
        int aux1 = 0,aux2 = 0,moda = 0;
        for(int i = 0;i < vec.length; ++i){
            for(int j = 0;j < vec.length; ++j){
                if(vec[i]==vec[j]){
                    ++aux2;
                }
            }
            if(aux2>aux1){
                aux1 = aux2;
                moda = vec[i];
            }
            aux2 = 0;
        }
        System.out.println("La moda es : "+moda);
    }
    
    public static double varianza(int []vec,double med){
        int suma = 0;
        for(int i = 0;i < vec.length; ++i){
            suma += Math.pow((vec[i]-med), 2);
        }
        return (double)suma/(vec.length-1);
    }
}