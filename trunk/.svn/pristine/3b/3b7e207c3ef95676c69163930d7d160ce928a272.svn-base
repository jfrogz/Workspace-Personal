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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import mx.vw.swf.security.controller.Controller;
import mx.vw.swf.security.model.SegUsuario;
import mx.vw.swf.sms.utilerias.LengthValidator;
import mx.vw.swf.sms.utilerias.Utilerias;
import org.controlsfx.validation.Validator;

import static mx.vw.swf.cdi.DataFXLauncher.*;
import mx.vw.swf.sms.dao.SmsProductoDAO;
import mx.vw.swf.sms.model.SmsProducto;
import mx.vw.swf.sms.service.CrearEditarProductoService;

/**
 * FXML Controller class
 *
 * @author fox1yij
 */
@ViewController("/fxml/catalogos/CrearEditarProductos.fxml")
public class CrearEditarProductos extends Controller implements Initializable {

    //labels
    @FXML
    private Label lblID;
    @FXML
    private Label lblIdSAP;
    @FXML
    private Label lblNombreProducto;
    @FXML
    private Label lblClave;
    @FXML
    private Label lblStatus;
    //Componentes
    @FXML
    private Label lblValorID;
    @FXML
    private TextField txtIdSap, txtNombreProducto;
    @FXML
    private TextField txtClave;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private CheckBox chxEstatuseEntidad;

    private SmsProducto entidadActual;
    private SegUsuario segUsuario;
    private AdministrarProductosController adminProductos;

    public CrearEditarProductos(SegUsuario segUsuario, SmsProducto entidadActual, AdministrarProductosController adminProductos) {
        this.segUsuario = segUsuario;
        this.entidadActual = entidadActual;
        this.adminProductos = adminProductos;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarControles();
        inicializaProducto();
        inicializarEventos();
    }

    private void inicializarControles() {
        txtIdSap.addEventFilter(KeyEvent.KEY_TYPED, Utilerias.permiteLetrasNumeros());
        txtClave.addEventFilter(KeyEvent.KEY_TYPED, Utilerias.permiteLetrasNumeros());

        Utilerias.addTextLimiter(txtIdSap, 50);
        Utilerias.addTextLimiter(txtClave, 50);

        lblID.setText(getProperty("Catalogos.CltsPrvs.NuevoEditar.lblID") + ":");
        lblIdSAP.setText(getProperty("Catalogos.CltsPrvs.NuevoEditar.lblIdSAP") + ":");
        lblNombreProducto.setText(getProperty("Catalogos.CltsProductos.lbProducto") + ":");
        lblClave.setText(getProperty("Catalogos.CltsPrvs.NuevoEditar.lblClave") + ":");
        lblStatus.setText(getProperty("Catalogos.CltsPrvs.NuevoEditar.lblStatus") + ":");
        btnGuardar.setText(getProperty("General.botonGuardar"));
        btnCancelar.setText(getProperty("General.btnCancelar"));

        txtIdSap.getProperties().put("fieldName", getProperty("Catalogos.CltsPrvs.NuevoEditar.lblIdSAP"));
        txtNombreProducto.getProperties().put("fieldName", getProperty("Catalogos.CltsProductos.lbProducto"));
        txtClave.getProperties().put("fieldName", getProperty("Catalogos.CltsPrvs.NuevoEditar.lblClave"));

        registerValidator(txtIdSap, Validator.createEmptyValidator(getProperty("General.Campo.Requerido", "IdSap")), new LengthValidator(1, 50));
        registerValidator(txtNombreProducto, Validator.createEmptyValidator(getProperty("General.Campo.Requerido", "Razon social")), new LengthValidator(1, 100));
        registerValidator(txtClave, Validator.createEmptyValidator(getProperty("General.Campo.Requerido", "Clave")), new LengthValidator(1, 50));
    }

    private void inicializaProducto() {
        if (null != entidadActual) {
            lblID.setVisible(true);
            lblValorID.setVisible(true);
            lblValorID.setText(entidadActual.getId().toString());
            txtIdSap.setText(entidadActual.getIdSap());
            txtNombreProducto.setText(entidadActual.getNombre());
            txtClave.setText(entidadActual.getClave());
            chxEstatuseEntidad.setSelected(entidadActual.getEstatus() != 1 ? false : true);
        } else {
            lblID.setVisible(false);
            lblValorID.setVisible(false);
        }
    }

    private void inicializarEventos() {
        btnGuardar.setOnAction((event) -> accionBtnAceptar(event));
        btnCancelar.setOnAction((event) -> Utilerias.cerrar(event));
    }

    public void accionBtnAceptar(ActionEvent e) {
        if (!displayValidationResult(true)) {
            return;
        }
        obtenerDatosPantalla();
        guardarActualizarEntidad(e);

    }

    private void obtenerDatosPantalla() {
        if (null == entidadActual) {
            entidadActual = new SmsProducto();
        }

        entidadActual.setIdSap(txtIdSap.getText().trim().toUpperCase());
        entidadActual.setNombre(txtNombreProducto.getText().trim().toUpperCase());
        entidadActual.setClave(txtClave.getText().trim().toUpperCase());
        entidadActual.setEstatus(chxEstatuseEntidad.isSelected() ? Short.parseShort("1") : Short.parseShort("0"));

    }

    private void guardarActualizarEntidad(ActionEvent e) {
        CrearEditarProductoService crearEditarProductoService = new CrearEditarProductoService();

        if (null == entidadActual.getId() || 0 == entidadActual.getId()) {

            List<SmsProducto> listaidSap = new SmsProductoDAO().findByIdSap(txtIdSap.getText().trim());

            if (!listaidSap.isEmpty()) {
                Utilerias.dialogOk(getProperty("Catalogos.CltsPrvs.IdSapExistente.Header"),
                        getProperty("Catalogos.CltsPrvs.IdSapExistente.Message"));

            } else {
                entidadActual.setSegUsuarioByCreatedBy(segUsuario);
                entidadActual.setCreatedOn(new Timestamp(Calendar.getInstance().getTimeInMillis()));
                crearEditarProductoService.guardarEntidad(entidadActual);
                Utilerias.dialogOk(getProperty("General.Save.Header"), getProperty("General.Save.Message"));
                adminProductos.inicializaPatalla();
                Utilerias.cerrar(e);
            }
        } else {
            entidadActual.setSegUsuarioByUpdatedBy(segUsuario);
            entidadActual.setUpdatedOn(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            crearEditarProductoService.actualizaProducto(entidadActual);
            Utilerias.dialogOk(getProperty("General.Update.Header"), getProperty("General.Update.Message"));
            adminProductos.inicializaPatalla();
            Utilerias.cerrar(e);
        }

    }
}
