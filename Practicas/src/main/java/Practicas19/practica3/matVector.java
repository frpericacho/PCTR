package Practicas19.practica3;

import java.util.Random;

class matVector{

    static Random r = new Random();
    public static void main(String[] args) {
        int i = (int)10e2;
        int mat[][] = new int[i][i]; 
        int vec[] = new int[i];
        int res[] = new int[i];

        double inicio = System.currentTimeMillis();
        for(int a = 0; a < i; ++a){
            for(int b = 0; b < i; ++b){
                mat[a][b] = r.nextInt(10);
            }
        }

        for(int a = 0; a < i; ++a){
            vec[a] = r.nextInt(10);
        }

        for(int a = 0; a < i; ++a){
            for(int b = 0; b < i; ++b){
                res[a] += mat[a][b] * vec[b];
            }
        }
        double fini = System.currentTimeMillis();
        System.out.printf("%f",fini-inicio);
    }
}