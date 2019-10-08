package Practicas18.practica11;

public class Libro{
	public String nombre,autor;
	public long id;
	
	public Libro(String nombre,String autor,long id){
		this.nombre=nombre;
		this.autor=autor;
		this.id=id;
	}

	public String get_nombre() {
		return nombre;
	}

	public String get_autor(){
		return autor;
	}

	public long get_id(){
		return id;
	}

	public void set_nombre(String str){
		nombre = str;
	}

	public void set_autor(String str){
		autor = str;
	}

	public void set_id(long str){
		id = str;
	}
}