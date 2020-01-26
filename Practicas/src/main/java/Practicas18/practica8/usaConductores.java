
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class usaConductores implements Runnable {
    private Random r = new Random();
    private static Conductores c = new Conductores(
        "/home/felix/Escritorio/ProyectosVisualStudio/PCTR/Practicas/src/main/java/practica8/data.csv");
    public void run() {
        int i = r.nextInt(c.tamaño());
        switch(r.nextInt(3)){
            case 0: System.out.println(Arrays.toString(c.da_conductores(0,i).toArray())); 
                    break;
            case 1: System.out.println(c.da_conductor(i)); 
                    break;
            case 2: System.out.println(Arrays.toString(c.da_conductores().toArray())); 
                    break;
        }
    }

    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 8; i++) {
            exe.execute(new usaConductores());
        }
        exe.shutdown();
    }
}