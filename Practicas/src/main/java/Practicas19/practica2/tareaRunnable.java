package Practicas19.practica2;

public class tareaRunnable implements Runnable{
    public static int n = 0;
    public boolean tipo;

    public tareaRunnable(boolean tipo){
        this.tipo = tipo;
    }

    public void run(){
        if(tipo){
            for(int i = 0;i < 10000; ++i){
                ++n;
            }
        }else{
            for(int i = 0;i < 10000; ++i){
                --n;
            }
        }
        System.out.println(n);
    }
}