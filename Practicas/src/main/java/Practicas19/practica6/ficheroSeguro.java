
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.Random;

public class ficheroSeguro extends Thread {

    static Scanner sc = new Scanner(System.in);
    static RandomAccessFile fichero = null;
    static Random gcl = new Random();
    public static Object cerrojo = new Object();

    public ficheroSeguro(){}

    public void run() {
        try {
            fichero = new RandomAccessFile("enteros.dat", "rw");
            for (int i = 1; i <= 10; i++) {
                synchronized (cerrojo) {
                    fichero.writeInt(gcl.nextInt());
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (fichero != null) {
                    fichero.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Thread[] hilos = new Thread[4];

        for (int i = 0; i < 4; i++) {
            hilos[i] = new ficheroSeguro();
            hilos[i].start();
        }

        for (int i = 0; i < 4; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}