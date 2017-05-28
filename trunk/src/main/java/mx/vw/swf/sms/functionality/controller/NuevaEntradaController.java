package mx.vw.swf.sms.functionality.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import io.datafx.controller.ViewController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import mx.vw.swf.cdi.DataFXLauncher;
import mx.vw.swf.security.controller.Controller;
import mx.vw.swf.security.model.SegUsuario;
import mx.vw.swf.sms.model.SmsCteProv;
import mx.vw.swf.sms.model.SmsMovimiento;
import mx.vw.swf.sms.model.SmsProducto;
import mx.vw.swf.sms.service.SmsMovimientoService;
import mx.vw.swf.sms.utilerias.Utilerias;
import org.controlsfx.validation.Validator;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import mx.vw.swf.security.controller.LoginController;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author fox1yij
 */
@ViewController("/fxml/functionality/NuevaEntrada.fxml")
public class NuevaEntradaController extends Controller implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="Labels FXML">    
    @FXML
    Label NuevaEntrada_lblNombreChofer;
    @FXML
    Label NuevaEntrada_lblPlaca;
    @FXML
    Label NuevaEntrada_lblNumContrato;
    @FXML
    Label NuevaEntrada_lblDestino;
    @FXML
    Label NuevaEntrada_lblPeso;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Botones FXML">
    @FXML
    Button NuevaEntrada_btnGuardar;
    @FXML
    Button NuevaEntrada_btnCancelar;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="TextFields FXML">
    @FXML
    TextField NuevaEntrada_txtFolio;

    @FXML
    TextField NuevaEntrada_txtNombreChofer;
    @FXML
    TextField NuevaEntrada_txtNumPlaca;
    @FXML
    TextField NuevaEntrada_txtNumContrato;
    @FXML
    TextField NuevaEntrada_txtDestino;
    @FXML
    TextField NuevaEntrada_txtPeso;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Del Sistema">
    private Stage stage;
    private Timeline timeline;

    private SmsCteProv smsCteProv;
    private SmsProducto smsProducto;
    private SegUsuario segUsuario;

    private double pesoBascula = 0;
    //</editor-fold>

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarLabels();
        inicializarValidaciones();
        inicializarEventos();
    }

    public NuevaEntradaController(SmsCteProv smsCteProv, SmsProducto producto, SegUsuario segUsuario) {
        this.smsCteProv = smsCteProv;
        this.smsProducto = producto;
        this.segUsuario = segUsuario;
    }

    private void inicializarLabels() {
        NuevaEntrada_lblNombreChofer.setText(DataFXLauncher.getProperty("NuevaEntrada.lblNombreChofer") + ":");
        NuevaEntrada_lblPlaca.setText(DataFXLauncher.getProperty("NuevaEntrada.lblPlaca") + ":");
        NuevaEntrada_lblNumContrato.setText(DataFXLauncher.getProperty("NuevaEntrada.lblNumContrato") + ":");
        NuevaEntrada_lblDestino.setText(DataFXLauncher.getProperty("NuevaEntrada.lblDestino") + ":");
        NuevaEntrada_lblPeso.setText(DataFXLauncher.getProperty("NuevaEntrada.lblPeso") + ":");
        NuevaEntrada_btnGuardar.setText(DataFXLauncher.getProperty("General.btnAceptar"));
        NuevaEntrada_btnCancelar.setText(DataFXLauncher.getProperty("General.btnCancelar"));
        pesoBascula = ObtenerPesoDeLaBascula();
        NuevaEntrada_txtPeso.setText(pesoBascula + " Kg");
    }

    private void inicializarValidaciones() {
        NuevaEntrada_txtNombreChofer.addEventFilter(KeyEvent.KEY_TYPED, Utilerias.permiteLetras());
        NuevaEntrada_txtNumPlaca.addEventFilter(KeyEvent.KEY_TYPED, Utilerias.permiteLetrasNumeros());
        NuevaEntrada_txtNumContrato.addEventFilter(KeyEvent.KEY_TYPED, Utilerias.permiteLetrasNumeros());
        NuevaEntrada_txtDestino.addEventFilter(KeyEvent.KEY_TYPED, Utilerias.permiteLetrasNumeros());

        Utilerias.addTextLimiter(NuevaEntrada_txtNombreChofer, 50);
        Utilerias.addTextLimiter(NuevaEntrada_txtNumPlaca, 7);
        Utilerias.addTextLimiter(NuevaEntrada_txtNumContrato, 50);
        Utilerias.addTextLimiter(NuevaEntrada_txtDestino, 50);

        registerValidator(NuevaEntrada_txtNumPlaca, Validator.createEmptyValidator(DataFXLauncher.getProperty("Validations.CampoVacio", DataFXLauncher.getProperty("NuevaEntrada.lblPlaca"))));
        registerValidator(NuevaEntrada_txtNumContrato, Validator.createEmptyValidator(DataFXLauncher.getProperty("Validations.CampoVacio", DataFXLauncher.getProperty("NuevaEntrada.lblNumContrato"))));
        registerValidator(NuevaEntrada_txtDestino, Validator.createEmptyValidator(DataFXLauncher.getProperty("Validations.CampoVacio", DataFXLauncher.getProperty("NuevaEntrada.lblDestino"))));
    }

    private void inicializarEventos() {
        /*
         timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
         NuevaEntrada_txtFecha.setText(sdf.format(Calendar.getInstance().getTime()));
         }));
         timeline.setCycleCount(Animation.INDEFINITE);
         timeline.play();
         */
        NuevaEntrada_btnGuardar.setOnAction((even) -> {
            if (displayValidationResult(false)) {
                guardarMovimiento();
            }
        });

        NuevaEntrada_btnCancelar.setOnAction((even) -> {
            salirDelSismtema(even);
        });
    }

    private void salirDelSismtema(Event even) {
        Action showConfirm = Dialogs.create()
                .owner(DataFXLauncher.primary)
                .title(DataFXLauncher.getProperty("Sistema.Nombre"))
                .masthead(DataFXLauncher.getProperty("NuevaEntrada.Cancelar.Header"))
                .message(DataFXLauncher.getProperty("NuevaEntrada.Cancelar.Message"))
                .actions(org.controlsfx.dialog.Dialog.ACTION_YES, org.controlsfx.dialog.Dialog.ACTION_NO)
                .showConfirm();

        if (showConfirm == org.controlsfx.dialog.Dialog.ACTION_YES) {
            stage.close();
        } else {
            even.consume();
        }
    }

    private double ObtenerPesoDeLaBascula() {
        Random random = new Random();
        int i = (int) ((random.nextDouble() * 10000 + 10));
        return i * 10;
    }

    private SmsMovimiento obtenerDatos() {
        SmsMovimiento movimiento = new SmsMovimiento();
        movimiento.setSmsCteProv(smsCteProv);
        movimiento.setEsMovCliente(smsCteProv.getEsProveedor() == 1 ? Short.valueOf(0 + "") : Short.valueOf(1 + ""));
        movimiento.setIdProducto(smsProducto.getId());
        movimiento.setIdCteProvSap(smsCteProv.getIdSap());
        movimiento.setIdProductoSap(smsProducto.getIdSap());
        movimiento.setPlacaVehiculo(NuevaEntrada_txtNumPlaca.getText().toUpperCase().trim());
        movimiento.setDestino(NuevaEntrada_txtDestino.getText().trim().toUpperCase());
        movimiento.setFactura(NuevaEntrada_txtNumContrato.getText().trim().toUpperCase());
        movimiento.setPesoEntrada(pesoBascula);
        if (smsCteProv.getEsProveedor() == 0) {
            movimiento.setPesoTara(movimiento.getPesoEntrada());
        }
        movimiento.setSegUsuarioByIdUsrPesoEntrada(segUsuario);
        movimiento.setEnviarSap(movimiento.getEsMovCliente());
        movimiento.setEnviadoSap(Short.valueOf("0"));
        movimiento.setBasculaContingEntrada(Short.valueOf("0"));
        movimiento.setBasculaContingSalida(Short.valueOf("0"));
        movimiento.setCancelado(Short.valueOf("0"));
        movimiento.setNombreConductor(NuevaEntrada_txtNombreChofer.getText().trim().toUpperCase());
        return movimiento;
    }

    private void guardarMovimiento() {
        SmsMovimientoService sms = new SmsMovimientoService();
        sms.save(obtenerDatos());
        stage.close();
    }

    public void setStage(Stage s) {
        stage = s;
        stage.setOnCloseRequest(even -> {
            salirDelSismtema(even);
        });
        /*
         stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
         @Override
         public void handle(WindowEvent event) {
         timeline.stop();
         }
         });*/
    }

}
