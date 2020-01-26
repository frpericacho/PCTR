package ejercicios_para_practicar;

public class aeropuerto {

    public static void main(String[] args) {
        avion[] hilos = new avion[100];
        //avion[] hilos = new avion[100];
        for (int i = 0; i < 100; i++) {
            hilos[i] = new avion(i);
            hilos[i].start();
        }

        for (int i = 0; i < 100; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}