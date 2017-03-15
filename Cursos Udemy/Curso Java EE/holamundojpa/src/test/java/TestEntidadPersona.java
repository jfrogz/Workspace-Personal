
import beans.dominio.Persona;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.*;


/**
 * Created by Fernando Robles on 15/03/2017.
 */
public class TestEntidadPersona {
    static EntityManager em = null;
    static EntityTransaction tx = null;
    static EntityManagerFactory emf = null;
    Logger log = Logger.getLogger(TestEntidadPersona.class.getName());

    @BeforeClass
    public static void init() throws Exception {
        emf = Persistence.createEntityManagerFactory("PersonaPU");
    }

    @Before
    public void setup (){
        try{
            em = emf.createEntityManager();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testPersonaEntity ()
    {
        log.debug("Iniciando Test Persona Entity con JPA");
        assertTrue(em != null);
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Persona persona1 = new Persona("Angelica", "Rodriguez", "Baltazar", "angelica@gmail.com", "5555854582");
        log.debug("Objeto a persistir: " +  persona1);
        em.persist(persona1);
        tx.commit();
        log.debug("Objeto persistido: " + persona1);
        log.debug("Fin Test Persona Entity con JPA");
    }
}
