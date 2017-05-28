package mx.vw.swf.sms.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SmsProdCteprovId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class SmsProdCteprovId implements java.io.Serializable {

    // Fields

    private Long idCteProv;
    private Long idProducto;

    // Constructors

    /** default constructor */
    public SmsProdCteprovId() {
    }

    /** full constructor */
    public SmsProdCteprovId(Long idCteProv, Long idProducto) {
        this.idCteProv = idCteProv;
        this.idProducto = idProducto;
    }

    // Property accessors

    @Column(name = "IdCteProv", nullable = false, precision = 10, scale = 0)
    public Long getIdCteProv() {
        return this.idCteProv;
    }

    public void setIdCteProv(Long idCteProv) {
        this.idCteProv = idCteProv;
    }

    @Column(name = "IdProducto", nullable = false, precision = 10, scale = 0)
    public Long getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof SmsProdCteprovId))
            return false;
        SmsProdCteprovId castOther = (SmsProdCteprovId) other;

        return ((this.getIdCteProv() == castOther.getIdCteProv()) || (this
                .getIdCteProv() != null && castOther.getIdCteProv() != null && this
                .getIdCteProv().equals(castOther.getIdCteProv())))
                && ((this.getIdProducto() == castOther.getIdProducto()) || (this
                        .getIdProducto() != null
                        && castOther.getIdProducto() != null && this
                        .getIdProducto().equals(castOther.getIdProducto())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result
                + (getIdCteProv() == null ? 0 : this.getIdCteProv().hashCode());
        result = 37
                * result
                + (getIdProducto() == null ? 0 : this.getIdProducto()
                        .hashCode());
        return result;
    }

}