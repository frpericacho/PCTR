package practica4;

import java.util.*;

class matVectorConcurrente implements Runnable{
    static Scanner S = new Scanner(System.in);
    static Random r = new Random();

    public matVectorConcurrente(int fil){
        this.fil = fil;
    }
    public static void main(String[] args) throws Exception {
        int a=0,f=0,c=0;

        System.out.println("introduzca el numero de columnas de la matriz");
        c = S.nextInt();
        System.out.println("introduzca el numero de filas de la matriz");
        f = S.nextInt();
        
        mat = new int[f][c]; 
        vec = new int[c];
        res = new int[f];
 
        do{
            System.out.println("introduzca la opcion deseada");
            System.out.println("1.- Manual");
            System.out.println("2.- Automatico");
            System.out.println("-----------------------------");
            a = S.nextInt();
        }while(a<1 && a>2);
            switch(a){
                case 1: manual(f,c); break;
                case 2: automatico(f,c); break;
                default: System.out.println("Opcion no posible"); break;
            }
    }

    public static void manual(int f,int c) throws Exception{

        Thread[] hilos = new Thread[f];

        rellena_mat();
        rellena_vec();

        for(int i = 0;i < f; ++i){
            hilos[i] = new Thread(new matVectorConcurrente(i));
            hilos[i].start();
        }
        for(int i = 0; i < f; ++i){
            hilos[i].join();
        }

        solucion();
    }

    public static void automatico(int f, int c) throws Exception{

        Thread[] hilos = new Thread[f];

        for(int a = 0; a < f; ++a){
            for(int b = 0; b < c; ++b){
                mat[a][b] = r.nextInt(10);
            }
        }

        for(int a = 0; a < f; ++a){
            vec[a] = r.nextInt(10);
        }

        for(int i = 0;i < f; ++i){
            hilos[i] = new Thread(new matVectorConcurrente(i));
            hilos[i].start();
        }
        for(int i = 0; i < f; ++i){
            hilos[i].join();
        }

        solucion();
    }

    public void run(){
        for(int a = 0; a < mat[0].length; ++a){
            res[fil] += mat[fil][a] * vec[a];
        }
    }

    public static void rellena_mat(){
        for(int a = 0; a < mat.length; ++a){
            for(int b = 0; b < mat[0].length; ++b){
                System.out.println("introduzca el elemento de la poicion ("+(a+1)+","+(b+1)+")");
                mat[a][b] = S.nextInt();
            }
        }
    }

    public static void rellena_vec(){
        for(int a = 0; a < vec.length; ++a){
            System.out.println("introduzca el valor "+(a+1)+" del vector");
            vec[a] = S.nextInt();
        }
        Arrays.fill(res,0);
    }

    public static void solucion(){
        System.out.println("el resultado es:");
        System.out.println(Arrays.toString(res));
    }

    private int fil;
    private static int[][] mat;
    private static int[] vec;
    private static int[] res;
}