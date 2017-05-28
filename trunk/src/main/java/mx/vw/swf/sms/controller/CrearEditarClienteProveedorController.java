package mx.vw.swf.sms.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import io.datafx.controller.ViewController;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import mx.vw.swf.cdi.DataFXLauncher;
import mx.vw.swf.security.controller.Controller;
import mx.vw.swf.security.model.SegUsuario;
import mx.vw.swf.sms.model.SmsCteProv;
import mx.vw.swf.sms.service.CrearEditarClienteProveedorService;
import mx.vw.swf.sms.utilerias.LengthValidator;
import mx.vw.swf.sms.utilerias.Utilerias;
import org.controlsfx.dialog.Dialogs;
import org.controlsfx.validation.Validator;

import static mx.vw.swf.cdi.DataFXLauncher.*;
import mx.vw.swf.sms.dao.SmsCteProvDAO;

/**
 * FXML Controller class
 *
 * @author fox1yij
 */
@ViewController("/fxml/catalogos/CrearEditarClienteProveedor.fxml")
public class CrearEditarClienteProveedorController extends Controller implements Initializable {

    //labels
    @FXML
    private Label lblTipo;
    @FXML
    private Label lblID;
    @FXML
    private Label lblIdSAP;
    @FXML
    private Label lblRazonSocial;
    @FXML
    private Label lblNombreEmpresa;
    @FXML
    private Label lblClave;
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblPermisoContingencia;
    //Componentes
    @FXML
    private Label lblValorID;
    @FXML
    private TextField txtIdSap;
    @FXML
    private TextField txtRazonSocial;
    @FXML
    private TextField txtNombreEmpresa;
    @FXML
    private TextField txtClave;
    @FXML
    private CheckBox chxPermisoContingencia;
    @FXML
    private CheckBox chxEstatuseEntidad;
    @FXML
    private RadioButton rbtnCliente;
    @FXML
    private RadioButton rbtnProveedor;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;

    private SmsCteProv entidadActual;
    private SegUsuario segUsuario;
    private RegistroDeClientesYProveedoresController regCliProv;

    public CrearEditarClienteProveedorController(SegUsuario segUsuario, SmsCteProv entidadActual, RegistroDeClientesYProveedoresController regCliProv) {
        this.segUsuario = segUsuario;
        this.entidadActual = entidadActual;
        this.regCliProv = regCliProv;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarControles();        
        inicializaClienteProveedor();
        inicializarEventos();
    }


