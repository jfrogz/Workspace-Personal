package mx.vw.swf.security.model;

import mx.vw.swf.security.model.SegPerfil;
import mx.vw.swf.security.model.SegPermiso;
import mx.vw.swf.security.model.SegPermisoPerfilId;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * AbstractSegPermisoPerfil entity provides the base persistence definition of
 * the SegPermisoPerfil entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSegPermisoPerfil implements java.io.Serializable {

    // Fields

    private SegPermisoPerfilId id;
    private SegPermiso segPermiso;
    private SegPerfil segPerfil;

    // Constructors

    /** default constructor */
    public AbstractSegPermisoPerfil() {
    }

    /** full constructor */
    public AbstractSegPermisoPerfil(SegPermisoPerfilId id,
            SegPermiso segPermiso, SegPerfil segPerfil) {
        this.id = id;
        this.segPermiso = segPermiso;
        this.segPerfil = segPerfil;
    }

    // Property accessors
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "idPerfil", column = @Column(name = "IdPerfil", nullable = false, precision = 5, scale = 0)),
            @AttributeOverride(name = "idPermiso", column = @Column(name = "IdPermiso", nullable = false, precision = 5, scale = 0)) })
    public SegPermisoPerfilId getId() {
        return this.id;
    }

    public void setId(SegPermisoPerfilId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdPermiso", nullable = false, insertable = false, updatable = false)
    public SegPermiso getSegPermiso() {
        return this.segPermiso;
    }

    public void setSegPermiso(SegPermiso segPermiso) {
        this.segPermiso = segPermiso;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdPerfil", nullable = false, insertable = false, updatable = false)
    public SegPerfil getSegPerfil() {
        return this.segPerfil;
    }

    public void setSegPerfil(SegPerfil segPerfil) {
        this.segPerfil = segPerfil;
    }

}