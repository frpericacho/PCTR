package practica4;

import java.util.*;

class prodMat{
    static Random r = new Random();
    public static void main(String[] args) {
        int f1 = 0, c1 = 0, f2 = 0, c2 = 0;
        Scanner S = new Scanner(System.in);
        System.out.println("introduzca las filas de la primera matriz:");
        f1 = S.nextInt();
        System.out.println("introduzca las columnas de la primera matriz:");
        c1 = S.nextInt();
        System.out.println("introduzca las filas de la segunda matriz:");
        f2 = S.nextInt();
        System.out.println("introduzca las columnas de la segunda matriz:");
        c2 = S.nextInt();
        S.close();

        if(f1!=c2) System.exit(0);

        int [][]a = new int[f1][c1];
        int [][]b = new int[f2][c2];
        int [][]res = new int[f1][c2];

        rellena(a);
        rellena(b);
        
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    res[i][j] += a[i][k] * b[k][j];
                }
            }
        }
    }

    public static void rellena(int[][]v){
        for(int i = 0; i < v.length; ++i){
            for(int j = 0; j < v[0].length; ++j){
                v[i][j] = r.nextInt(10);
            }
        }
    }
}