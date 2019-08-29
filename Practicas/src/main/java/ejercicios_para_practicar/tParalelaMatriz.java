package ejercicios_para_practicar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Random;

public class tParalelaMatriz implements Runnable{
    public static int[][] mlec = new int[10][10];
    public static int[][] mesc = new int[10][10];
    public static Random r = new Random();
    public int ini,fin;

    public tParalelaMatriz(int inicio, int fini) {
        this.ini = inicio;
        this.fin = fini;
    }

    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(4);
        int parte = (10 + 4 - 1)/4;
        rellena();
        imprime(mlec);
        for(int i = 0; i < 4; i++){
            int inicio = parte * i;
            int fini = Math.min(parte*(i+1), 10);
            System.out.println(inicio + " " + fini);
            exe.execute(new tParalelaMatriz(inicio,fini));
        }
        exe.shutdown();
        while(!exe.isTerminated());
        imprime(mesc);
    }

    private static void rellena() {
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                mlec[i][j] = r.nextInt(10)+1;
            }
        }
    }

    private static void imprime(int mat[][]) {
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void run() {
        for(int i = ini; i < fin; i++){
            for(int j = 0; j < 10; j++){
                int arriba,abajo,der,izq;
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
                    der = mlec[i][j+1];
                } catch (Exception e) {
                    der = 0;
                }
                try {
                    izq = mlec[i][j-1];
                } catch (Exception e) {
                    izq = 0;
                }
                mesc[i][j] = 3*mlec[i][j] + (arriba+abajo+der+izq);
            }
        }
    }
}