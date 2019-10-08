package practica4;

class prodMatConcurrente extends Thread{

    public prodMatConcurrente(int fil){
        this.fil = fil;
    }

    public void run(){
        for (int j = 0; j < a[0].length; j++) {
            for (int k = 0; k < b[0].length; k++) {
                res[fil][k] += a[fil][j] * b[j][k];    
            }
        }
    }

    public static void setA(int [][]a,int m,int n){
        prodMatConcurrente.a = new int[m][n];
        prodMatConcurrente.a = a;
    }

    public static void setB(int [][]b,int n,int p){
        prodMatConcurrente.b = new int[n][p];
        prodMatConcurrente.b = b;
    }

    public static void setRes(int [][]res,int m,int p){
        prodMatConcurrente.res = new int[m][p];
        prodMatConcurrente.res = res;
    }
    
    private int fil;
    private static int[][] res;
    private static int[][] a;
    private static int[][] b;
} 