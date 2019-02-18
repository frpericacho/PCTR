package ejercicios_para_practicar;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ejercico2 implements Runnable{
    private double x,y;
    private Random r = new Random();
    static Object cerrojo = new Object();
    static volatile int cont=0;

    public ejercico2(){

    }

    public void run(){
        for(int i = 0; i < 10000; i++){
            x = r.nextDouble();
            y = r.nextDouble();
            if(y < Math.sin(x)){
                synchronized(cerrojo){
                    cont++;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int i = 0; i < Runtime.getRuntime().availableProcessors(); i++){
            exe.execute(new ejercico2());
        }
        exe.shutdown();
        exe.awaitTermination(1,TimeUnit.DAYS);

        System.out.println("el area es: "+(double)cont/(10000*4));
    }
}