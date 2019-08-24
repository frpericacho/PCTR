package ejercicios_para_practicar;

import java.util.Random;

public class pruebaThrSum extends Thread{
    public static int[] datArr = new int[10];
    public static Random r = new Random();
    public static Object cerrojo = new Object();
    public static int cont;
    public int ini,fin;

    public pruebaThrSum(int ini,int fin){
        this.ini = ini;
        this.fin = fin;
    }
    public static void main(String[] args) {
        Thread[] hilos = new Thread[10];
        int parte = (10 + 10 -1)/10;

        rellena();
        print();
        for(int i = 0; i < 10; i++){
            int inicio = parte * i;
            int fini = Math.min(inicio + parte, 10);
            hilos[i] = new pruebaThrSum(inicio, fini);
            hilos[i].start();
        }

        for(int i = 0; i < 10; i++){
            try {
                hilos[i].join();
            } catch (Exception e) {}
        }

        System.out.println(cont);
    }

    public void run(){
        int aux = 0;
        for(int i = ini; i < fin; i++){
            aux += datArr[i];
        }

        synchronized(cerrojo){
            cont += aux;
        }
    }

    public static void rellena(){
        for(int i = 0; i < 10; i++){
            datArr[i] = r.nextInt(10)+1;
        }
    }

    public static void print(){
        System.out.println();
        System.out.println("------------------------");
        for(int i = 0; i < 10; i++){
            System.out.print(datArr[i] + ", ");
        }
        System.out.println();
        System.out.println("------------------------");
    }
}