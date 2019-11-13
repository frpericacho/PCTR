package Practicas19.practica3;

import java.util.Random;

class matVector {

    static Random r = new Random();

    public static void main(String[] args) {
        int i = (int) 10e2;
        int mat[][] = new int[i][i];
        int mat2[][] = new int[i][i];
        int res[][] = new int[i][i];

        double inicio = System.currentTimeMillis();
        for (int a = 0; a < i; ++a) {
            for (int b = 0; b < i; ++b) {
                mat[a][b] = r.nextInt(10);
            }
        }

        for (int b = 0; b < i; ++b) {
            for (int a = 0; a < i; ++a) {
                mat2[b][a] = r.nextInt(10);
            }
        }

        for (int x = 0; x < res.length; x++) {
            for (int y = 0; y < res[x].length; y++) {
                for (int z = 0; z < res.length; z++) {
                    res[x][y] += mat[x][z] * mat2[z][y];
                }
            }
        }

        double fini = System.currentTimeMillis();
        System.out.printf("%f", fini - inicio);
    }
}