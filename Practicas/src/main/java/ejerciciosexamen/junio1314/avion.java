package ejerciciosexamen.junio1314;

public class avion implements Runnable{
    public static torre monitor = new torre();
    public static int id;

    public void run() {
        torre.aterriza();
        System.out.println("he avion "+id+" ha aterrizado y despegado");
    }

    public avion(int i){
        id = i;
    }

}