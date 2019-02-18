package ejercicios_para_practicar;
public class Libro{
	public String nombre,autor,editorial;
	public int isbn;
	
	public Libro(String nombre,String autor,String editorial,int isbn){
		this.nombre=nombre;
		this.autor=autor;
		this.editorial=editorial;
		this.isbn=isbn;
	}

	public String get_nombre() {
		return nombre;
	}

	public String get_autor() {
		return autor;
	}

	public String get_editorial() {
		return editorial;
	}

	public long get_isbn() {
		return isbn;
	}

	public void set_nombre(String s) {
		nombre = s;
	}

	public void set_autor(String s) {
		autor = s;
	}

	public void set_editorial(String s) {
		editorial = s;
	}

	public void set_isbn(int i) {
		isbn = i;
	}
}