package Practicas19.practica3;

import java.util.Random;

class matVector{

    static Random r = new Random();
    public static void main(String[] args) {
        int aux[] = new int[(int)10e2];
        int mat[][] = new int[(int)10e2][(int)10e2]; 
        int vec[] = new int[(int)10e2];
        int i = (int)10e2;

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
                aux[a] += mat[a][b] * vec[b];
            }
        }
    }
}