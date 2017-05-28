package mx.vw.swf.sms.functionality.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowHandler;
import java.io.Serializable;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.annotation.PostConstruct;
import mx.vw.swf.cdi.DataFXLauncher;
import mx.vw.swf.security.model.SegUsuario;
import mx.vw.swf.sms.controller.MovimientosClientesController;
import mx.vw.swf.sms.dao.SmsProductoDAO;
import mx.vw.swf.sms.model.SmsMovimiento;
import mx.vw.swf.sms.functionality.controller.MovimientosController.TipoEntidad;
import static mx.vw.swf.sms.functionality.controller.MovimientosController.TipoEntidad.CLIENTE;
import static mx.vw.swf.sms.functionality.controller.MovimientosController.TipoEntidad.PROVEEDOR;
import mx.vw.swf.sms.service.SmsMovimientoService;
import mx.vw.swf.sms.utilerias.Utilerias;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author fox1yij
 */
@ViewController("/fxml/functionality/SalidaMovimiento.fxml")
public class SalidaMovimientoController implements Initializable, Serializable {

    //<editor-fold defaultstate="collapsed" desc="LABELS">
    @FXML
    private Label lblFolio_Salida;
    @FXML
    private Label lblFechaEntrada_Salida;
    @FXML
    private Label lblIdentidad_Salida;
    @FXML
    private Label lblProducto_Salida;
    @FXML
    private Label lblPlacas_Salida;
    @FXML
    private Label lblDestino_Salida;
    @FXML
    private Label lblPesoBruto_Salida;
    @FXML
    private Label lblPesoTara_Salida;
    @FXML
    private Label lblPesoNeto_Salida;
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="BOTONES">
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="TEXTFIELD">
    @FXML
    private TextField txtFolio;
    @FXML
    private TextField txtFecha;
    @FXML
    private TextField txtNombreIdentidad;
    @FXML
    private TextField txtNombreProducto;
    @FXML
    private TextField txtPlaca;
    @FXML
    private TextField txtDestino;
    @FXML
    private TextField txtPesoBruto;
    @FXML
    private TextField txtPesoTara;
    @FXML
    private TextField txtPesoNeto;
    @FXML
    private TextField txtPesoBascula;
    //</editor-fold>

