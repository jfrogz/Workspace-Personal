/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.sms.controller;

import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.util.VetoException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.paint.Color;
import javax.annotation.PostConstruct;
//import javax.comm.CommPortIdentifier;
import mx.vw.swf.cdi.DataFXLauncher;
import mx.vw.swf.fwk.SerialPortUtils;
import mx.vw.swf.security.controller.MenuController;
import mx.vw.swf.fwk.model.FwkCatContent;
import mx.vw.swf.fwk.dao.FwkCatContentDAO;
import mx.vw.swf.fwk.model.EntityManagerHelper;
import mx.vw.swf.security.controller.WelcomeController;
import mx.vw.swf.sms.service.ActualizarDatosPuertoService;
import mx.vw.swf.sms.utilerias.ComboBoxValidatior;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author fox1yij
 */
@ViewController("/fxml/configuration/ConfigurarPuerto.fxml")
public class ConfigurarPuertoController extends MenuController {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox cbbBaudaje;
    @FXML
    private ComboBox cbbPuerto;
    @FXML
    private ComboBox cbbParidad;
    @FXML
    private ComboBox cbbBistDeDatos;
    @FXML
    private ComboBox cbbParidadDelPuerto;

    @FXML
    private Label puerto;
    @FXML
    private Label baudaje;
    @FXML
    private Label bitsDeDatos;
    @FXML
    private Label bitsDeParada;
    @FXML
    private Label paridadDePuerto;

    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnCancelar;
    @ActionHandler
    FlowActionHandler actionHandler;

    @FXML
    private void handleButtonAction_boton1(ActionEvent event) {
        if (displayValidationResult(false)) {
            boolean bandera = true;

            ActualizarDatosPuertoService adps = new ActualizarDatosPuertoService();
            FwkCatContentDAO actionItemDAO = new FwkCatContentDAO();
            List<FwkCatContent> findAll = actionItemDAO.findAll(11, 5);
            for (FwkCatContent fwkCatContent : findAll) {
                switch (fwkCatContent.getKeyValue()) {
                    case "BASCULA_PUERTO":
                        fwkCatContent.setDescription(cbbPuerto.getValue().toString());
                        EntityManagerHelper.beginTransaction();
                        bandera &= adps.ActualizarDatosPuerto(fwkCatContent).getDescription().equals(fwkCatContent.getDescription());
                        EntityManagerHelper.commit();
                        break;
                    case "BASCULA_BAUDIOS":
                        fwkCatContent.setDescription(cbbBaudaje.getValue().toString());
                        EntityManagerHelper.beginTransaction();
                        bandera &= adps.ActualizarDatosPuerto(fwkCatContent).getDescription().equals(fwkCatContent.getDescription());
                        EntityManagerHelper.commit();
                        break;
                    case "BASCULA_BITS":
                        fwkCatContent.setDescription(cbbBistDeDatos.getValue().toString());
                        EntityManagerHelper.beginTransaction();
                        bandera &= adps.ActualizarDatosPuerto(fwkCatContent).getDescription().equals(fwkCatContent.getDescription());
                        EntityManagerHelper.commit();
                        break;
                    case "BASCULA_PARADA":
                        fwkCatContent.setDescription(cbbParidad.getValue().toString());
                        EntityManagerHelper.beginTransaction();
                        bandera &= adps.ActualizarDatosPuerto(fwkCatContent).getDescription().equals(fwkCatContent.getDescription());
                        EntityManagerHelper.commit();
                        break;
                    case "BASCULA_PARIDAD":
                        fwkCatContent.setDescription(cbbParidadDelPuerto.getValue().toString());
                        EntityManagerHelper.beginTransaction();
                        bandera &= adps.ActualizarDatosPuerto(fwkCatContent).getDescription().equals(fwkCatContent.getDescription());
                        EntityManagerHelper.commit();
                        break;
                }
            }

            //Cargar lista de puertos disponibles en una PC
            Dialogs.create()
                    .owner(DataFXLauncher.primary)
                    .title(DataFXLauncher.getProperty("Sistema.Nombre"))
                    .masthead(DataFXLauncher.getProperty("General.Update.Header"))
                    .message(DataFXLauncher.getProperty("General.Update.Message"))
                    .showInformation();
        }
    }

