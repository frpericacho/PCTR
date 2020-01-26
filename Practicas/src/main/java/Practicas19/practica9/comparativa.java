import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class comparativa implements Runnable{
    public static Object cerrojo = new Object();
    public static Semaphore semaforo = new Semaphore(1);
    public static ReentrantLock reentrant = new ReentrantLock();
    public static AtomicInteger atom = new AtomicInteger();
    public static Condition cond = reentrant.newCondition();
    public static int cant_SYN = 0;
    public static int cant_REEN = 0;
    public static int cant_SEM = 0;
    public static long total = 0;

    public static long SYN(int iter){
        long ini = System.currentTimeMillis();
        for(int i = 0; i < iter; i++){
            synchronized(cerrojo){
                cant_SYN++;
            }
        }
        long fini = System.currentTimeMillis();
        return fini-ini;
    }

    public static long SEM(int iter) throws Exception{
        long ini = System.currentTimeMillis();
        for(int i = 0; i < iter; i++){
            semaforo.acquire();
            cant_SEM++;
            semaforo.release();
        }
        long fini = System.currentTimeMillis();
        return fini-ini;
    }

    public static long REEN(int iter) throws Exception{
        long ini = System.currentTimeMillis();
        for(int i = 0; i < iter; i++){
            try{
                reentrant.lock();
                cant_REEN++;
            }finally{
                reentrant.unlock();
            }
        }
        long fini = System.currentTimeMillis();
        return fini-ini;
    }

    public static long ATOM(int iter){
        long ini = System.currentTimeMillis();
        for(int i = 0; i < iter; i++){
            atom.incrementAndGet();
        }
        long fini = System.currentTimeMillis();
        return fini-ini;
    }

    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(8);
        for(int i = 0; i < 8; i++){
            exe.execute(new comparativa());
        }
        exe.shutdown();
        while(!exe.isTerminated());
        System.out.println(total);
    }

    @Override
    public void run(){
        try {
            total += SYN(1000000);
            //total += SEM(1000000);
            //total += REEN(1000000);
            //total += ATOM(1000000);
        } catch (Exception e) {}
    }
}