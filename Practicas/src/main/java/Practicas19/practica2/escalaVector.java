package Practicas19.practica2;

import java.util.Random;

public class escalaVector{
    static Random r = new Random(100);
    static int [] vector; 

    public static void main(String[] args) {
        escalaVector vec = new escalaVector();
        vec.valores();
        vec.escalado(10);
    }

    public escalaVector(){
        vector = new int[(int)Math.pow(10,8)];
    }

    public static void valores(){
        for(int i = 0; i < vector.length; ++i){
            vector[i] = r.nextInt();
        }
    }
    public static void escalado(int n){
        for(int i = 0; i < vector.length; ++i){
            vector[i] *= n;
        }
    }

}