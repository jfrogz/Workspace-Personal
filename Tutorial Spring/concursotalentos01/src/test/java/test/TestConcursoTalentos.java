package test;

import concursantes.Concursante;
import concursantes.Malabarista;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Fernando Robles on 09/02/2017.
 */
public class TestConcursoTalentos {
    private static Log logger = LogFactory.getLog("TestConcursoTalentos");
    private Concursante malabarista1;

    @Before
    public void before() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        malabarista1 = (Concursante) ctx.getBean("solei");
    }

    @Test
    public void TestMalabarista() {
        logger.info("Inicio de ejecutar Malabarista");
        int noPelotas = 5;
        malabarista1.ejecutar();
        assertEquals(noPelotas, ((Malabarista)malabarista1).getPelotas());
        logger.info("Fin de Ejecutar Malabarista");
    }
}
