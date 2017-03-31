import com.jfrogz.sga.domain.Persona;
import com.jfrogz.sga.servicio.PersonaService;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import java.util.List;

public class PersonaServiceTest {

    private PersonaService personaService;
    @Before
    public void setUp () throws Exception {
        EJBContainer contenedor = EJBContainer.createEJBContainer();
        personaService = (PersonaService) contenedor.getContext()
                .lookup("java:global/classes/PersonaServiceImp!com.jfrogz.sga.servicio.PersonaService");
    }

    //@Test
    public void testEJBPersonaService ()
    {
        System.out.println("Iniciando test EJB PersonaService");
        assertTrue(personaService != null);
        assertEquals(3, personaService.listarPersonas().size());
        System.out.println("El no. de personas es igual a: " + personaService.listarPersonas().size());
        this.desplegarPersonas(personaService.listarPersonas());
        System.out.println("Fin del test EJB PersonaService");
    }

    private void desplegarPersonas(List<Persona> personas) {
        for(Persona persona : personas)
        {
            System.out.println(persona);
        }
    }

}
