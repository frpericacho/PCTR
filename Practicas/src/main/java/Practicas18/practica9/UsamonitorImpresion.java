package practica9;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class UsamonitorImpresion implements Runnable{
    private int accion;
    private monitorImpresion n = new monitorImpresion();

    public UsamonitorImpresion(int i){
        accion = i;
    }

    public static void main(String[] args) throws Exception{
        ExecutorService exe = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 2; i++) {
            exe.execute(new UsamonitorImpresion(i));
        }
        exe.shutdown();
    }

    public void run(){
        try {
            while(true){
                switch(accion){
                    case 0: n.usar(accion);
                            System.out.println("Usando");
                    case 1: n.liberar(accion);
                            System.out.println("Liberando");
                }
            }
        } catch (Exception e) {}
    }
}