package ejercicios_para_practicar;

public class pruebaMonitor extends Thread{
    public static monitorSem mon = new monitorSem(1);
    public static int val = 0;

    public void run(){
        for(int i = 0; i < 10; i++)
            inc();
    }

    public void inc(){
        mon.waitSem();
        val++;
        mon.signalSem();
    }

    public static void main(String[] args) {
        Thread h1 = new Thread(new pruebaMonitor());
        Thread h2 = new Thread(new pruebaMonitor());
        h1.start();
        h2.start();

        try {
            h1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            h2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(val);
    }
}