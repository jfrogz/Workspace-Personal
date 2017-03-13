package concursantes;

/**
 * Created by Fernando Robles on 09/02/2017.
 */
public class EjecucionException extends RuntimeException {
    public EjecucionException(){}
    public EjecucionException(String msg){
        super(msg);
    }
}
