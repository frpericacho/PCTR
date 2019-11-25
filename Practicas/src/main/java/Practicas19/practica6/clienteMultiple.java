package Practicas19.practica6;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class clienteMultiple {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int j = 0;
        int cant;
        System.out.println("introduzca cantidad de peticiones");
        cant = sc.nextInt();
        while (j < cant) {
            int i = (int) (Math.random() * 10);
            int puerto = 2001;
            try {
                System.out.println("Realizando conexion...");
                Socket cable = new Socket("localhost", puerto);
                System.out.println("Realizada conexion a " + cable);
                PrintWriter salida = new PrintWriter(
                        new BufferedWriter(new OutputStreamWriter(cable.getOutputStream())));
                salida.println(i);
                salida.flush();
                System.out.println("Cerrando conexion...");
                cable.close();

            } catch (Exception e) {
                System.out.println("Error en sockets...");
            }
            j++;
        }
    }
}
