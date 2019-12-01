
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class drakkarVikingo implements Runnable {
    static private int marmita;
    int idHilo;

    public drakkarVikingo(int i) {
        this.idHilo = i;
    }

    public static void main(String[] args) {
        marmita = 4;
        ExecutorService exe = Executors.newFixedThreadPool(4);
        exe.execute(new drakkarVikingo(1));
        for (int i = 0; i < 4; i++) {
            exe.execute(new drakkarVikingo(0));
        }
        exe.shutdown();
    }

    public synchronized void comer() throws InterruptedException {
        while (marmita == 0) {
            notifyAll();
            wait();
        }
        marmita--;
        System.out.println("he comido");

    }

    public synchronized void cocinar() throws InterruptedException {
        while (marmita > 0)
            wait();
        marmita = 4;
        System.out.println("he cocinado");
        notifyAll();
    }

    @Override
    public void run() {
        switch (idHilo) {
        case 0:
            while (true)
                try {
                    cocinar();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        case 1:
            while (true)
                try {
                    comer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }
}