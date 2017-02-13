package concursantes;

import org.springframework.stereotype.Component;

/**
 * Created by Fernando Robles on 13/02/2017.
 */
@Component
public class Audiencia {
    public void sentarse(){
        System.out.println("El show está por comenzar, favor de tomar asiento...");
    }
    public void apagarCelulares(){
        System.out.println("Favor de apagar celulares...");
    }
    public void aplaudir(){
        System.out.println("El show ha terminado, clap clap clap");
    }
    public void devolucion (){
        System.out.println("El show fue terrible, se devoleran las entradas");
    }
}
