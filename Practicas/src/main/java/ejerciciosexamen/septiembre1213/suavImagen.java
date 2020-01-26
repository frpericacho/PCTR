package ejerciciosexamen.septiembre1213;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class suavImagen implements Runnable {
    public static float[][] mlec = new float[4][4];
    public static float[][] mesc = new float[4][4];
    public static float[] escala = new float[20];
    public static int ini, fin;

    public suavImagen(int inicio, int fini) {
        ini = inicio;
        fin = fini;
    }

    public void run() {
    
        for (int i = ini; i < fin; i++){
            for (int j = 0; j < 4; j++){
                mesc[i][j] = (4.0f*mlec[i][j]+mlec[(i-1+4)%4][j]+mlec[(i+1+4)%4][j]+mlec[i][(j-1+4)%4]+mlec[i][(j+1+4)%4])/8.0f;
            }
        }   
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int opc = 0, salida = 0;
        escala[0] = 0.0f;
        for(int i = 1;i < 20; i++){
            escala[i] = i*(1.0f/19);
        }

        do {
            do {
                System.out.println("escoja una opcion");
                System.out.println("1.- generar imagen aleatoria");
                System.out.println("2.- generar imagen manual");
                opc = s.nextInt();
            } while (opc < 1 || opc > 2);
            switch (opc) {
            case 1:
                aleatorio();
                break;
            case 2:
                manual();
                break;
            }
            System.out.println("desea continuar?(3 para salir)");
            salida = s.nextInt();
        } while (salida != 3);
        s.close();
    }

    public static void aleatorio() {
        int inicio = 0, fini = 0;
        Random r = new Random();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                mlec[i][j] = escala[r.nextInt(20)];
            }
        }

        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            inicio = (i * (4 / 4));
            fini = ((i + 1) * (4 / 4));
            exe.execute(new suavImagen(inicio, fini));
        }
        exe.shutdown();
        while(!exe.isTerminated());

        for (int x = 0; x < mesc.length; x++) {
            System.out.print("|");
            for (int y = 0; y < mesc[x].length; y++) {
                System.out.print(mesc[x][y]);
                if (y != mesc[x].length - 1)
                    System.out.print("\t");
            }
            System.out.println("|");
        }
    }

    public static void manual() {

    }
}