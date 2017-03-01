package com.jfrogz.Indentity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("sobreMiVar")
public class Generales {
    private String pathFotoPerfil;
    private String nombreCompleto;
    private String carrera;
    private String sobreMi;
    private boolean escuchandoOfertas;
    private boolean empleado;
    @Autowired
    private List<Telefono> telefonos;
    @Autowired
    private List<Trabajo> trabajos;

    public String getPathFotoPerfil() {
        return pathFotoPerfil;
    }

    public void setPathFotoPerfil(String pathFotoPerfil) {
        this.pathFotoPerfil = pathFotoPerfil;
    }

    public List<Trabajo> getTrabajos() {
        return trabajos;
    }

    public void setTrabajos(List<Trabajo> trabajos) {
        this.trabajos = trabajos;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public boolean isEmpleado() {
        return empleado;
    }

    public void setEmpleado(boolean empleado) {
        this.empleado = empleado;
    }

    public boolean isEscuchandoOfertas() {
        return escuchandoOfertas;
    }

    public void setEscuchandoOfertas(boolean escuchandoOfertas) {
        this.escuchandoOfertas = escuchandoOfertas;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getSobreMi() {

        return sobreMi;
    }

    public void setSobreMi(String sobreMi) {
        this.sobreMi = sobreMi;
    }
}
