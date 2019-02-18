package ejercicios_para_practicar;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ejercicio1 implements Runnable{
    private int i;
    private static RandomAccessFile file;

    public ejercicio1(int i){
        this.i = i;
    }
    
    public void run(){
        synchronized(file){
            try {
                file.writeBytes(i+" ,");
            } catch (Exception e) {}
        }
    }

    public static void file(String nombre) {
        File f = new File(nombre);
        try {
            f.createNewFile();
            file = new RandomAccessFile(nombre, "rw");
        } catch (Exception e) {}
    }
    
    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        file("juasjuas");
        for(int i = 0;i < Runtime.getRuntime().availableProcessors(); i++){
            exe.execute(new ejercicio1(i));
        }
        exe.shutdown(); 
    }
}