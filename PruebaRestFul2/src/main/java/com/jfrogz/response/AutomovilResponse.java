package com.jfrogz.response;

/**
 * Created by Fernando Robles on 30/01/2017.
 */
public class AutomovilResponse {
    private String fabricante;


    private String modelo;
    private int anio;

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
}
