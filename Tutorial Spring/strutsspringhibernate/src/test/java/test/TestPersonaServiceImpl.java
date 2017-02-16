package test;

import com.jfrogz.capadatos.PersonaDao;
import com.jfrogz.capadatos.domain.Persona;
import com.jfrogz.capaservicio.PersonaService;
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

/**
 * Created by Fernando Robles on 15/02/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:datasource-test.xml"})
public class TestPersonaServiceImpl {
    private static Log logger = LogFactory.getLog(TestPersonaServiceImpl.class);

    @Autowired
    private PersonaService personaService;

    @Autowired
    private PersonaDao personaDao;

    @Test
    @Transactional
    public  void deberiaMostrarPersonas(){
        try{
            logger.info("\nInicio del test deberiaMostrarPersonas");
            int contadorPersonas = this.desplegarPersonas();
            //Segun el número de personas recuperadas,
            //deberia ser el mismo de la tabla
            assertEquals(contadorPersonas, personaDao.contadorPersonas());
            logger.info("Fin del test deberiaMostrarPersonas\n");
        }catch (Exception e){
            logger.error("Error en Servicio:", e);
        }
    }

    private int desplegarPersonas(){
        List<Persona> personas = personaService.listarPersonas();
        int contadorPersonas =0;
        for (Persona persona : personas){
            logger.info("Persona: " + persona );
            contadorPersonas++;
        }
        return contadorPersonas;
    }

    @Test
    @Transactional
    public  void testOperaciones(){
        try{
            logger.info("\nInicio del test testOperaciones");
            Persona persona1 = new Persona();
            persona1.setNombre("Andrea");
            persona1.setApePaterno("Lara");
            persona1.setEmail("andrea.lara@ymail.com");
            personaService.agregarPersona(persona1);
            //Deberia haber 4 personas
            assertEquals(4, personaDao.contadorPersonas());

            //Actualizamos la persona con id=1
            Persona persona2 = personaService.recuperarPersona(new Persona(1));
            persona2.setNombre("Administrador");
            personaService.modificarPersona(persona2);
            this.desplegarPersonas();
            logger.info("Fin del test testOperaciones\n");
        }catch (Exception e){
            logger.error("Error en Servicio:", e);
        }
    }

    @Test
    @Transactional
    public  void testCompruebaOperaciones(){
        try{
            logger.info("\nInicio del test testCompruebaOperaciones");
            //Deberia haber 3 personas, en el caso anterior aplico rollback por
            //el uso de AbstractTransactionalJUnit4SpringContextTests
            assertEquals(3, personaDao.contadorPersonas());
            this.desplegarPersonas();
            logger.info("Fin del test testCompruebaOperaciones\n");
        }catch (Exception e){
            logger.error("Error en Servicio:", e);
        }
    }

}
