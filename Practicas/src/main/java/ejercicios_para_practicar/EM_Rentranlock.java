package ejercicios_para_practicar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class EM_Rentranlock implements Runnable{
    static ReentrantLock cerrojo = new ReentrantLock();
    static volatile int res;

    public EM_Rentranlock(){}

    public void run(){
        cerrojo.lock();
        try{
            res++;
        }finally{
            cerrojo.unlock();
        }
    }

    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int i = 0;i < 4; i++){
            exe.execute(new EM_Atomic());
        }
        exe.shutdown();

        System.out.println(res); 
    }
}