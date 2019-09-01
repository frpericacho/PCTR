package ejercicios_para_practicar;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EscalarFuture implements Callable<Float> {
    public static float[] vec1 = new float[10];
    public static float[] vec2 = new float[10];
    public int ini,fin;

    public EscalarFuture(int ini, int fin){
        this.ini = ini;
        this.fin = fin;
    }

    public static void main(String[] args) {
        int parte = (10+4-1)/4;
        ExecutorService exe = Executors.newFixedThreadPool(4);
        ArrayList<Future<Float>> res = new ArrayList<Future<Float>>();
        for(int i = 0; i < 10; i++){
            vec1[i] = 2;
            vec2[i] = 4;
        }
        for(int i = 0; i < 4; i++){
            int inicio = parte * i;
            int fini = Math.min(parte * (i+1), 10);
            res.add(exe.submit(new EscalarFuture(inicio,fini)));
        }
        exe.shutdown();
        while(!exe.isTerminated());

        Float resultado = 0.0f;

        for(int i = 0; i < 4; i++) {
            try {
                resultado += res.get(i).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
			}
        }
        System.out.println(resultado);
    }

    @Override
    public Float call() throws Exception {
        Float res = 0.0f;
        for(int i = ini; i < fin; i++){
            res += vec1[i] * vec2[i];
        }
        return res;
    }

}