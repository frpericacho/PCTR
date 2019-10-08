package practica3;

import java.util.Random;

class escalaVector{
    Random r = new Random(100);
    
    public escalaVector(){
        vector = new int[(int)Math.pow(10,8)];
    }

    public void valores(){
        for(int i = 0; i < vector.length; ++i){
            vector[i] = r.nextInt();
        }
    }
    public void escalado(int n){
        for(int i = 0; i < vector.length; ++i){
            vector[i] *= n;
        }
    }

    static int [] vector; 
}