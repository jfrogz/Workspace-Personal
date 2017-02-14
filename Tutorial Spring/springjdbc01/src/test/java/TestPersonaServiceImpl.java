import com.jfrogz.jdbc.Persona;
import com.jfrogz.jdbc.PersonaDao;
import com.jfrogz.servicio.PersonaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Fernando Robles on 14/02/2017.
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
    public void deberiaMostrarPersonas() {
        try {
            System.out.println();
            logger.info("Inicio del test deberiaMostrarPersonas");
            int contadorPersonas = this.desplegarPersonas();

            //Segun el númeroo de personas recuperadas, deberia ser el mismo de la tabla
            assertEquals(contadorPersonas, personaDao.contadorPersonas());
            logger.info("Fin del test deberiaMostrarPersonas");
            System.out.println();
        } catch (Exception e) {
            logger.error("Error Servicio: " + e);
        }
    }

    private int desplegarPersonas() {
        List<Persona> personas = personaService.listarPersonas();
        int contadorPersonas = 0;
        for (Persona persona : personas) {
            logger.info("Persona: " + persona);
            contadorPersonas++;
        }
        return contadorPersonas;
    }


}
