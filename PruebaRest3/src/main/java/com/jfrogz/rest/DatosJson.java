package com.jfrogz.rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DatosJson {

    @XmlElement
    public String mensaje;
}
