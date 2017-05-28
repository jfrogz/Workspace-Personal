package mx.vw.swf.fwk.model;

import mx.vw.swf.fwk.model.AbstractFwkCatContent;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * FwkCatContent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FWK_CAT_CONTENT", schema = "dbo", catalog = "SMS")
public class FwkCatContent extends AbstractFwkCatContent implements
        java.io.Serializable {

    // Constructors

    /** default constructor */
    public FwkCatContent() {
    }

    /** minimal constructor */
    public FwkCatContent(Long idContent, Short idApp, Short idAdmin) {
        super(idContent, idApp, idAdmin);
    }

    /** full constructor */
    public FwkCatContent(Long idContent, Short idApp, Short idAdmin,
            String keyValue, String value, String description,
            Timestamp createdOn, String createdBy, Timestamp updatedOn,
            String updatedBy, Short status) {
        super(idContent, idApp, idAdmin, keyValue, value, description,
                createdOn, createdBy, updatedOn, updatedBy, status);
    }

}
