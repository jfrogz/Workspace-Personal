/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.security.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import mx.vw.swf.security.dao.SegPerfilDAO;
import mx.vw.swf.security.model.SegPerfil;
import mx.vw.swf.security.service.CrearEditarPerfilService;
import mx.vw.swf.sms.utilerias.Utilerias;
import static mx.vw.swf.cdi.DataFXLauncher.getProperty;
import static mx.vw.swf.cdi.DataFXLauncher.getSecurityProperty;
import mx.vw.swf.sms.utilerias.LengthValidator;

/**
 * FXML Controller class
 *
 * @author fox1x0d
 */
public class CrearEditarPerfilController extends Controller implements Initializable {

    private SegPerfil perfil;
    private AdministrarPerfilesController administrarPerfilesController;
    @FXML
    private TextField txtDesPerfil;
    @FXML
    private CheckBox chxEstatusPerfil;
    @FXML
    private Label id, idEtiqueta, lbDesPerfil, lbEstatus;
    @FXML
    private Button btnGuardar, btnCerrar;
    private Short status = 1;

    public CrearEditarPerfilController(SegPerfil perfil, AdministrarPerfilesController administrarPerfilesController) {
        this.perfil = perfil;
        this.administrarPerfilesController = administrarPerfilesController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializaControles();
        inicializaPerfil();
        inicializarEventos();
        initialize();
    }

    public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtDesPerfil.requestFocus();
            }
        });
    }

    public void inicializaControles() {
        txtDesPerfil.addEventFilter(KeyEvent.KEY_TYPED, Utilerias.permiteLetrasNumeros());
        Utilerias.addTextLimiter(txtDesPerfil, 50);
        
        lbDesPerfil.setText(getSecurityProperty("AdmininstraPerfiles.lbDesPerfil") + ":");
        lbEstatus.setText(getProperty("General.lbEstatus") + ":");
        id.setText(getProperty("General.lbID") + ":");
        btnCerrar.setText(getProperty("General.btnCancelar"));
        btnGuardar.setText(getProperty("General.botonGuardar"));

        txtDesPerfil.getProperties().put("fieldName", getProperty("Catalogos.Perfil.Label.Perfil"));
        registerValidator(txtDesPerfil, new LengthValidator(1, 50));

    }

    public void inicializaPerfil() {
        if (null != perfil) {
            id.setVisible(true);
            idEtiqueta.setVisible(true);
            idEtiqueta.setText(perfil.getId().toString());
            txtDesPerfil.setText(perfil.getPerfil());
            chxEstatusPerfil.setSelected(perfil.getEstatus() != 1 ? false : true);

        } else {
            id.setVisible(false);
            idEtiqueta.setVisible(false);
            idEtiqueta.setText("");

        }
    }

    public void inicializarEventos() {
        btnGuardar.setOnAction((event) -> accionBtnAceptar(event));
        btnCerrar.setOnAction((event) -> Utilerias.cerrar(event));
    }

    public void accionBtnAceptar(ActionEvent e) {
        if (!displayValidationResult(true)) {
            return;
        }
        obtenerDatosPantalla();
        guardarActualizarEntidad(e);
        
    }

    public void obtenerDatosPantalla() {
        if (null == perfil) {
            perfil = new SegPerfil();
        }
        perfil.setPerfil(txtDesPerfil.getText().trim());
        perfil.setEstatus(chxEstatusPerfil.isSelected() == true ? status : 0);
    }

    public void guardarActualizarEntidad(ActionEvent e) {

        CrearEditarPerfilService crearEditarPerfilService = new CrearEditarPerfilService();
        if (null == perfil.getId() || 0 == perfil.getId()) {

            List<SegPerfil> lista = new SegPerfilDAO().findByPerfil(txtDesPerfil.getText().trim());
            if (!lista.isEmpty()) {
                Utilerias.dialogOk(getSecurityProperty("AdmininstraPerfiles.PerfilExistente.Header"),
                        getSecurityProperty("AdmininstraPerfiles.PerfilExistente.Message"));
            } else {
                crearEditarPerfilService.guardarPerfil(perfil);
                Utilerias.dialogOk(getProperty("General.Save.Header"), getProperty("General.Save.Message"));
                administrarPerfilesController.inicializaPantalla();
                Utilerias.cerrar(e);

            }
        } else {
            crearEditarPerfilService.actualizarPerfil(perfil);
            Utilerias.dialogOk(getProperty("General.Update.Header"), getProperty("General.Update.Message"));
            administrarPerfilesController.inicializaPantalla();
            Utilerias.cerrar(e);

        }

       
    }

    public void cerrar(ActionEvent event) {
        Utilerias.cerrar(event);
    }
}
