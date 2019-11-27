package Practicas19.practica6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class intParalelomultiCont implements Runnable{
    public int parcial;
    public int hebra;
    public static int cont;
    public static int cores = Runtime.getRuntime().availableProcessors();
    public static int cant = 100000;
    public static double puntos;
    public static Object cerrojo = new Object();


    public intParalelomultiCont(double puntos,int hebra){
        this.puntos = puntos;
        this.hebra = hebra;
    }
    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(cores);
        long inicio = System.currentTimeMillis();
        for(int i = 0; i < cores; i++){
            exe.execute(new intParalelomultiCont(cant/cores,i));
        }
        exe.shutdown();
        while(!exe.isTerminated());
        long fin = System.currentTimeMillis();
        System.out.println("    El area es: "+ (double)cont/cant);
        System.out.println("el tiempo es: " + (fin-inicio));
    }
    
    @Override
    public void run() {
        double x,y;
        for(int i=0; i < (int)puntos; ++i){
            x = Math.random();
            y = Math.random();
            if(Math.sin(x) >= y){
                ++parcial;
            }
        }
        synchronized(cerrojo){cont+=parcial;}
    }
}