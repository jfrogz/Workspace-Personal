/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Identities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.StringPropertyBase;

/**
 *
 * @author fernandorobles
 */
public class Responsable {
    
    private int responsable;
    private StringProperty nombre;

    public int getResponsable() {
        return responsable;
    }

    public void setResponsable(int responsable) {
        this.responsable = responsable;
    }

    public String getNombre() {
        return nombre.get();
    }
    
    public StringProperty getNombreProperty ()
    {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = new SimpleStringProperty(nombre);
    }
    
    public String toString ()
    {
        return nombre.get();
    }
}
