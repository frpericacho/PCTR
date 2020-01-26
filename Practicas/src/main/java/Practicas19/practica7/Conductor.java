
import java.util.Date;

public class Conductor {
    int idConductor;
    short nPuntos;
    String nombreConductor, nocionalidadConductor;
    String sexoConductor;
    String fechaExpedicion, fechaCaducidad;
    String tipoPermiso;
    String lugarNacimiento;
    String autoridadEmisora;

    public Conductor(){
        
    }

    public Conductor(int id, int i, String nombre, String nacionalidad, String sexo, String string, String string2,
            String tipo, String nacimiento, String autoridad) {
        this.idConductor = id;
        this.nPuntos = (short) i;
        this.nombreConductor = nombre;
        this.nocionalidadConductor = nacionalidad;
        this.sexoConductor = sexo;
        this.fechaExpedicion = string;
        this.fechaCaducidad = string2;
        this.tipoPermiso = tipo;
        this.lugarNacimiento = nacimiento;
        this.autoridadEmisora = autoridad;
    }

	public int get_idConductor() {
        return idConductor;
    }

    public void set_idConductor(int id) {
        this.idConductor = id;
    }

    public short get_nPuntos() {
        return nPuntos;
    }

    public void set_nPuntos(short puntos) {
        this.nPuntos = puntos;
    }

    public String get_nombreConductor() {
        return nombreConductor;
    }

    public void set_nombreConductor(String nombre) {
        this.nombreConductor = nombre;
    }

    public String get_nocionalidadConductor() {
        return nocionalidadConductor;
    }

    public void set_nocionalidadConductor(String nacionalidad) {
        this.nocionalidadConductor = nacionalidad;
    }

    public String get_sexoConductor() {
        return sexoConductor;
    }

    public void set_sexoConductor(String sexo) {
        this.sexoConductor = sexo;
    }
}