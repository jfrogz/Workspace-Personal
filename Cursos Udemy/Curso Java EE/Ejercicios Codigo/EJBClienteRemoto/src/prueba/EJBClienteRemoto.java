package prueba;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import mx.com.gm.sga.domain.Persona;
import mx.com.gm.sga.servicio.PersonaServiceRemote;

public class EJBClienteRemoto {

	/**
	 * @param args
	 * Documentacion de las librerias:
	 * http://stackoverflow.com/questions/11436024/standalone-java-glasshfish-client-reference-to-gf-client-jar
	 * Por el momento, se debe tener descargado e instalado glassfish en el equipo cliente, pero hay maneras de agregar 
	 * solo las dependencias que se necesitan. Glassfish no debe levantarse, solo se toman las librerías respectivas
	 * 
	 * Por otro lado se agrego el proyecto sga-jee.jar desplegado en el servidor de Glassfish como parte de las dependencias del proyecto
	 * http://screencast.com/t/tROiStXd7
	 * 
	 * El jar del proyecto remoto (sga-jee.jar) que se agregó aquí únicamente es para que compile el proyecto, pero los cambios que se realicen,
	 * por ejemplo en el listado de personas, se debe hacer en el servidor, y no es necesario actualizar el .jar de este proyecto, ya que la llamada
	 * se realiza sobre el servidor remoto que se está ejecutando sobre glassfish, no sobre este .jar local.
	 */
	public static void main(String[] args) {
		System.out.println("Iniciando llamada al EJB desde el cliente\n");
		try {
			Properties props = new Properties();

			props.setProperty("java.naming.factory.initial","com.sun.enterprise.naming.SerialInitContextFactory");
			props.setProperty("java.naming.factory.url.pkgs","com.sun.enterprise.naming");
			props.setProperty("java.naming.factory.state","com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");

			// optional. Default localhost. Aqui se cambia la IP
			props.setProperty("org.omg.CORBA.ORBInitialHost","192.168.100.3");

			// optional. Puerto por Default 3700. Solo se necesita cambiar si el puerto no es 3700.
			//props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");

			Context jndi = new InitialContext(props);
			PersonaServiceRemote personaService = (PersonaServiceRemote) jndi.lookup("java:global/sga-jee/PersonaServiceImplementation!mx.com.gm.sga.servicio.PersonaServiceRemote");
			List<Persona> personas = personaService.listarPersonas();
			for (Persona persona : personas) {
				System.out.println(persona);
			}
			System.out.println("Fin Llamada");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
