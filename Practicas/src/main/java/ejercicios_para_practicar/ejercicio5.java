package ejercicios_para_practicar;
import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class ejercicio5 implements Runnable{
    private ReentrantLock cerrojo = new ReentrantLock();
    static BigInteger a = new BigInteger("100");
    static BigInteger b = new BigInteger("200");
    static BigInteger c = new BigInteger("0");
    static BigInteger res;
    private int i;

    public void run(){
        cerrojo.lock();
        c = a.add(b);
        System.out.println("el hilo "+i+":"+c);
        res.add(c);
        cerrojo.unlock();
    }

    public ejercicio5(int i){
        this.i = i;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int i = 0; i < Runtime.getRuntime().availableProcessors(); i++){
            exe.execute(new ejercicio5(i));
        }
        exe.shutdown();
        exe.awaitTermination(1, TimeUnit.DAYS);
        System.out.println(res);
    }
}