package ejercicios_para_practicar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Contador implements Runnable{
    static public int cont=0;

    public void run(){    
        cont++;
    }

    public Contador(){
    }

    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int i = 0;i < Runtime.getRuntime().availableProcessors(); i++){
            exe.execute(new Contador());
        }
        exe.shutdown();
        while(!exe.isTerminated());
        System.out.println(cont);
    }
}