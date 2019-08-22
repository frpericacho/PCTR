package ejercicios_para_practicar;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Life {
    public static int[][] mlec = new int[10][10];
    public static int[][] mesc = new int[10][10];
    public static int cant = 50;

    public static CyclicBarrier barrera = new CyclicBarrier(10, () -> {
        mlec = mesc.clone();
        mostrar();
    });

    public synchronized void nextGen(int fila) {
        int cont = 0;
        for (int i = 0; i < 10; i++) {
            if (mlec[Math.floorMod(fila - 1, mlec.length)][Math.floorMod(i - 1, mlec.length)] == 1)
                cont++;
            if (mlec[Math.floorMod(fila - 1, mlec.length)][Math.floorMod(i, mlec.length)] == 1)
                cont++;
            if (mlec[Math.floorMod(fila - 1, mlec.length)][Math.floorMod(i + 1, mlec.length)] == 1)
                cont++;
            if (mlec[Math.floorMod(fila, mlec.length)][Math.floorMod(i - 1, mlec.length)] == 1)
                cont++;
            if (mlec[Math.floorMod(fila, mlec.length)][Math.floorMod(i + 1, mlec.length)] == 1)
                cont++;
            if (mlec[Math.floorMod(fila + 1, mlec.length)][Math.floorMod(i - 1, mlec.length)] == 1)
                cont++;
            if (mlec[Math.floorMod(fila + 1, mlec.length)][Math.floorMod(i, mlec.length)] == 1)
                cont++;
            if (mlec[Math.floorMod(fila + 1, mlec.length)][Math.floorMod(i + 1, mlec.length)] == 1)
                cont++;

            if (cont < 2) {
                mesc[fila][i] = 0; 
                cant--;
            } else if (cont == 2 || cont == 3) {
                mesc[fila][i] = mlec[fila][i];
            } else if (cont > 3) {
                mesc[fila][i] = 0;
                cant--;
            } else if (cont == 3) {
                mesc[fila][i] = 1;
            }
        }
        try {
            status();
            barrera.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public synchronized void status() {
        System.out.format("Soy el hilo %s, mi prioridad es %d y el estatus actual es: %d células vivas\n",
                Thread.currentThread().getId(), Thread.currentThread().getPriority(), cant);
    }

    public static synchronized void mostrar() {
        System.out.println("new generation");
        for (int i = 0; i < mlec.length; i++) {
            for (int j = 0; j < mlec[i].length; j++) {
                System.out.print(mlec[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}