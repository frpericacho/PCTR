package Practicas18.practica7;
import java.util.*;
import java.util.concurrent.*;

public class intParalelauniCont implements Runnable{
    public static int n = 100000;
    private double y,x;
    private Random r = new Random();
    static Object cerrojo = new Object();
    static volatile int cont=0;
 
    public intParalelauniCont(){
        
    }
   
    public void run(){
        for(int i = 0; i < n; i++){
            x = r.nextDouble();
            y = r.nextDouble();
            if(y < Math.sin(x)){
                synchronized(cerrojo){
                    cont++;
                }
            }
        }
    }
    public static void main(String[] args)throws Exception{
        ExecutorService exe=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int i=0;i<Runtime.getRuntime().availableProcessors();i++){
            exe.execute(new intParalelauniCont());
        }
        exe.shutdown();
        exe.awaitTermination(1,TimeUnit.DAYS);
        
        System.out.println("El resultado es: " +(double)cont/(n*4));
    }
}