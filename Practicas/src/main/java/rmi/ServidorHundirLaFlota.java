
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServidorHundirLaFlota extends UnicastRemoteObject implements IservidorHundirLaFlota {
    private static final long serialVersionUID = 1L;
    int[][] tablero = new int[10][10];

    public ServidorHundirLaFlota() throws RemoteException{
        for(int i = 0; i < tablero.length; i++){
            for(int j = 0; j < tablero.length; j++){
                tablero[i][j] = 0;
            }
        }
        tablero[2][1] =tablero[2][2] = tablero[2][3] = 1; //barco
    }

    
    public int comprobarCelda(int i, int j) throws RemoteException {
        int resultado = 0;
        
        if(tablero[i][j] != 0)   resultado = 1;
        //hacer k cuando da a un barco
        return resultado;
    }
    
    public static void main(String[] args) throws Exception{
        ServidorHundirLaFlota servidor = new ServidorHundirLaFlota();
        Naming.rebind("hundirlaflota", servidor);
        System.out.println("ejecutado preparado....");
    }
}