import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

class UsamonitorImpresion implements Runnable {
    private static monitorImpresion mon = new monitorImpresion(3);

    public void run() {
        int i = mon.inicio();

        try {
            //Thread.sleep(1000);
        } catch (Exception e) {}

        mon.fin(i);
    }

    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); ++i) {
            exe.execute(new UsamonitorImpresion());
        }

        exe.shutdown();
        while (!exe.isTerminated())
            ;
    }
}