package ejerciciosexamen.monitor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Torre {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition c = lock.newCondition();
    public static Condition c_a = lock.newCondition();
    public static boolean[] pistas = new boolean[3];
    public static int pistas_D = 3;
    public static boolean pista_A = true;

    public int entraPdespege(int id) {
        lock.lock();
        try {
            while (pistas_D == 0) {
                try {
                    c.await();
                } catch (InterruptedException ie) {
                    System.err.println("Error en await");
                }
            }
            int i;
            for (i = 0; i < 3; ++i) {
                if (!pistas[i]) {
                    --pistas_D;
                   pistas[i] = true;
                    break;
                }
            }

            System.out.println("Avion " + id + " entra en pista de despegue " + i);
            return i;
        } finally {
            lock.unlock();
        }
    }

    public void salePdespege(int id, int pista) {
        lock.lock();
        try {
            System.out.println("Avion " + id + " sale de pista de despegue " + pista);
            pistas_D++;
            pistas[pista] = false;
            c.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void entraPaterrizaje(int id) {
        lock.lock();
        try {
            while(!pista_A){
                try {
                    c_a.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            pista_A = true;
            System.out.println("Avion " + id + " entra en la pista de aterrizaje");
        } finally {
            lock.unlock();
        }
    }

    public void salePaterrizaje(int id) {
        lock.lock();
        try {
            System.out.println("Avion " + id + " saliendo de la pista de aterrizaje");
            pista_A = false;
            c_a.signalAll();
        } finally {
            lock.unlock();
        }
    }
}