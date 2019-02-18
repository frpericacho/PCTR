package ejercicios_para_practicar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ejercicio4 implements Runnable{
    private int accion;
    static ejercicio4_2 n = new ejercicio4_2();

    public ejercicio4(int i){
        accion = i;
    }   
    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int i = 0; i < Runtime.getRuntime().availableProcessors(); i++){
            exe.execute(new ejercicio4(i%2));
        }
        exe.shutdown();
    }

    public void run(){
        try {
            while(true){
                switch(accion){
                    case 0: n.iniEscritura(); n.finEscritura(); 
                    case 1: n.iniLectura(); n.finLectura(); 
                }
            }
        } catch (Exception e) {}
    }
}

class ejercicio4_2{
    static Integer n = 0;
    static boolean escribiendo = false;

    public synchronized void iniLectura() throws Exception {
        while (escribiendo) {
            wait();
        }
        System.out.println("he empezado a leer");
        n++;
        notifyAll();
    }

    public synchronized void finLectura()throws Exception{
        n--;
        System.out.println("he terminado de leer");
        if(n == 0) notifyAll();
    }

    public synchronized void iniEscritura()throws Exception{
        while (n != 0 || escribiendo) {
            wait();
        }
        System.out.println("he empezado a escribir");
        escribiendo = true;   
    }

    public synchronized void finEscritura()throws Exception{
        escribiendo = false;
        System.out.println("he terminado de escribir");
        notifyAll();
    }
}