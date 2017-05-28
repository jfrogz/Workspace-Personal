/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Identities;

/**
 *
 * @author fernandorobles
 */
public class Expediente {
    
    private int instrumento;
    private int volumen;
    private String nombre;
    private String operacion;
    private Responsable responsable;

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public int getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(int instrumento) {
        this.instrumento = instrumento;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString ()
    {
        return instrumento +"";
    }
    
    
}
