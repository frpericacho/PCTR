package monitortres;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class lineaCajas {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition c = lock.newCondition();
    public static int[] cajas = new int[10];
    public static boolean haycaja;

    public synchronized int entraCola(int id) {
        lock.lock();
        int caja = 0;
        try {
            while (!haycaja) {
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 10; i++) {
                if (cajas[i] < 10) {
                    cajas[i]++;
                    caja = i;
                    break;
                }
            }
            int total_client = 0;
            for (int i = 0; i < 20; i++) {
                total_client += cajas[i];
            }
            if (total_client == 10 * 20)
                haycaja = false;
            System.out.println("la caja " + caja + " esta atendido al cliente " + id);
        } finally {
            lock.unlock();
        }
        return caja;
    }

    public synchronized void saleCola(int id, int caja) {
        lock.lock();
        try {
            cajas[caja]--;
            System.out.println("la caja " + " ha atendido al cliente " + id);
            c.signalAll();
        } finally {
            lock.unlock();
        }
    }
}