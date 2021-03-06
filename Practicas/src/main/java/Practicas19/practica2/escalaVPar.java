package Practicas19.practica2;

import java.util.Random; 

class escalaVPar extends Thread{
    static Random r = new Random();
    private int ini,fin,n=2;
    static int []vector = new int[(int)Math.pow(10, 8)];
    public static void main(String[] args) throws Exception{
        rellena();
        escalaVPar[] hilos = new escalaVPar[Runtime.getRuntime().availableProcessors()];
        int len = (int)Math.pow(10, 8) / hilos.length;
        for(int i = 0; i < hilos.length; ++i){
            hilos[i] = new escalaVPar(i*len, (i+1)*len);
            hilos[i].start();
        }
        for(int i = 0; i < hilos.length; ++i){
            hilos[i].join();
        }
    }
    
    public static void rellena(){
        for(int i = 0; i < vector.length; ++i){
            vector[i] = r.nextInt(10);
        }
    }

    public escalaVPar(int ini, int fin){
        this.ini = ini;
        this.fin = fin;
    }

    public void run(){
        for(int i = ini; i < fin; ++i){
            vector[i] *= n;
        }
    }
}