package ejercicios_para_practicar;

import java.util.concurrent.Semaphore;

public class MatSum extends Thread{
    public int fila;
    public static Semaphore cerrojo = new Semaphore(10);

    public MatSum(int fila){
        this.fila = fila;
    }

    public void run(){
        int cont = 0;
        for(int i = 0; i < 10; i++){
            cont += pruebaMatSum.datMat[fila][i];
        }
        
        try {
            cerrojo.acquire();
            pruebaMatSum.total += cont;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cerrojo.release();
    }
}