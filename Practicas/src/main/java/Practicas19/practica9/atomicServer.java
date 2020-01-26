
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class atomicServer implements Runnable {
    Socket s;
    static AtomicInteger cont = new AtomicInteger();

    public atomicServer(Socket so) {
        s = so;
    }

    public void run() {
        try {
            BufferedReader entry = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String data = entry.readLine();
            int i = Integer.valueOf(data).intValue();
            cont.incrementAndGet();
            for (int j = 1; j <= 20; j++) {
                System.out.println("hilo: " + Thread.currentThread().getName() + " escribe :" + i);
            }
            s.close();
            System.out.println("hilo: " + Thread.currentThread().getName() + " se va");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ServerSocket chuff = new ServerSocket(2001, 3000);
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        while (true) {
            System.out.println("Espera");
            Socket s = chuff.accept();
            System.out.println("Recibe");
            exe.execute(new atomicServer(s));
        }
    }
}