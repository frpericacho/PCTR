package practica11;
import java.rmi.*;
import java.rmi.server.*;
import java.util.concurrent.locks.*;
public class sLibro extends UnicastRemoteObject implements iLibro{

	private static final long serialVersionUID = 1L;
    private Libro[] bd = new Libro[100];
	private final ReentrantLock cerrojo=new ReentrantLock();
	public sLibro() throws RemoteException{}
	
	public void insertar(String[] datos,long id)throws RemoteException{
		cerrojo.lock();
		try{
		int i=0;
		while(bd[i]!=null){
		i++;
		}
		bd[i] = new Libro(datos[0],datos[1], id);
		System.out.println("Libro: "+ bd[i].get_nombre()+" insertado");
		
		}finally{cerrojo.unlock();}
	}
	
	public String consultar(String parametro) throws RemoteException{
			
			int i=0;
			String datos="";
			try{
			while(!(parametro.equals(bd[i].get_nombre())) && !(parametro.equals(bd[i].get_autor())) && !(parametro.equals(bd[i].get_autor())) && !(parametro.equals(Long.toString(bd[i].get_id()))) && bd[i]!=null){
				i++;
			}
			System.out.println("Se ha consultado el libro: "+bd[i].get_nombre());
			datos="El titulo es: "+bd[i].get_nombre()+"\nEl autor es: "+bd[i].get_autor()+"\nEl autor es: "+bd[i].get_autor()+"\nid: "+Long.toString(bd[i].get_id())+"\n";
			}catch(java.lang.NullPointerException e){
			datos="Error Libro no encontrado";
        }
        return datos;
	}

	public void borrar(String nombre) throws RemoteException{
		cerrojo.lock();
			int i=0;
			try{
			while(!(nombre.equals(bd[i].get_nombre()))){
				i++;
			}
			System.out.println("Se ha extraido el libro: "+bd[i].get_nombre());
			bd[i].set_nombre(null);
			bd[i].set_autor(null);
			bd[i].set_autor(null);
			bd[i].set_id(0);
			}catch(java.lang.NullPointerException e){System.out.println("Han inentado borrar un libro que no estaba");}
			finally{cerrojo.unlock();}
		
	}
	public static void main(String []arg)throws Exception{

		iLibro ORemoto=new sLibro();
		Naming.bind("servidor",ORemoto);
		System.out.println("Servidor remoto preparado");
		
		
	}
	
	
}