package practica8;
import java.util.Objects;

public class Conductor{
    private String nombre,dni,matricula,marca;

    public Conductor(String nombre,String dni,String matricula,String marca){
        this.nombre = nombre;
        this.dni = dni;
        this.matricula = matricula;
        this.marca = marca;
    } 
    
    public void pon_nombre(String nombre2){
        nombre = nombre2;
    }
    public String da_nombre(){
        return nombre;
    }

    public void pon_dni(String dni2){
        dni = dni2;
    }
    public String da_dni(){
        return dni;
    }

    public void pon_matricula(String matricula2){
        matricula = matricula2;
    }
    public String da_matricula(){
        return matricula;
    }

    public void pon_marca(String marca2){
        marca = marca2;
    }
    public String da_marca(){
        return marca;
    }

    public String toString(){
        return String.format("{ nombre %s, dni %s, matricula %s, marca %s }", nombre,dni,marca,matricula);
    }

    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(!(o instanceof Conductor)){
            return false;
        }
        Conductor conductor = (Conductor) o;
        return Objects.equals(nombre, conductor.nombre) && Objects.equals(dni, conductor.dni) && Objects.equals(marca, conductor.marca) && Objects.equals(matricula, conductor.matricula);
    }

    public int hashCode(){
        return Objects.hash(nombre,dni,marca,matricula);
    }

}