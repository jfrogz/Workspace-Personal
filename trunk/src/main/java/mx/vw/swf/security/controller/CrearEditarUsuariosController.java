/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.security.controller;

import java.net.URL;
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
import mx.vw.swf.security.dao.SegUsuarioDAO;
import mx.vw.swf.security.service.CrearEditarUsuariosService;
import mx.vw.swf.security.model.SegUsuario;
import mx.vw.swf.sms.utilerias.LengthValidator;
import mx.vw.swf.sms.utilerias.Utilerias;
import static mx.vw.swf.cdi.DataFXLauncher.getProperty;
import static mx.vw.swf.cdi.DataFXLauncher.getSecurityProperty;
import mx.vw.swf.sms.utilerias.EmailValidator;
import mx.vw.swf.sms.utilerias.LengthNoEmptyValidator;
import org.controlsfx.validation.Validator;

/**
 * FXML Controller class
 *
 * @author fox1x0d
 */
public class CrearEditarUsuariosController extends Controller implements Initializable {

    @FXML
    private TextField txtUsuario, txtNombreUsuario, txtApellidoPat, txtApellidoMat, txtCorreo;
    @FXML
    private CheckBox chxEstatusUsuario;
    @FXML
    private Label id, idEtiqueta, lbUsuario, lbNombre, lbApPaterno, lbApMaterno, lbCorreo, lbEstatus, lbUsuarioEdicion;
    @FXML
    private Button btnGuardar, btnCancelar;
    private Short status = 1;
    private SegUsuario usuario;
    private AdministrarUsuariosController administrarUsuarios;

    public CrearEditarUsuariosController(SegUsuario usuario, AdministrarUsuariosController administrarUsuarios) {
        this.usuario = usuario;
        this.administrarUsuarios = administrarUsuarios;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inicializaControles();
        inicializaUsuario();
        inicializarEventos();
       
    }
    
    public void inicializaControles() {
        

        btnGuardar.setText(getProperty("General.botonGuardar"));
        btnCancelar.setText(getProperty("General.btnCancelar"));
        lbUsuario.setText(getProperty("Login.UsrLabel") + ":");
        lbNombre.setText(getSecurityProperty("AdmininstraUsuario.lbNombre") + ":");
        lbApPaterno.setText(getSecurityProperty("AdmininstraUsuario.lbApPaterno") + ":");
        lbApMaterno.setText(getSecurityProperty("AdmininstraUsuario.lbApMaterno") + ":");
        lbCorreo.setText(getSecurityProperty("AdmininstraUsuario.lbCorreo") + ":");
        lbEstatus.setText(getProperty("General.lbEstatus") + ":");
        id.setText(getProperty("General.lbID") + ":");

        txtUsuario.getProperties().put("fieldName", getProperty("Login.UsrLabel"));
        txtNombreUsuario.getProperties().put("fieldName", getSecurityProperty("AdmininstraUsuario.lbNombre"));
        txtApellidoPat.getProperties().put("fieldName", getSecurityProperty("AdmininstraUsuario.lbApPaterno"));
        txtApellidoMat.getProperties().put("fieldName", getSecurityProperty("AdmininstraUsuario.lbApMaterno"));
        txtCorreo.getProperties().put("fieldName", getSecurityProperty("AdmininstraUsuario.lbCorreo"));
            
        registerValidator(txtNombreUsuario, new LengthValidator(1, 75));
        registerValidator(txtUsuario, new LengthValidator(1, 20));
        registerValidator(txtCorreo, Validator.combine(new EmailValidator(), new LengthNoEmptyValidator(1, 100)));

    }

    public void inicializaUsuario() {

        if (null != usuario) {

            id.setVisible(true);
            idEtiqueta.setVisible(true);
            idEtiqueta.setText(usuario.getId().toString());
            lbUsuarioEdicion.setText(usuario.getUserId());
            txtUsuario.setText(usuario.getUserId());
            txtNombreUsuario.setText(usuario.getNombreUsuario());
            txtApellidoPat.setText(usuario.getApPaterno());
            txtApellidoMat.setText(usuario.getApMaterno());
            txtCorreo.setText(usuario.getCorreoE());
            chxEstatusUsuario.setSelected(usuario.getEstatus() != 1 ? false : true);
            txtUsuario.setVisible(false);
            lbUsuarioEdicion.setVisible(true);
            
         if(null==usuario.getApPaterno()){
                registerValidator(txtApellidoPat, new LengthNoEmptyValidator(1, 75));
         } else if(null==usuario.getApMaterno()){
              registerValidator(txtApellidoMat, new LengthNoEmptyValidator(1, 75));

         }
           
            

        } else {
            id.setVisible(false);
            idEtiqueta.setVisible(false);
            idEtiqueta.setText("");
            txtUsuario.setVisible(true);
            lbUsuarioEdicion.setVisible(false);            
            registerValidator(txtApellidoPat, new LengthValidator(1, 75));
            registerValidator(txtApellidoMat, new LengthValidator(1, 75));

        }

    }

