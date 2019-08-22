package ejercicios_para_practicar;
import java.lang.Runnable;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class pruebaLife implements Runnable{
    public static int[][] mat = new int[10][10];
    public static Life f = new Life();
    public static int id;
    public static Random r = new Random();
    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(10);
        rellena();
        for(int i = 0; i < 10; i++){
            exe.execute(new pruebaLife(i));
        }
    }

    public static void rellena(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                mat[i][j] = 0;
            }
        }

        for(int i = 0; i < 50; i++){
            mat[r.nextInt(10)][r.nextInt(10)] = 1;
        }
    }

    public void run(){
        f.nextGen(id);
    }

    public pruebaLife(int i){
        Life.mlec = mat;
        id = i;
    }
}