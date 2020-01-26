package ejerciciosexamen.septiembre1314;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class aRendimiento {
    private static final long nIter = 100000L;
    private static Object cerrojo = new Object();
    private static ReentrantLock cerrojoR = new ReentrantLock();
    private static AtomicLong atm = new AtomicLong(0);
    private static ExecutorService ejecutor = null;
    private static long val = 0;

    public static void main(String[] args) {
        int max = (int)nIter/5000;
        long startS,endS,startC,endC,startA,endA;
        for(int i = 0;i < max; i++){
            startS = System.currentTimeMillis();
            conSyn();
            endS = System.currentTimeMillis()-startS;
            startC = System.currentTimeMillis();
            conCerr();
            endC = System.currentTimeMillis() - startC;
            startA = System.currentTimeMillis();
            conAtom();
            endA = System.currentTimeMillis() - startA;
            System.out.println("el tiempo de Synchronized es: "+endS);
            System.out.println("el tiempo de AtomicLong es: "+endA);
            System.out.println("el tiempo de Cerrojos es: "+endC);
        }
    }

    //public aRendimiento(){}

    public static void conSyn(){
        ejecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int i = 0; i < 5000; i++){
            ejecutor.execute(new Runnable(){
                public void run(){
                    sincronizado();
                }});
            ejecutor.shutdown();
            while(!ejecutor.isTerminated());
        }
    }

    public static void sincronizado(){
        synchronized(cerrojo){
            val++;
        }
    }

    public static void conCerr(){
        ejecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int i = 0; i < 5000; i++){
            ejecutor.execute(new Runnable(){
                public void run(){
                    cerrojos();
                }});
            ejecutor.shutdown();
            while(!ejecutor.isTerminated());
        }

    }

    public static void cerrojos(){
        cerrojoR.lock();
        val++;
        cerrojoR.unlock();
    }

    public static void conAtom(){
        ejecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int i = 0; i < 5000; i++){
            ejecutor.execute(new Runnable(){
                public void run(){
                    atm.incrementAndGet();
                }});
            ejecutor.shutdown();
            while(!ejecutor.isTerminated());
            System.out.println(val);
        }
    }

}