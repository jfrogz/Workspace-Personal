import com.jfrogz.jdbc.Persona;
import com.jfrogz.jdbc.PersonaDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
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
    public void deberiaMostrarPersonas(){
        try{
            System.out.println();
            logger.info("Inicio del test deberiaMostrarPersonas");
            List<Persona> personas = personaDao.findAllPersonas();
            int contadorPersonas = 0;
            for (Persona persona : personas){
                logger.info("Persona: " + persona);
                contadorPersonas++;
            }
            Assert.assertEquals(contadorPersonas, personaDao.contadorPersonas());
            logger.info("Fin del testDeberiaMostrarPersonas");

        }catch(Exception e){
            logger.error("Error JBDC", e);
        }
    }
}
