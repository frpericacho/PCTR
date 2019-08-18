package ejercicios_para_practicar;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class aunicel implements Runnable {
    public static int[] vlec = new int[10];
    public static int[] vesc = new int[10];
    public static Random r = new Random();

    public int ini, fin;

    public static void main(String[] args) throws InterruptedException {
        int tam = 10;
        int cores = Runtime.getRuntime().availableProcessors();
        int parte = (tam + cores - 1) / cores;
        rellena();
        ExecutorService exe = Executors.newFixedThreadPool(cores);
        for (int i = 0, begin, end; i < cores; ++i) {
            begin = i * parte;
            end = Math.min(begin + parte, tam);
            System.out.format("%d %d\n", begin, end);
            exe.execute(new aunicel(begin, end));
        }

        exe.shutdown();

        for (int i = 0; i < 10; i++) {
            System.out.print(vlec[i] + ", ");
        }

        System.out.println();
        System.out.println("-----------------------------");

        for (int i = 0; i < 10; i++) {
            System.out.print(vesc[i] + ", ");
        }
    }

    public void run() {
        for (int i = ini; i < fin; i++) {
            vesc[i] = (vlec[Math.floorMod(i - 1, vlec.length)] + vlec[i] + vlec[Math.floorMod(i + 1, vlec.length)]) % 3;
        }
    }

    public static void rellena() {
        for (int i = 0; i < 10; i++) {
            vlec[i] = r.nextInt(100);
        }
    }

    public aunicel(int ini, int fin) {
        this.ini = ini;
        this.fin = fin;
    }
}