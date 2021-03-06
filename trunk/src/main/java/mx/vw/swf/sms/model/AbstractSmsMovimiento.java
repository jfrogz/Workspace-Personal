package mx.vw.swf.sms.model;

import mx.vw.swf.security.model.SegUsuario;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenerationTime;

/**
 * AbstractSmsMovimiento entity provides the base persistence definition of the
 * SmsMovimiento entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSmsMovimiento implements java.io.Serializable {

    // Fields

    private Long id;
    private SegUsuario segUsuarioByIdUsrCancela;
    private SegUsuario segUsuarioByIdUsrEnvioSap;
    private SmsCteProv smsCteProv;
    private SegUsuario segUsuarioByIdUsrPesoSalida;
    private SegUsuario segUsuarioByIdUsrPesoEntrada;
    private Short esMovCliente;
    private Long idProducto;
    private String idCteProvSap;
    private String idProductoSap;
    private String placaVehiculo;
    private String destino;
    private String factura;
    private Timestamp fechaEntrada;
    private Double pesoEntrada;
    private Timestamp fechaSalida;
    private Double pesoSalida;
    private Short basculaContingEntrada;
    private String guardiaContingEntrada;
    private Short basculaContingSalida;
    private String guardiaContingSalida;
    private Double pesoNeto;
    private Double pesoTara;
    private Short enviarSap;
    private Short enviadoSap;
    private Timestamp fechaEnviadoSap;
    private Short cancelado;
    private String motivoCancela;
    private String nombreConductor;
    // Constructors

    /** default constructor */
    public AbstractSmsMovimiento() {
    }

    /** minimal constructor */
    public AbstractSmsMovimiento(Long id, SmsCteProv smsCteProv,
            SegUsuario segUsuarioByIdUsrPesoEntrada, Short esMovCliente,
            Long idProducto, String placaVehiculo, Timestamp fechaEntrada,
            Double pesoEntrada, Short basculaContingEntrada, Short basculaContingSalida, 
            Short enviarSap, Short enviadoSap, Short cancelado) {
        this.id = id;
        this.smsCteProv = smsCteProv;
        this.segUsuarioByIdUsrPesoEntrada = segUsuarioByIdUsrPesoEntrada;
        this.esMovCliente = esMovCliente;
        this.idProducto = idProducto;
        this.placaVehiculo = placaVehiculo;
        this.fechaEntrada = fechaEntrada;
        this.pesoEntrada = pesoEntrada;
        this.basculaContingEntrada = basculaContingEntrada;
        this.basculaContingSalida = basculaContingSalida;
        this.enviarSap = enviarSap;
        this.enviadoSap = enviadoSap;
        this.cancelado = cancelado;
    }

    /** full constructor */
    public AbstractSmsMovimiento(Long id, SegUsuario segUsuarioByIdUsrCancela,
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
        this.id = id;
        this.segUsuarioByIdUsrCancela = segUsuarioByIdUsrCancela;
        this.segUsuarioByIdUsrEnvioSap = segUsuarioByIdUsrEnvioSap;
        this.smsCteProv = smsCteProv;
        this.segUsuarioByIdUsrPesoSalida = segUsuarioByIdUsrPesoSalida;
        this.segUsuarioByIdUsrPesoEntrada = segUsuarioByIdUsrPesoEntrada;
        this.esMovCliente = esMovCliente;
        this.idProducto = idProducto;
        this.idCteProvSap = idCteProvSap;
        this.idProductoSap = idProductoSap;
        this.placaVehiculo = placaVehiculo;
        this.destino = destino;
        this.factura = factura;
        this.fechaEntrada = fechaEntrada;
        this.pesoEntrada = pesoEntrada;
        this.fechaSalida = fechaSalida;
        this.pesoSalida = pesoSalida;
        this.basculaContingEntrada = basculaContingEntrada;
        this.guardiaContingEntrada = guardiaContingEntrada;
        this.basculaContingSalida = basculaContingSalida;
        this.guardiaContingSalida = guardiaContingSalida;
        this.pesoNeto = pesoNeto;
        this.pesoTara = pesoTara;
        this.enviarSap = enviarSap;
        this.enviadoSap = enviadoSap;
        this.fechaEnviadoSap = fechaEnviadoSap;
        this.cancelado = cancelado;
        this.motivoCancela = motivoCancela;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdUsrCancela")
    public SegUsuario getSegUsuarioByIdUsrCancela() {
        return this.segUsuarioByIdUsrCancela;
    }

    public void setSegUsuarioByIdUsrCancela(SegUsuario segUsuarioByIdUsrCancela) {
        this.segUsuarioByIdUsrCancela = segUsuarioByIdUsrCancela;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdUsrEnvioSAP")
    public SegUsuario getSegUsuarioByIdUsrEnvioSap() {
        return this.segUsuarioByIdUsrEnvioSap;
    }

    public void setSegUsuarioByIdUsrEnvioSap(
            SegUsuario segUsuarioByIdUsrEnvioSap) {
        this.segUsuarioByIdUsrEnvioSap = segUsuarioByIdUsrEnvioSap;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdCteProveedor", nullable = false)
    public SmsCteProv getSmsCteProv() {
        return this.smsCteProv;
    }

    public void setSmsCteProv(SmsCteProv smsCteProv) {
        this.smsCteProv = smsCteProv;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdUsrPesoSalida")
    public SegUsuario getSegUsuarioByIdUsrPesoSalida() {
        return this.segUsuarioByIdUsrPesoSalida;
    }

    public void setSegUsuarioByIdUsrPesoSalida(
            SegUsuario segUsuarioByIdUsrPesoSalida) {
        this.segUsuarioByIdUsrPesoSalida = segUsuarioByIdUsrPesoSalida;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdUsrPesoEntrada", nullable = false)
    public SegUsuario getSegUsuarioByIdUsrPesoEntrada() {
        return this.segUsuarioByIdUsrPesoEntrada;
    }

    public void setSegUsuarioByIdUsrPesoEntrada(
            SegUsuario segUsuarioByIdUsrPesoEntrada) {
        this.segUsuarioByIdUsrPesoEntrada = segUsuarioByIdUsrPesoEntrada;
    }

    @Column(name="NombreConductor", nullable = true)
    public String getNombreConductor() {
        return this.nombreConductor;
    }

    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }
    
    @Column(name = "EsMovCliente", nullable = false)
    public Short getEsMovCliente() {
        return this.esMovCliente;
    }

    public void setEsMovCliente(Short esMovCliente) {
        this.esMovCliente = esMovCliente;
    }

    @Column(name = "IdProducto", nullable = false, precision = 10, scale = 0)
    public Long getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    @Column(name = "IdCteProvSAP", length = 50)
    public String getIdCteProvSap() {
        return this.idCteProvSap;
    }

    public void setIdCteProvSap(String idCteProvSap) {
        this.idCteProvSap = idCteProvSap;
    }

    @Column(name = "IdProductoSAP", length = 50)
    public String getIdProductoSap() {
        return this.idProductoSap;
    }

    public void setIdProductoSap(String idProductoSap) {
        this.idProductoSap = idProductoSap;
    }

    @Column(name = "PlacaVehiculo", nullable = false, length = 7)
    public String getPlacaVehiculo() {
        return this.placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    @Column(name = "Destino", length = 50)
    public String getDestino() {
        return this.destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Column(name = "Factura", length = 50)
    public String getFactura() {
        return this.factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }
    
    @Column(name = "FechaEntrada", nullable = false, length = 23)
    public Timestamp getFechaEntrada() {
        return this.fechaEntrada;
    }

    public void setFechaEntrada(Timestamp fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    @Column(name = "PesoEntrada", nullable = false, precision = 10, scale = 4)
    public Double getPesoEntrada() {
        return this.pesoEntrada;
    }

    public void setPesoEntrada(Double pesoEntrada) {
        this.pesoEntrada = pesoEntrada;
    }
    
    @Column(name = "FechaSalida", length = 23)
    public Timestamp getFechaSalida() {
        return this.fechaSalida;
    }
    
    public void setFechaSalida(Timestamp fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    @Column(name = "PesoSalida", precision = 10, scale = 4)
    public Double getPesoSalida() {
        return this.pesoSalida;
    }

    public void setPesoSalida(Double pesoSalida) {
        this.pesoSalida = pesoSalida;
    }

    @Column(name = "BasculaContingEntrada", nullable = false)
    public Short getBasculaContingEntrada() {
        return this.basculaContingEntrada;
    }

    public void setBasculaContingEntrada(Short basculaContingEntrada) {
        this.basculaContingEntrada = basculaContingEntrada;
    }

    @Column(name = "GuardiaContingEntrada", length = 75)
    public String getGuardiaContingEntrada() {
        return this.guardiaContingEntrada;
    }

    public void setGuardiaContingEntrada(String guardiaContingEntrada) {
        this.guardiaContingEntrada = guardiaContingEntrada;
    }

    @Column(name = "BasculaContingSalida", nullable = false)
    public Short getBasculaContingSalida() {
        return this.basculaContingSalida;
    }

    public void setBasculaContingSalida(Short basculaContingSalida) {
        this.basculaContingSalida = basculaContingSalida;
    }

    @Column(name = "GuardiaContingSalida", length = 75)
    public String getGuardiaContingSalida() {
        return this.guardiaContingSalida;
    }

    public void setGuardiaContingSalida(String guardiaContingSalida) {
        this.guardiaContingSalida = guardiaContingSalida;
    }

    @Column(name = "PesoNeto", precision = 10, scale = 4)
    public Double getPesoNeto() {
        return this.pesoNeto;
    }

    public void setPesoNeto(Double pesoNeto) {
        this.pesoNeto = pesoNeto;
    }

    @Column(name = "PesoTara", precision = 10, scale = 4)
    public Double getPesoTara() {
        return this.pesoTara;
    }

    public void setPesoTara(Double pesoTara) {
        this.pesoTara = pesoTara;
    }

    @Column(name = "EnviarSAP", nullable = false)
    public Short getEnviarSap() {
        return this.enviarSap;
    }

    public void setEnviarSap(Short enviarSap) {
        this.enviarSap = enviarSap;
    }

    @Column(name = "EnviadoSAP", nullable = false)
    public Short getEnviadoSap() {
        return this.enviadoSap;
    }

    public void setEnviadoSap(Short enviadoSap) {
        this.enviadoSap = enviadoSap;
    }

    @Column(name = "FechaEnviadoSAP", length = 23)
    public Timestamp getFechaEnviadoSap() {
        return this.fechaEnviadoSap;
    }

    public void setFechaEnviadoSap(Timestamp fechaEnviadoSap) {
        this.fechaEnviadoSap = fechaEnviadoSap;
    }

    @Column(name = "Cancelado", nullable = false)
    public Short getCancelado() {
        return this.cancelado;
    }

    public void setCancelado(Short cancelado) {
        this.cancelado = cancelado;
    }

    @Column(name = "MotivoCancela", length = 250)
    public String getMotivoCancela() {
        return this.motivoCancela;
    }

    public void setMotivoCancela(String motivoCancela) {
        this.motivoCancela = motivoCancela;
    }

}