package monitortres;

import static java.lang.Thread.sleep;

public class cliente implements Runnable {
    public static lineaCajas cajas = new lineaCajas();
    public int id;

    public cliente(int id){
        this.id = id;
    }

    public void run(){
        int caja = cajas.entraCola(id);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cajas.saleCola(id, caja);
    }
}