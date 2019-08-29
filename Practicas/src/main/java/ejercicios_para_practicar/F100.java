package ejercicios_para_practicar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class F100 {

    public static void main(String[] args) {
        ExecutorService ex = Executors.newFixedThreadPool(16);
        for (int i = 0; i < 16; ++i) {
            ex.execute(new combatStations(i));
        }
        ex.shutdown();
        while (!ex.isTerminated()) {
        }
    }
}