package Practicas19.practica3;

import java.util.Random;

class prodEscalarParalelo extends Thread {
    public static int cant = (int) 10;
    public static int hebras = 2;
    public static int inicio, fin, idHebra;
    public static int produtoParcial[] = new int[2];
    public static int vec[] = new int[cant];
    public static int vec2[] = new int[cant];
    public static Random r = new Random();

    public prodEscalarParalelo(int idHebra, int inicio, int fin) {
        this.inicio = inicio;
        this.fin = fin;
        this.idHebra = idHebra;
    }

    public static void main(String[] args) {
        rellena();
        prodEscalarParalelo hilo[] = new prodEscalarParalelo[2];
        for (int i = 0; i < hebras; i++) {
            hilo[i] = new prodEscalarParalelo(i, cant * i, cant * (i + 1));
        }

        for (int i = 0; i < hebras; i++)
            hilo[i].start();

        try {
            for (int i = 0; i < hebras; i++)
                hilo[i].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int total = 0;
        for (int i = 0; i < hebras; i++) {
            total += produtoParcial[i];
        }
        for (int i = 0; i < 10; i++)
            System.out.print(vec[i] + " ");

        System.out.println();

        for (int i = 0; i < 10; i++)
            System.out.print(vec2[i] + " ");

    }

    public static void rellena() {
        for (int i = 0; i < cant; i++) {
            vec[i] = r.nextInt(10);
            vec2[i] = r.nextInt(10);
        }
    }

    public void run() {
        for (int i = inicio; i < fin; i++) {
            produtoParcial[idHebra] += vec[i] * vec2[i];
        }
    }
}