package mx.com.gm.sga.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.com.gm.sga.domain.Persona;

@Generated(value="EclipseLink-2.6.4.v20170112-rNA", date="2017-04-20T17:00:22")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> password;
    public static volatile SingularAttribute<Usuario, Persona> persona;
    public static volatile SingularAttribute<Usuario, Integer> idUsuario;
    public static volatile SingularAttribute<Usuario, String> username;

}