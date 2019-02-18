package practica10;

import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class triatlonBarreras implements Runnable{
    CyclicBarrier b1 = null,b2 = null,b3 = null;
    private int con, t;
    public static long[] tiempo = new long[100];

    public void run() {
        Date d = new Date();
        
        try{
            b1.await();
        }catch(BrokenBarrierException e){}catch(InterruptedException e){}

        long ini = System.currentTimeMillis();
        d.setTime(ini);
        
        try {
            wait(t);
        } catch (Exception e) {}
        
        long fin = System.currentTimeMillis();
        d.setTime(fin);

        tiempo[con] = fin - ini;
        System.out.println("el concursante "+con+" ha pasado la prueba ciclista");
        

        try{
            b2.await();
        }catch(BrokenBarrierException e){}catch(InterruptedException e){}
        ini = System.currentTimeMillis();
        d.setTime(ini);

        try {
            wait(t);
        } catch (Exception e) {}

        fin = System.currentTimeMillis();
        d.setTime(fin);

        tiempo[con] = tiempo[con] + (fin - ini);
        System.out.println("el concursante "+con+" ha pasado la prueba natacion");

        try{
            b3.await();
        }catch(BrokenBarrierException e){}catch(InterruptedException e){}

        ini = System.currentTimeMillis();
        d.setTime(ini);

        try {
            wait(t);
        } catch (Exception e) {}

        fin = System.currentTimeMillis();
        d.setTime(fin);

        tiempo[con] = tiempo[con] + (fin - ini);
        System.out.println("el concursante "+con+" ha pasado la carrera");
    }

    public triatlonBarreras(CyclicBarrier b,int con,int t){
        b1 = b;
        b2 = b;
        b3 = b;
        this.con = con;
        this.t = t;
    }

    public static void main(String[] args) throws InterruptedException {
        int concursantes = 100;
        CyclicBarrier b = new CyclicBarrier(concursantes);
        Thread hilos[] = new Thread[concursantes];
        long ti = 1000000;
        int co = 0;

        for(int i = 0; i < concursantes; i++){
            hilos[i] = new Thread(new triatlonBarreras(b, i, 0));
            hilos[i].start();
        }

        for(int i = 0; i < concursantes; i++){
            hilos[i].join();
        }


        for(int i = 0; i < concursantes; i++){
            //System.out.println(tiempo[i]);
            if(ti > tiempo[i]){
                ti = tiempo[i];
                co = i;
            }
        }

        System.out.println("el concursate "+co+" lo ha conseguido en: "+ti);
    }
}