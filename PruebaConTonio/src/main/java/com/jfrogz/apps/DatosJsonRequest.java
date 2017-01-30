package com.jfrogz.apps;

import javax.ws.rs.Path;
import java.util.List;

public class DatosJsonRequest {

    private String mensaje;
    private List<Frutas> listFrutas;
    private Parametro parametro;

    public Parametro getParametro() {
        return parametro;
    }

    public void setParametro(Parametro parametro1) {
        this.parametro = parametro1;
    }

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
