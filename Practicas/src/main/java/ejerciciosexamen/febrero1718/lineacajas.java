package ejerciciosexamen.febrero1718;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class lineacajas {
    public static ReentrantLock cerrojo = new ReentrantLock();
    public static Condition[] cajas = new Condition[10];
    public static int clientes = 0;
    public static int ultimacaja = 0;

    public static void inicializar() {
        for (int i = 0; i < 10; i++) {
            cajas[i] = cerrojo.newCondition();
            clientes = 0;
        }
    }

    public static void espera() {
        for (int i = ultimacaja; i != (ultimacaja - 1 + 10) % 10; i = (i + 1 + 10) % 10) {
            cerrojo.lock();
            while (clientes == 10) {
                try {
                    cajas[i].await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            clientes++;
            cerrojo.unlock();
            trata(i);
            break;
        }
    }

    public static void trata(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cerrojo.lock();
        clientes--;
        cajas[i].signalAll();
        cerrojo.unlock();
    }
}