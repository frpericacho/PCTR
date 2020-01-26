
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class usaConductores implements Runnable {
    static int hebras = Runtime.getRuntime().availableProcessors();
    static Conductor c = new Conductor();
    static Conductores cond = new Conductores();
    int tipohilo;

    public static void main(String[] args) {
        cond.iniciarOcupados();
        ExecutorService exe = Executors.newFixedThreadPool(hebras);
        for (int i = 0; i < hebras; i++) {
            exe.execute(new usaConductores(i));
        }
        exe.shutdown();
        while (!exe.isTerminated())
            ;
    }

    public usaConductores(int i) {
        this.tipohilo = i;
    }

    public void run() {
        switch(tipohilo){
            case 0: c = new Conductor(1, 10, "felix", "Espana", "M", "10/10/10", "10/10/09", "tipo", "nacimiento",
            "autoridad");
            cond.introducirConductor(c);
            break;
            case 1: 
            System.out.println(cond.buscarConductor(1));
            break;
            case 2: cond.borrarConductor(1);
            break;
        }
    }
}