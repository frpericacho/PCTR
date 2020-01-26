
import java.rmi.*;
import java.rmi.server.*;
import java.util.Random;

import java.rmi.registry.*;
import java.net.*;

public class sLibros extends UnicastRemoteObject implements iLibros {

    /**
     *
     */
    private static final long serialVersionUID = -774648203401007854L;
    private static Libro biblioteca[] = new Libro[100];
    private static int cont = 0;

    protected sLibros() throws RemoteException {
        super();
    }


    public static void main(String[] args)
 	  throws Exception 
 	{
 		iLibros ORemoto = new sLibros();
 		
 		Naming.bind("Servidor", ORemoto);
 		  
 		System.out.println("Servidor Remoto Preparado");
 	}

    @Override
    public void inserta(String nom, String autor, int id) throws RemoteException {
        biblioteca[cont] = new Libro(nom, autor, id);
        cont++;
    }

    @Override
    public String[] consulta(String nombre) throws RemoteException {
        String res[] = new String[cont];
        int j = 0;
        for(int i = 0; i < cont; i++){
            if(biblioteca[i].nombre.equals(nombre)){
                res[j] = biblioteca[i].nombre;
                j++;
            }
        }
        return res;
    }

    @Override
    public void borra(String nombre) throws RemoteException {
        for(int i = 0; i < cont; i++){
            if(biblioteca[i].nombre.equals(nombre)){
                biblioteca[i].autor = null;
                biblioteca[i].nombre = null;
                biblioteca[i].id = 0;
            }
        }
    }
}
