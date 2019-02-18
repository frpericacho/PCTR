package seminarios.seminario2;
import java.util.*; 

class ejercicio7{
    public static void main(String[] args) {
        int []vec = new int[10];
        Random r = new Random();
        for(int i=0;i<vec.length;++i){
            vec[i]=r.nextInt(3);
        }

        System.out.println("el primero");
        for(int i=0;i<vec.length;++i){
            System.out.print(vec[i]+", ");
        }
        System.out.println("");

        for(int gen=0;gen < 10; ++gen){
            int []vec_aux = new int[vec.length];
            for(int i=0;i<10;++i){
                if(i==0) vec_aux[i]=(vec[i]+vec[i+1]+vec[vec.length-1])%3;
                else if(i==vec.length-1) vec_aux[i]=(vec[i-1]+vec[i]+vec[0])%3;
                else vec_aux[i]=(vec[i-1]+vec[i]+vec[i+1])%3;
            }
            System.out.println("Generacion "+(gen+1)+":");
            for(int i=0;i<vec_aux.length;++i){
                System.out.print(vec_aux[i]+", ");
            }
            System.out.println("");
            vec = vec_aux;
        }
    }
}