
import java.util.concurrent.locks.*;

class RWMonitorAN {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition leer = lock.newCondition();
    private static Condition escribir = lock.newCondition();
    private int cant;
    volatile boolean escribiendo;

    public RWMonitorAN() {
    }

    public void ini_leer() {
        lock.lock();
        try {
            while (escribiendo)
                try {
                    leer.await();
                } catch (InterruptedException ie) {
                }
            cant++;
            System.out.println("leo");
            leer.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void fin_leer() {
        lock.lock();
        try {
            cant--;
            if (cant == 0)
                escribir.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void ini_escribir() {
        lock.lock();
        try {
            while (escribiendo || cant != 0) {
                try {
                    escribir.await();
                } catch (InterruptedException ie) {
                }
            }
            System.out.println("escribo");
        } finally {
            lock.unlock();
        }
    }

    public void fin_escribir() {
        lock.lock();
        try {
            escribiendo = false;
            if (cant != 0)
                escribir.signalAll();
            else
                leer.signalAll();
        } finally {
            lock.unlock();
        }
    }
}