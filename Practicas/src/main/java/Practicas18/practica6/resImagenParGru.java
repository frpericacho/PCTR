package practica6;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class resImagenParGru implements Runnable{
    static Random r = new Random();

    public resImagenParGru(int fil){
        this.fil = fil;
    }
    public static void main(String[] args) throws Exception{
        rellena();
        
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int i = 0; i < n; i++){
            exe.execute(new matVectorConcurrente(i));
        }
        exe.shutdown();
    }

    public static void rellena(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                mat[i][j] = r.nextInt(20);
            }
        }
    }

    public void run(){
        for(int j = 0; j < n; j++){
            mat[fil][j] = (4*mat[fil][j] - mat[fil+1][j] - mat[fil][j+1] - mat[fil-1][j] - mat[fil][j-1])/8;
        }
    }

    private int fil;
    private static int n;
    private static int[][] mat;
}