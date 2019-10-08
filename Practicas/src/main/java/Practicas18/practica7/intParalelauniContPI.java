package practica7;
import java.util.*;
import java.util.concurrent.*;

public class intParalelauniContPI implements Runnable{
    public static int n = 100000;
    private double y,x;
    private Random r = new Random();
    static Object cerrojo = new Object();
    static volatile int cont=0;
 
    public intParalelauniContPI(){
        
    }
   
    public void run(){
        for(int i = 0; i < n; i++){
            x = r.nextDouble();
            y = r.nextDouble();
            if(Math.pow(x, 2) + Math.pow(y, 2) <= 1){
                synchronized(cerrojo){
                    cont++;
                }
            }
        }
    }
    public static void main(String[] args)throws Exception{
        ExecutorService exe=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int i=0;i<Runtime.getRuntime().availableProcessors();i++){
            exe.execute(new intParalelauniContPI());
        }
        exe.shutdown();
        exe.awaitTermination(1,TimeUnit.DAYS);
        
        System.out.println("El resultado es: " +(double)cont/n);
    }
}