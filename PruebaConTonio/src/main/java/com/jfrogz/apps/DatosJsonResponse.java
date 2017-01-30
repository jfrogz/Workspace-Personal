package com.jfrogz.apps;

import java.util.List;

/**
 * Created by Fernando Robles on 27/01/2017.
 */
public class DatosJsonResponse {

    private String mensaje;
    private List<Frutas> listFrutas;

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    private int valor;

    public List<Frutas> getListFrutas() {
        return listFrutas;
    }

    public void setListFrutas(List<Frutas> listFrutas) {
        this.listFrutas = listFrutas;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
