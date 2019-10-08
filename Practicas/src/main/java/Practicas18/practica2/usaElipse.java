package practica2;
import java.util.*;

class usaElipse{
     public static void main(String[] args) {
        double a = 0,b = 0,x = 0,y = 0;
        Scanner S = new Scanner(System.in);
        System.out.println("introduzca el eje mayor:");
        a = S.nextDouble();
        System.out.println("introduzca el eje menor:");
        b = S.nextDouble();
        Elipse elip = new Elipse(a, b);
        System.out.println("el area es: "+elip.area());
        System.out.println("el perimetro es: "+elip.perimetro());
        System.out.println("introduzca la coordenada x del punto a buscar:");
        x = S.nextDouble();
        System.out.println("introduzca la coordenada y del punto a buscar:");
        y = S.nextDouble();
        if(elip.punto(x, y)){
            System.out.println("el punto esta en la elipse");
        } 
        else System.out.println("el punto no esta en la elipse");
        S.close();
     }
}