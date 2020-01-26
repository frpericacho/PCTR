import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class primosRunnable implements Runnable {
    public int ini, fini;
    public static AtomicInteger atom = new AtomicInteger();
    public static int hebras = 8;
    public static int num = 10000000;

    public primosRunnable(int ini, int fini) {
        this.ini = ini;
        this.fini = fini;
    }

    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(hebras);

        long inicio = System.currentTimeMillis();
        for (int i = 0; i < hebras; i++) {
            int ini = (i * num) / hebras;
            int fini = ((i + 1) * num) / hebras;
            System.out.println(ini + " " + fini);
            exe.execute(new primosRunnable(ini, fini));
        }
        exe.shutdown();
        while (!exe.isTerminated())
            ;
        long fint = System.currentTimeMillis();
        
        System.out.println(atom);
        System.out.println("el tiempo es de: " + (fint-inicio));
    }
    public boolean esPrimo(int n) {
        if (n <= 1) {
            return (false);
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return (false);
            }
        }
        return (true);
    }

    @Override
    public void run() {
        for (int i = ini; i < fini; i++) {
            if(esPrimo(i)){
                atom.incrementAndGet();
            }
        }
    }

}