    public void inicializarEventos() {
        txtUsuario.addEventFilter(KeyEvent.KEY_TYPED, Utilerias.permiteLetrasNumeros());
        txtNombreUsuario.addEventFilter(KeyEvent.KEY_TYPED, Utilerias.permiteLetras());
        txtApellidoPat.addEventFilter(KeyEvent.KEY_TYPED, Utilerias.permiteLetras());
        txtApellidoMat.addEventFilter(KeyEvent.KEY_TYPED, Utilerias.permiteLetras());
        Utilerias.addTextLimiter(txtUsuario, 20);
        Utilerias.addTextLimiter(txtNombreUsuario, 75);
        Utilerias.addTextLimiter(txtApellidoPat, 75);
        Utilerias.addTextLimiter(txtApellidoMat, 75);
        
        btnGuardar.setOnAction((event) -> accionBtnAceptar(event));
        btnCancelar.setOnAction((event) -> Utilerias.cerrar(event));

        txtApellidoPat.setOnKeyReleased((KeyEvent event) -> {
            validaApellidos();
        });

        txtApellidoMat.setOnKeyReleased((KeyEvent event) -> {
            validaApellidos();
        });
        
    }

    public void validaApellidos() {

        if (txtApellidoPat.getText().isEmpty() && txtApellidoMat.getText().isEmpty()) {
            registerValidator(txtApellidoMat, new LengthValidator(1, 75));
            registerValidator(txtApellidoPat, new LengthValidator(1, 75));
        } else {
            registerValidator(txtApellidoPat, new LengthNoEmptyValidator(1, 75));
            registerValidator(txtApellidoMat, new LengthNoEmptyValidator(1, 75));
        }

    }

    public void accionBtnAceptar(ActionEvent e) {
        if (!displayValidationResult(true)) {
            return;
        }
        obtenerDatosPantalla();
        guardarActualizarEntidad(e);
        
    }

    public void obtenerDatosPantalla() {
        if (null == usuario) {
            usuario = new SegUsuario();
        }
        usuario.setUserId(txtUsuario.getText().trim());
        usuario.setNombreUsuario(txtNombreUsuario.getText().trim());
        usuario.setApMaterno(txtApellidoMat.getText().trim());
        usuario.setApPaterno(txtApellidoPat.getText().trim());
        usuario.setCorreoE(txtCorreo.getText().trim());
        usuario.setEstatus(chxEstatusUsuario.isSelected() == true ? status : 0);

    }

    public void guardarActualizarEntidad(ActionEvent e) {
        CrearEditarUsuariosService crearEditarUsuariosService = new CrearEditarUsuariosService();

        if (null == usuario.getId() || 0 == usuario.getId()) {
            List<SegUsuario> lista = new SegUsuarioDAO().findByUserId(txtUsuario.getText().trim());
            if (!lista.isEmpty()) {
                Utilerias.dialogOk(getSecurityProperty("AdmininstraUsuario.UserExistente.Header"),
                        getSecurityProperty("AdmininstraUsuario.UserExistente.Message"));

            } else {
                crearEditarUsuariosService.guardaUsuario(usuario);
                Utilerias.dialogOk(getProperty("General.Save.Header"), getProperty("General.Save.Message"));
                administrarUsuarios.inicializaPatalla();
                Utilerias.cerrar(e);
            }
        } else {

            crearEditarUsuariosService.actualizaUsuario(usuario);
            Utilerias.dialogOk(getProperty("General.Update.Header"), getProperty("General.Update.Message"));
            administrarUsuarios.inicializaPatalla();
            Utilerias.cerrar(e);
        }

        
    }

}