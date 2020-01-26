import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class mutex implements Runnable {
    private static monitorSemaforo mon = new monitorSemaforo(1);
    static newDrakkar drakk = new newDrakkar();
    private int accion;

    public mutex(int i) {
        accion = i;
    }

    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        exe.execute(new mutex(1));
        for (int i = 0; i < 8; i++) {
            exe.execute(new mutex(0));
        }
        exe.shutdown();
        while (!exe.isTerminated())
            ;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (accion == 0) {
                    drakk.come();
                } else {
                    drakk.llena();
                }
            }
        } catch (Exception e) {
        }
    }
}

class newDrakkar {
    static int marmita = 0;

    public newDrakkar() {
        marmita = 5;
    }

    public synchronized void llena() throws Exception {
        while (marmita > 0) {
            wait();
        }
        marmita = 5;
        System.out.println("lleno");
        notifyAll();
    }

    public synchronized void come() throws Exception {
        while (marmita == 0) {
            notifyAll();
            wait();
        }
        System.out.println("como");
        marmita--;
    }
}