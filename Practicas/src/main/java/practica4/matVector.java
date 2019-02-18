package practica4;
import java.util.*;

class matVector{ 
    static Scanner S = new Scanner(System.in);
    static Random r = new Random();
    public static void main(String[] args) {
        int a=0;
        do{
            System.out.println("introduzca la opcion deseada");
            System.out.println("1.- Manual");
            System.out.println("2.- Automatico");
            System.out.println("-----------------------------");
            a = S.nextInt();
        }while(a<1 && a>2);
            switch(a){
                case 1: manual(); break;
                case 2: automatico(); break;
                default: System.out.println("Opcion no posible"); break;
            }
    }

    public static void manual(){
        System.out.println("introduzca el numero de columnas de la matriz");
        i = S.nextInt();
        System.out.println("introduzca el numero de filas de la matriz");
        j = S.nextInt();
        System.out.println("introduzca el numero de elementos del vector");
        k = S.nextInt();

        mat = new int[i][j]; 
        vec = new int[k];
        
        for(int a = 0; a < i; ++a){
            for(int b = 0; b < j; ++b){
                System.out.println("introduzca el elemento de la poicion ("+(a+1)+","+(b+1)+")");
                mat[a][b] = S.nextInt();
            }
        }

        for(int a = 0; a < k; ++a){
            System.out.println("introduzca el valor "+(a+1)+" del vector");
            vec[a] = S.nextInt();
        }

        int []aux = multiplicar(mat, vec);

        System.out.println("el resultado es:");
        for(int a = 0; a < k; ++a){
            System.out.println(aux[a]);
        }
    }

    public static void automatico(){
        System.out.println("introduzca el numero de columnas de la matriz");
        i = S.nextInt();
        System.out.println("introduzca el numero de filas de la matriz");
        j = S.nextInt();
        System.out.println("introduzca el numero de elementos del vector");
        k = S.nextInt();

        mat = new int[i][j]; 
        vec = new int[k];

        for(int a = 0; a < i; ++a){
            for(int b = 0; b < j; ++b){
                mat[a][b] = r.nextInt(10);
            }
        }

        for(int a = 0; a < k; ++a){
            vec[a] = r.nextInt(10);
        }

        int []aux = multiplicar(mat, vec);

        System.out.println("el resultado es:");
        for(int a = 0; a < k; ++a){
            System.out.println(aux[a]);
        }
    }

    public static int[] multiplicar(int[][] mat,int[] vec){
        int aux[] = new int[k];

        for(int a = 0; a < j; ++a){
            for(int b = 0; b < i; ++b){
                aux[a] += mat[a][b] * vec[b];
            }
        }

        return aux;
    }

    private static int i,j,k;
    private static int[][] mat;
    private static int[] vec;
}