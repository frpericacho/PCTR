package ejerciciosexamen.junio1314;

import java.rmi.Naming;
import java.util.Scanner;

public class cIBEX5{
    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner(System.in);
        int op = 20;
        iIBEX5 obj = (iIBEX5)Naming.lookup("//localhost/servidor");

        while(op!=0){
            System.out.println("1.- consultar todos");
            System.out.println("2.- consultar una");
            System.out.println("0.- salir");
            op = s.nextInt();
            switch(op){
                case 1: obj.consultar_todos(); break;
                case 2: System.out.println("1.- TRI");
                        System.out.println("2.- CHN");
                        System.out.println("3.- BMM");
                        System.out.println("4.- CRR");
                        System.out.println("5.- TDC");
                        int i = s.nextInt();
                obj.consultar_uno(i); break;
                case 0: op = 0; break;
            }
        }
        s.close();
    }
}