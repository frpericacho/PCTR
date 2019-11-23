package Practicas19.practica7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//import java.util.concurrent.Semaphore;

public class drakkarVikingo implements Runnable {
    static private int marmita;
    int idHilo;

    public drakkarVikingo(int i) {
        this.idHilo = i;
    }

    // static Semaphore em, cocinero, vikingo, espera;
    public static void main(String[] args) {
        marmita = 4;
        ExecutorService exe = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            exe.execute(new drakkarVikingo(i));
        }
        exe.shutdown();
    }

    public synchronized void comer() throws InterruptedException {
        if (marmita == 0) {
            notifyAll();
            wait();
        } else {
            marmita--;
            System.out.println("he comido");
        }
    }

    public synchronized void cocinar() throws InterruptedException {
        while (marmita > 0)
            wait();
        marmita = 4;
        System.out.println("he cocinado");
        notifyAll();
    }

    @Override
    public void run() {
        switch (idHilo) {
        case 0:
            while (true)
                try {
                    cocinar();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        default:
            while (true)
                try {
                    comer();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }

    // version semaphore

    // public static void comer() throws InterruptedException {
    // while(true){
    // em.acquire();
    // if(marmita == 0){
    // cocinero.release();
    // espera.acquire();
    // }
    // marmita--;
    // em.release();
    // }
    // }

    // public static void cocina() throws InterruptedException {
    // while(true){
    // cocinero.acquire();
    // marmita = 20;
    // espera.release();
    // }
    // }
}