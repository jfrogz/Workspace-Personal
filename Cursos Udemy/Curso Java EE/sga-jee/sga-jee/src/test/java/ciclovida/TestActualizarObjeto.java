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

public class TestActualizarObjeto {

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
	public void testActualizarObjeto() {

		//Paso 1. Inicia transacción 1
		EntityTransaction tx1 = em.getTransaction();
		tx1.begin();

		//Paso 2. Ejecuta SQL de tipo select
		Persona persona1 = em.find(Persona.class, 23);
		
		//Paso 3. Termina transacción 1
		tx1.commit();
		
		//Objeto en estado detached
		log.debug("Objeto recuperado:" + persona1);
		
		//Paso 4. setValue (nuevoValor)
		persona1.setApeMaterno("Nava");
		
		//Paso 5. Incia transacción 2
		EntityTransaction tx2 = em.getTransaction();
		tx2.begin();
		
		//Paso 6. Ejecuta SQL (es un select, pero al estar modificado, al terminar la transacción hará un update)
		//Como ya tenemos el objeto hacemos solo un merge para resincronizar
		em.merge(persona1);
		
		//Paso 7. Termina transacción 2
		//Al momento de hacer commit, se revisan las diferencias entre el objeto de la base de datos
		//y el objeto en memoria, y se aplican los cambios si los hubiese
		tx2.commit();
		
		//Objeto en estado detached ya modificado
		log.debug("Objeto modificado:" + persona1);
	}
	
	@After
    public void tearDown() throws Exception {
        if (em != null) {
            em.close();
        }
    }

}
