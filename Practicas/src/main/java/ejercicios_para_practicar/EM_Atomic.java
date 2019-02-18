package ejercicios_para_practicar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class EM_Atomic implements Runnable{
    static AtomicInteger c = new AtomicInteger(0); 
    static volatile int res;

    public void run(){
        res = c.incrementAndGet();
    }

    public EM_Atomic(){}

    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int i = 0;i < 4; i++){
            exe.execute(new EM_Atomic());
        }
        exe.shutdown();

        System.out.println(res);
    }
}