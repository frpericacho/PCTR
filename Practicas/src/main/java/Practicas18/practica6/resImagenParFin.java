package practica6;
import java.util.*;

class resImagenParFi extends Thread{
    static Random r = new Random();

    public static void main(String[] args) throws Exception{
        rellena();
        Thread[] hilos = new Thread[Runtime.getRuntime().availableProcessors()];
        for(int i = 0;i < n; ++i){
            hilos[i] = new Thread(new resImagenParFi(i));
            hilos[i].start();
        }
        for(int i = 0; i < n; ++i){
            hilos[i].join();
        }
    }

    public static void rellena(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                mat[i][j] = r.nextInt(20);
            }
        }
    }

    public resImagenParFi(int fil){
        this.fil = fil;
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