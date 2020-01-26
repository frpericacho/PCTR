package Practicas19.practica2;

public class expresiones_lambda {
    public static int aux = 0;
    public static void main(String[] args) throws InterruptedException {

        Runnable task = () -> {
            if(Thread.currentThread().getName() == "0"){
                for(int i = 0; i < 100; i++) aux++;
            }else{
                for(int i = 0;i < 100; i++) aux--;
            }
        };

        Thread hilo1 = new Thread(task);
        Thread hilo2 = new Thread(task);

        hilo1.start();
        hilo2.start();

        hilo1.join();
        hilo2.join();

        System.out.println(aux);
    }
}