    private void inicializarControles() {
        txtIdSap.addEventFilter(KeyEvent.KEY_TYPED, Utilerias.permiteLetrasNumeros());        
        txtClave.addEventFilter(KeyEvent.KEY_TYPED, Utilerias.permiteLetrasNumeros());
        
        Utilerias.addTextLimiter(txtIdSap, 50);
        Utilerias.addTextLimiter(txtRazonSocial, 200);
        Utilerias.addTextLimiter(txtNombreEmpresa, 100);
        Utilerias.addTextLimiter(txtClave, 50);


        
        lblTipo.setText(getProperty("Catalogos.CltsPrvs.NuevoEditar.lblTipo") + ":");
        lblID.setText(getProperty("Catalogos.CltsPrvs.NuevoEditar.lblID") + ":");
        lblIdSAP.setText(getProperty("Catalogos.CltsPrvs.NuevoEditar.lblIdSAP") + ":");
        lblRazonSocial.setText(getProperty("Catalogos.CltsPrvs.NuevoEditar.lblRazonSocial") + ":");
        lblNombreEmpresa.setText(getProperty("Catalogos.CltsPrvs.NuevoEditar.lblNombreEmpresa") + ":");
        lblClave.setText(getProperty("Catalogos.CltsPrvs.NuevoEditar.lblClave") + ":");
        lblStatus.setText(getProperty("Catalogos.CltsPrvs.NuevoEditar.lblStatus") + ":");
        lblPermisoContingencia.setText(getProperty("Catalogos.CltsPrvs.NuevoEditar.lblPermisoContingencia") + ":");
        rbtnCliente.setText(getProperty("Catalogos.CltsPrvs.lbCliente"));
        rbtnProveedor.setText(getProperty("Catalogos.CltsPrvs.lbProveedor"));
        btnGuardar.setText(getProperty("General.botonGuardar"));
        btnCancelar.setText(getProperty("General.btnCancelar"));

        ToggleGroup group = new ToggleGroup();
        group.getToggles().add(rbtnCliente);
        group.getToggles().add(rbtnProveedor);
        group.selectToggle(rbtnCliente);
        
        txtIdSap.getProperties().put("fieldName" , getProperty("Catalogos.CltsPrvs.NuevoEditar.lblIdSAP"));
        txtRazonSocial.getProperties().put("fieldName" , getProperty("Catalogos.CltsPrvs.NuevoEditar.lblRazonSocial"));
        txtNombreEmpresa.getProperties().put("fieldName" , getProperty("Catalogos.CltsPrvs.NuevoEditar.lblNombreEmpresa"));
        txtClave.getProperties().put("fieldName" , getProperty("Catalogos.CltsPrvs.NuevoEditar.lblClave"));

        registerValidator(txtIdSap, Validator.createEmptyValidator(getProperty("General.Campo.Requerido", "IdSap")), new LengthValidator(1, 50));
        registerValidator(txtRazonSocial, Validator.createEmptyValidator(getProperty("General.Campo.Requerido", "Razon social")), new LengthValidator(1, 200));
        registerValidator(txtNombreEmpresa, Validator.createEmptyValidator(getProperty("General.Campo.Requerido", "Nombre empresa")), new LengthValidator(1, 100));
        registerValidator(txtClave, Validator.createEmptyValidator(getProperty("General.Campo.Requerido", "Clave")), new LengthValidator(1, 50));
    }

    private void inicializaClienteProveedor() {
        if (null != entidadActual) {
            lblID.setVisible(true);
            lblValorID.setVisible(true);
            lblValorID.setText(entidadActual.getId().toString());
            txtIdSap.setText(entidadActual.getIdSap());
            txtRazonSocial.setText(entidadActual.getRazonSocial());
            txtNombreEmpresa.setText(entidadActual.getNombre());
            txtClave.setText(entidadActual.getClave());
            chxEstatuseEntidad.setSelected(entidadActual.getEstatus() != 1 ? false : true);
            chxPermisoContingencia.setSelected(entidadActual.getSalidaContingencia() != 1 ? false : true);            
            rbtnCliente.setSelected(entidadActual.getEsProveedor() == 0 ? true : false);
            rbtnProveedor.setSelected(entidadActual.getEsProveedor() == 1 ? true : false);
            if (entidadActual.getEsProveedor() == 1) {
                lblPermisoContingencia.setVisible(false);
                chxPermisoContingencia.setVisible(false);
            }
            
        } else {
            lblID.setVisible(false);
            lblValorID.setVisible(false);
        }
    }

    private void inicializarEventos() {
        rbtnCliente.setOnAction((event) -> {
            lblPermisoContingencia.setVisible(true);
            chxPermisoContingencia.setVisible(true);
            chxPermisoContingencia.setSelected(false);
        });

        rbtnProveedor.setOnAction((event) -> {
            lblPermisoContingencia.setVisible(false);
            chxPermisoContingencia.setVisible(false);
        });

        btnGuardar.setOnAction((event) -> accionBtnAceptar(event));
        btnCancelar.setOnAction((event) -> Utilerias.cerrar(event));
    }

    public void accionBtnAceptar(ActionEvent e) {
        if (!displayValidationResult(true))
            return;
        obtenerDatosPantalla();
        guardarActualizarEntidad(e);
        
    }

