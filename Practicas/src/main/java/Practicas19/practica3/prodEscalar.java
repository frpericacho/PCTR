package Practicas19.practica3;
import java.util.Random;

class prodEscalar{
    static int vec[] = new int[(int)10e6];
    Random r = new Random();
    static int val = 2;
    public static void main(String[] args) {
        fun();
    }

    public void rellena(){
        for(int i = 0; i < (int)10e6; i++){
            vec[i] = r.nextInt();
        }
    }

    public static void fun(){
        for(int i = 0; i < (int)10e6; i++){
            vec[i] *= val;
        }
    }
}