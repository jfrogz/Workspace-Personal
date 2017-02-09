package concursantes;

/**
 * Created by Fernando Robles on 09/02/2017.
 */
public class MalabaristaRecitador extends Malabarista{

    private Poema poema;

    public MalabaristaRecitador(Poema poema){
        super();
        this.poema = poema;
    }

    public MalabaristaRecitador(int pelotas, Poema poema){
        super(pelotas);
        this.poema = poema;
    }

     public void ejecutar () throws EjecucionException{
        super.ejecutar();
         System.out.println("Mientras recita...");
         poema.recitar();
         System.out.println("Termina recitación...");
     }


}
