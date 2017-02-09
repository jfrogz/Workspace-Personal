package test;

import concursantes.Concursante;
import concursantes.Malabarista;
import concursantes.Musico;
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
    private Concursante malabarista2;
    private Concursante musico1;
    private Concursante musico2;

    @Before
    public void before() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        malabarista1 = (Concursante) ctx.getBean("solei");
        malabarista2 = (Concursante) ctx.getBean("soleiRecitador");
        musico1 = (Concursante) ctx.getBean("jasonPiano");
        musico2 = (Concursante) ctx.getBean("jasonSax");
    }

    @Test
    public void TestMalabarista() {
        logger.info("Inicio de ejecutar Malabarista");
        int noPelotas = 10;
        malabarista1.ejecutar();
        assertEquals(noPelotas, ((Malabarista)malabarista1).getPelotas());
        logger.info("Fin de Ejecutar Malabarista");

        logger.info("Inicio de ejecutar MalabaristaRecitador");
        noPelotas = 15;
        malabarista2.ejecutar();
        assertEquals(noPelotas, ((Malabarista)malabarista2).getPelotas());
        logger.info("Fin de Ejecutar MalabaristaRecitador");

        logger.info("Inicio de ejecutar Pianista");
        String cancion = "Noche de paz";
        musico1.ejecutar();
        assertEquals(cancion, ((Musico)musico1).getCancion());
        logger.info("Fin de Ejecutar Pianista");

        logger.info("Inicio de ejecutar Saxofonista");
        cancion = "Equinox";
        musico2.ejecutar();;
        assertEquals(cancion, ((Musico)musico2).getCancion());
        logger.info("Fin de Ejecutar Saxofonista");
    }
}
