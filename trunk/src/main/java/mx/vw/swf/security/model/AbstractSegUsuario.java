package mx.vw.swf.security.model;

import mx.vw.swf.security.model.SegPerfilUsr;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import mx.vw.swf.sms.model.SmsCteProv;
import mx.vw.swf.sms.model.SmsMovimiento;
import mx.vw.swf.sms.model.SmsProducto;


/**
 * AbstractSegUsuario entity provides the base persistence definition of the
 * SegUsuario entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSegUsuario implements java.io.Serializable {

    // Fields

    private Long id;
    private String userId;
    private String nombreUsuario;
    private String apPaterno;
    private String apMaterno;
    private String correoE;
    private Short estatus;
   
    
    private Set<SmsProducto> smsProductosForUpdatedBy = new HashSet<SmsProducto>(
            0);
    private Set<SmsCteProv> smsCteProvsForCreatedBy = new HashSet<SmsCteProv>(0);
    private Set<SmsMovimiento> smsMovimientosForIdUsrPesoEntrada = new HashSet<SmsMovimiento>(
            0);
    private Set<SmsMovimiento> smsMovimientosForIdUsrEnvioSap = new HashSet<SmsMovimiento>(
            0);
    private Set<SegPerfilUsr> segPerfilUsrs = new HashSet<SegPerfilUsr>(0);
    private Set<SmsProducto> smsProductosForCreatedBy = new HashSet<SmsProducto>(
            0);
    private Set<SmsMovimiento> smsMovimientosForIdUsrPesoSalida = new HashSet<SmsMovimiento>(
            0);
    private Set<SmsMovimiento> smsMovimientosForIdUsrCancela = new HashSet<SmsMovimiento>(
            0);
    private Set<SmsCteProv> smsCteProvsForUpdatedBy = new HashSet<SmsCteProv>(0);

    // Constructors

    /** default constructor */
    public AbstractSegUsuario() {
    }

    /** minimal constructor */
    public AbstractSegUsuario(Long id, String userId, Short estatus) {
        this.id = id;
        this.userId = userId;
        this.estatus = estatus;
    }

    /** full constructor */
    public AbstractSegUsuario(Long id, String userId, String nombreUsuario,
            String apPaterno, String apMaterno, String correoE, Short estatus,
            Set<SmsProducto> smsProductosForUpdatedBy,
            Set<SmsCteProv> smsCteProvsForCreatedBy,
            Set<SmsMovimiento> smsMovimientosForIdUsrPesoEntrada,
            Set<SmsMovimiento> smsMovimientosForIdUsrEnvioSap,
            Set<SegPerfilUsr> segPerfilUsrs,
            Set<SmsProducto> smsProductosForCreatedBy,
            Set<SmsMovimiento> smsMovimientosForIdUsrPesoSalida,
            Set<SmsMovimiento> smsMovimientosForIdUsrCancela,
            Set<SmsCteProv> smsCteProvsForUpdatedBy) {
        this.id = id;
        this.userId = userId;
        this.nombreUsuario = nombreUsuario;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.correoE = correoE;
        this.estatus = estatus;
        this.smsProductosForUpdatedBy = smsProductosForUpdatedBy;
        this.smsCteProvsForCreatedBy = smsCteProvsForCreatedBy;
        this.smsMovimientosForIdUsrPesoEntrada = smsMovimientosForIdUsrPesoEntrada;
        this.smsMovimientosForIdUsrEnvioSap = smsMovimientosForIdUsrEnvioSap;
        this.segPerfilUsrs = segPerfilUsrs;
        this.smsProductosForCreatedBy = smsProductosForCreatedBy;
        this.smsMovimientosForIdUsrPesoSalida = smsMovimientosForIdUsrPesoSalida;
        this.smsMovimientosForIdUsrCancela = smsMovimientosForIdUsrCancela;
        this.smsCteProvsForUpdatedBy = smsCteProvsForUpdatedBy;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false, precision = 10, scale = 0)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "User_Id", unique = true, nullable = false, length = 20)
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "NombreUsuario", length = 75)
    public String getNombreUsuario() {
        return this.nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Column(name = "ApPaterno", length = 75)
    public String getApPaterno() {
        return this.apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    @Column(name = "ApMaterno", length = 75)
    public String getApMaterno() {
        return this.apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    @Column(name = "CorreoE", length = 100)
    public String getCorreoE() {
        return this.correoE;
    }

    public void setCorreoE(String correoE) {
        this.correoE = correoE;
    }

    @Column(name = "Estatus", nullable = false)
    public Short getEstatus() {
        return this.estatus;
    }

    public void setEstatus(Short estatus) {
        this.estatus = estatus;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "segUsuarioByUpdatedBy")
    public Set<SmsProducto> getSmsProductosForUpdatedBy() {
        return this.smsProductosForUpdatedBy;
    }

    public void setSmsProductosForUpdatedBy(
            Set<SmsProducto> smsProductosForUpdatedBy) {
        this.smsProductosForUpdatedBy = smsProductosForUpdatedBy;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "segUsuarioByCreatedBy")
    public Set<SmsCteProv> getSmsCteProvsForCreatedBy() {
        return this.smsCteProvsForCreatedBy;
    }

    public void setSmsCteProvsForCreatedBy(
            Set<SmsCteProv> smsCteProvsForCreatedBy) {
        this.smsCteProvsForCreatedBy = smsCteProvsForCreatedBy;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "segUsuarioByIdUsrPesoEntrada")
    public Set<SmsMovimiento> getSmsMovimientosForIdUsrPesoEntrada() {
        return this.smsMovimientosForIdUsrPesoEntrada;
    }

    public void setSmsMovimientosForIdUsrPesoEntrada(
            Set<SmsMovimiento> smsMovimientosForIdUsrPesoEntrada) {
        this.smsMovimientosForIdUsrPesoEntrada = smsMovimientosForIdUsrPesoEntrada;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "segUsuarioByIdUsrEnvioSap")
    public Set<SmsMovimiento> getSmsMovimientosForIdUsrEnvioSap() {
        return this.smsMovimientosForIdUsrEnvioSap;
    }

    public void setSmsMovimientosForIdUsrEnvioSap(
            Set<SmsMovimiento> smsMovimientosForIdUsrEnvioSap) {
        this.smsMovimientosForIdUsrEnvioSap = smsMovimientosForIdUsrEnvioSap;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "segUsuario")
    public Set<SegPerfilUsr> getSegPerfilUsrs() {
        return this.segPerfilUsrs;
    }

    public void setSegPerfilUsrs(Set<SegPerfilUsr> segPerfilUsrs) {
        this.segPerfilUsrs = segPerfilUsrs;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "segUsuarioByCreatedBy")
    public Set<SmsProducto> getSmsProductosForCreatedBy() {
        return this.smsProductosForCreatedBy;
    }

    public void setSmsProductosForCreatedBy(
            Set<SmsProducto> smsProductosForCreatedBy) {
        this.smsProductosForCreatedBy = smsProductosForCreatedBy;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "segUsuarioByIdUsrPesoSalida")
    public Set<SmsMovimiento> getSmsMovimientosForIdUsrPesoSalida() {
        return this.smsMovimientosForIdUsrPesoSalida;
    }

    public void setSmsMovimientosForIdUsrPesoSalida(
            Set<SmsMovimiento> smsMovimientosForIdUsrPesoSalida) {
        this.smsMovimientosForIdUsrPesoSalida = smsMovimientosForIdUsrPesoSalida;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "segUsuarioByIdUsrCancela")
    public Set<SmsMovimiento> getSmsMovimientosForIdUsrCancela() {
        return this.smsMovimientosForIdUsrCancela;
    }

    public void setSmsMovimientosForIdUsrCancela(
            Set<SmsMovimiento> smsMovimientosForIdUsrCancela) {
        this.smsMovimientosForIdUsrCancela = smsMovimientosForIdUsrCancela;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "segUsuarioByUpdatedBy")
    public Set<SmsCteProv> getSmsCteProvsForUpdatedBy() {
        return this.smsCteProvsForUpdatedBy;
    }

    public void setSmsCteProvsForUpdatedBy(
            Set<SmsCteProv> smsCteProvsForUpdatedBy) {
        this.smsCteProvsForUpdatedBy = smsCteProvsForUpdatedBy;
    }

}