package ejercicios_para_practicar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ejercicio3 implements Runnable{
    private int accion;
    static ejercicio3_2 n = new ejercicio3_2();
    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int i = 0; i < Runtime.getRuntime().availableProcessors(); i++){
            exe.execute(new ejercicio3(i));
        }
        exe.shutdown();
    }

    public ejercicio3(int i){
        accion = i;
    }

    public void run(){
        try {
            while(true){
                if(accion == 0){
                    n.llenar();
                }else{
                    n.comer();
                }
            }
        } catch (Exception e) {}
    }
}

class ejercicio3_2{
    static int marmita = 0;

    public synchronized void comer() throws Exception{
        while(marmita == 0){
            notifyAll();
            wait();
        } 
        System.out.println("como");
        marmita--;
    }
    
    public synchronized void llenar() throws Exception{
        while(marmita > 0) wait();
        marmita = 10;
        System.out.println("lleno");
        notifyAll();
    }

    public ejercicio3_2(){
        marmita = 10;
    }
}