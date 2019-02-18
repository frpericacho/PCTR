package practica3;
import java.util.*;

public class usaTodo{
    public static void main(String[] args) {
        triangulo tri = new triangulo();
        cuadrado cuad = new cuadrado();
        pentagono penta = new pentagono();
        hexagono hexa = new hexagono();
        Scanner S = new Scanner(System.in);
        double x = 0,y = 0;
        System.out.println("TRIANGULO");
        for(int i = 0;i < 3; ++i){
            System.out.println("Introduzca el punto de la coordenada X; ");
            x = S.nextDouble();
            System.out.println("introduzca el punto de la coordenada Y: ");
            y = S.nextDouble();
            tri.mas_puntos(new Punto(x, y));
        }
        System.out.println("El perimetro es: "+tri.perimetro());
        tri.nuevo_punto();

        System.out.println("CUADRADO");
        for(int i = 0;i < 4; ++i){
            System.out.println("Introduzca el punto de la coordenada X; ");
            x = S.nextDouble();
            System.out.println("introduzca el punto de la coordenada Y: ");
            y = S.nextDouble();
            cuad.mas_puntos(new Punto(x, y));
        }
        System.out.println("El perimetro es: "+cuad.perimetro());
        cuad.nuevo_punto();

        System.out.println("PENTAGONO");
        for(int i = 0;i < 5; ++i){
            System.out.println("Introduzca el punto de la coordenada X; ");
            x = S.nextDouble();
            System.out.println("introduzca el punto de la coordenada Y: ");
            y = S.nextDouble();
            penta.mas_puntos(new Punto(x, y));
        }
        System.out.println("El perimetro es: "+penta.perimetro());
        penta.nuevo_punto();
        
        System.out.println("HEXAGONO");
        for(int i = 0;i < 6; ++i){
            System.out.println("Introduzca el punto de la coordenada X; ");
            x = S.nextDouble();
            System.out.println("introduzca el punto de la coordenada Y: ");
            y = S.nextDouble();
            hexa.mas_puntos(new Punto(x, y));
        }
        S.close();
        System.out.println("El perimetro es: "+hexa.perimetro());
        hexa.nuevo_punto();
    }
}