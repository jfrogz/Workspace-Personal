import com.jfrogz.jdbc.Persona;
import com.jfrogz.jdbc.PersonaDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Fernando Robles on 13/02/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:datasource-test.xml", "classpath:applicationContext.xml"})
public class TestPersonasImpl {
    private static Log logger = LogFactory.getLog("TestPersonaDaoImpl");

    @Autowired
    private PersonaDao personaDao;

    @Test
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
            Assert.assertEquals(contadorPersonas, personaDao.contadorPersonas());
            logger.info("Fin del testDeberiaMostrarPersonas");

        } catch (Exception e) {
            logger.error("Error JBDC", e);
        }
    }

    @Test
    public void testContarPersonasPorNombre() {
        try {
            System.out.println();
            logger.info("Inicio del test Contar Personas por nombre");
            String nombre = "Juan";
            Persona personaEjemplo = new Persona();
            personaEjemplo.setNombre(nombre);
            int noPersonasEncontradas = personaDao.contadorPersonasPorNombre(personaEjemplo);
            logger.info("Número de personas encontradas por nombre " + nombre + ": " + noPersonasEncontradas);
            assertEquals(2, noPersonasEncontradas);
            logger.info("Fin del test Contar Persona por nombre");
        } catch (Exception e) {
            logger.error("Error JDBC", e);
        }
    }

    @Test
    public void testEncontrarPersonaPorId() {
        try {
            System.out.println();
            logger.info("Inicio del test Encontrar Persona por Id");
            int idPersona = 1;
            Persona persona = personaDao.findPersona(idPersona);
            assertEquals("Admin", persona.getApePaterno());
            logger.info("Persona recuperada (id=" + idPersona + "): " + persona);
            logger.info("Fin del test testEncontrarPersonasPorId");
        } catch (Exception e) {
            logger.error("Error JDBC", e);
        }
    }

    @Test
    public void deberiaInsertarPersona() {
        try {
            System.out.println();
            logger.info("Inicio del test deberiamosInsertarPersona");
            //El script de datos tiene 3 registros
            assertEquals(3, personaDao.contadorPersonas());
            Persona persona = new Persona();
            persona.setNombre("Gabriela");
            persona.setApePaterno("Romero");
            persona.setApeMaterno("Lopez");
            persona.setEmail("azulaikoo@gmail.com");
            personaDao.insertPersona(persona);
            //Recuperamos a la persona recien insertada por su email
            persona = personaDao.getPersonaByEmail(persona);
            logger.info("Persona insertada: " + persona);
            //Beberia haber ya cuatro personas
            assertEquals(4, personaDao.contadorPersonas());
            logger.info("Fin del test deberiaInsertarPersona");
        } catch (Exception e) {
            logger.error("Error JDBC", e);
        }
    }

    @Test
    public void debaeriaActualizarPersona() {
        try {
            System.out.println();
            logger.info("Inicio del test deberiaActualizarPersona");
            int idPersona = 1;
            Persona persona = personaDao.findPersona(idPersona);
            logger.info("Persona a modificar {id= " + idPersona + "):" + persona);

            //Actualizamos el nombre y el apellido materno
            persona.setNombre("Administrador");
            persona.setApeMaterno("Sistemas");
            personaDao.updatePersona(persona);

            //volvemos a leer el usuario
            persona = personaDao.findPersona(idPersona);

            //Segun la persona recuperada, deberia ser la misma que el registro 1
            assertEquals("Administrador", persona.getNombre());

            //Imprimimos todo el objeto
            logger.info("Persona modificada (id= " + idPersona
                    + "): " + persona);
            logger.info("Fin del test deberiamosActualizarPersona");
        } catch (Exception e) {
            logger.error("Error JDBC", e);
        }
    }

    @Test
    public void deberiaEliminarPersona() {
        try {
            System.out.println();
            logger.info("Inicio del test deberiaEliminarPersona");
            //Buscamos eliminar la persona con id = 2
            long idPersona = 2;
            Persona persona = personaDao.findPersona(idPersona);
            logger.info("Persona a eliminar (id=" + idPersona + "): " +  persona);

            //Eliminamos la persona recuperada
            personaDao.deletePersona(persona);
            Persona personaDelete = personaDao.findPersona(idPersona);

            //Deberia de regresar nullo al buscar la persona 2
            assertNull(personaDelete);

            //Imprimimos todo el objeto
            logger.info("Nuevo listado de personas:");
            List<Persona> personas = personaDao.findAllPersonas();
            int contadorPersonas =0;
            for (Persona persona2 : personas){
                logger.info("Persona: " + persona2);
                contadorPersonas++;
            }
            //Segun el número de personas recuperadas, deberia ser el mismo de la tabla
            assertEquals(contadorPersonas, personaDao.contadorPersonas());
            logger.info("Fin del test deberiaEliminarPersona");
            personaDao.insertPersona(persona);

        } catch (Exception e) {
            logger.error("Error en JDBC", e);
        }
    }
}
