package practica3;

public class hilo extends Thread{

    public hilo(boolean tipo){
        this.tipo = tipo;
    }

    public void run(){
        if(tipo){
            for(int i = 0;i < 100; ++i){
                ++n;
                //System.out.println(n);
            }
        }else{
            for(int i = 0;i < 100; ++i){
                --n;
                //System.out.println(n);
            }
        }
        System.out.println(n);
    }

    private static int n=0;
    private boolean tipo;
}