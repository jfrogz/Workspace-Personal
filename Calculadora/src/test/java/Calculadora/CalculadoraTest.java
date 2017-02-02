package Calculadora;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Create
 * */

@RunWith(value = Parameterized.class)
public class CalculadoraTest {
    enum TypeOperation {SUBSTRACT, ADD};
    static Calculadora calculadora = null;
    private int a, b, exp;
    private TypeOperation type;

    @Parameterized.Parameters
    public static Iterable<Object[]> getData(){

        List<Object[]> obj = new ArrayList();
        obj.add(new Object[] {TypeOperation.ADD, 3,1,4});
        obj.add(new Object[] {TypeOperation.ADD, 7,3,10});
        obj.add(new Object[] {TypeOperation.ADD, 15,10,25});
        obj.add(new Object[] {TypeOperation.SUBSTRACT, 3,1,2});
        obj.add(new Object[] {TypeOperation.SUBSTRACT, 7,3,4});
        obj.add(new Object[] {TypeOperation.SUBSTRACT, 15,20,-5});
        return obj;
    }

    public CalculadoraTest (TypeOperation type, int a, int b, int exp){
        this.type = type;
        this.a = a;
        this.b = b;
        this.exp = exp;
    }
    @BeforeClass
    public static void inicializacionClass ()
    {
         calculadora = new Calculadora();
    }


    @Test
    public void testSuma() {
        Assume.assumeThat(type, is(TypeOperation.ADD));
        int resultado = calculadora.suma(a,b);
        assertEquals(exp, resultado);
    }

    @Test
    public void TestResta() {
        Assume.assumeThat(type, is(TypeOperation.SUBSTRACT));
        int resultado = calculadora.resta(a,b);
        assertEquals(exp, resultado);

    }

    @Test ()
    public void TestResta2(){
        for (int i = 0; i < 5; i++) {
            assertTrue(true);
        }

    }

}