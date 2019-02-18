package practica6;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class matVectorConcurrente implements Runnable{
    static Scanner S = new Scanner(System.in);
    static Random r = new Random();

    public matVectorConcurrente(int fil){
        this.fil = fil;
    }

    public void run(){
        for(int a = 0; a < mat[0].length; ++a){
            res[fil] += mat[fil][a] * vec[a];
        }
    }
    public static void main(String[] args) throws Exception{
        System.out.println("introduzca el numero de columnas de la matriz");
        i = S.nextInt();
        System.out.println("introduzca el numero de filas de la matriz");
        j = S.nextInt();
        System.out.println("introduzca el numero de elementos del vector");
        k = S.nextInt();

        mat = new int[i][j]; 
        vec = new int[k];
        res = new int[i];

        rellena();
        rellenaV();
        
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int a = 0; a < j; a++){
            exe.execute(new matVectorConcurrente(a));
        }
        exe.shutdown();
        
        System.out.println("el resultado es:");
        System.out.println(Arrays.toString(res));
    }
    
    public static void rellena(){
        for(int a = 0; a < i; a++){
            for(int b = 0; b < j; b++){
                mat[a][b] = r.nextInt(10);
            }
        }
    }
    
    public static void rellenaV(){
        for(int a = 0; a < k; a++){
            vec[a] = r.nextInt(10);
        }
        Arrays.fill(res,0);
    }

    private static int i,j,k;
    private int fil;
    private static int[][] mat;
    private static int[] vec;
    private static int[] res;
}