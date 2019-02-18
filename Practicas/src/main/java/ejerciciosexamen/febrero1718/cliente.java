package ejerciciosexamen.febrero1718;

public class cliente implements Runnable{
    public static lineacajas monitor = new lineacajas();
    public int id;

    public cliente(int i){
        id = i;
    }

    public void run(){
        lineacajas.espera();
        System.out.println("el cliente "+id+" ha sido atendido");
    }
}