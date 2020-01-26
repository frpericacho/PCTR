/*package practica10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class reentrantServer implements Runnable{
    Socket so;
    private final ReentrantLock cerrojo = new ReentrantLock();
    public int cont = 0;
    static long inicio = System.currentTimeMillis();
    static Date d = new Date();

    public void run(){
        try {
            BufferedReader entry = new BufferedReader(new InputStreamReader(so.getInputStream()));
            String data = entry.readLine();
            int i = Integer.valueOf(data).intValue();
            cerrojo.lock();
            try {
                
            } finally {
                cerrojo.unlock();
            }
            for(int j = 0;j <= 20; j++){
                System.out.println("hilo: "+Thread.currentThread().getName()+" escribe "+i);
                Thread.sleep(1000);
            }
            so.close();
            System.out.println("hilo: "+Thread.currentThread().getName()+" cierra");
            long fin = System.currentTimeMillis();
            d.setTime(fin);
            System.out.println("tiempo:(ReentrntLock) "+(fin-inicio));
        } catch (Exception e) {}
    }
    
    public reentrantServer(Socket s){
        so = s;
    }

    public static void main(String[] args) throws Exception{
        ServerSocket chuff = new ServerSocket(2001,3000);
        ExecutorService exe = Executors.newCachedThreadPool();
        while(true){
            System.out.println("Espera");
            Socket s = chuff.accept();
            d.setTime(inicio);
            System.out.println("recibe");
            exe.execute(new reentrantServer(s));
        }
    }
}*/