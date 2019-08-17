package ejercicios_para_practicar; 

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EM_Monitor implements Runnable{ //para modificar variables hay que hacerlo desde la clase del monitor?
    private int accion;
    static monitor n = new monitor(); // cuando deberia crear un vector de otra clase?

    public EM_Monitor(int i){
        accion = i;
    }

    public void run(){
        if(accion == 0){
            n.empiezo1();
            n.termino1();
        }else{
            n.empiezo2();
            n.termino2();
        }
    }

    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int i = 0; i < 4; i++){
            exe.execute(new EM_Monitor(i%2));
        }
        exe.shutdown();
    }
}

class monitor{
    static int n = 0; //alguna razon para poner Integer en vez de int
    static boolean condicion = false;

    public synchronized void empiezo1(){  // por que no se puede poner: synchronized void empiezo1(int res)
        while(condicion){
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        System.out.println("empiezo 1");
        n++;
        notifyAll();        
    }

    public synchronized void termino1(){
        n--;
        System.out.println("termino 1");
        if(n == 0)          
            notifyAll();
    }


    public synchronized void empiezo2(){  
        while(n != 0 || condicion){
            try {
                wait();
            } catch (Exception e) {}
        }
        System.out.println("empiezo 2");
        condicion = true;
    }

    public synchronized void termino2(){
        condicion = false;
        System.out.println("termino 2");
        notifyAll();
    }
}