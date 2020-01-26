package ejercicios_para_practicar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Random;

public class intMontecarlo implements Runnable{
    public static int cores = Runtime.getRuntime().availableProcessors();
    public static int N, p_debajo;
    public static Random r = new Random();
    public static Object cerrojo = new Object();
    public int rep;

    public intMontecarlo(int rep){
        this.rep = rep;
    }

    public void run() {
        int cont = 0;
        System.out.println(rep);
        for(int i = 0; i < rep; i++){
            double x = r.nextDouble();
            double y = r.nextDouble();
            if(y < x*x)
                cont++;
        }
        synchronized(cerrojo){
            p_debajo += cont;
        }
    }

    public static void main(String[] args) {
        ExecutorService exe = Executors.newCachedThreadPool();
        N = 100;
        for(int i = 0; i < cores; i++){
            // if(i == cores-1)
            //     exe.execute(new intMontecarlo(N%cores));
            // else
                exe.execute(new intMontecarlo(N/cores));
        }
        exe.shutdown();
        while(!exe.isTerminated());

        System.out.println("el valor es: " + (double)p_debajo/N);
    }

}