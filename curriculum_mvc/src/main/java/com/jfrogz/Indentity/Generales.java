package com.jfrogz.Indentity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component ("sobreMiVar")
public class Generales {

    private String nombreCompleto;
    private String carrera;
    private String sobreMi;
    private boolean escuchandoOfertas;
    private boolean empleado;

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    @Autowired
    private List<Telefono> telefonos;

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
