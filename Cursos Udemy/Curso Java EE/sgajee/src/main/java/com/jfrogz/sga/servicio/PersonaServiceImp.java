package com.jfrogz.sga.servicio;

import com.jfrogz.sga.domain.Persona;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PersonaServiceImp implements PersonaServiceRemote {

    public List<Persona> listarPersonas() {
        List<Persona> personas = new ArrayList<Persona>();
        personas.add(new Persona(1, "Fernando", "Robles", "Guzmán", "sr.frog@hotmail.com", "2225661166"));
        personas.add(new Persona(2, "Fernando", "Robles", "Dosamantes","adair@yahoo.com", "22256639"));
        personas.add(new Persona(3, "Jennifer", "Robles", "Dosamantes", "eileen@esmas.com","22234361166"));
        return personas;
    }

    public Persona encontrarPersonaPorId(Persona persona) {

        return null;
    }

    public Persona entontrarPersonaPorEmail(Persona persona) {

        return null;
    }

    public void registrarPersona(Persona persona) {

    }

    public void modificarPersona(Persona persona) {

    }

    public void eliminarPersona(Persona persona) {

    }
}
