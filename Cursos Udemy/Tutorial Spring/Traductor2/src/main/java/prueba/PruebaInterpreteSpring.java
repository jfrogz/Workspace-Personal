package prueba;

import beans.Traductor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Fernando Robles on 09/02/2017.
 */
public class PruebaInterpreteSpring {

    public static void main(String[] args) {
        BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
        Traductor traducto1 = (Traductor) factory.getBean("traductor1");
        traducto1.hablar();
        System.out.println("\n");
        Traductor traductor2 = (Traductor) factory.getBean("traductor2");
        traductor2.hablar();



    }
}
