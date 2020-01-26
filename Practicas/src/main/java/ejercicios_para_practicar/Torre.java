package ejercicios_para_practicar;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Torre {
    public ReentrantLock lock = new ReentrantLock();
    private boolean[] pistasDespegue = new boolean[3];
    private boolean aterrizajeDisp = true;
    private int pistasDespDisp = 3;
    public Condition despegueLibre = lock.newCondition();
    public Condition aterrizajeLibre = lock.newCondition();

    public int despega(int id) {
        lock.lock();
        try {
            while (pistasDespDisp == 0) {
                try {
                    despegueLibre.await();
                } catch (InterruptedException ie) {
                    System.err.println("Error en await");
                }
            }
            int i;
            for (i = 0; i < 3; ++i) {
                if (!pistasDespegue[i]) {
                    --pistasDespDisp;
                    pistasDespegue[i] = true;
                    break;
                }
            }

            System.out.println("Avion " + id + " entra en pista de despegue " + i);
            return i;
        } finally {
            lock.unlock();
        }
    }

    public void despega_fin(int id, int pista) {
        lock.lock();
        try {
            System.out.println("Avion " + id + " despega de la pista " + pista);
            ++pistasDespDisp;
            pistasDespegue[pista] = false;
            despegueLibre.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void aterriza(int id) {
        lock.lock();
        try {
            while (!aterrizajeDisp) {
                try {
                    aterrizajeLibre.await();
                } catch (InterruptedException ie) {
                    System.err.println("Error en await");
                }
            }
            aterrizajeDisp = true;

            System.out.println("Avion " + id + " entra en pista de aterrizaje");
        } finally {
            lock.unlock();
        }
    }

    public void aterriza_fin(int id) {
        lock.lock();
        try {
            System.out.println("Avion " + id + " sale de la pista de aterrizaje");
            aterrizajeDisp = false;
            aterrizajeLibre.signalAll();
        } finally {
            lock.unlock();
        }
    }
}