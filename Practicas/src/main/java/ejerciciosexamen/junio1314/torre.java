package ejerciciosexamen.junio1314;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class torre {
    public static ReentrantLock cerrojo = new ReentrantLock();
    public static Condition aterrizaje;
    public static int aviones;
    public static Condition[] despege = new Condition[3];
    public static int ultimo_avion = 0;

    public static void inicializar() {
        aterrizaje = cerrojo.newCondition();
        for (int i = 0; i < 3; i++) {
            despege[i] = cerrojo.newCondition();
        }
        aviones = 0;
    }

    public static void aterriza() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cerrojo.lock();
        aviones++;
        cerrojo.unlock();
        despega();
    }

    public static void despega() {
        for (int i = ultimo_avion; i != (ultimo_avion - 1 + 3) % 3; i = (i + 1 + 3) % 3) {
            cerrojo.lock();
            while (aviones == 3) {
                try {
                    despege[i].await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            aviones--;
            despege[i].signalAll();
            cerrojo.unlock();
            break;
        }
    }
}