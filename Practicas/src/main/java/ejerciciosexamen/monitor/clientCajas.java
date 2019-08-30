package ejerciciosexamen.monitor;

import static java.lang.Thread.sleep;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class clientCajas implements Runnable{
    public int id;
    static lineaCajas cajas = new lineaCajas();
    public clientCajas(int id){
        this.id = id;
    }

    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(1000);
        for(int i = 0; i < 1000; i++){
            exe.execute(new clientCajas(i));
        }
        exe.shutdown();
        while(!exe.isTerminated());
    }

    @Override
    public void run() {
        int caja = cajas.irCaja(id);
        try {
            sleep(100);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        cajas.salirCaja(id, caja);
    }

}