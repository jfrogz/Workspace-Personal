package mx.vw.swf.sms.model;

import mx.vw.swf.security.model.SegUsuario;
import java.sql.Timestamp;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import mx.vw.swf.cdi.DataFXLauncher;
import mx.vw.swf.fwk.model.EntityManagerHelper;
import mx.vw.swf.sms.dao.SmsProductoDAO;

/**
 * SmsMovimiento entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SMS_MOVIMIENTO", schema = "dbo", catalog = "SMS")
public class SmsMovimiento extends AbstractSmsMovimiento implements
        java.io.Serializable {
    private SmsProducto producto;
    

    

    public void setProducto(SmsProducto producto) {
        this.producto = producto;
    }
    
    @ManyToOne(targetEntity = SmsProducto.class)
    @JoinColumn(name = "idProducto", referencedColumnName = "id", updatable = false, insertable = false)
    public SmsProducto getProducto() {
        return producto;
    }
    
    
    
    @Transient
    public StringProperty getRowNombreProducto() {
        return new SimpleStringProperty(getProducto().getNombre());
    }
    
    @Transient
    public StringProperty getRowNombreEmpresa() {
        return new SimpleStringProperty(getSmsCteProv().getNombre());
    }
    
    @Transient
    public StringProperty getRowEnviadoASAP() {
       return new SimpleStringProperty(getEnviadoSap() == 1 ? DataFXLauncher.getProperty("General.lbSi") : DataFXLauncher.getProperty("General.lbNo"));
    }
    
    // Constructors

    /** default constructor */
    public SmsMovimiento() {
    }

    /**
     * minimal constructor
     * @param id
     * @param smsCteProv
     * @param segUsuarioByIdUsrPesoEntrada
     * @param esMovCliente
     * @param idProducto
     * @param placaVehiculo
     * @param fechaEntrada
     * @param pesoEntrada
     * @param basculaContingEntrada
     * @param basculaContingSalida
     * @param enviarSap
     * @param enviadoSap
     * @param cancelado 
     */
    public SmsMovimiento(Long id, SmsCteProv smsCteProv,
           SegUsuario segUsuarioByIdUsrPesoEntrada, Short esMovCliente,
           Long idProducto, String placaVehiculo, Timestamp fechaEntrada,
           Double pesoEntrada, Short basculaContingEntrada, Short basculaContingSalida,
           Short enviarSap, Short enviadoSap, Short cancelado) {
        super(id, smsCteProv, segUsuarioByIdUsrPesoEntrada, esMovCliente,
                idProducto, placaVehiculo, fechaEntrada, pesoEntrada,
                basculaContingEntrada, basculaContingSalida, enviarSap, enviadoSap, cancelado);
    }

    /**
     * full constructor
     * @param id
     * @param segUsuarioByIdUsrCancela
     * @param segUsuarioByIdUsrEnvioSap
     * @param smsCteProv
     * @param segUsuarioByIdUsrPesoSalida
     * @param segUsuarioByIdUsrPesoEntrada
     * @param esMovCliente
     * @param idProducto
     * @param idCteProvSap
     * @param idProductoSap
     * @param placaVehiculo
     * @param destino
     * @param factura
     * @param fechaEntrada
     * @param pesoEntrada
     * @param fechaSalida
     * @param pesoSalida
     * @param basculaContingEntrada
     * @param guardiaContingEntrada
     * @param basculaContingSalida
     * @param guardiaContingSalida
     * @param pesoNeto
     * @param pesoTara
     * @param enviarSap
     * @param enviadoSap
     * @param fechaEnviadoSap
     * @param cancelado
     * @param motivoCancela 
     */
    public SmsMovimiento(Long id, SegUsuario segUsuarioByIdUsrCancela,
            SegUsuario segUsuarioByIdUsrEnvioSap, SmsCteProv smsCteProv,
            SegUsuario segUsuarioByIdUsrPesoSalida,
            SegUsuario segUsuarioByIdUsrPesoEntrada, Short esMovCliente,
            Long idProducto, String idCteProvSap, String idProductoSap,
            String placaVehiculo, String destino, String factura,
            Timestamp fechaEntrada, Double pesoEntrada, Timestamp fechaSalida,
            Double pesoSalida, Short basculaContingEntrada,
            String guardiaContingEntrada, Short basculaContingSalida,
            String guardiaContingSalida, Double pesoNeto, Double pesoTara,
            Short enviarSap, Short enviadoSap, Timestamp fechaEnviadoSap,
            Short cancelado, String motivoCancela) {
        super(id, segUsuarioByIdUsrCancela, segUsuarioByIdUsrEnvioSap,
                smsCteProv, segUsuarioByIdUsrPesoSalida,
                segUsuarioByIdUsrPesoEntrada, esMovCliente, idProducto,
                idCteProvSap, idProductoSap, placaVehiculo, destino, factura,
                fechaEntrada, pesoEntrada, fechaSalida, pesoSalida,
                basculaContingEntrada, guardiaContingEntrada,
                basculaContingSalida, guardiaContingSalida, pesoNeto, pesoTara,
                enviarSap, enviadoSap, fechaEnviadoSap, cancelado,
                motivoCancela);
    }
    
    @PrePersist
    public void prePerpersist() {
        if (0 == getBasculaContingEntrada().intValue()) {
            EntityManager em = EntityManagerHelper.getEntityManager();
            Date date = (Date) em.createNativeQuery("select getDate()").getSingleResult();
            this.setFechaEntrada(new Timestamp(date.getTime()));
        }
    }
    /*
    @PreUpdate
    public void preUpdate() {
        if (0 == getBasculaContingSalida().intValue() && null != getFechaEntrada() && 0 == getCancelado().intValue()) {
            EntityManager em = EntityManagerHelper.getEntityManager();            
            Date date = (Date) em.createNativeQuery("select getDate()").getSingleResult();//            
            this.setFechaSalida(new Timestamp(date.getTime()));
            
        }
    }
    */
}

