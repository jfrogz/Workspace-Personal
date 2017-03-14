package com.jfrogz.sga.domain;

import java.io.Serializable;

public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idPersona;
    private String nombre;
    private String apePaerno;
    private String apeMaterno;
    private String telefono;

    public Persona() {

    }

    public Persona(int idPersona, String nombre, String apePaerno, String apeMaterno, String telefono) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apePaerno = apePaerno;
        this.apeMaterno = apeMaterno;
        this.telefono = telefono;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApePaerno() {
        return apePaerno;
    }

    public void setApePaerno(String apePaerno) {
        this.apePaerno = apePaerno;
    }

    public String getApeMaterno() {
        return apeMaterno;
    }

    public void setApeMaterno(String apeMaterno) {
        this.apeMaterno = apeMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "idPersona=" + idPersona +
                ", nombre='" + nombre + '\'' +
                ", apePaerno='" + apePaerno + '\'' +
                ", apeMaterno='" + apeMaterno + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
