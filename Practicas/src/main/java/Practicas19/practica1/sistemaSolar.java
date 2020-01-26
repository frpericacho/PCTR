package Practicas19.practica1;

import java.util.*;

public class sistemaSolar {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        double d_t;
        int s_t, a_t, cant; 

        System.out.println("Introduzca el diametro del planeta: Tierra");
        d_t = sc.nextDouble();
        
        System.out.println("Introduzca los satelites del planeta: Tierra");
        s_t = sc2.nextInt();
        
        System.out.println("Introduzca las atmosferas del planeta: Tierra");
        a_t = sc2.nextInt();
        
        tierra t = new tierra(d_t,s_t,a_t);

        System.out.println("las atmosferas del planeta: Tierra, son:");
        System.out.println(t.getAtmosferas());

        System.out.println("introduzca la nueva cantidad de atmosforeas:");
        cant = sc2.nextInt();
        t.setAtmosferas(cant);

        System.out.println(t.getDiametro());
        System.out.println(t.getSatelites());
        System.out.println(t.getAtmosferas());
    }
}