package mx.vw.swf.security.controller;

import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javax.annotation.PostConstruct;

import mx.com.vw.swf.exception.SWFLoginException;
import mx.vw.swf.security.service.LoginService;

import javax.naming.NamingException;

import mx.vw.swf.cdi.DataFXLauncher;
import mx.vw.swf.security.dao.SegUsuarioDAO;
import mx.vw.swf.security.model.SegUsuario;
import mx.vw.swf.sms.utilerias.LengthValidator;
import mx.vw.swf.sms.utilerias.Utilerias;
import org.controlsfx.validation.*;

/**
 * Created by dzm152z on 08/01/2015.
 */
//@FXMLController("/fxml/security/Login.fxml")
@ViewController("/fxml/security/Login.fxml")
public class LoginController extends MenuController {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblUsuario;
    @FXML
    private Label lblPassword;

    @FXML
    private Button btnInicioUsuario;
    @FXML
    private Button btnLimpiar;

    @ActionHandler
    FlowActionHandler actionHandler;

    LoginService loginService = new LoginService();

    @FXMLViewFlowContext
    ViewFlowContext viewFlowContext;

    @PostConstruct
    public void postConstruct() {
        super.postConstruct();
        welcomeMenuBar.setVisible(false);
        linkManualUsuario.setVisible(false);
        labelIdentificadorUsuario.setVisible(false);
        labelMovimientoActual.setText(DataFXLauncher.getProperty("Login.Header"));
        lblUsuario.setText(DataFXLauncher.getProperty("Login.UsrLabel") + ":");
        lblPassword.setText(DataFXLauncher.getProperty("Login.PwdLabel") + ":");
        btnInicioUsuario.setText(DataFXLauncher.getProperty("General.btnAceptar"));
        btnLimpiar.setText(DataFXLauncher.getProperty("General.btnLimpiar"));

        txtUsuario.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (txtUsuario.getText().isEmpty()) {
                    displayErrorMessage(String.format(DataFXLauncher.getProperty("Validations.CampoVacio"), txtUsuario.getProperties().get("fieldName")));
                    txtUsuario.requestFocus();
                } else if (txtPassword.getText().isEmpty()) {
                    displayErrorMessage(String.format(DataFXLauncher.getProperty("Validations.CampoVacio"), txtPassword.getProperties().get("fieldName")));
                    txtPassword.requestFocus();
                } else {
                    login(event);
                }
            }
            if (event.getCode() == KeyCode.TAB) {
                txtPassword.requestFocus();
            }
            if (event.getCode() == KeyCode.ESCAPE) {
                txtPassword.clear();
                txtUsuario.clear();
            }
        });

        txtPassword.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (txtUsuario.getText().isEmpty()) {
                    displayErrorMessage(String.format(DataFXLauncher.getProperty("Validations.CampoVacio"), txtUsuario.getProperties().get("fieldName")));
                    txtUsuario.requestFocus();
                } else if (txtPassword.getText().isEmpty()) {
                    displayErrorMessage(String.format(DataFXLauncher.getProperty("Validations.CampoVacio"), txtPassword.getProperties().get("fieldName")));
                    txtPassword.requestFocus();
                } else {
                    login(event);
                }
            }
            if (event.getCode() == KeyCode.TAB) {
                btnInicioUsuario.requestFocus();
            }
            if (event.getCode() == KeyCode.ESCAPE) {
                txtPassword.clear();
                txtUsuario.clear();
            }
        });

        btnInicioUsuario.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (txtUsuario.getText().isEmpty()) {
                    displayErrorMessage(String.format(DataFXLauncher.getProperty("Validations.CampoVacio"), txtUsuario.getProperties().get("fieldName")));
                    txtUsuario.requestFocus();
                } else if (txtPassword.getText().isEmpty()) {
                    displayErrorMessage(String.format(DataFXLauncher.getProperty("Validations.CampoVacio"), txtPassword.getProperties().get("fieldName")));
                    txtPassword.requestFocus();
                } else {
                    login(event);
                }
            }
            if (event.getCode() == KeyCode.TAB) {
                btnLimpiar.requestFocus();
            }
            if (event.getCode() == KeyCode.ESCAPE) {
                txtPassword.clear();
                txtUsuario.clear();
            }
        });

        btnLimpiar.setOnAction((ActionEvent) -> {
            txtPassword.clear();
            txtUsuario.clear();
        });

        inicialilzarValidaciones();
        sobreEscribirSalir();
        initialize();
    }

    public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtUsuario.requestFocus();
            }
        });
    }

    private void sobreEscribirSalir() {
        labelSalida.setText(DataFXLauncher.getProperty("General.Exit"));
        labelSalida.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Utilerias.cerrar(event);
            }
        });

        imagenSalida.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Utilerias.cerrar(event);
            }
        });
    }

    private void inicialilzarValidaciones() {
        txtUsuario.getProperties().put("fieldName", DataFXLauncher.getProperty("Login.UsrLabel"));
        txtPassword.getProperties().put("fieldName", DataFXLauncher.getProperty("Login.PwdLabel"));

        //registerValidator(txtUsuario, Validator.combine(Validator.createEmptyValidator("El usuario no puede ser vacío"), new LengthValidator(1,10)));
        registerValidator(txtUsuario, Validator.combine(
                Validator.createEmptyValidator(
                        String.format(DataFXLauncher.getProperty("Validations.CampoVacio"),
                                txtUsuario.getProperties().get("fieldName"))),
                new LengthValidator(1, 20)));

        registerValidator(txtPassword, Validator.createEmptyValidator(
                String.format(DataFXLauncher.getProperty("Validations.CampoVacio"),
                        txtPassword.getProperties().get("fieldName"))));

        txtUsuario.addEventFilter(KeyEvent.KEY_TYPED, Utilerias.permiteLetrasNumeros());
        txtPassword.addEventFilter(KeyEvent.KEY_TYPED, Utilerias.permiteLetrasNumeros());
        Utilerias.addTextLimiter(txtUsuario, 20);
        Utilerias.addTextLimiter(txtPassword, 20);
    }

    public void login(Event event) {
        SegUsuario loggedUser = null;
        if (displayValidationResult(false)) {
            try {
                String username = txtUsuario.getText();
                String password = txtPassword.getText();

                SegUsuarioDAO sudao = new SegUsuarioDAO();

                List<SegUsuario> findByUserId = sudao.findByUserId(username);
                if (findByUserId.isEmpty()) {
                    displayErrorMessage(DataFXLauncher.getProperty("Login.Valid.NoAccess.Message"));
                } else {
                    loggedUser = loginService.tryLogin(username, password);
                }
            } catch (SWFLoginException | NamingException e) {
                displayErrorMessage(e.getMessage());
                showError(DataFXLauncher.getProperty("Login.Valid.Credentials.Header"), e.getMessage());
                return;
            }
        }

        if (null != loggedUser) {
            viewFlowContext.register("USERPRINCIPAL", loggedUser);
            try {
                actionHandler.navigate(WelcomeController.class);
                
            } catch (VetoException | FlowException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                displayErrorMessage(ex.getMessage());
            }
        } else {
            displayErrorMessage(DataFXLauncher.getSecurityProperty("errorAuthLDAP"));
        }
    }
}
