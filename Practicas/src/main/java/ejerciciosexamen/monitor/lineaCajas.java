package ejerciciosexamen.monitor;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class lineaCajas{
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition c = lock.newCondition();
    public static int clientes = 0;
    public static boolean[] cajas = new boolean[20];

    public static void espera(int id){
        lock.lock();
        try{
            while(clientes == 10){
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cajas[id] = false;
            clientes++;
            c.signalAll();
        }finally{
            lock.unlock();
        }
    }

    public static void atiende(int id){
        lock.lock();
        try{
            clientes--;
            cajas[id] = true;
            c.signalAll();
        }finally{
            lock.unlock();
        }
    }
}