package com.jfrogz.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fernando on 28/01/2017.
 */
public class ListaFrutasResponse {

    private List<String> listaFrutas = new ArrayList<String>();

    public List<String> getListaFrutas() {
        listaFrutas.add("Manzana");
        listaFrutas.add("Pera");
        listaFrutas.add("Guayaba");
        listaFrutas.add("Ciruela");
        return listaFrutas;
    }

    public void setListaFrutas(List<String> listaFrutas) {
        this.listaFrutas = listaFrutas;
    }
}
