package ejerciciosexamen.monitor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class pruebaSem implements Runnable{
    public static monitorSem monitor = new monitorSem(1);
    public static int val=0;
    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(10);
        for(int i = 0; i < 10; i++){
            exe.execute(new pruebaSem());
        }
        exe.shutdown();
        while(!exe.isTerminated());
        System.out.println(val);
    }

    @Override
    public void run() {
        monitor.waitSem();
        for(int i = 0; i < 10; i++)
            val++;
        monitor.signalSem();
    }
}