package practica12;

import java.util.Random;
import java.util.Scanner;

public class conVolSecuencial{
    public static int[] escala = new int[41];
    public static int size = 10000;
    public static int[][] enf ={{0,-1,0},{-1,5,1},{0,-1,0}};
    public static int[][] rbor ={{0,0,0},{-1,1,0},{0,0,0}};
    public static int[][] dbor ={{0,1,0},{1,-4,1},{0,1,0}};
    public static int[][] sobel ={{-1,0,1},{-2,0,2},{-1,0,1}};
    public static int[][] sharpen ={{1,-2,1},{-2,5,-2},{1,-2,1}};
    public static int[][] mat = new int[size][size];
    public static int[][] mat2 = new int[size][size];
    public static void main(String[] args) {
        int opc;
        Scanner s = new Scanner(System.in);
        inicializa();

        System.out.println("introduzca una opcion");
        System.out.println("1.- enfocar");
        System.out.println("2.- realzar bordes");
        System.out.println("3.- detectar bordes");
        System.out.println("4.- filtro sobel");
        System.out.println("5.- filtro sharpen");
        opc = s.nextInt();
        switch(opc){
            case 1: fun_enf(); break;
            case 2: fun_rbor(); break;
            case 3: fun_dbor(); break;
            case 4: fun_sobel(); break;
            case 5: fun_sharpen(); break;
        }
        s.close();
    }

    public static void fun_sharpen() {
        for(int i = 0;i < size; i++){
            for(int j = 0;j < size; j++){
                mat2[i][j] = convolucion(i,j,sharpen);
            }
        }
    }

    public static void fun_sobel() {
        for(int i = 0;i < size; i++){
            for(int j = 0;j < size; j++){
                mat2[i][j] = convolucion(i,j,sobel);
            }
        }
    }

    public static void fun_dbor() {
        for(int i = 0;i < size; i++){
            for(int j = 0;j < size; j++){
                mat2[i][j] = convolucion(i,j,dbor);
            }
        }
    }

    public static void fun_rbor() {
        for(int i = 0;i < size; i++){
            for(int j = 0;j < size; j++){
                mat2[i][j] = convolucion(i,j,rbor);
            }
        }
    }

    public static void fun_enf() {
        for(int i = 0;i < size; i++){
            for(int j = 0;j < size; j++){
                mat2[i][j] = convolucion(i,j,enf);
            }
        }
    }

    public static int convolucion(int m,int n,int[][]kernel){
        int res = 0;
        int i = (m-1+size)%size;
        int j = (n-1+size)%size;
                
        for(int a = 0;a != 3;a++){
            for(int b = 0;b != 3;b++){
                res += mat[i][j] * kernel[a][b];
                i = (i+1)%size;
                j = (j+1)%size;
            }
        }
        return res;
    }

    public static void inicializa() {
        Random r = new Random();
        int cont = -20;
        for(int i = 0;i < 41; i++){
            escala[i] = cont;
            cont++;
        }

        for(int i = 0; i < size; i++){
            for(int j = 0;j < size; j++){
                mat[i][j] = escala[r.nextInt(41)];
            }
        }

        for(int i = 0; i < size; i++){
            for(int j = 0;j < size; j++){
                mat2[i][j] = 0;
            }
        }
    }
}