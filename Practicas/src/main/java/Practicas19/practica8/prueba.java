package Practicas19.practica8;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class prueba implements Runnable {
    static ejercicio eje = new ejercicio();
    static Random r = new Random();
    int impresora_asignada;

    public prueba() {
    }

    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            exe.execute(new prueba());
        }
        exe.shutdown();
    }

    @Override
    public void run() {
        while (true) {
            impresora_asignada = eje.take_print();
            eje.drop_print(impresora_asignada);
        }
    }
}