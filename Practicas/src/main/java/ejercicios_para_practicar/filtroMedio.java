package ejercicios_para_practicar;

import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class filtroMedio implements Runnable {
    public static int n, m;
    public int ini,fin;
    public static int[] valores = new int[256];
    public static int[][] mlec = new int[10][10];
    public static int[][] mesc = new int[10][10];
    public static Scanner s = new Scanner(System.in);
    public static Random r = new Random();

    public filtroMedio(int ini,int fin){
        this.ini = ini;
        this.fin = fin;
    }

    public static void main(String[] args) {
        System.out.println("introduzca el tamaño de la matriz");
        n = s.nextInt();
        System.out.println("introduzca el numero de tareas");
        m = s.nextInt();
        r_val();
        r_mat();
        ThreadPoolExecutor exe = new ThreadPoolExecutor(m, m, 10000L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        int parte = (10+m-1)/m;
        imprime(mlec);
        for(int i = 0; i < m; i++){
            int inicio = parte * i;
            int fini = Math.min(parte*(i+1), 10);
            System.out.println(inicio + " " + fini);
            exe.execute(new filtroMedio(inicio,fini));
        }
        exe.shutdown();
        while(!exe.isTerminated());
        System.out.println();
        System.out.println("-------------------------");
        imprime(mesc);
    }

    @Override
    public void run() {
        for(int i = ini; i < fin; i++){
            for(int j = 0; j < 10; j++){
                int aux = aplica_fil(i,j);
                mesc[i][j] = aux/9;
            }
        }
    }

    public static void imprime(int mat[][]){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int aplica_fil(int x, int y){
        int res = 0;
        for(int i = x-1; i < x+2; i++){
            for(int j = y-1; j < y+2; j++){
                try {
                    res += mlec[i][j];
                } catch (Exception e) {
                    res += 0;
                }
            }
        }
        return res;
    }

    private static void r_mat() {
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                mlec[i][j] = valores[r.nextInt(256)];
            }
        }
    }

    private static void r_val() {
        for(int i = 0; i < 256; i++){
            valores[i] = i;
        }
    }
}