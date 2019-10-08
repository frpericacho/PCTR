package Practicas19.practica1;
import java.util.Scanner;
import java.util.*;

class intDefinidaMonteCarlo{
    public static void main(String[] args) {
        double x,y;
        int puntos,p_abajo=0;

        System.out.println("introduzca la cantidad de puntos aleatorios que desea probar");
        Scanner S = new Scanner(System.in);
        puntos = S.nextInt();
        S.close();

        System.out.println("para el seno: ");
        for(int i=0; i < puntos; ++i){
            x = Math.random();
            y = Math.random();
            if(Math.sin(x) >= y){
                ++p_abajo;
            }
        }
        System.out.println("    El area es: "+ (double)p_abajo/puntos);
       
        p_abajo = 0;
        System.out.println("para x: ");
        for(int i=0; i < puntos; ++i){
            x = Math.random();
            y = Math.random();
            if(x > y){
                ++p_abajo;
            }
        }
        System.out.println("    El area es: "+ (double)p_abajo/puntos);
    }
}