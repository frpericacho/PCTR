/*
package ejerciciosexamen.septiembre1213;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

class aRendimiento extends Thread{
    private static int cont1=0;
    private static int cont2=0;
    private static ReentrantLock reen = new ReentrantLock();
    private static AtomicLong atomico = new AtomicLong(0);
    private static Object cerrojo = new Object();

    public static void lock(){
        synchronized(cerrojo){
            cont1++;
        }
    }

    public static void reentrank(){
        reen.lock();
        cont2++;
        reen.unlock();
    }
    public static void main(String[] args) throws Exception{
        long cuantaAtomic,cuentaLock,cuentaReentrank,iniAtomic,endAtomic,iniLock,endLock,iniReentrank,endReentrank;
        int nIter=200;

        Thread[] hilos = new Thread[5000];
        for(int j = 0; j < 5000; j++){
            hilos[j] = new Thread(new Runnable(){
                @Override
                public void run() {
                    atomico.incrementAndGet();
                }
            });
        }
        iniAtomic = System.currentTimeMillis();
        for(int i = 0; i < nIter; i++){
            hilos[i].start();
        }
        for(int i = 0; i < nIter; i++){
            hilos[i].join();
        }
        endAtomic = System.currentTimeMillis();
        cuantaAtomic = endAtomic-iniAtomic;
        //////////////////////////////
        for(int j = 0; j < 5000; j++){
            hilos[j] = new Thread(new Runnable(){
                @Override
                public void run() {
                    lock();
                }
            });
        }
        iniLock = System.currentTimeMillis();
        for(int i = 0; i < nIter; i++){
            hilos[i].start();
        }
        for(int i = 0; i < nIter; i++){
            hilos[i].join();
        }
        endLock = System.currentTimeMillis();
        cuentaLock = endLock - iniLock;
        ///////////////////////////////
        for(int j = 0; j < 5000; j++){
            hilos[j] = new Thread(new Runnable(){
                @Override
                public void run() {
                    atomico.incrementAndGet();
                }
            });
        }
        iniReentrank = System.currentTimeMillis();
        for(int i = 0; i < nIter; i++){
            hilos[i].start();
        }
        for(int i = 0; i < nIter; i++){
            hilos[i].join();
        }
        endReentrank = System.currentTimeMillis();
        cuentaReentrank = endReentrank - iniReentrank;

        System.out.println(cuantaAtomic);
        System.out.println(cuentaLock);
        System.out.println(cuentaReentrank);
    }
}
*/