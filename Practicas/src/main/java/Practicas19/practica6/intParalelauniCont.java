
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class intParalelauniCont implements Runnable{
    public static int cont;
    public static int cores = Runtime.getRuntime().availableProcessors();
    public static int cant = 100000000;
    public static double puntos;
    public static Object cerrojo = new Object();


    public intParalelauniCont(double puntos){
        this.puntos = puntos;
    }
    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(cores);
        long inicio = System.currentTimeMillis();
        for(int i = 0; i < cores; i++){
            exe.execute(new intParalelauniCont(cant/cores));
        }
        exe.shutdown();
        while(!exe.isTerminated());
        long fin = System.currentTimeMillis();
        System.out.println("    El area es: "+ (double)cont/cant);
        System.out.println("el tiempo es: " + (fin-inicio));
    }
    
    @Override
    public void run() {
        double x,y;
        for(int i=0; i < (int)puntos; ++i){
            x = Math.random();
            y = Math.random();
            if(Math.sin(x) >= y){
                synchronized(cerrojo){++cont;};
            }
        }
    }
}