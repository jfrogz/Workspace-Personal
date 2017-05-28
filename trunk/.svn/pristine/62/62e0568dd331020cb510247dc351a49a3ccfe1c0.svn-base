package mx.vw.swf.security.model;

import mx.vw.swf.security.model.SegPermisoPerfil;
import mx.vw.swf.security.model.SegPerfilUsr;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

/**
 * AbstractSegPerfil entity provides the base persistence definition of the
 * SegPerfil entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSegPerfil implements java.io.Serializable {

    // Fields

    private Integer id;
    private String perfil;
    private Short estatus;
    private Set<SegPerfilUsr> segPerfilUsrs = new HashSet<SegPerfilUsr>(0);
    private Set<SegPermisoPerfil> segPermisoPerfils = new HashSet<SegPermisoPerfil>(
            0);

    // Constructors

    /** default constructor */
    public AbstractSegPerfil() {
    }

    /** minimal constructor */
    public AbstractSegPerfil(Integer id, String perfil, Short estatus) {
        this.id = id;
        this.perfil = perfil;
        this.estatus = estatus;
    }

    /** full constructor */
    public AbstractSegPerfil(Integer id, String perfil, Short estatus,
            Set<SegPerfilUsr> segPerfilUsrs,
            Set<SegPermisoPerfil> segPermisoPerfils) {
        this.id = id;
        this.perfil = perfil;
        this.estatus = estatus;
        this.segPerfilUsrs = segPerfilUsrs;
        this.segPermisoPerfils = segPermisoPerfils;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false, precision = 5, scale = 0)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "Perfil", unique = true, nullable = false, length = 50)
    public String getPerfil() {
        return this.perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Column(name = "Estatus", nullable = false)
    public Short getEstatus() {
        return this.estatus;
    }

    public void setEstatus(Short estatus) {
        this.estatus = estatus;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "segPerfil")
    public Set<SegPerfilUsr> getSegPerfilUsrs() {
        return this.segPerfilUsrs;
    }

    public void setSegPerfilUsrs(Set<SegPerfilUsr> segPerfilUsrs) {
        this.segPerfilUsrs = segPerfilUsrs;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "segPerfil")
    public Set<SegPermisoPerfil> getSegPermisoPerfils() {
        return this.segPermisoPerfils;
    }

    public void setSegPermisoPerfils(Set<SegPermisoPerfil> segPermisoPerfils) {
        this.segPermisoPerfils = segPermisoPerfils;
    }

}