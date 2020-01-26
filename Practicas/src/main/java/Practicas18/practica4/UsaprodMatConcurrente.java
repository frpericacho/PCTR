package practica4;
import java.util.*;

class UsaprodMatConcurrente{
    static Scanner S = new Scanner(System.in);
    static Random r = new Random();
    public static void main(String[] args) throws Exception{
        int f1 = 0,c1 = 0,f2 = 0,c2 = 0;
        System.out.println("introduzca las filas de la primera matriz:");
        f1 = S.nextInt();
        System.out.println("introduzca las columnas de la primera matriz:");
        c1 = S.nextInt();
        System.out.println("introduzca las filas de la segunda matriz:");
        f2 = S.nextInt();
        System.out.println("introduzca las columnas de la segunda matriz:");
        c2 = S.nextInt();
        S.close();
        
        int [][]a = new int[f1][c1];
        int [][]b = new int[f2][c2];
        int [][]res = new int[f1][c2];
        prodMatConcurrente[] hilos = new prodMatConcurrente[f1];

        rellena(a);
        escribe(a);

        rellena(b);
        escribe(b);

        for(int i = 0;i < f1; ++i){
            for(int j = 0;j < c2; ++j){
                res[i][j] = 0;
            }
        }
        
        prodMatConcurrente.setA(a, f1, c1);
        prodMatConcurrente.setB(b, f2, c2);
        prodMatConcurrente.setRes(res, f1, c2);
        
        for(int i = 0;i < f1; ++i){
            hilos[i] = new prodMatConcurrente(i);
            hilos[i].start();
        }


        for(int i = 0;i < f1; ++i){
            hilos[i].join();
        }

        escribe(res);
    }

    public static void escribe(int [][]v){
        System.out.println("///////////");
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void rellena(int [][]v){
        for(int i = 0;i < v.length; ++i){
            for(int j = 0;j < v[0].length; ++j){
                v[i][j] = r.nextInt(10);
            }
        }
    }
} 