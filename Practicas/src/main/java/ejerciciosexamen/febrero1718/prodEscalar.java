package ejerciciosexamen.febrero1718;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class prodEscalar implements Callable<Float>{
    private static float[] v2 = new float[10000];
    private static float[] v1 = new float[10000];
    private int ini,fin;

    public prodEscalar(int ini,int fin){
        this.ini = ini;
        this.fin = fin;
    }

    public Float call(){
        float res = 0.0f;

        for(int i = ini; i < fin; i++){
            res += v1[i] * v2[i];
        }

        return res;
    }

    public static void main(String[] args) throws Exception {
        float res = 0.0f;
        int ini1,fin1;
        Scanner s = new Scanner(System.in);

        ExecutorService exe=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ArrayList<Future<Float>> resultado = new ArrayList<Future<Float>>();

        for(int i = 0; i < 10000; i++){
            v1[i] = 1; 
            v2[i] = 1; 
        }

        for(int i = 0; i < Runtime.getRuntime().availableProcessors(); i++){
            System.out.println("ini hilo "+(i+1)+": ");
            ini1 = s.nextInt();
            System.out.println("fin hilo "+(i+1)+":");
            fin1 = s.nextInt();
            resultado.add(exe.submit(new prodEscalar(ini1,fin1)));
        }
        exe.shutdown();
        while(!exe.isTerminated());

        for(Future<Float> iter: resultado){
            res += iter.get();
        }

        s.close();
        System.out.println(res);
    }     
}