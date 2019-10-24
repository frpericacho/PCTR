package Practicas19.practica3;

import java.util.Random;

class matVectorConcurrente implements Runnable {
    static Random r = new Random();
    private int fil;
    private static int[][] mat;
    private static int[] vec;
    private static int[] res;

    public matVectorConcurrente(int fil){
        this.fil = fil;
    }

    public static void main(String[] args) throws InterruptedException {

        mat = new int[(int)10e2][(int)10e2]; 
        vec = new int[(int)10e2];
        res = new int[(int)10e2];
        int cant = (int)10e2;

        Thread[] hilos = new Thread[cant];

        for (int a = 0; a < cant; ++a) {
            for (int b = 0; b < cant; ++b) {
                mat[a][b] = r.nextInt(10);
            }
        }

        for (int a = 0; a < cant; ++a) {
            vec[a] = r.nextInt(10);
        }

        for(int i = 0;i < cant; ++i){
            hilos[i] = new Thread(new matVectorConcurrente(i));
            hilos[i].start();
        }
        for(int i = 0; i < cant; ++i){
            hilos[i].join();
        }
        
    }

    @Override
    public void run() {
        for(int a = 0; a < mat[0].length; ++a){
            res[fil] += mat[fil][a] * vec[a];
        }
    }

}