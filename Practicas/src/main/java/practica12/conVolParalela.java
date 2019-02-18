package practica12;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class conVolParalela implements Runnable {
    public static int[] escala = new int[41];
    public int ini, fin;
    public static int size = 10000;
    public static int op;
    public static int res;
    public static int[][] enf = { { 0, -1, 0 }, { -1, 5, 1 }, { 0, -1, 0 } };
    public static int[][] rbor = { { 0, 0, 0 }, { -1, 1, 0 }, { 0, 0, 0 } };
    public static int[][] dbor = { { 0, 1, 0 }, { 1, -4, 1 }, { 0, 1, 0 } };
    public static int[][] sobel = { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };
    public static int[][] sharpen = { { 1, -2, 1 }, { -2, 5, -2 }, { 1, -2, 1 } };
    public static int[][] mat = new int[size][size];
    public static int[][] mat2 = new int[size][size];

    public conVolParalela(int inicio, int fini, int opc) {
        ini = inicio;
        fin = fini;
        op = opc;
    }

    public void run() {
        switch (op) {
        case 1:
            for (int i = ini; i < fin; i++) {
                for (int j = 0; j < size; j++) {
                    mat2[i][j] = convolucion(i, j, enf);
                }
            }
            break;
        case 2:
            for (int i = ini; i < fin; i++) {
                for (int j = 0; j < size; j++) {
                    mat2[i][j] = convolucion(i, j, rbor);
                }
            }
            break;
        case 3:
            for (int i = ini; i < fin; i++) {
                for (int j = 0; j < size; j++) {
                    mat2[i][j] = convolucion(i, j, dbor);
                }
            }
            break;
        case 4:
            for (int i = ini; i < fin; i++) {
                for (int j = 0; j < size; j++) {
                    mat2[i][j] = convolucion(i, j, sobel);
                }
            }
            break;
        case 5:
            
            for (int i = ini; i < fin; i++) {
                for (int j = 0; j < size; j++) {
                    mat2[i][j] = convolucion(i, j, sharpen);
                }
            }
            break;
        }
    }

    public static int convolucion(int m, int n, int[][] kernel) {
        int res = 0;
        int i = (m - 1 + size) % size;
        int j = (n - 1 + size) % size;

        for (int a = 0; a != 3; a++) {
            for (int b = 0; b != 3; b++) {
                res += mat[i][j] * kernel[a][b];
                i = (i + 1) % size;
                j = (j + 1) % size;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        inicializa();
        int nHilos, inicio, fini, opc;
        System.out.println("introduzca el numero de hilos");
        nHilos = s.nextInt();
        System.out.println("eliga una opcion");
        System.out.println("1.- enfocar");
        System.out.println("2.- realzar bordes");
        System.out.println("3.- detectar bordes");
        System.out.println("4.- filtro sobel");
        System.out.println("5.- filtro sharpen");
        opc = s.nextInt();

        ExecutorService exe = Executors.newFixedThreadPool(nHilos);
        for (int i = 0; i < nHilos; i++) {
            inicio = i * (size / nHilos);
            fini = (i + 1) * (size / nHilos);
            exe.execute(new conVolParalela(inicio, fini, opc));
        }
        exe.shutdown();
        while (!exe.isTerminated());
        s.close();

        for (int x=0; x < mat2.length; x++) {
            System.out.print("|");
            for (int y=0; y < mat2[x].length; y++) {
              System.out.print (mat2[x][y]);
              if (y!=mat2[x].length-1) System.out.print("\t");
            }
            System.out.println("|");
        }
    }

    public static void inicializa() {
        Random r = new Random();
        int cont = -20;
        for (int i = 0; i < 41; i++) {
            escala[i] = cont;
            cont++;
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mat[i][j] = escala[r.nextInt(41)];
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mat2[i][j] = 0;
            }
        }
    }

}