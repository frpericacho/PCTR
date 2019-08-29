package ejerciciosexamen.monitor;

public class Aeropuerto{
    public static void main(String[] args) {
        Thread[] hilos = new Thread[1000];
        
        for(int i = 0; i < 1000; i++){
            hilos[i] = new Avion(i);
            hilos[i].start();
        }

        for(int i = 0; i < 1000; i++){
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}