package mx.vw.swf.security.model;

import mx.vw.swf.security.model.SegUsuario;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SegPerfilUsr entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SEG_PERFIL_USR", schema = "dbo", catalog = "SMS")
public class SegPerfilUsr extends AbstractSegPerfilUsr implements
        java.io.Serializable {

    // Constructors

    /** default constructor */
    public SegPerfilUsr() {
    }

    /** full constructor */
    public SegPerfilUsr(SegPerfilUsrId id, SegPerfil segPerfil,
            SegUsuario segUsuario) {
        super(id, segPerfil, segUsuario);
    }

}
