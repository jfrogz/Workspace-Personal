package com.jfrogz.capaservicio;

import com.jfrogz.capadatos.PersonaDao;
import com.jfrogz.capadatos.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Fernando Robles on 15/02/2017.
 */
@Service("personaService")
@Transactional
public class PersonaServiceImpl  implements PersonaService{

    @Autowired
    private PersonaDao personaDao;

    public List<Persona> listarPersonas() {
        return personaDao.findAllPersonas();
    }

    public Persona recuperarPersona(Persona persona) {
        return personaDao.findPersonaById(persona.getIdPersona());
    }

    public void agregarPersona(Persona persona) {
        personaDao.insertPersona(persona);
    }

    public void modificarPersona(Persona persona) {
        personaDao.updatePersona(persona);
    }

    public void eliminarPersona(Persona persona) {
        personaDao.deletePersona(persona);
    }
}
