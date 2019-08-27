package ejercicios_para_practicar;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class aRendimiento implements Runnable{
    public static int nIter;
    public static ReentrantLock cerrojo = new ReentrantLock();
    public static AtomicLong atom = new AtomicLong(0);
    public static Object cerr = new Object();
    public int val;
    public static long cont;
    public static long cont2;
    public static long t_syn, t_atom, t_reen;

    public aRendimiento(int val){
        this.val = val;
    }

    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(10);
        for(int i = 0; i < 90; i++){
            exe.execute(new aRendimiento(i%3));
        }
        exe.shutdown();
        while(!exe.isTerminated());
        System.out.println(t_syn);
        System.out.println(t_atom);
        System.out.println(t_reen);
    }

    @Override
    public void run() {
        switch(val){
            case 0: long inicio1 = System.currentTimeMillis();
                    synchronized(cerr){
                        cont++;
                    }
                    long fin1 = System.currentTimeMillis();
                    synchronized(cerr){
                        t_syn += (fin1-inicio1);
                    }
            break;

            case 1: long inicio2 = System.currentTimeMillis();
                    atom.incrementAndGet();
                    long fin2 = System.currentTimeMillis();
                    synchronized(cerr){
                        t_atom += (fin2-inicio2);
                    }
            break;
            
            case 2: long inicio3 = System.currentTimeMillis();
                    cerrojo.lock();
                    cont2++;
                    cerrojo.unlock();
                    long fin3 = System.currentTimeMillis();
                    synchronized(cerr){
                        t_reen += (fin3-inicio3);
                    }
            break;
        }
    }    
}