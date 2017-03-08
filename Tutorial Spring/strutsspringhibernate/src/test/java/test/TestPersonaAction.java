package test;

import org.junit.runner.RunWith;
import servletunit.struts.MockStrutsTestCase;

import java.io.File;


public class TestPersonaAction extends MockStrutsTestCase {

    public void testListar(){
        System.out.println();
        logger.info("Iniciamos test Listar con Struts");
        setRequestPathInfo("/inicio");
        addRequestParameter("metodo", "accionListar");
        actionPerform();
        verifyForward("listar");
        logger.info("Se redirecciono exitosamente");
        verifyNoActionErrors();
        logger.info("Termino test Listar con Struts");
        System.out.println();
    }
}
