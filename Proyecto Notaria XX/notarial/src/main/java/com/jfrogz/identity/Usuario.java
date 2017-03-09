package com.jfrogz.identity;

import org.springframework.stereotype.Component;

@Component
public class Usuario {
    public String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
