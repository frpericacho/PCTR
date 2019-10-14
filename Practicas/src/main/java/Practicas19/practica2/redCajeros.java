package Practicas19.practica2;

public class redCajeros {
    public static void main(String[] args) {
        Thread[] hilos = new Thread[4];
        cajero.inicializa(122, 10e4);
        for (int i = 0; i < hilos.length; ++i) {
            hilos[i] = new Thread(new cajero((i % 2) == 0));
            hilos[i].start();
        }
        try {
            for (Thread t : hilos) {
                t.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.format("%f ",cajero.cantidad());
    }
}