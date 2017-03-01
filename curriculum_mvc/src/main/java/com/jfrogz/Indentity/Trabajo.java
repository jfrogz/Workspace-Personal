package com.jfrogz.Indentity;

import org.springframework.stereotype.Component;

/**
 * Created by Fernando on 28/02/2017.
 */
@Component
public class Trabajo {
    private String empresa;
    private String puesto;
    private String actividades;
    private String ubicacionImagen;
    private String fechaInicial;
    private String fechaFinal;

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public String getUbicacionImagen() {
        return ubicacionImagen;
    }

    public void setUbicacionImagen(String ubicacionImagen) {
        this.ubicacionImagen = ubicacionImagen;
    }
}
