package practica8;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class usalectorEscritor implements Runnable{
    lectorEscritor m = new lectorEscritor();
    private int accion;

    public usalectorEscritor(int accion){
        this.accion = accion;
    }

    public void run(){
        while(true){
            try {
                if(accion == 0){
                    m.inicia_escritura();
                    m.fin_escritura(); break;
                }else{
                    m.inicia_lecture();
                    m.fin_lecture(); break;
                }
            }catch (Exception e) {}
        }    
    }

    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 10; i++) {
            exe.execute(new usalectorEscritor(i%2));
        }
        exe.shutdown();
    }
}