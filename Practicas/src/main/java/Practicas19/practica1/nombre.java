package Practicas19.practica1;
import java.util.*;

class cuerpoAstrofisico{
    public static double masa;

    cuerpoAstrofisico(double m){
        masa = m;
    }

    public static double obser(){
        return masa;
    }

    public static void modif(double m){
        masa = m;
    }
}

class estrella{
    public static double masa;

    estrella(double m){
        masa = m;
    }

    public static double obser(){
        return masa;
    }

    public static void modif(double m){
        masa = m;
    }
}

class cuerpoPlanetario{
    public static double masa;

    cuerpoPlanetario(double m){
        masa = m;
    }

    public static double obser(){
        return masa;
    }

    public static void modif(double m){
        masa = m;
    }
}

class satelite{
    public static double masa;

    satelite(double m){
        masa = m;
    }

    public static double obser(){
        return masa;
    }

    public static void modif(double m){
        masa = m;
    }
}

class sol extends estrella{

    sol(double m) {
        super(m);
    }

    public static double obser(){
        return masa;
    }

    public static void modif(double m){
        masa = m;
    }
}

class tierra extends cuerpoPlanetario{

    tierra(double m) {
        super(m);
    }

    public static double obser(){
        return masa;
    }

    public static void modif(double m){
        masa = m;
    }
}

class luna extends satelite{

    luna(double m) {
        super(m);
    }

    public static double obser(){
        return masa;
    }

    public static void modif(double m){
        masa = m;
    }
}