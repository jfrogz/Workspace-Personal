package test;


import com.jfrogz.capadatos.PersonaDao;
import com.jfrogz.capadatos.domain.Persona;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:datasource-test.xml", "classpath:applicationContext.xml"})
public class TestPersonaDaoImpl {
    private static Log logger = LogFactory.getLog(TestPersonaDaoImpl.class);
    @Autowired
    private PersonaDao personaDao;

    @Test
    @Transactional
    public void deberiaMostrarPersonas() {
        try {
            System.out.println();
            logger.info("Inicio del test deberiaMostrarPersonas");
            List<Persona> personas = personaDao.findAllPersonas();
            int contadorPersonas = 0;
            for (Persona persona : personas) {
                logger.info("Persona: " + persona);
                contadorPersonas++;
            }
            //Segun el número de personas recuperadas, deberia de ser el mismo de la tabla
            assertEquals(contadorPersonas, personaDao.contadorPersonas());
            logger.info("Fin del test deberiaMostrarPersonas");
        } catch (Exception e) {
            logger.error("Error JDBC", e);
        }
    }

    @Test
    @Transactional
    public void deberiaEncontrarPersonaPorId() {
        try {
            System.out.println();
            logger.info("Inicio del test deberiaEncontrarPersonaPorId");
            int idPersona = 1;
            Persona persona = personaDao.findPersonaById(idPersona);
            // Segun la persona recuperada, deberia se la misma que en el registro
            assertEquals("Admin", persona.getNombre());
            //Imprimimostodo el objeto
            logger.info("Persona recuperada (id=" + idPersona + ") " + persona);
            logger.info("Fin del test deberiamosEncontrarPersonaPorId");
        } catch (Exception e) {
            logger.error("Error en JDBC", e);
        }
    }

    @Test
    @Transactional
    public void deberiaInsertarPersona() {
        try {
            System.out.println();
            logger.info("Inicio del test deberiaInsertarPersona");
            //El script de datos tiene 3 registros
            assertEquals(3, personaDao.contadorPersonas());
            Persona persona = new Persona();
            persona.setNombre("Carlos");
            persona.setApePaterno("Romero");
            persona.setApeMaterno("Esparza");
            persona.setEmail("carlos.romero@gmail.com");
            personaDao.insertPersona(persona);

            //Recuperamos a la persona recien insertada por su email
            persona = personaDao.getPersonabyEmail(persona);
            logger.info("Persona insertada: " +  persona);
            //Deberia de haber ya cuatro personas
            logger.info("Fin del test deberiaInsertaPersona");
        } catch (Exception e) {
            logger.error("Error en JDBC", e);
        }
    }

}
