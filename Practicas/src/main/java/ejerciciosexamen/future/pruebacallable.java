package ejerciciosexamen.future;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class pruebacallable implements Callable<Float> {

    public pruebacallable() {

    }

    public Float call() throws Exception {
        float res = 0.0f;

        res++;

        return res;
    }

    public static void main(String[] args) {
        float res = 0.0f;
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ArrayList<Future<Float>> resultado = new ArrayList<Future<Float>>();
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            resultado.add(exe.submit(new pruebacallable()));
        }
        exe.shutdown();
        while (!exe.isTerminated())
            ;

        for (Future<Float> iter : resultado) {
            try {
                res += iter.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(res);
    }
}