package Practicas19.practica8;

public class ejercicio {
    static int impresoras;
    static boolean libre[] = new boolean[3];

    public ejercicio(){
        impresoras = 3;
        for(int i = 0; i < 3; i++){
            libre[i] = true;
        }
    }

    public synchronized int take_print() {
        while(impresoras == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int n=0;
        while(!libre[n])
            n++;
        libre[n] = false;
        impresoras--;
        return n;
    }

    public synchronized void drop_print(int n){
        libre[n] = true;
        impresoras++;
        notifyAll();
    }
}