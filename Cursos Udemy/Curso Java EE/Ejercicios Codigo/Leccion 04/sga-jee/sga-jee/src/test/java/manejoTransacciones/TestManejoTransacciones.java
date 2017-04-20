package manejoTransacciones;

import static org.junit.Assert.assertTrue;

import javax.ejb.embeddable.EJBContainer;

import mx.com.gm.sga.domain.Persona;
import mx.com.gm.sga.servicio.PersonaService;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TestManejoTransacciones {

	private PersonaService personaService;
	Logger log = Logger.getLogger("TestManejoTransacciones");
	

	@Before
	public void setUp() throws Exception {
		EJBContainer contenedor = EJBContainer.createEJBContainer();
		personaService = (PersonaService) contenedor.getContext().lookup("java:global/classes/PersonaServiceImpl!mx.com.gm.sga.servicio.PersonaService");
	}

	//@Test
	public void testEJBPersonaService() {
		log.debug("Iniciando test Manejo Transaccional PersonaService");
		
		assertTrue(personaService != null);
		
		//Buscamos un objeto persona
		Persona persona1 = personaService.encontrarPersonaPorId(new Persona(1));
		
		//Cambiamos la persona
		//persona1.setApeMaterno("Cambio con error....................................................................");
		persona1.setApeMaterno("Aguilar");
		
		this.personaService.modificarPersona(persona1);
		
		log.debug("Objeto modificado:" + persona1);
		
		log.debug("Fin test EJB PersonaService");
	}
	
}
