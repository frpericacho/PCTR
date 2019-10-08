package Practicas19.practica1;
import java.util.*;

class usaComplejos{
    public static void main(String[] args) {
        double a = 0,b = 0;
        int c = 0,d = 0;
        System.out.println("introduzca la parte real de a");
        Scanner S = new Scanner(System.in);
        Scanner Sc = new Scanner(System.in); 
        a = S.nextDouble();
        System.out.println("introduzca la parte imaginaria de a");
        b = S.nextDouble();
        Complejos comple = new Complejos(a,b);

        System.out.println("introduzca la parte real de b");
        a = S.nextDouble();
        System.out.println("introduzca la parte imaginaria de b");
        b = S.nextDouble();
        Complejos comple2 = new Complejos(a,b);
        do{
            do{
                System.out.println("escoja una opcion:");
                System.out.println("1.- Suma");
                System.out.println("2.- Resta");
                System.out.println("3.- Modulo de a");
                System.out.println("4.- Modulo de b");
                System.out.println("5.- Producto");
                System.out.println("6.- Division");
                System.out.println("7.- Salir");
                c = Sc.nextInt();
            }while(c < 1 || c > 7);
            switch(c){                
                case 1: System.out.println("La suma es: "+comple.suma(comple2).toString()); break;
                case 2: System.out.println("La resta es: "+comple.resta(comple2).toString()); break;
                case 3: System.out.println("El Modulo es: "+comple.modulo()); break;
                case 4: System.out.println("El Modulo es: "+comple2.modulo()); break;
                case 5: System.out.println("El Producto es: "+comple.producto(comple2).toString()); break;
                case 6: System.out.println("La Division es: "+comple.division(comple2).toString()); break;
                case 7: System.exit(0); break;
                default: System.out.println("opcion no valida");
            }
            System.out.println("Desea realizar otra operacion?(1 para salir)");
            d = Sc.nextInt();
        }while(d!=1);
        S.close();
        Sc.close();
    }
}