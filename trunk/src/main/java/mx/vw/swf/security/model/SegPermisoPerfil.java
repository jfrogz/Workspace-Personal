package mx.vw.swf.security.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SegPermisoPerfil entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SEG_PERMISO_PERFIL", schema = "dbo", catalog = "SMS")
public class SegPermisoPerfil extends AbstractSegPermisoPerfil implements
        java.io.Serializable {

    // Constructors

    /** default constructor */
    public SegPermisoPerfil() {
    }

    /** full constructor */
    public SegPermisoPerfil(SegPermisoPerfilId id, SegPermiso segPermiso,
            SegPerfil segPerfil) {
        super(id, segPermiso, segPerfil);
    }

}
