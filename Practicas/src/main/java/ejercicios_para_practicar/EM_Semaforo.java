package ejercicios_para_practicar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class EM_Semaforo implements Runnable{
    static Semaphore s = new Semaphore(1);
    static volatile int res;

    public EM_Semaforo(){

    }

    public void run(){
        try {
            s.acquire();
        } catch (Exception e) {}
        res++;
        s.release();
    }

    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int i = 0; i < 4; i++){
            exe.execute(new EM_Semaforo());
        }
        exe.shutdown();

        System.out.println(res);
    }
}