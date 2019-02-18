package ejercicios_para_practicar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EM_Cerrojo implements Runnable{
    static volatile int res; 
    static Object cerrojo = new Object();
    public static void main(String[] args) throws Exception{
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            exe.execute(new EM_Cerrojo());
        }
        exe.shutdown();
        System.out.println(res);
    }

    public EM_Cerrojo(){
    }

    public void run(){
        synchronized(cerrojo){
            res++;
        }
    }
}