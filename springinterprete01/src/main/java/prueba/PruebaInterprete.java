package prueba;

import beans.InterpreteEspanol;
import beans.Traductor;

/**
 * Created by Fernando on 08/02/2017.
 */
public class PruebaInterprete {
    public static void main(String[] args) {
        Traductor traductor = new Traductor();
        InterpreteEspanol interprete = new InterpreteEspanol();


        traductor.setInterprete(interprete);
        traductor.setNombre("Fernando Robles");
        traductor.hablar();
    }
}
