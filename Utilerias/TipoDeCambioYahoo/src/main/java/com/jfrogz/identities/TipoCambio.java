package com.jfrogz.identities;

public class TipoCambio {
    private String deUnidad;
    private String aUnidad;
    private String valor;

    public String getDeUnidad() {
        return deUnidad;
    }

    public void setDeUnidad(String deUnidad) {
        this.deUnidad = deUnidad;
    }

    public String getaUnidad() {
        return aUnidad;
    }

    public void setaUnidad(String aUnidad) {
        this.aUnidad = aUnidad;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "TipoCambio{" +
                "deUnidad='" + deUnidad + '\'' +
                ", aUnidad='" + aUnidad + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }
}
