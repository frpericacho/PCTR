package Practicas19.practica2;

public class cajero implements Runnable{
    private boolean op;
    private static cuentaCorrienta caja;
    public cajero(boolean op){
        this.op=op;
    }

    public static void inicializa(int id, double disponible){
        caja = new cuentaCorrienta(id, disponible);
    }

    public static double cantidad(){
        return caja.Saldo();
    }

    public void run(){
        for(int i = 0;i < 1000; ++i){
            if(op){
                caja.Deposito(100);
            }else{
                caja.Reintegro(100);
            }
        }
    }
}