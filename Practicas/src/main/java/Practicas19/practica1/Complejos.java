package Practicas19.practica1;
//import java.util.*;

class Complejos{
    public Complejos(double real,double imaginario){
        this.real=real;
        this.imaginario=imaginario;
    }
    public Complejos suma(Complejos a){
        return new Complejos(this.real+a.real,this.imaginario+a.imaginario);
    }
    public Complejos resta(Complejos a){
        return new Complejos(this.real-a.real,this.imaginario-a.imaginario);
    }
    public double modulo(){
        return Math.hypot(this.real, this.imaginario);
    }
    public Complejos producto(Complejos a){
        return new Complejos(((this.real*a.real)-(this.imaginario*a.imaginario)),((this.real*a.imaginario)+(this.imaginario*a.real)));
    }
    public Complejos division(Complejos a){
        return new Complejos(((this.real*a.real)+(this.imaginario*a.imaginario))/((Math.pow(a.real, 2))+Math.pow(a.imaginario, 2)),((this.imaginario*a.real)-(this.real*a.imaginario))/((Math.pow(a.real, 2))+(Math.pow(a.imaginario, 2))));
    } 
    private double real;
    private double imaginario;

    public String toString(){
        return real+" " +imaginario +"i";
    }
}