package practica7;
/*Ejemplo de cliente de sockets
*@Antonio Tomeu
*@version 1.0
*/
import java.net.*;
import java.io.*;

public class clienteMultiple{
    public static void main (String[] args){
        int j=0,aux=0;
        aux = 20;
        while(j < aux){
            int i = (int)(Math.random()*10);
            int puerto = 2001;
            try{
                System.out.println("Realizando conexion...");
                Socket cable = new Socket("localhost", puerto);
                System.out.println("Realizada conexion a "+cable);
                PrintWriter salida= new PrintWriter(new BufferedWriter(new OutputStreamWriter(cable.getOutputStream())));
                salida.println(i);  
                salida.flush();
                System.out.println("Cerrando conexion...");
                cable.close();
            }catch (Exception e){
                System.out.println("Error en sockets...");
            }    
            j++;
        }
    }//main
}//clienteMultiple