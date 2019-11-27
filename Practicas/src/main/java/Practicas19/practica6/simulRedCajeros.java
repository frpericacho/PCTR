package Practicas19.practica6;

public class simulRedCajeros implements Runnable{
    cuentaCorrienteSegura cuenta = new cuentaCorrienteSegura();
    int id;

    public simulRedCajeros(int id){
        this.id = id;
    }
    
    public static void main(String[] args) {
        
        ExecutorService exe = Executors.newCachedpool();
        for(int i = 0; i < 4; i++){
            exe.(new simulRedCajeros(i%2));     //Falta por hacer que sea lambda
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