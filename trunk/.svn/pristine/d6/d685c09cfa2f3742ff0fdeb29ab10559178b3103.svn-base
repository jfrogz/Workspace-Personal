package mx.vw.swf.fwk.model;

import mx.vw.swf.fwk.model.AbstractFwkCorreoTemplate;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * FwkCorreoTemplate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FWK_CORREO_TEMPLATE", schema = "dbo", catalog = "SMS")
public class FwkCorreoTemplate extends AbstractFwkCorreoTemplate implements
        java.io.Serializable {

    // Constructors

    /** default constructor */
    public FwkCorreoTemplate() {
    }

    /** minimal constructor */
    public FwkCorreoTemplate(String id) {
        super(id);
    }

    /** full constructor */
    public FwkCorreoTemplate(String id, FwkCatApps fwkCatApps, String mailto,
            String mailcc, String mailbcc, String mailsubject, String mailbody,
            String mailattach, String mailimages, Timestamp fechacreacion,
            String usuariocreacion, Timestamp fechaactualiza,
            String usuarioactualiza, Short estatus) {
        super(id, fwkCatApps, mailto, mailcc, mailbcc, mailsubject, mailbody,
                mailattach, mailimages, fechacreacion, usuariocreacion,
                fechaactualiza, usuarioactualiza, estatus);
    }

}
