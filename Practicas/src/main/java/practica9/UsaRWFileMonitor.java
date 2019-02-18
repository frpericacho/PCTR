package practica9;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class UsaRWFileMonitor implements Runnable{
    private int i;
    static RWFileMonitor rwf;

    public UsaRWFileMonitor(int i){
        this.i = i;
    }
    public static void main(String[] args) throws Exception{
        rwf = new RWFileMonitor();
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int i = 0; i < Runtime.getRuntime().availableProcessors(); i++){
            exe.execute(new UsaRWFileMonitor(i%2));
        }
        exe.shutdown();
    }

    public void run(){
        try {
            while(true){
                switch(i){
                    case 0: rwf.StartWrite();
                            rwf.EndWrite();
                            break;
                    case 1: rwf.StartRead();
                            rwf.EndRead();
                            break;
                }
            }
        } catch (Exception e) {}
    }
}