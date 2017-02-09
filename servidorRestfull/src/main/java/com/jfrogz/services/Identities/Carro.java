package com.jfrogz.services.Identities;

import com.jfrogz.services.Request.CarroRequest;
import com.jfrogz.services.response.CarroResponse;

/**
 * Created by Fernando Robles on 02/02/2017.
 */
public abstract class Carro {

    private String fabricante;
    private String modelo;
    private String anio;

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

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }
}
