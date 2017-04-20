package mx.com.gm.sga.servicio;

import mx.com.gm.sga.domain.Persona;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface PersonaServiceWS {
    @WebMethod
    public List<Persona> listarPersonas();
}
