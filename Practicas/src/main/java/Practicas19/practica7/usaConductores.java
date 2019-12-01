
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class usaConductores implements Runnable {
    static int hebras = Runtime.getRuntime().availableProcessors();
    static Conductores cond = new Conductores();

    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(hebras);
        for (int i = 0; i < hebras; i++) {
            exe.execute(new usaConductores());
        }
        exe.shutdown();
        while (!exe.isTerminated())
            ;
    }

    public usaConductores() {
        cond.iniciarOcupados();
    }

    public void run() {
        Conductor c = new Conductor(1, 10, "felix", "Espana", "M", "10/10/10", "10/10/09", "tipo", "nacimiento",
                "autoridad");
        cond.introducirConductor(c);
        System.out.println(cond.buscarConductor(1));
    }
}