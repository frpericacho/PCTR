package ejercicios_para_practicar;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class lineaCajas {
    private static ReentrantLock cerrojo = new ReentrantLock();
    private static int nClientes[] = new int[20];
    private static Condition cajas[] = new Condition[20];
    private static int ultimaCaja = 0;

    public static void recibe() {
        for (int i = ultimaCaja; i != (ultimaCaja - 1 + 20) % 20; i = (i + 1 + 20) % 20) {
            cerrojo.lock();// Adquirir cerrojo
            while (nClientes[i] == 10)// Si esta llena,a esperar
            {
                try {
                    cajas[i].await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            nClientes[i]++;// Se incrementa en 1 el numero de clientes
            cerrojo.unlock();// Liberar cerrojo
            atiende(i);// Se atiende el cliente en la caja i-esima
            break;// Salir del bucle tras ser atendido

        }
    }

    public static void atiende(int id) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } // Dormir
          // Variable nClientes en e.m.
        cerrojo.lock();
        try {
            nClientes[id]--;// Un cliente menos
        } finally {
            cajas[id].signalAll();// Despertar a los otros
            cerrojo.unlock();// Librar el cerrojo
        }
    }

    public static void inicializar() {
        for (int i = 0; i < 20; ++i) {
            cajas[i] = cerrojo.newCondition();
            nClientes[i] = 0;
        }
    }
}