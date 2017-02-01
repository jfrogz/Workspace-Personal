package Calculadora;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Fernando Robles on 31/01/2017.
 */
public class CalculadoraTest {
    @Test
    public void testSuma() throws Exception {
        int valorA=1, valorB=5, esperado=6;
        int resultado = Calculadora.suma(valorA, valorB);
        assertEquals(esperado, resultado);
    }

    @Test
    public void TestResta() throws Exception {
        int valorA=1, valorB=5, esperado=-4;
        int resultado = Calculadora.resta(valorA, valorB);
        assertEquals(esperado, resultado);

        assert
    }

}