package Practicas18.practica7;
import java.util.*;
import java.util.concurrent.*;

public class intParaleloFutureCont implements Callable<Integer>{
    public static int n = 100000;
    private double y,x;
    private Random r = new Random();
    
    public intParaleloFutureCont(){

    }
    
    public Integer call(){
        int cont = 0;
        for(int i = 0; i < n; i++){
            x = r.nextDouble();
            y = r.nextDouble();
            if(y < Math.sin(x)){
                cont++;
            }
        }
        return cont;
    }
    public static void main(String[] args)throws Exception{
        int n = 100000,cont = 0;
        ExecutorService exe=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ArrayList<Future<Integer>> fin = new ArrayList<Future<Integer>>();

        for(int i = 0; i < Runtime.getRuntime().availableProcessors(); i++){
            fin.add(exe.submit(new intParaleloFutureCont()));
        }
        exe.shutdown();
        exe.awaitTermination(1, TimeUnit.DAYS);

        for(Future<Integer> iter: fin){
            cont += iter.get();
        }
        
        System.out.println("El resultado es: " +((double)cont/(n*4)));
    }
}