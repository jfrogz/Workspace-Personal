package concursantes;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * Created by Fernando Robles on 13/02/2017.
 */
@Component
public class Audiencia {
    /*
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
    }*/

    public void monitorearShow(ProceedingJoinPoint joinPoint){
        try{
            System.out.println("El show esta por comenzar, favor de tomar asiento...");
            //Anotamos la hora de inicio
            long horaInicio = System.currentTimeMillis();
            //Se llama al metodo de negocio (método objetivo)
            joinPoint.proceed();
            Thread.sleep(1000);
            long horaTermino = System.currentTimeMillis();
            System.out.println("El show ha terminado, aplausos");
            System.out.println("El show tuvo una duracion: " + (horaTermino-horaInicio));
        }catch (Throwable t){
            System.out.println("El show fue terrible, se devolveran las entradas");
        }
    }
}
