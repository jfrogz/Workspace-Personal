package ciclovida;

import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import mx.com.gm.sga.domain.Persona;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class TestEncontrarObjeto {
	
	static EntityManager em = null;
	static EntityTransaction tx = null;
	static EntityManagerFactory emf = null;
	Logger log = Logger.getLogger("TestEncontarObjeto");

	@BeforeClass
	public static void init() throws Exception {
		EJBContainer.createEJBContainer();
		emf = Persistence.createEntityManagerFactory("PersonaPU");
	}

	@Before
	public void setup() {
		try {
			em = emf.createEntityManager();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	//@Test
	public void testEncontrarObjeto() {

		//Paso 1. Inicia transacción
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		//Paso 2. Ejecuta SQL de tipo select
		Persona persona1 = em.find(Persona.class, 23);
		
		//Paso 3. Termina transacción
		tx.commit();
		
		//Objeto en estado detached
		log.debug("Objeto recuperado:" + persona1);
	}
	
	@After
    public void tearDown() throws Exception {
        if (em != null) {
            em.close();
        }
    }

}
