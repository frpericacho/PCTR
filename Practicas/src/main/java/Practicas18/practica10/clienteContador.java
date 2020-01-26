package Practicas18.practica10;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class clienteContador{

    public static void main(String[] args) throws Exception{
        int n = 10;

        for(int i = 0;i < n; i++){
            int j = (int)(Math.random() * 10);
            System.out.println("inicia conexion");
            Socket so = new Socket("localhost",2001);
            System.out.println("conexion a "+so);
            PrintWriter exit = new PrintWriter(new BufferedWriter(new OutputStreamWriter(so.getOutputStream())));
            exit.println(j);
            exit.flush();
            System.out.println("acaba conexion");
            so.close();
        }
    }
}