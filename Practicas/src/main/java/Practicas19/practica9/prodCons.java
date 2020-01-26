
import java.util.*;
import java.util.concurrent.*;

public class prodCons extends Thread {
    private static int tamBuffer = 100;
    private static double[] buffer;
    private static int InPtr = 0;
    private static int OutPtr = 0;
    private static Semaphore em = new Semaphore(1);
    private static Semaphore espacios = new Semaphore(tamBuffer);
    private static Semaphore elementos = new Semaphore(0);

    private int tipoHilo;

    public prodCons(int tipo) {
        tipoHilo = tipo;
    }

    public void run() {
        switch (tipoHilo) {
        case 0: {
            while (true) {
                try {
                    espacios.acquire();
                } catch (InterruptedException e) {
                }
                try {
                    em.acquire();
                } catch (InterruptedException e) {
                }
                buffer[InPtr] = Math.random();
                System.out
                        .println("Hilo productor " + Thread.currentThread().getName() + " insertando " + buffer[InPtr]);
                InPtr = (InPtr + 1) % tamBuffer;
                em.release();
                elementos.release();
            }
        }
        case 1: {
            while (true) {
                try {
                    elementos.acquire();
                } catch (InterruptedException e) {
                }
                try {
                    em.acquire();
                } catch (InterruptedException e) {
                }
                System.out.println(
                        "Hilo consumidor " + Thread.currentThread().getName() + " extrayendo " + buffer[OutPtr]);
                OutPtr = (OutPtr + 1) % tamBuffer;
                em.release();
                espacios.release();
            }
        }
        }
    }

    public static void main(String[] args) {
        buffer = new double[tamBuffer];
        ExecutorService exe = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 8; i++) {
            exe.execute(new prodCons(i % 2));
        }
        exe.shutdown();
    }
}