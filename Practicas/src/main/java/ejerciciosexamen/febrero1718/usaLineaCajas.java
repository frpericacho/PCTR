package ejerciciosexamen.febrero1718;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class usaLineaCajas{
    
    public static void main(String[] args) {
        lineacajas.inicializar();
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int i = 0;i < 100; i++){
            exe.execute(new cliente(i));
        }
        exe.shutdown();
        while(!exe.isTerminated());
    }
}