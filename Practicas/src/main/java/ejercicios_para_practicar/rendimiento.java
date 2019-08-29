package ejercicios_para_practicar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class rendimiento implements Runnable {

    public int id;
    public static int val = 0, cant = 100;
    public static Object cerrojo = new Object();
    public static CyclicBarrier barrera = new CyclicBarrier(3);
    public static ReentrantLock lock = new ReentrantLock();
    public static AtomicInteger atom = new AtomicInteger(0);

    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(90);
        for (int i = 0; i < 90; i++) {
            exe.execute(new rendimiento(i % 3));
        }
        exe.shutdown();
        while (!exe.isTerminated())
            ;
    }

    public rendimiento(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        double t_s = 0, t_a = 0, t_r = 0;
        if (id == 0) {
            double inicio_s = System.currentTimeMillis();
            f_syn();
            double fin_s = System.currentTimeMillis();
            t_s = fin_s - inicio_s;
        } else if (id == 1) {
            double inicio_a = System.currentTimeMillis();
            f_atom();
            double fin_a = System.currentTimeMillis();
            t_a = fin_a - inicio_a;
        } else {
            double inicio_r = System.currentTimeMillis();
            f_reen();
            double fin_r = System.currentTimeMillis();
            t_r = fin_r - inicio_r;
        }
        try {
            barrera.await();
            imprime(t_s, t_a, t_r);
            cant += 100;
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void imprime(double t_s, double t_a, double t_r) {
        System.out.println(cant + " || " + t_s + " || " + t_a + " || " + t_r);
    }

    public static void f_syn() {
        synchronized (cerrojo) {
            for (int i = 0; i < cant; i++) {
                val++;
            }
        }
    }

    public static void f_atom() {
        for (int i = 0; i < cant; i++) {
            atom.getAndIncrement();
        }
    }

    public static void f_reen() {
        lock.lock();
        for (int i = 0; i < cant; i++) {
            val++;
        }
        lock.unlock();
    }
}