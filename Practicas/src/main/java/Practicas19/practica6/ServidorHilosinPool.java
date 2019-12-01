
import java.util.*;
import java.net.*;
import java.io.*;

public class ServidorHilosinPool implements Runnable {
    Socket enchufe;
    private int name;
    public static long total;


    public ServidorHilosinPool(Socket s, int name) {
        enchufe = s;
        this.name = name;
    }

    public void run() {
        Date d = new Date();
        long inicCronom = System.currentTimeMillis();
        d.setTime(inicCronom);
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(enchufe.getInputStream()));
            String datos = entrada.readLine();
            int j;
            int i = Integer.valueOf(datos).intValue();
            for (j = 1; j <= 20; j++) {
                System.out.println("El hilo " + name + " escribiendo el dato " + i);
            }
            enchufe.close();
            System.out.println("El hilo " + name + "cierra su conexion...");
        } catch (Exception e) {
            System.out.println("Error...");
        }
        long finCronom = System.currentTimeMillis();
        d.setTime(finCronom);
        total += (finCronom - inicCronom);

        System.out.println("Tiempo: " + (finCronom - inicCronom) + " milisegundos");
        System.out.println("total es: " + total);

    }

    public static void main(String[] args) {
        int i = 0;
        int puerto = 2001;
        try {
            ServerSocket chuff = new ServerSocket(puerto, 3000);

            while (true) {
                System.out.println("Esperando solicitud de conexion...");
                Socket cable = chuff.accept();
                System.out.println("Recibida solicitud de conexion...");
                new Thread(new ServidorHilosinPool(cable, i)).start();
                i++;
            }
        } catch (Exception e) {
            System.out.println("Error en sockets...");
        }
    }

}
