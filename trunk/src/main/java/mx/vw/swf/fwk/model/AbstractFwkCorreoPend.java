package mx.vw.swf.fwk.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * AbstractFwkCorreoPend entity provides the base persistence definition of the
 * FwkCorreoPend entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractFwkCorreoPend implements java.io.Serializable {

    // Fields

    private Long id;
    private FwkCatApps fwkCatApps;
    private Timestamp tsmensaje;
    private Short lenviado;
    private byte[] bmensaje;
    private Timestamp tsenviado;

    // Constructors

    /** default constructor */
    public AbstractFwkCorreoPend() {
    }

    /** minimal constructor */
    public AbstractFwkCorreoPend(Long id, FwkCatApps fwkCatApps,
            Timestamp tsmensaje, Short lenviado) {
        this.id = id;
        this.fwkCatApps = fwkCatApps;
        this.tsmensaje = tsmensaje;
        this.lenviado = lenviado;
    }

    /** full constructor */
    public AbstractFwkCorreoPend(Long id, FwkCatApps fwkCatApps,
            Timestamp tsmensaje, Short lenviado, byte[] bmensaje,
            Timestamp tsenviado) {
        this.id = id;
        this.fwkCatApps = fwkCatApps;
        this.tsmensaje = tsmensaje;
        this.lenviado = lenviado;
        this.bmensaje = bmensaje;
        this.tsenviado = tsenviado;
    }

    // Property accessors
    @Id
    @Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPS_ID", nullable = false)
    public FwkCatApps getFwkCatApps() {
        return this.fwkCatApps;
    }

    public void setFwkCatApps(FwkCatApps fwkCatApps) {
        this.fwkCatApps = fwkCatApps;
    }

    @Column(name = "TSMENSAJE", nullable = false, length = 23)
    public Timestamp getTsmensaje() {
        return this.tsmensaje;
    }

    public void setTsmensaje(Timestamp tsmensaje) {
        this.tsmensaje = tsmensaje;
    }

    @Column(name = "LENVIADO", nullable = false, precision = 3, scale = 0)
    public Short getLenviado() {
        return this.lenviado;
    }

    public void setLenviado(Short lenviado) {
        this.lenviado = lenviado;
    }

    @Column(name = "BMENSAJE")
    public byte[] getBmensaje() {
        return this.bmensaje;
    }

    public void setBmensaje(byte[] bmensaje) {
        this.bmensaje = bmensaje;
    }

    @Column(name = "TSENVIADO", length = 23)
    public Timestamp getTsenviado() {
        return this.tsenviado;
    }

    public void setTsenviado(Timestamp tsenviado) {
        this.tsenviado = tsenviado;
    }

}