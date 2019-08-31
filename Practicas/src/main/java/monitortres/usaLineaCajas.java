package monitortres;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class usaLineaCajas{
    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(100);
        for(int i = 0; i < 100; i++){
            exe.execute(new cliente(i));
        }
        exe.shutdown();
        while(!exe.isTerminated());
    }
}