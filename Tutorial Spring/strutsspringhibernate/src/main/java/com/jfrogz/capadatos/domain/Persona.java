package com.jfrogz.capadatos.domain;

import javax.persistence.*;

/**
 * Created by Fernando Robles on 14/02/2017.
 */
@Entity
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private long idPersona;
    private String nombre;
    @Column(name = "ape_paterno")
    private String apePaterno;
    @Column(name = "ape_materno")
    private String apeMaterno;
    private String email;

    public Persona() {
    }

    public Persona(long idPersona) {
        this.idPersona = idPersona;
    }


}
