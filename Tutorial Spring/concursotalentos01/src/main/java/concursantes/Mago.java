package concursantes;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Controller;

@Controller
@Aspect
public class Mago implements Adivinador {
    private String pensamientos;

    @Pointcut("execution(* concursantes.Pensador.pensarEnAlgo(String)) && args(pensamientos)")
    public void pensar(String pensamientos){
    }

    @Before("pensar(pensamientos)")
    public void interceptarPensamientos(String pensamientos) {
        System.out.println("Magia....");
        System.out.println("Estos son los pensamientos del voluntario " + pensamientos);
        this.pensamientos = pensamientos;
    }

    public String getPensamiento() {
        return this.pensamientos;
    }
}
