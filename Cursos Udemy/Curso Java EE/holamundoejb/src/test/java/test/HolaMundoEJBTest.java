package test;

import beans.HolaMundoEJB;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

public class HolaMundoEJBTest {
    private static EJBContainer  contenedor;
    private static Context contexto;
    private static HolaMundoEJB ejb;

    @Before
    public void iniciarContenedor () throws Exception{
        System.out.println("Iniciamos EJB Container");
        contenedor = EJBContainer.createEJBContainer();
        contexto = contenedor.getContext();
        ejb = (HolaMundoEJB) contexto.lookup("java:global/classes/HolaMundoEJB!beans.HolaMundoEJB");
    }

    @Test
    public void testAddNumbers() throws Exception {
        int dato1 = 3;
        int dato2 = 5;
        int resultado = ejb.sumar(dato1, dato2);
        Assert.assertEquals((dato1+dato2), resultado);
        System.out.println("Operación terminada, resultado " + resultado);
    }
}
