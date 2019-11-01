package Practicas19.practica3;
import java.util.Random;

class prodEscalar{
    static int vec[] = new int[(int)10e6];
    static int vec2[] = new int[(int)10e6];
    static int res;
    static Random r = new Random();
    public static void main(String[] args) {
        double inicio = System.currentTimeMillis();
        rellena();
        fun();
        double fini = System.currentTimeMillis();
        System.out.println(inicio);
        System.out.println(fini);
        System.out.printf("%f", fini-inicio);
    }

    public static void rellena(){
        for(int i = 0; i < (int)10e6; i++){
            vec[i] = r.nextInt(10);
            vec2[i] = r.nextInt(10);
        }
    }

    public static void fun(){
        for(int i = 0; i < (int)10e6; i++){
            res += vec2[i] * vec[i];
        }
    }
}