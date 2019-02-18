package practica3;

public class cajero implements Runnable{
    private boolean op;
    private static Cuenta_Banca caja;
    public cajero(boolean op){
        this.op=op;
    }

    public static void inicializa(int id, double disponible){
        caja = new Cuenta_Banca(id, disponible);
    }

    public static double cantidad(){
        return caja.Saldo();
    }

    public void run(){
        for(int i = 0;i < 10; ++i){
            if(op){
                caja.Deposito(100);
            }else{
                caja.Reintegro(100);
            }
        }
    }
}