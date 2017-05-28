package mx.vw.swf.fwk.model;

import mx.vw.swf.fwk.model.AbstractFwkCatAdmin;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * FwkCatAdmin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FWK_CAT_ADMIN", schema = "dbo", catalog = "SMS")
public class FwkCatAdmin extends AbstractFwkCatAdmin implements
        java.io.Serializable {

    // Constructors

    /** default constructor */
    public FwkCatAdmin() {
    }

    /** minimal constructor */
    public FwkCatAdmin(Short idAdmin, Short idApp) {
        super(idAdmin, idApp);
    }

    /** full constructor */
    public FwkCatAdmin(Short idAdmin, Short idApp, String description,
            Timestamp createdOn, String createdBy, Timestamp updatedOn,
            String updatedBy, Short status) {
        super(idAdmin, idApp, description, createdOn, createdBy, updatedOn,
                updatedBy, status);
    }

}
