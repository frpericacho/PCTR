package ejerciciosexamen.junio1112;
import java.util.Random;

class ThrSum{
    static Random R = new Random();
    public static int cont = 0;
    public static int ini;
    public static int fin;
    public static int[] vector = new int[10];

    public static void main(String[] args){
        Thread hilos[] = new Thread[5];

        for(int i = 0; i < 10; i++){
            vector[i] = R.nextInt(9)+1;
        }

        for(int i = 0; i < 5; i++){
            hilos[i] = new pruebaThrSum((10/5)*i,(10/5)*(i+1));
            hilos[i].start();
        }

        for(int i = 0; i < 5; i++){
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
            }
        }

        for(int i = 0; i < 10; i++){
            System.out.println(vector[i]);
        }
        System.out.println("la suma es:");
        System.out.println(cont);
    }
}