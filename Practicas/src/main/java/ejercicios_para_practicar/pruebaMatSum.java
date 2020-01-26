package ejercicios_para_practicar;
import java.util.Random;

public class pruebaMatSum {
    public static int[][] datMat = new int[10][10];
    public static int total;
    public static Random r = new Random();
    public static void main(String[] args) {
        rellena();
        Thread [] hilos = new Thread[10];

        for(int i = 0; i < 10; i++){
            hilos[i] = new Thread(new MatSum(i));
        }

        for(int i = 0; i < 10; i++){
            hilos[i].start();
        }

        for(int i = 0; i < 10; i++){
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                System.out.print(datMat[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("------------------");
        System.out.println(total);
    }

    public static void rellena(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                datMat[i][j] = 2;
            }
        }
    }
}