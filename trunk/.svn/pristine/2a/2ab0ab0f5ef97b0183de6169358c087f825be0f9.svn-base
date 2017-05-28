package mx.vw.swf.security.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SegPermisoPerfilId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class SegPermisoPerfilId implements java.io.Serializable {

    // Fields

    private Integer idPerfil;
    private Integer idPermiso;

    // Constructors

    /** default constructor */
    public SegPermisoPerfilId() {
    }

    /** full constructor */
    public SegPermisoPerfilId(Integer idPerfil, Integer idPermiso) {
        this.idPerfil = idPerfil;
        this.idPermiso = idPermiso;
    }

    // Property accessors

    @Column(name = "IdPerfil", nullable = false, precision = 5, scale = 0)
    public Integer getIdPerfil() {
        return this.idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    @Column(name = "IdPermiso", nullable = false, precision = 5, scale = 0)
    public Integer getIdPermiso() {
        return this.idPermiso;
    }

    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof SegPermisoPerfilId))
            return false;
        SegPermisoPerfilId castOther = (SegPermisoPerfilId) other;

        return ((this.getIdPerfil() == castOther.getIdPerfil()) || (this
                .getIdPerfil() != null && castOther.getIdPerfil() != null && this
                .getIdPerfil().equals(castOther.getIdPerfil())))
                && ((this.getIdPermiso() == castOther.getIdPermiso()) || (this
                        .getIdPermiso() != null
                        && castOther.getIdPermiso() != null && this
                        .getIdPermiso().equals(castOther.getIdPermiso())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result
                + (getIdPerfil() == null ? 0 : this.getIdPerfil().hashCode());
        result = 37 * result
                + (getIdPermiso() == null ? 0 : this.getIdPermiso().hashCode());
        return result;
    }

}