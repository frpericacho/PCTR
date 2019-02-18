package ejerciciosexamen.febrero1718;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class prodEscalar2 implements Callable<Float>{
    public static float[] a = new float[10000];
    public static float[] b = new float[10000];
    private int ini = 0,fin = 10000/4;

    public prodEscalar2(int ini1,int fin1){
        ini = ini1;
        fin = fin1;
    }
    
    public Float call() throws Exception {
        float res = 0.0f;
        
        for(int i = ini; i < fin; i++){
            res += a[i] * b[i];
        }
        return res;
    }

    public static void main(String[] args) {
        float res = 0.0f;
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ArrayList<Future<Float>> resultado = new ArrayList<Future<Float>>();
        
        for(int i = 0;i < 10000; i++){
            a[i] = 1;
            b[i] = 1;
        }

        for(int i = 0;i < Runtime.getRuntime().availableProcessors(); i++){
            resultado.add(exe.submit(new prodEscalar2(i*(10000/4),(i+1)*(10000/4))));
        }

        for(Future<Float> iter : resultado) {
            try {
                res += iter.get();
            } catch (InterruptedException e) {e.printStackTrace();} catch (ExecutionException e) {e.printStackTrace();}
        }
        exe.shutdown();
        System.out.println(res);
    }
}