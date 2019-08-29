package ejercicios_para_practicar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class F100{
    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(16);
        for(int i = 0; i < 16; i++){
            exe.execute(new combatStations(i));
        }
        exe.shutdown();
        while(!exe.isTerminated());
    }
}