
import java.rmi.Naming;
import java.util.Scanner;

public class ClienteHundirLaFlota{
    
    char[][] tablero = new char[10][10];

    public static void main(String[] args) throws Exception{
        char tablero[][] = new char[10][10];
        for(int i = 0; i < tablero.length; i++){
            for(int j = 0; j < tablero.length; j++){
                tablero[i][j] = ' ';
            }
        }
        IservidorHundirLaFlota servidor = (IservidorHundirLaFlota) Naming.lookup("hundirlaflota");        
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("fila: ");
            int i = sc.nextInt();
            System.out.print("columna: ");
            int j = sc.nextInt();
            switch(servidor.comprobarCelda(i, j)){
                case 0: tablero[i][j] = 'a';
                    break;
                case 1: tablero[i][j] = '*';
                    break;
            }
            for( i = 0; i < tablero.length; i++){
                for(j = 0; j < tablero.length; j++){
                    System.out.print(tablero[i][j]);
                }
                System.out.println();
            }
        }
    }
}