package seminarios;

public class Daemon extends Thread{
    
    public void run(){
        System.out.println("Run del hilo deamon");
    }
    public static void main(String[] args) throws Exception {
        Daemon h1 = new Daemon();
        h1.setDaemon(true);
        h1.start();
        h1.join(); 
    }
}