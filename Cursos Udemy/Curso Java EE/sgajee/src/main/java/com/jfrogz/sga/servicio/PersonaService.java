package com.jfrogz.sga.servicio;

import com.jfrogz.sga.domain.Persona;

import javax.ejb.Local;
import java.util.List;

@Local
public  interface PersonaService {
    public List<Persona> listarPersonas();
    public Persona encontrarPersonaPorId(Persona persona);
    public Persona entontrarPersonaPorEmail (Persona persona);
    public void registrarPersona(Persona persona);
    public void modificarPersona(Persona persona);
    public void eliminarPersona(Persona persona);

}