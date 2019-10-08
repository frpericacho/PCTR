package practica8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 
public class drakkarVikingo implements Runnable {

    static drakkarVikingo2 drakk = new drakkarVikingo2();
    private int accion;

    public static void main(String[] args) throws Exception {

        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        exe.execute(new drakkarVikingo(1));
        for (int i = 0; i < 10; i++) {
            exe.execute(new drakkarVikingo(0));
        }
        exe.shutdown();
    }

    public void run() {
        try {
            while(true){
                if(accion == 0){
                    drakk.come();
                }else{
                    drakk.llena();
                }
            }
        } catch (Exception e) {
        }
    }

    public drakkarVikingo(int i) {
        accion = i;
    }
}

class drakkarVikingo2 {
    static int marmita = 0;

    public drakkarVikingo2() {
        marmita = 5;
    }

    public synchronized void llena() throws Exception {
        while (marmita > 0) {
            wait();
        }
        marmita = 5;
        System.out.println("lleno");
        notifyAll();
    }

    public synchronized void come() throws Exception {
        while (marmita == 0) {
            notifyAll();
            wait();
        }
        System.out.println("como");
        marmita--;
    }
}