    @PostConstruct
    public void PostContruct() {
        puerto.setText(DataFXLauncher.getProperty("Puerto.lblPuerto") + ":");
        baudaje.setText(DataFXLauncher.getProperty("Puerto.lblBaudaje") + ":");
        bitsDeParada.setText(DataFXLauncher.getProperty("Puerto.lblBitsDeParada") + ":");
        bitsDeDatos.setText(DataFXLauncher.getProperty("Puerto.lblBitsDeDatos") + ":");
        paridadDePuerto.setText(DataFXLauncher.getProperty("Puerto.lblParidadDePuerto") + ":");
        btnAceptar.setText(DataFXLauncher.getProperty("General.btnAceptar"));
        btnCancelar.setText(DataFXLauncher.getProperty("General.btnCancelar"));

        //Quitar linea si es una ventana pop-up
        setEncabezado(DataFXLauncher.getProperty("Puerto.Movimiento"));

        FwkCatContentDAO actionItemDAO = new FwkCatContentDAO();
        List<FwkCatContent> findAll = actionItemDAO.findAll(7, 4);
        for (FwkCatContent fwkCatContent : findAll) {
            switch (fwkCatContent.getKeyValue()) {
                case "BASCULA_OPC_BAUDIOS":
                    String[] baudajeOptions = fwkCatContent.getDescription().split(",");
                    ObservableList<String> baudiosList = FXCollections.observableArrayList(baudajeOptions);
                    cbbBaudaje.setItems(baudiosList);
                    break;
                case "BASCULA_OPC_BITDATO":
                    String[] bitDatosOptions = fwkCatContent.getDescription().split(",");
                    ObservableList<String> bitDatosList = FXCollections.observableArrayList(bitDatosOptions);
                    cbbBistDeDatos.setItems(bitDatosList);
                    break;
                case "BASCULA_OPC_BITPARA":
                    String[] bitParadaOptions = fwkCatContent.getDescription().split(",");
                    ObservableList<String> bitParadaList = FXCollections.observableArrayList(bitParadaOptions);
                    cbbParidad.setItems(bitParadaList);
                    break;
                case "BASCULA_OPC_BITPARID":
                    String[] bitParidadOptions = fwkCatContent.getDescription().split(",");
                    ObservableList<String> bitParidadList = FXCollections.observableArrayList(bitParidadOptions);
                    cbbParidadDelPuerto.setItems(bitParidadList);
                    break;
            }
        }

        //Carga la lista de puertos disponibles en el equipo
        List<String> ports = SerialPortUtils.getSerialPortsList();
        ports.stream().forEach((port) -> {
            System.out.format("\nDiscovered serial port with name: %S", port);
        });

        cbbPuerto.setItems(FXCollections.observableList(ports));

        btnCancelar.setOnAction((action) -> {
            try {
                actionHandler.navigate(WelcomeController.class);
            } catch (VetoException ex) {
                Logger.getLogger(ConfigurarPuertoController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FlowException ex) {
                Logger.getLogger(ConfigurarPuertoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        inicializarValidaciones();
        cargarValoresIniciales();

    }

    private void cargarValoresIniciales() {
        FwkCatContentDAO actionItemDAO = new FwkCatContentDAO();
        List<FwkCatContent> findAll = actionItemDAO.findAll(11, 15);
        for (FwkCatContent fwkCatContent : findAll) {
            switch (fwkCatContent.getKeyValue()) {
                case "BASCULA_PUERTO":
                    String puertoValue = fwkCatContent.getDescription();
                    cbbPuerto.getSelectionModel().select(getIndexCbb(cbbPuerto, puertoValue));
                    break;
                case "BASCULA_BAUDIOS":
                    String baudiosValue = fwkCatContent.getDescription();
                    cbbBaudaje.getSelectionModel().select(getIndexCbb(cbbBaudaje, baudiosValue));
                    break;
                case "BASCULA_BITS":
                   String bitsValue = fwkCatContent.getDescription();
                    cbbBistDeDatos.getSelectionModel().select(getIndexCbb(cbbBistDeDatos, bitsValue));
                    break;
                case "BASCULA_PARADA":
                   String paradaValue = fwkCatContent.getDescription();
                    cbbParidad.getSelectionModel().select(getIndexCbb(cbbParidad, paradaValue));
                    break;
                case "BASCULA_PARIDAD":
                   String paridadValue = fwkCatContent.getDescription();
                    cbbParidadDelPuerto.getSelectionModel().select(getIndexCbb(cbbParidadDelPuerto, paridadValue));
                    break;
            }
        }
    }

    private int getIndexCbb(ComboBox box, String value) {
        ObservableList items = box.getItems();
        if (null == items || items.size() == 0) {
            return -1;
        } else {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).equals(value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private void inicializarValidaciones() {
        cbbPuerto.getProperties().put("fieldName", DataFXLauncher.getProperty("Puerto.lblPuerto"));
        cbbBaudaje.getProperties().put("fieldName", DataFXLauncher.getProperty("Puerto.lblBaudaje"));
        cbbBistDeDatos.getProperties().put("fieldName", DataFXLauncher.getProperty("Puerto.lblBitsDeDatos"));
        cbbParidad.getProperties().put("fieldName", DataFXLauncher.getProperty("Puerto.lblBitsDeParada"));
        cbbParidadDelPuerto.getProperties().put("fieldName", DataFXLauncher.getProperty("Puerto.lblParidadDePuerto"));

        registerValidator(cbbBaudaje, new ComboBoxValidatior());
        registerValidator(cbbBistDeDatos, new ComboBoxValidatior());
        registerValidator(cbbParidad, new ComboBoxValidatior());
        registerValidator(cbbParidadDelPuerto, new ComboBoxValidatior());
        registerValidator(cbbPuerto, new ComboBoxValidatior());
    }

    /*
     @Deprecated
     private boolean validation() {
     boolean bandera = cbbBaudaje.getValue() != null && cbbBistDeDatos.getValue() != null && cbbParidad.getValue() != null && cbbParidadDelPuerto.getValue() != null;

     if (!bandera) {
     Dialogs.create()
     .owner(DataFXLauncher.primary)
     .title(DataFXLauncher.getProperty("Sistema.Nombre"))
     .masthead(DataFXLauncher.getProperty("Login.Valid.IncompleteData.Header"))
     .message(DataFXLauncher.getProperty("Login.Valid.IncompleteData.Message"))
     .showError();
     }
       
     /*Enumeration ports = CommPortIdentifier.getPortIdentifiers();
     int count =0;
     while (ports.hasMoreElements())
     {
     CommPortIdentifier curPort = (CommPortIdentifier)ports.nextElement();
     //get only serial ports
     System.out.println(curPort.getName() + " " + curPort.isCurrentlyOwned());
     count++;
     }
     System.out.println("" +count);
        
     if (cbbBaudaje.getValue() == null) {
     baudaje.setTextFill(Color.RED);
     } else {
     baudaje.setTextFill(Color.BLACK);
     }
     if (cbbBistDeDatos.getValue() == null) {
     bitsDeDatos.setTextFill(Color.RED);
     } else {
     bitsDeDatos.setTextFill(Color.BLACK);
     }
     if (cbbParidad.getValue() == null) {
     bitsDeParada.setTextFill(Color.RED);
     } else {
     bitsDeParada.setTextFill(Color.BLACK);
     }
     if (cbbParidadDelPuerto.getValue() == null) {
     paridadDePuerto.setTextFill(Color.RED);
     } else {
     paridadDePuerto.setTextFill(Color.BLACK);
     }
     if (cbbPuerto.getValue() == null) {
     puerto.setTextFill(Color.RED);
     } else {
     puerto.setTextFill(Color.BLACK);
     }
     return bandera;
     }
     */
}
