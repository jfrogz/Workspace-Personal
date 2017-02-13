package com.jfrogz.jdbc;

import java.util.List;

/**
 * Created by Fernando Robles on 13/02/2017.
 */
public interface PersonaDao {
    void insertPersona(Persona persona);
    void updatePersona(Persona persona);
    void deletePersona(Persona persona);

    Persona findPersona (long idPersona);
    List<Persona> findAllPersonas();
    int contadorPersonasPorNombre(Persona persona);
    int contadorPersonas();
    Persona getPersonaByEmail(Persona persona);
}
