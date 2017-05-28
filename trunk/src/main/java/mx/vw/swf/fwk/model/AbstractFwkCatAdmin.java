package mx.vw.swf.fwk.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractFwkCatAdmin entity provides the base persistence definition of the
 * FwkCatAdmin entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractFwkCatAdmin implements java.io.Serializable {

    // Fields

    private Short idAdmin;
    private Short idApp;
    private String description;
    private Timestamp createdOn;
    private String createdBy;
    private Timestamp updatedOn;
    private String updatedBy;
    private Short status;

    // Constructors

    /** default constructor */
    public AbstractFwkCatAdmin() {
    }

    /** minimal constructor */
    public AbstractFwkCatAdmin(Short idAdmin, Short idApp) {
        this.idAdmin = idAdmin;
        this.idApp = idApp;
    }

    /** full constructor */
    public AbstractFwkCatAdmin(Short idAdmin, Short idApp, String description,
            Timestamp createdOn, String createdBy, Timestamp updatedOn,
            String updatedBy, Short status) {
        this.idAdmin = idAdmin;
        this.idApp = idApp;
        this.description = description;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
        this.status = status;
    }

    // Property accessors
    @Id
    @Column(name = "ID_ADMIN", unique = true, nullable = false, precision = 4, scale = 0)
    public Short getIdAdmin() {
        return this.idAdmin;
    }

    public void setIdAdmin(Short idAdmin) {
        this.idAdmin = idAdmin;
    }

    @Column(name = "ID_APP", nullable = false, precision = 4, scale = 0)
    public Short getIdApp() {
        return this.idApp;
    }

    public void setIdApp(Short idApp) {
        this.idApp = idApp;
    }

    @Column(name = "DESCRIPTION", length = 100)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "CREATED_ON", length = 23)
    public Timestamp getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "CREATED_BY", length = 15)
    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "UPDATED_ON", length = 23)
    public Timestamp getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Timestamp updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Column(name = "UPDATED_BY", length = 15)
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Column(name = "STATUS", precision = 3, scale = 0)
    public Short getStatus() {
        return this.status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

}