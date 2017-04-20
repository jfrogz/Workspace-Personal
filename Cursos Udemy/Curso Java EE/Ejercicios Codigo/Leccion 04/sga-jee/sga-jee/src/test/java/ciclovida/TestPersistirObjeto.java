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

public class TestPersistirObjeto {

	static EntityManager em = null;
	static EntityTransaction tx = null;
	static EntityManagerFactory emf = null;
	Logger log = Logger.getLogger("TestPersistirObjeto");

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
	public void testPersistirObjeto() {

		//Paso 1. Crea nuevo objeto
		//Objeto en estado transitivo
		Persona persona1 = new Persona("Pedro","Luna",null,"pluna@mail.com","19292943");
		
		//Paso 2. Inicia transacción
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		//Paso 3. Ejecuta SQL de tipo insert
		em.persist(persona1);

		//Paso 4. Commit/rollback
		tx.commit();
		
		//Objeto en estado detached
		log.debug("Objeto persistido:" + persona1);
	}
	
	@After
    public void tearDown() throws Exception {
        if (em != null) {
            em.close();
        }
    }
}