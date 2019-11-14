package Practicas19.practica5;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class prodMatricesParalelo implements Runnable {
    static Random r = new Random();
    private int fil,ini,fini;
    private static int hebras = 16;
    private static int[][] mat;
    private static int[][] mat2;
    private static int[][] res;
    private static int Cb = 0;
    private static int cant = (int) 10e4;

    public prodMatricesParalelo(int fil,int ini,int fini) {
        this.fil = fil;
        this.ini = ini;
        this.fini = fini;
    }

    public static void main(String[] args) throws InterruptedException {

        mat = new int[cant][cant];
        mat2 = new int[cant][cant];
        res = new int[cant][cant];
        int subra = hebras / (1 - Cb);

        rellena(mat);
        rellena(mat2);

        ExecutorService exe = Executors.newFixedThreadPool(subra);
        double inicio = System.currentTimeMillis();
        for (int i = 0; i < subra; i++) {
            int ini = (i * cant)/hebras;
            int fini = ((i+1) * cant)/hebras;
            System.out.println(ini + " " + fini);
            exe.execute(new prodMatricesParalelo(i,ini,fini));
        }
        exe.shutdown();
        while (!exe.isTerminated())
            ;
        double fin = System.currentTimeMillis();

        System.out.printf("el tiempo ha sido de: " + (fin - inicio));
    }

    @Override
    public void run() {
        for(int k = ini; k < fini; k++){
            for (int i = 0; i < mat[0].length; i++) {
                for (int j = 0; j < mat[0].length; ++j) {
                    res[k][i] += mat[k][j] * mat2[j][i];
                }
            }
        }
    }

    public static void rellena(int mat[][]) {
        for (int a = 0; a < cant; ++a) {
            for (int b = 0; b < cant; ++b) {
                mat[a][b] = r.nextInt(10);
            }
        }
    }
}