import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class semafMonitor{
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition wait = lock.newCondition();
    private static Condition sign = lock.newCondition();
    public static int cant;
    

    public semafMonitor(int c) {
        cant = c;
    }

    public void waitSem() {
        lock.lock();
        try {
            while (cant == 0)
                try {
                    wait.await();
                } catch (InterruptedException ie) {
                }
            cant--;
            wait.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void signalSem() {
        lock.lock();
        try {
            cant++;
            if (cant == 0)
                sign.signalAll();
        } finally {
            lock.unlock();
        }
    }
}