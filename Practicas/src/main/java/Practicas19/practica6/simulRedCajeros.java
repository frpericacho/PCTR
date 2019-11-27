package Practicas19.practica6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class simulRedCajeros implements Runnable {
    static cuentaCorrienteSegura cuenta = new cuentaCorrienteSegura();
    int id;

    public simulRedCajeros(int id){
        this.id = id;
    }
    
    public static void main(String[] args) {
        
        ExecutorService exe = Executors.newCachedThreadPool();
        for(int i = 0; i < 4; i++){
            exe.execute(new simulRedCajeros(i%2));     //Falta por hacer que sea lambda
        }
        exe.shutdown();
        while(!exe.isTerminated());

        System.out.println(cuenta.Saldo());
    }

    public void run(){
        if(id==0){
            cuenta.Deposito(100);
        }else{
            cuenta.Reintegro(100);
        }
    }
}