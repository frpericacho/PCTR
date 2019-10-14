package Practicas19.practica2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Usa_tareaRunnable{

    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(2);
        for(int i = 0; i < 2; i++){
            if(i==0)
                exe.execute(new tareaRunnable(true));
            else
                exe.execute(new tareaRunnable(false));
        }
        exe.shutdown();
    }
}