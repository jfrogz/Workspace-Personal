package concursantes;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by Fernando Robles on 13/02/2017.
 */
@Component
@Aspect
public class Audiencia {

    @Pointcut("execution(* concursantes.Concursante.ejecutar(..))")
    public void ejecutarShow(){}

    @Around("ejecutarShow()")
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
