package com.jfrogz.capadatos;

import com.jfrogz.capadatos.domain.Persona;

import java.util.List;

public interface PersonaDao {
    void insertPersona(Persona persona);
    void updatePersona(Persona persona);
    void deletePersona(Persona persona);
    Persona findPersonaById (long idPersona);
    List<Persona> findAllPersonas ();
    long contadorPersonas();
    Persona getPersonabyEmail(Persona persona);
}
