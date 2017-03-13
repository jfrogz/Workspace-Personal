package com.jfrogz.servicio;

import com.jfrogz.jdbc.Persona;

import java.util.List;

/**
 * Created by Fernando Robles on 14/02/2017.
 */
public interface PersonaService {
    public List<Persona> listarPersonas();

    public Persona recuperarPersona(Persona persona);

    public void agregarPersona(Persona persona);

    public void modificarPersona(Persona persona);

    public void eliminarPersona(Persona persona);

}
