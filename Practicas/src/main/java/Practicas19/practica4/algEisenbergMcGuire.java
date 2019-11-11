package Practicas19.practica4;

import java.util.concurrent.*;

public class algEisenbergMcGuire implements Runnable {
    private static enum estados {Esperando, Ocioso, Activo};

    static estados est[] = new estados[2];
    private static volatile int n = 0;
    private int tipohilo;
    private static int turno = (int) Math.floor(Math.random() * (0 - (+1)) + (1));

    public algEisenbergMcGuire(int tipohilo) {
        this.tipohilo = tipohilo;
    }

    public void run() {
        int index;
        do {
            est[tipohilo] = estados.Esperando;
            index = turno;
            while (index != tipohilo) {
                if (est[index] != estados.Ocioso)
                    index = turno;
                else
                    index = (index + 1) % 2;
            }

            est[tipohilo] = estados.Activo;

            index = 0;
            while ((index < 2) && ((index == tipohilo) || (est[index] != estados.Activo)))
                index = index + 1;
        } while ((index < 2) && ((turno == tipohilo) || est[turno] == estados.Ocioso));
        turno = tipohilo;
        switch (tipohilo) {
        case 0:
            n++;
            break;
        case 1:
            n--;
            break;
        }
        index = (turno + 1) % 2;
        while (est[index] == estados.Ocioso) {
            index = (index + 1) % 2;
        }
        turno = index;
        est[tipohilo] = estados.Ocioso;
    }

    public static void main(String[] arg) throws Exception {
        ExecutorService exe = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; i++) {
            exe.execute(new algEisenbergMcGuire(i));
        }

        exe.shutdown();
        while (!exe.isTerminated()) {
        }

        System.out.println(n);
    }
}