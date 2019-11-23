package Practicas19.practica7;

import java.util.Date;

public class Conductor {
    int idConductor;
    short nPuntos;
    String nombreConductor, nocionalidadConductor;
    String sexoConductor;
    Date fechaExpedicion, fechaCaducidad;
    String tipoPermiso;
    String lugarNacimiento;
    String autoridadEmisora;

    // constructor(es)
    // gets y set

    public Conductor(int id, short puntos, String nombre, String nacionalidad, String sexo, Date fechaE, Date fechaC,
            String tipo, String nacimiento, String autoridad) {
                this.idConductor = id;
                this.nPuntos = puntos;
                this.nombreConductor = nombre;
                this.nocionalidadConductor = nacionalidad;
                this.sexoConductor = sexo;
                this.fechaExpedicion = fechaE;
                this.fechaCaducidad = fechaC;
                this.tipoPermiso = tipo;
                this.lugarNacimiento = nacimiento;
                this.autoridadEmisora = autoridad;
    }

    public int get_idConductor() {
        return idConductor;
    }

    public void set_idConductor(int id) {
        idConductor = id;
    }

    public short get_nPuntos() {
        return nPuntos;
    }

    public void set_nPuntos(short puntos) {
        nPuntos = puntos;
    }

    public String get_nombreConductor() {
        return nombreConductor;
    }

    public void set_nombreConductor(String nombre) {
        nombreConductor = nombre;
    }

    public String get_nocionalidadConductor() {
        return nocionalidadConductor;
    }

    public void set_nocionalidadConductor(String nacionalidad) {
        nocionalidadConductor = nacionalidad;
    }

    public String get_sexoConductor() {
        return sexoConductor;
    }

    public void set_sexoConductor(String sexo) {
        sexoConductor = sexo;
    }
}