package mx.vw.swf.fwk.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * AbstractFwkCorreoTemplate entity provides the base persistence definition of
 * the FwkCorreoTemplate entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractFwkCorreoTemplate implements java.io.Serializable {

    // Fields

    private String id;
    private FwkCatApps fwkCatApps;
    private String mailto;
    private String mailcc;
    private String mailbcc;
    private String mailsubject;
    private String mailbody;
    private String mailattach;
    private String mailimages;
    private Timestamp fechacreacion;
    private String usuariocreacion;
    private Timestamp fechaactualiza;
    private String usuarioactualiza;
    private Short estatus;

    // Constructors

    /** default constructor */
    public AbstractFwkCorreoTemplate() {
    }

    /** minimal constructor */
    public AbstractFwkCorreoTemplate(String id) {
        this.id = id;
    }

    /** full constructor */
    public AbstractFwkCorreoTemplate(String id, FwkCatApps fwkCatApps,
            String mailto, String mailcc, String mailbcc, String mailsubject,
            String mailbody, String mailattach, String mailimages,
            Timestamp fechacreacion, String usuariocreacion,
            Timestamp fechaactualiza, String usuarioactualiza, Short estatus) {
        this.id = id;
        this.fwkCatApps = fwkCatApps;
        this.mailto = mailto;
        this.mailcc = mailcc;
        this.mailbcc = mailbcc;
        this.mailsubject = mailsubject;
        this.mailbody = mailbody;
        this.mailattach = mailattach;
        this.mailimages = mailimages;
        this.fechacreacion = fechacreacion;
        this.usuariocreacion = usuariocreacion;
        this.fechaactualiza = fechaactualiza;
        this.usuarioactualiza = usuarioactualiza;
        this.estatus = estatus;
    }

    // Property accessors
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPS_ID")
    public FwkCatApps getFwkCatApps() {
        return this.fwkCatApps;
    }

    public void setFwkCatApps(FwkCatApps fwkCatApps) {
        this.fwkCatApps = fwkCatApps;
    }

    @Column(name = "MAILTO", length = 200)
    public String getMailto() {
        return this.mailto;
    }

    public void setMailto(String mailto) {
        this.mailto = mailto;
    }

    @Column(name = "MAILCC", length = 200)
    public String getMailcc() {
        return this.mailcc;
    }

    public void setMailcc(String mailcc) {
        this.mailcc = mailcc;
    }

    @Column(name = "MAILBCC", length = 200)
    public String getMailbcc() {
        return this.mailbcc;
    }

    public void setMailbcc(String mailbcc) {
        this.mailbcc = mailbcc;
    }

    @Column(name = "MAILSUBJECT", length = 150)
    public String getMailsubject() {
        return this.mailsubject;
    }

    public void setMailsubject(String mailsubject) {
        this.mailsubject = mailsubject;
    }

    @Column(name = "MAILBODY", length = 2500)
    public String getMailbody() {
        return this.mailbody;
    }

    public void setMailbody(String mailbody) {
        this.mailbody = mailbody;
    }

    @Column(name = "MAILATTACH", length = 2500)
    public String getMailattach() {
        return this.mailattach;
    }

    public void setMailattach(String mailattach) {
        this.mailattach = mailattach;
    }

    @Column(name = "MAILIMAGES", length = 200)
    public String getMailimages() {
        return this.mailimages;
    }

    public void setMailimages(String mailimages) {
        this.mailimages = mailimages;
    }

    @Column(name = "FECHACREACION", length = 23)
    public Timestamp getFechacreacion() {
        return this.fechacreacion;
    }

    public void setFechacreacion(Timestamp fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    @Column(name = "USUARIOCREACION")
    public String getUsuariocreacion() {
        return this.usuariocreacion;
    }

    public void setUsuariocreacion(String usuariocreacion) {
        this.usuariocreacion = usuariocreacion;
    }

    @Column(name = "FECHAACTUALIZA", length = 23)
    public Timestamp getFechaactualiza() {
        return this.fechaactualiza;
    }

    public void setFechaactualiza(Timestamp fechaactualiza) {
        this.fechaactualiza = fechaactualiza;
    }

    @Column(name = "USUARIOACTUALIZA", length = 20)
    public String getUsuarioactualiza() {
        return this.usuarioactualiza;
    }

    public void setUsuarioactualiza(String usuarioactualiza) {
        this.usuarioactualiza = usuarioactualiza;
    }

    @Column(name = "ESTATUS")
    public Short getEstatus() {
        return this.estatus;
    }

    public void setEstatus(Short estatus) {
        this.estatus = estatus;
    }

}