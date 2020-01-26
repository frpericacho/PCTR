package ejercicios_para_practicar;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class suavImagen implements Runnable {
    public static double[][] mlec = new double[10][10];
    public static double[][] mesc = new double[10][10];
    public static double[] valores = new double[20];
    public static int cores = Runtime.getRuntime().availableProcessors();
    public static Random r = new Random();
    public int ini,fin;

    public suavImagen(int ini, int fin){
        this.ini = ini;
        this.fin = fin;
    }

    public static void imprime(double[][] mlec2) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(mlec2[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void r_val() {
        for (int i = 0; i < 20; i++) {
            valores[i] = 0.05 * (i + 1);
        }
    }

    public static void r_mat() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                mlec[i][j] = valores[r.nextInt(20)];
            }
        }
    }

    @Override
    public void run() {
        for(int i = ini; i < fin; i++){
            for(int j = 0; j < 10; j++){
                double arriba,abajo,derch,izq;
                try {
                    arriba = mlec[i+1][j];
                } catch (Exception e) {
                    arriba = 0;
                }
                try {
                    abajo = mlec[i-1][j];
                } catch (Exception e) {
                    abajo = 0;
                }
                try {
                    izq = mlec[i][j-1];
                } catch (Exception e) {
                    izq = 0;
                }
                try {
                    derch = mlec[i][j+1];
                } catch (Exception e) {
                    derch = 0;
                }
                mesc[i][j] = (4 * mlec[i][j] + arriba + derch + abajo + izq)/8;
            }
        }
    }

    public static void main(String[] args) {
        r_val();
        r_mat();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(cores, cores, 10000L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        int parte = (10 + 4 - 1)/4;
        imprime(mlec);
        for(int i = 0; i < cores; i++){
            int inicio = parte * i;
            int fini = Math.min(parte * (i+1), 10);
            System.out.println(inicio + " " + fini);
            executor.execute(new suavImagen(inicio,fini));
        }

        executor.shutdown();
        while(!executor.isTerminated());
        System.out.println();
        System.out.println("-----------------------------");
        imprime(mesc);
    }
}