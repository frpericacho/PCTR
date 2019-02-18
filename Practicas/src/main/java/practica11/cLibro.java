package practica11;
import java.rmi.*;
import java.util.*;

public class cLibro{

	public static void main(String []arg)throws Exception{
	
		Scanner sc = new Scanner(System.in);
		iLibro RefORemoto=(iLibro)Naming.lookup("//localhost/servidor");
		int op=10;
		
		while(op!=0){
			System.out.print("Menu\n1.Insertar libro\n2.Consultar libro\n3.Extraer libro\n0.salir\n");
			op=sc.nextInt();
			switch(op){
			case 1:
				String[] datos=new String[3];
				long isbn;
				System.out.println("Introduzca el nombre:");
				datos[0]=sc.nextLine();
				datos[0]=sc.nextLine();
				System.out.println("Introduzca el autor:");
				datos[1]=sc.nextLine();
				System.out.println("Introduzca la editorial:");
				datos[2]=sc.nextLine();
				System.out.println("Introduzca el isbn:");
				isbn=sc.nextLong();
				RefORemoto.insertar(datos,isbn);
				
				break;
			case 2:
				String parametro;
			
				System.out.println("Introduzca un parametro para buscar el libro");
				parametro=sc.nextLine();
				parametro=sc.nextLine();
				System.out.println(RefORemoto.consultar(parametro));
				
				
				break;
			case 3:
				String nombre;
				System.out.println("Introduzca el nombre del libro a extraer");
				nombre=sc.nextLine();
				nombre=sc.nextLine();
				RefORemoto.borrar(nombre);
				
				break;
			}
		}
		sc.close();	
	}
}