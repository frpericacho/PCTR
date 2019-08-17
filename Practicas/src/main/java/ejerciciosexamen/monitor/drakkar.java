package ejerciciosexamen.monitor;

/*
La tripulación de un drakkar vikingo comparte una marmita con un al-
muerzo a base de m anguilas cocinadas al eneldo. Cuando un vikingo quiere
comer, se sirve una anguila. Si ya no quedan, avisa al vikingo cocinero pa-
ra que proceda a llenar la marmita de nuevo, utilizando las inagotables pro-
visiones de anguilas disponibles. Desarrolle un programa en java que modele
esta curiosa situación, y provea la sincronización necesaria utilizando méto-
dos synchronized y notificacion wait()-notifyAll(). Llame a su programa
drakkarVikingo.java.
*/
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class drakkar implements Runnable {
    private int tipo = 0;
    public static monitor m = new monitor();

    public drakkar(int tipo) {
        this.tipo = tipo;
    }

    public static void main(String args[]) {
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService exe = Executors.newFixedThreadPool(cores);
        for (int i = 0; i < cores; i++) {
            exe.execute(new drakkar(i));
        }
        exe.shutdown();
        try {
            exe.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            if (tipo == 0)
                m.rellenar();
            else
                m.comer();
        }
    }
}

class monitor {
    private static int cant = 0;

    public synchronized void comer() {
        try {
            if (cant > 0) {
                System.out.println("como");
                cant -= 1;
                System.out.println(cant);
            } else {
                notify();
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void rellenar() {
        cant += 10;
        System.out.println("rellena");
        System.out.println(cant);
        notifyAll();
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}