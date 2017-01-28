package com.jfrogz.app;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Fernando on 26/01/2017.
 */
@XmlRootElement

public class DatosPrueba {

    @XmlElement
    public String nombre;

    @Override
    public String toString() {
        return "DatosPrueba{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
