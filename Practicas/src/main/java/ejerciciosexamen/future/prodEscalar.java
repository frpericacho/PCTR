package ejerciciosexamen.future;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class prodEscalar implements Callable<Float> {
    public static int ini, fin;
    public static int[]v1 = new int[10000];
    public static int[]v2 = new int[10000];

    public prodEscalar(int inicio, int fini) {
        ini = inicio;
        fin = fini;
    }

    public Float call() {
        float res = 0.0f;

        for(int i = 0;i < 10000; i++){
            res += v1[i] * v2[i];
        }
        return res;
    }

    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ArrayList<Future<Float>> resultado = new ArrayList<Future<Float>>();
        int inicio = 0, fini = 10000 / 4;
        float res = 0.0f;
        Scanner s = new Scanner(System.in);

        for(int i = 0;i < 10000; i++){
            v1[i] = 1;
            v2[i] = 1;
        }

        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            System.out.println("introduzca el inicio de "+(i+1)+": ");
            inicio = s.nextInt();
            System.out.println("introduzca el fin de "+(i+1)+": ");
            fini = s.nextInt();
            resultado.add(exe.submit(new prodEscalar(inicio, fini)));
        }
        exe.shutdown();
        while (!exe.isTerminated())
            ;

        for (Future<Float> iter : resultado) {
            try {
                res = iter.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(res);
        s.close();
    }
}