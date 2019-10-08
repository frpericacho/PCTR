package Practicas18.practica10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class usaFiloApiAn implements Runnable{
    private int id;
    public filoApiAN f = new filoApiAN();

    public usaFiloApiAn(int id){
        this.id = id;
    }

    public void run(){
        try {
            f.empieza(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int n = 5;
        ExecutorService exe = Executors.newFixedThreadPool(n);
        for(int i = 0;i < n; i++){
            exe.execute(new usaFiloApiAn(i));
        }
    }
}