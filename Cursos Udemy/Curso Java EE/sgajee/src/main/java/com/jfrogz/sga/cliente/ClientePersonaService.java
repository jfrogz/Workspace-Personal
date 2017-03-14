package com.jfrogz.sga.cliente;

import com.jfrogz.sga.domain.Persona;
import com.jfrogz.sga.servicio.PersonaServiceRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

public class ClientePersonaService {

    public static void main(String[] args) {
        System.out.println("Iniciando llamada al EJB desde el cliente\n");
        try {
            Context jndi = new InitialContext();
            PersonaServiceRemote personaService = (PersonaServiceRemote) jndi.lookup("java:global/sga-jee-1.0-SNAPSHOT/PersonaServiceImp!com.jfrogz.sga.servicio.PersonaServiceRemote");
            List<Persona> personas = personaService.listarPersonas();
            for(Persona persona : personas){
                System.out.println(persona);
            }
            System.out.println("\nFin llamada al EJB desde el cliente\n");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
