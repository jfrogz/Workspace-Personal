package mx.vw.swf.sms.model;

import mx.vw.swf.security.model.SegUsuario;
import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import mx.vw.swf.cdi.DataFXLauncher;

/**
 * SmsCteProv entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SMS_CTE_PROV", schema = "dbo", catalog = "SMS")
public class SmsCteProv extends AbstractSmsCteProv implements
        java.io.Serializable {

    private String esClienteProveedor;
    private String etiquetaEstatus;
    private String esSalidaContingencia;
    // Constructors


   
    /** default constructor */
    public SmsCteProv() {
    }

    /** minimal constructor */
    public SmsCteProv(Long id, SegUsuario segUsuarioByCreatedBy,
            Short esProveedor, String clave, String nombre, Short estatus,
            Short salidaContingencia, Timestamp createdOn) {
        super(id, segUsuarioByCreatedBy, esProveedor, clave, nombre, estatus,
                salidaContingencia, createdOn);
    }

    /** full constructor */
    public SmsCteProv(Long id, SegUsuario segUsuarioByCreatedBy,
            SegUsuario segUsuarioByUpdatedBy, Short esProveedor, String clave,
            String nombre, String razonSocial, String idSap, Short estatus,
            Short salidaContingencia, Timestamp createdOn, Timestamp updatedOn,
            Set<SmsMovimiento> smsMovimientos,
            Set<SmsProdCteprov> smsProdCteprovs) {
        super(id, segUsuarioByCreatedBy, segUsuarioByUpdatedBy, esProveedor,
                clave, nombre, razonSocial, idSap, estatus, salidaContingencia,
                createdOn, updatedOn, smsMovimientos, smsProdCteprovs);
    }
    
     @Transient
     public String getEsClienteProveedor() {
         if(null != this.getEsProveedor()){
             esClienteProveedor= this.getEsProveedor()==1?DataFXLauncher.getProperty("Catalogos.CltsPrvs.lbProveedor"):DataFXLauncher.getProperty("Catalogos.CltsPrvs.lbCliente");
         }
        return esClienteProveedor;
    }
    @Transient
    public String getEtiquetaEstatus(){        
        if(null != this.getEstatus()){
           etiquetaEstatus= this.getEstatus()==1?DataFXLauncher.getProperty("General.lbActivo"):DataFXLauncher.getProperty("General.lbInactivo");
        }
          return etiquetaEstatus; 
    }
    
    @Transient
    public String getEsSalidaContingencia() {
        if(null != this.getSalidaContingencia()){
           esSalidaContingencia= this.getSalidaContingencia()==1?DataFXLauncher.getProperty("General.lbSi"):DataFXLauncher.getProperty("General.lbNo");
        }
        return esSalidaContingencia;
    }

}
