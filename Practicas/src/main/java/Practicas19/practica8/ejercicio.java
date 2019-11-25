package Practicas19.practica8;

public class ejercicio {
    static int impresoras;
    static boolean libre[] = new boolean[3];
    static Object cerrojo = new Object();

    public ejercicio() {
        impresoras = 3;
        for (int i = 0; i < 3; i++) {
            libre[i] = true;
        }
    }
    //monitor
    // public synchronized int take_print() {
    // while (impresoras == 0) {
    // try {
    // wait();
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // }
    // int n = 0;
    // while (!libre[n])
    // n++;
    // libre[n] = false;
    // impresoras--;
    // System.out.println("cojo la impresora");
    // return n;
    // }

    // public synchronized void drop_print(int n) {
    // libre[n] = true;
    // impresoras++;
    // System.out.println("suelto la impresora");
    // notifyAll();
    // }
    //API estandar
    public int take_print() {
        synchronized(cerrojo){
            while (impresoras == 0) {
                try {
                    cerrojo.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        int n = 0;
        while (!libre[n])
            n++;
        libre[n] = false;
        impresoras--;
        System.out.println("cojo la impresora");
        return n;
    }

    public void drop_print(int n) {
        synchronized (cerrojo) {
            libre[n] = true;
            impresoras++;
            System.out.println("suelto la impresora");
            cerrojo.notifyAll();
        }
    }
}