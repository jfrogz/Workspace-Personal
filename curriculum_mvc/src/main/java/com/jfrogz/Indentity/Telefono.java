package com.jfrogz.Indentity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Fernando Robles on 28/02/2017.
 */
@Component
@Scope(value = "prototype")
public class Telefono {
   private String numero;
   private String descripcion;
   private String nombreIcon;

    public String getNombreIcon() {
        return nombreIcon;
    }

    public void setNombreIcon(String nombreIcon) {
        this.nombreIcon = nombreIcon;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
