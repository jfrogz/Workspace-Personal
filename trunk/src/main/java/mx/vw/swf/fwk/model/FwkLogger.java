package mx.vw.swf.fwk.model;

import mx.vw.swf.fwk.model.AbstractFwkLogger;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * FwkLogger entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FWK_LOGGER", schema = "dbo", catalog = "SMS")
public class FwkLogger extends AbstractFwkLogger implements
        java.io.Serializable {

    // Constructors

    /** default constructor */
    public FwkLogger() {
    }

    /** minimal constructor */
    public FwkLogger(Long id) {
        super(id);
    }

    /** full constructor */
    public FwkLogger(Long id, FwkCatApps fwkCatApps, Timestamp tsevento,
            Timestamp tsbasedatos, String nivel, String clase, String metodo,
            Long linea, String mensaje, String stacktrace, String userid) {
        super(id, fwkCatApps, tsevento, tsbasedatos, nivel, clase, metodo,
                linea, mensaje, stacktrace, userid);
    }

}
