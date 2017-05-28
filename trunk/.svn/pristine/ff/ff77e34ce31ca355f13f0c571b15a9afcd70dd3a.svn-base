package mx.vw.swf.sms.model;

import mx.vw.swf.security.model.SegUsuario;
import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.*;

import mx.vw.swf.cdi.DataFXLauncher;

/**
 * SmsProducto entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SMS_PRODUCTO", schema = "dbo", catalog = "SMS")
public class SmsProducto extends AbstractSmsProducto implements
        java.io.Serializable {
    
    private String etiquetaEstatus;
    private Boolean selected;
    // Constructors

    /** default constructor */
    public SmsProducto() {
    }

    /** minimal constructor */
    public SmsProducto(Long id, SegUsuario segUsuarioByCreatedBy, String clave,
            String nombre, Short estatus, Timestamp createdOn) {
        super(id, segUsuarioByCreatedBy, clave, nombre, estatus, createdOn);
    }

    /** full constructor */
    public SmsProducto(Long id, SegUsuario segUsuarioByCreatedBy,
            SegUsuario segUsuarioByUpdatedBy, String clave, String nombre,
            String idSap, Short estatus, Timestamp createdOn,
            Timestamp updatedOn, Set<SmsProdCteprov> smsProdCteprovs) {
        super(id, segUsuarioByCreatedBy, segUsuarioByUpdatedBy, clave, nombre,
                idSap, estatus, createdOn, updatedOn, smsProdCteprovs);
    }

    @Transient
    public String getEtiquetaEstatus(){        
        if(null != this.getEstatus()){
           etiquetaEstatus= this.getEstatus()==1?DataFXLauncher.getProperty("General.lbActivo"):DataFXLauncher.getProperty("General.lbInactivo");;
        }
          return etiquetaEstatus; 
    }
    @Transient
    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
