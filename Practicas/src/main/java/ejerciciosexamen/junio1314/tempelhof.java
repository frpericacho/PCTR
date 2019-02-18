package ejerciciosexamen.junio1314;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class tempelhof{
    
    public static void main(String[] args) {
        torre.inicializar();
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int i = 0;i < 3; i++){
            exe.execute(new avion(i));
        }
        exe.shutdown();
        while(!exe.isTerminated());
    }
}