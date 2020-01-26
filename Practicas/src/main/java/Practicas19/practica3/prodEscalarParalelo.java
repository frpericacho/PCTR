package Practicas19.practica3;

import java.util.Random;

class prodEscalarParalelo extends Thread {
    public static int cant = (int) 10e6;
    public static int hebras = 10;
    public int inicio, fin, idHebra;
    public static int produtoParcial[] = new int[hebras];
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
        prodEscalarParalelo hilo[] = new prodEscalarParalelo[hebras];
        for (int i = 0; i < hebras; i++) {
            int ini = cant/hebras * i;
            int fini = cant/hebras * (i + 1);
            hilo[i] = new prodEscalarParalelo(i, ini, fini);
        }

        double inicio = System.currentTimeMillis();

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
        double fini = System.currentTimeMillis();
        System.out.println(fini-inicio);
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