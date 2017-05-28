package mx.vw.swf.sms.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * AbstractSmsProdCteprov entity provides the base persistence definition of the
 * SmsProdCteprov entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSmsProdCteprov implements java.io.Serializable {

    // Fields

    private SmsProdCteprovId id;
    private SmsCteProv smsCteProv;
    private SmsProducto smsProducto;

    // Constructors

    /** default constructor */
    public AbstractSmsProdCteprov() {
    }

    /** full constructor */
    public AbstractSmsProdCteprov(SmsProdCteprovId id, SmsCteProv smsCteProv,
            SmsProducto smsProducto) {
        this.id = id;
        this.smsCteProv = smsCteProv;
        this.smsProducto = smsProducto;
    }

    // Property accessors
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "idCteProv", column = @Column(name = "IdCteProv", nullable = false, precision = 10, scale = 0)),
            @AttributeOverride(name = "idProducto", column = @Column(name = "IdProducto", nullable = false, precision = 10, scale = 0)) })
    public SmsProdCteprovId getId() {
        return this.id;
    }

    public void setId(SmsProdCteprovId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCteProv", nullable = false, insertable = false, updatable = false)
    public SmsCteProv getSmsCteProv() {
        return this.smsCteProv;
    }

    public void setSmsCteProv(SmsCteProv smsCteProv) {
        this.smsCteProv = smsCteProv;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdProducto", nullable = false, insertable = false, updatable = false)
    public SmsProducto getSmsProducto() {
        return this.smsProducto;
    }

    public void setSmsProducto(SmsProducto smsProducto) {
        this.smsProducto = smsProducto;
    }

}