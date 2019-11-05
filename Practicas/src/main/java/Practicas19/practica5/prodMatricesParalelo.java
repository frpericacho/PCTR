package Practicas19.practica5;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class prodMatricesParalelo implements Runnable {
    static Random r = new Random();
    private int fil;
    private static int hebras = Runtime.getRuntime().availableProcessors();
    private static int[][] mat;
    private static int[][] mat2;
    private static int[][] res;
    private static int Cb = 0;
    private static int cant = (int)4;

    public prodMatricesParalelo(int fil) {
        this.fil = fil;
    }

    public static void main(String[] args) throws InterruptedException {

        mat = new int[cant][cant];
        mat2 = new int[cant][cant];
        res = new int[cant][cant]; 
        int subra = hebras/(1-Cb);

        rellena(mat);
        rellena(mat2);

        ExecutorService exe = Executors.newFixedThreadPool(subra);
        for(int i = 0; i < subra; i++){
            exe.execute(new prodMatricesParalelo(i));
        }
        exe.shutdown();
        while(!exe.isTerminated());


        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("---------------------");
        System.out.println();


        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                System.out.print(mat2[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("---------------------");
        System.out.println();

        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void run() {
        for(int i = 0; i < mat[0].length; ++i){
            res[fil][i] += mat[fil][i] * mat2[i][fil];
        }
    }

    public static void rellena(int mat[][]){
        for (int a = 0; a < cant; ++a) {
            for (int b = 0; b < cant; ++b) {
                mat[a][b] = r.nextInt(10);
            }
        }
    }
}