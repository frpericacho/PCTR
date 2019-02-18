package practica6;
import java.util.*;

class resImagen{
    static Random r = new Random();
    public static void main(String[] args) {
        rellena();
        resalta();
    }

    public static void rellena(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                mat[i][j] = r.nextInt(20);
            }
        }
    }

    public static void resalta(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                mat[i][j] = (4*mat[i][j] - mat[i+1][j] - mat[i][j+1] - mat[i-1][j] - mat[i][j-1])/8;
            }
        }
    }

    private static int n;
    private static int[][] mat;
}