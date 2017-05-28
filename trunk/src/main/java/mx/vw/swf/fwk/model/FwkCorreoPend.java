package mx.vw.swf.fwk.model;

import mx.vw.swf.fwk.model.AbstractFwkCorreoPend;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * FwkCorreoPend entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FWK_CORREO_PEND", schema = "dbo", catalog = "SMS")
public class FwkCorreoPend extends AbstractFwkCorreoPend implements
        java.io.Serializable {

    // Constructors

    /** default constructor */
    public FwkCorreoPend() {
    }

    /** minimal constructor */
    public FwkCorreoPend(Long id, FwkCatApps fwkCatApps, Timestamp tsmensaje,
            Short lenviado) {
        super(id, fwkCatApps, tsmensaje, lenviado);
    }

    /** full constructor */
    public FwkCorreoPend(Long id, FwkCatApps fwkCatApps, Timestamp tsmensaje,
            Short lenviado, byte[] bmensaje, Timestamp tsenviado) {
        super(id, fwkCatApps, tsmensaje, lenviado, bmensaje, tsenviado);
    }

}