    private SmsMovimiento movimientoActual;
    private TipoEntidad entidadActual;
    private double pesoBascula;
    private Stage stage = null;
    private SegUsuario segUsuario;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inicializarPalabras();
        inicializarEventos();
        pesoBascula = ObtenerPesoDeLaBascula();
        cargardatosDeMovimiento(movimientoActual);

    }

    SalidaMovimientoController(SmsMovimiento selectedItem, SegUsuario segUsuario) {
        movimientoActual = selectedItem;
        this.segUsuario = segUsuario;
        entidadActual = movimientoActual.getSmsCteProv().getEsProveedor() == Short.valueOf("1")
                ? PROVEEDOR : CLIENTE;
    }

    private void inicializarPalabras() {
        lblFolio_Salida.setText(DataFXLauncher.getProperty("Movimientos.Salida.lblFolio") + ":");
        lblFechaEntrada_Salida.setText(DataFXLauncher.getProperty("Movimientos.Salida.lblFechaEntrada") + ":");
        lblIdentidad_Salida.setText(entidadActual == PROVEEDOR
                ? DataFXLauncher.getProperty("General.Entidad.Proveedor") + ":"
                : DataFXLauncher.getProperty("General.Entidad.Cliente") + ":");
        lblProducto_Salida.setText(DataFXLauncher.getProperty("Movimientos.Salida.lblProducto") + ":");
        lblPlacas_Salida.setText(DataFXLauncher.getProperty("Movimientos.Salida.lblPlaca") + ":");
        lblDestino_Salida.setText(DataFXLauncher.getProperty("Movimientos.Salida.lblDestino") + ":");
        lblPesoBruto_Salida.setText(DataFXLauncher.getProperty("Movimientos.Salida.lblPesoBruto") + ":");
        lblPesoTara_Salida.setText(DataFXLauncher.getProperty("Movimientos.Salida.lblPesoTara") + ":");
        lblPesoNeto_Salida.setText(DataFXLauncher.getProperty("Movimientos.Salida.lblPesoNeto") + ":");
        btnGuardar.setText(DataFXLauncher.getProperty("General.btnAceptar"));
        btnCancelar.setText(DataFXLauncher.getProperty("General.btnCancelar"));
    }

    private boolean iniciarValidaciones() {
        switch (entidadActual) {
            //PesoEntrada=pesoTara, pesoSalida=pesoBruto
            case CLIENTE:
                if (movimientoActual.getPesoEntrada() >= pesoBascula) {
                    Action showConfirm = Dialogs.create()
                            .owner(DataFXLauncher.primary)
                            .title(DataFXLauncher.getProperty("Sistema.Nombre"))
                            .masthead(DataFXLauncher.getProperty("Movimientos.Salida.ValorIncorrecto.Header"))
                            .message(DataFXLauncher.getProperty("Movimientos.Salida.ValorIncorrecto.Message",
                                            DataFXLauncher.getProperty("Movimientos.General.Entradas.lblPesoTara"),
                                            DataFXLauncher.getProperty("Movimientos.General.Entradas.lblPesoBruto")))
                            .actions(org.controlsfx.dialog.Dialog.ACTION_OK)
                            .showWarning();
                    return false;
                }
                break;
            case PROVEEDOR:
                if (movimientoActual.getPesoSalida() <= pesoBascula) {
                    Action showConfirm = Dialogs.create()
                            .owner(DataFXLauncher.primary)
                            .title(DataFXLauncher.getProperty("Sistema.Nombre"))
                            .masthead(DataFXLauncher.getProperty("Movimientos.Salida.ValorIncorrecto.Header"))
                            .message(DataFXLauncher.getProperty("Movimientos.Salida.ValorIncorrecto.Message",
                                            DataFXLauncher.getProperty("Movimientos.General.Entradas.lblPesoBruto"),
                                            DataFXLauncher.getProperty("Movimientos.General.Entradas.lblPesoTara")))
                            .actions(org.controlsfx.dialog.Dialog.ACTION_OK)
                            .showWarning();
                    return false;
                }
                break;
        }
        return true;
    }

    private void inicializarEventos() {
        btnGuardar.setOnAction(event -> {
            if (iniciarValidaciones()) {
                guardarMovimiento();
            }
        });

        btnCancelar.setOnAction(event -> {
            salirDelSismtema(event);
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

    private void cargardatosDeMovimiento(SmsMovimiento movimiento) {
        SmsProductoDAO productoDAO = new SmsProductoDAO();
        txtFolio.setText(movimiento.getId().toString());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        txtFecha.setText(sdf.format(movimiento.getFechaEntrada()));
        txtNombreIdentidad.setText(movimiento.getSmsCteProv().getNombre());
        txtNombreProducto.setText(productoDAO.findById(movimiento.getIdProducto()).getNombre());
        txtPlaca.setText(movimiento.getPlacaVehiculo());
        txtDestino.setText(movimiento.getDestino());
        //txtPesoBruto.setText(movimiento.getpe);

        txtPesoBascula.setText(String.format("%,10.0f Kg", pesoBascula));

        switch (entidadActual) {
            //PesoEntrada=pesoTara, pesoSalida=pesoBruto
            case CLIENTE:
                txtPesoTara.setText(String.format("%,10.0f Kg", movimiento.getPesoEntrada()));
                txtPesoBruto.setText(String.format("%,10.0f Kg", pesoBascula));
                txtPesoNeto.setText(String.format("%,10.0f Kg", (pesoBascula - movimiento.getPesoEntrada())));
                break;
            //PesoEntrada=pesoBruto, pesoSalida=pesoTara
            case PROVEEDOR:
                txtPesoTara.setText(String.format("%,10.0f Kg", pesoBascula));
                txtPesoBruto.setText(String.format("%,10.0f Kg", (movimiento.getPesoEntrada())));
                txtPesoNeto.setText(String.format("%,10.0f Kg", (movimiento.getPesoEntrada() - pesoBascula)));
                break;
        }
    }

    private double ObtenerPesoDeLaBascula() {
        Random random = new Random();
        int i = (int) ((random.nextDouble() * 10000 + 10));
        return i * 10;
    }

    private void guardarMovimiento() {
        //actualizamos los campos necesarios de acuerdo al tipo
        SmsMovimientoService sms = new SmsMovimientoService();
        switch (entidadActual) {
            case CLIENTE:
                movimientoActual.setPesoSalida(pesoBascula);
                movimientoActual.setPesoNeto(movimientoActual.getPesoSalida() - movimientoActual.getPesoEntrada());
                break;
            case PROVEEDOR:
                movimientoActual.setPesoSalida(pesoBascula);
                movimientoActual.setPesoTara(pesoBascula);
                movimientoActual.setPesoNeto(movimientoActual.getPesoEntrada() - movimientoActual.getPesoSalida());
                break;
        }
        movimientoActual.setSegUsuarioByIdUsrPesoSalida(segUsuario);
        
        sms.update(movimientoActual);
        stage.close();
    }

    void setStage(Stage stage) {
        this.stage = stage;
        stage.setOnCloseRequest(event -> {
            salirDelSismtema(event);
        });
    }

}
