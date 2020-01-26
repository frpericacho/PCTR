package ejerciciosexamen.septiembre1112;
import java.util.Random;

class pruebamatSum{
    static Random R = new Random();
    public static int [][] mat = new int[10][10];
    public static int cont = 0;

    public static void main(String[] args)throws Exception{
        
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                mat[i][j] = R.nextInt(9)+1;
            }
        }//Relleno de la matriz
        
        Thread hilos[] = new Thread[10];
        for(int i = 0;i < 10; i++){
            hilos[i] = new Thread(new matSum(i));
            hilos[i].start();
        }

        for(int i = 0; i < 10; i++){
            hilos[i].join();
        }

        System.out.println(cont);
    }
}