    @Deprecated
    private boolean validaciones() {
        boolean bandera = true;

        if (txtIdSap.getText().isEmpty()) {
            bandera = false;
            lblIdSAP.setTextFill(Color.RED);
        } else {
            lblIdSAP.setTextFill(Color.BLACK);
        }

        if (txtClave.getText().isEmpty()) {
            bandera = false;
            lblClave.setTextFill(Color.RED);
        } else {
            lblClave.setTextFill(Color.BLACK);
        }

        if (txtNombreEmpresa.getText().isEmpty()) {
            lblNombreEmpresa.setTextFill(Color.RED);
            bandera = false;
        } else {
            lblNombreEmpresa.setTextFill(Color.BLACK);
        }

        if (txtRazonSocial.getText().isEmpty()) {
            bandera = false;
            lblRazonSocial.setTextFill(Color.RED);
        } else {
            lblRazonSocial.setTextFill(Color.BLACK);
        }

        if (!bandera) {
            Dialogs.create()
                    .owner(DataFXLauncher.primary)
                    .title(DataFXLauncher.getProperty("Sistema.Nombre"))
                    .masthead(DataFXLauncher.getProperty("Login.Valid.IncompleteData.Heade"))
                    .message(DataFXLauncher.getProperty("Login.Valid.IncompleteData.Message"))
                    .showWarning();
        }
        return bandera;
    }

    private void obtenerDatosPantalla() {
        if (null == entidadActual) {
            entidadActual = new SmsCteProv();
        }

        
        entidadActual.setIdSap(txtIdSap.getText().trim().toUpperCase());
        entidadActual.setRazonSocial(txtRazonSocial.getText().trim().toUpperCase());
        entidadActual.setNombre(txtNombreEmpresa.getText().trim().toUpperCase());
        entidadActual.setClave(txtClave.getText().trim().toUpperCase());
        entidadActual.setEstatus(chxEstatuseEntidad.isSelected() ? Short.parseShort("1") : Short.parseShort("0"));

        if (rbtnProveedor.isSelected()) {
            entidadActual.setEsProveedor(Short.parseShort("1"));
            entidadActual.setSalidaContingencia(Short.parseShort("0"));
        } else {
            entidadActual.setEsProveedor(Short.parseShort("0"));
            entidadActual.setSalidaContingencia(chxPermisoContingencia.isSelected() ? Short.parseShort("1") : Short.parseShort("0"));
        }
    }

    private void guardarActualizarEntidad(ActionEvent e) {
        CrearEditarClienteProveedorService cecps = new CrearEditarClienteProveedorService();

        if (null == entidadActual.getId() || 0 == entidadActual.getId()) {            
            
             List<SmsCteProv> listaidSap = new SmsCteProvDAO().findByIdSap(txtIdSap.getText().trim());
             List<SmsCteProv> listaRazonSocial = new SmsCteProvDAO().findByRazonSocial(txtRazonSocial.getText().trim());
             
            if (!listaidSap.isEmpty()) {
                Utilerias.dialogOk(getProperty("Catalogos.CltsPrvs.IdSapExistente.Header"),
                        getProperty("Catalogos.CltsPrvs.IdSapExistente.Message"));
               

            } 
            else if(!listaRazonSocial.isEmpty()){
                 Utilerias.dialogOk(getProperty("Catalogos.CltsPrvs.RazonSocialExistente.Header"),
                        getProperty("Catalogos.CltsPrvs.RazonSocialExistente.Message"));
                  
            }
            else {
                entidadActual.setSegUsuarioByCreatedBy(segUsuario);
                entidadActual.setCreatedOn(new Timestamp(Calendar.getInstance().getTimeInMillis()));
                cecps.guardarEntidad(entidadActual);
                Utilerias.dialogOk(getProperty("General.Save.Header"), getProperty("General.Save.Message"));
                regCliProv.inicializaPatalla();
                Utilerias.cerrar(e);
            }
        } else {
            entidadActual.setSegUsuarioByUpdatedBy(segUsuario);
            entidadActual.setUpdatedOn(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            cecps.actualizaClienteProveedor(entidadActual);
            Utilerias.dialogOk(getProperty("General.Update.Header"), getProperty("General.Update.Message"));
            regCliProv.inicializaPatalla();
            Utilerias.cerrar(e);
        }
        
    }
}
