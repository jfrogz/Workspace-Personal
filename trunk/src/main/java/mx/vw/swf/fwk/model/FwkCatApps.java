package mx.vw.swf.fwk.model;

import mx.vw.swf.fwk.model.AbstractFwkCatApps;
import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * FwkCatApps entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FWK_CAT_APPS", schema = "dbo", catalog = "SMS")
public class FwkCatApps extends AbstractFwkCatApps implements
        java.io.Serializable {

    // Constructors

    /** default constructor */
    public FwkCatApps() {
    }

    /** minimal constructor */
    public FwkCatApps(Short idApp, String applicationName) {
        super(idApp, applicationName);
    }

    /** full constructor */
    public FwkCatApps(Short idApp, String applicationName, Timestamp createdOn,
            String createdBy, Timestamp updatedOn, String updatedBy,
            Short status, Set<FwkLogger> fwkLoggers,
            Set<FwkCorreoPend> fwkCorreoPends,
            Set<FwkCorreoTemplate> fwkCorreoTemplates) {
        super(idApp, applicationName, createdOn, createdBy, updatedOn,
                updatedBy, status, fwkLoggers, fwkCorreoPends,
                fwkCorreoTemplates);
    }

}
