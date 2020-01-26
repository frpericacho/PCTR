/*Ejemplo de servidor de sockets multihilo
*@Antonio Tomeu
*@version 1.0
*/
package practica7;
import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorHiloconPool implements Runnable{
    Socket enchufe;
    static ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public ServidorHiloconPool(Socket s){
        enchufe = s; 
        exe.execute(new ServidorHiloconPool(enchufe));
    }

    public void run()
    {
        try{
            BufferedReader entrada = new BufferedReader(new InputStreamReader(enchufe.getInputStream()));
            String datos = entrada.readLine();
            int j;
            int i = Integer.valueOf(datos).intValue();
            for(j=1; j<=20; j++){
                System.out.println("El hilo "+Thread.currentThread().getName()+" escribiendo el dato "+i);
                    Thread.sleep(1000);
            }
            enchufe.close();
            System.out.println("El hilo "+Thread.currentThread().getName()+"cierra su conexion...");
        } catch(Exception e) {System.out.println("Error...");}
    }//run

	/*public static void main (String[] args)
    {
        int puerto = 2001;
            try{
                ServerSocket chuff = new ServerSocket (puerto, 3000);

                while (true){
                    System.out.println("Esperando solicitud de conexion...");
                    Socket cable = chuff.accept();
                    System.out.println("Recibida solicitud de conexion...");
                    exe.execute(new ServidorHilosinPool(cable));
            }//while
        } catch (Exception e)
            {System.out.println("Error en sockets...");}
    }//main*/
}//ServidorHiloconPool