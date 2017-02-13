package test;

import concursantes.Adivinador;
import concursantes.Pensador;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestMago {
    public static Log logger = LogFactory.getLog("TestMago");
    @Autowired
    private Pensador voluntario;
    @Autowired
    private Adivinador mago;

    @Test
    public void testMagoAdivinador(){
        logger.info("Inicio de la adivinacion");
        voluntario.pensarEnAlgo("\"Hoy ganaré la loteria\"");
        assertEquals("\"Hoy ganaré la loteria\"", mago.getPensamiento());
        logger.info("Fin de adivinacion");
    }
}
