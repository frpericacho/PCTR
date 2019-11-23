package Practicas19.practica7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class usaConductores{
    static int hebras = Runtime.getRuntime().availableProcessors();
    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(hebras);
        for(int i = 0; i < hebras; i++){

        }
        exe.shutdown();
        while(!exe.isTerminated());
    }
}