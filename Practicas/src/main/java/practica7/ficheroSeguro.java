package practica7;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ficheroSeguro implements Runnable{
    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        file("hola");
        for(int i = 0; i < Runtime.getRuntime().availableProcessors(); i++){
            exe.execute(new ficheroSeguro(i));
        }
        exe.shutdown();
    }

    public ficheroSeguro(int i){
        this.i = i;
    }

    public void run(){
        synchronized(file){
            try{
                file.writeBytes(i+"\n");
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void file(String name){
        File newfile = new File(name);
        try{
            newfile.createNewFile();
            file = new RandomAccessFile(name, "rw");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private int i;
    private static RandomAccessFile file;
}