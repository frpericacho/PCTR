import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ImpMonitor {
    boolean[] impresoras = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition[] imp;

    public ImpMonitor(int num) {
        impresoras = new boolean[num];
        imp = new Condition[num];
        for (int i = 0; i < impresoras.length; ++i) {
            impresoras[i] = true;
            imp[i] = lock.newCondition();
        }
    }

    private int libre() {
        for (int i = 0; i < impresoras.length; ++i) {
            if (impresoras[i])
                return i;
        }
        return -1;
    }

    public int inicio() {
        int id;
        lock.lock();
        try {
            while ((id = libre()) == -1)
                try {
                    imp[id].await();
                } catch (InterruptedException ie) {
                }
            System.out.println("Estoy imprimiendo en la impresora " + id + " " + Thread.currentThread());
            impresoras[id] = false;
            imp[id].signalAll();
            return id;
        } finally {
            lock.unlock();
        }
    }

    public void fin(int i) {
        lock.lock();
        try {
            impresoras[i] = true;
            System.out.println("Fin de impresion en la impresora " + i);
            imp[i].signalAll();
        } finally {
            lock.unlock();
        }
    }

}