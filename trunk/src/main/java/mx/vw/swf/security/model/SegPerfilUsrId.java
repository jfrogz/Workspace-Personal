package mx.vw.swf.security.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SegPerfilUsrId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class SegPerfilUsrId implements java.io.Serializable {

    // Fields

    private Long idUsuario;
    private Integer idPerfil;

    // Constructors

    /** default constructor */
    public SegPerfilUsrId() {
    }

    /** full constructor */
    public SegPerfilUsrId(Long idUsuario, Integer idPerfil) {
        this.idUsuario = idUsuario;
        this.idPerfil = idPerfil;
    }

    // Property accessors

    @Column(name = "IdUsuario", nullable = false, precision = 10, scale = 0)
    public Long getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Column(name = "IdPerfil", nullable = false, precision = 5, scale = 0)
    public Integer getIdPerfil() {
        return this.idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof SegPerfilUsrId))
            return false;
        SegPerfilUsrId castOther = (SegPerfilUsrId) other;

        return ((this.getIdUsuario() == castOther.getIdUsuario()) || (this
                .getIdUsuario() != null && castOther.getIdUsuario() != null && this
                .getIdUsuario().equals(castOther.getIdUsuario())))
                
                && ((this.getIdPerfil() == castOther.getIdPerfil()) || (this
                        .getIdPerfil() != null
                        && castOther.getIdPerfil() != null && this
                        .getIdPerfil().equals(castOther.getIdPerfil())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result
                + (getIdUsuario() == null ? 0 : this.getIdUsuario().hashCode());
        result = 37 * result
                + (getIdPerfil() == null ? 0 : this.getIdPerfil().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return this.idUsuario + " " + this.idPerfil;
    }
    

}