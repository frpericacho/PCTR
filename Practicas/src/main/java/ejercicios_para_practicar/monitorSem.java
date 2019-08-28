package ejercicios_para_practicar;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class monitorSem {
    static int cant;
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition c = lock.newCondition();

    public monitorSem(int cant) {
        this.cant = cant;
    }

    public void waitSem() {
        lock.lock();
        try {
            while (cant == 0) {
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cant--;
        } finally {
            lock.unlock();
        }
    }

    public void signalSem() {
        lock.lock();
        try {
            cant++;
            c.signal();
        } finally {
            lock.unlock();
        }
    }
}