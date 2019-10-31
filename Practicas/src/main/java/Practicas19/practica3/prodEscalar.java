package Practicas19.practica3;
import java.util.Random;

class prodEscalar{
    static int vec[] = new int[(int)10e6];
    static int vec2[] = new int[(int)10e6];
    static int res;
    static Random r = new Random();
    public static void main(String[] args) {
        rellena();
        fun();
    }

    public static void rellena(){
        for(int i = 0; i < (int)10e6; i++){
            vec[i] = r.nextInt();
            vec2[i] = r.nextInt();
        }
    }

    public static void fun(){
        for(int i = 0; i < (int)10e6; i++){
            res += vec2[i] * vec[i];
        }
    }
}