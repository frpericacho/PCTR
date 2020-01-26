
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class monitorSemaforo {
    public static Object cerrojo = new Object();
    public static int cant;

    public monitorSemaforo(int c) {
        cant = c;
    }

    public void waitSem() {
        synchronized (cerrojo) {
            while (cant == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                --cant;
            }

        }
    }

    public void signalSem() {
        synchronized(cerrojo){
            cant++;
            notifyAll();
        }
    }
}