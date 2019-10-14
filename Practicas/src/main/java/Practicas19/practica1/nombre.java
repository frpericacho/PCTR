package Practicas19.practica1;
import java.util.*;

class cuerpoAstrofisico {

    private double diametro;

    public cuerpoAstrofisico(double d) {
        diametro = d;
    }

    public double getDiametro() {
        return this.diametro;
    }

    public void setDiametro(double d) {
        this.diametro = d;
    }
}

class estrella extends cuerpoAstrofisico {

    double masa;

    public estrella(double d, double l) {
        super(d);
        masa = l;
    }

    public double getMasa() {
        return this.masa;
    }

    public void setMasa(double l) {
        this.masa = l;
    }
}

class cuerpoPlanetario extends cuerpoAstrofisico {

    int sat;

    public cuerpoPlanetario(double d, int n) {
        super(d);

        sat = n;
    }

    public int getSatelites() {
        return this.sat;
    }

    public void setSatelites(int s) {
        this.sat = s;
    }
}

class satelite extends cuerpoAstrofisico {

    double volumen;
    
    public satelite(double d, double v) {
        super(d);

        volumen = v;
    }

    public double getVolumen() {
        return this.volumen;
    }

    public void setVolumen(double v) {
        this.volumen = v;
    }
}

class tierra extends cuerpoPlanetario {

    int atmosferas;

    public tierra(double d, int n, int at) {
        super(d, n);

        atmosferas = at;
    }

    public int getAtmosferas() {
        return this.atmosferas;
    }

    public void setAtmosferas(int at) {
        this.atmosferas = at;
    }
}

class sol extends estrella {

    double temperatura;

    public sol(double d, double m, double t) {
        super(d, m);

        temperatura = t;
    }

    public double getTemperatura() {
        return this.temperatura;
    }

    public void setTemperatura(double t) {
        this.temperatura = t;
    }
}

class luna extends satelite {

    int crateres;

    public luna(double d, double v, int c) {
        super(d, v);

        crateres = c;
    }

    public int getCrateres() {
        return this.crateres;
    }

    public void setCrateres(int c) {
        this.crateres = c;
    }
}