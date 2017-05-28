/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.ExpedienteDao;
import Utilerias.Utilerias;
import io.datafx.controller.ViewController;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.annotation.PostConstruct;
import DAO.ResponsablesDao;
import Identities.Expediente;
import Identities.Responsable;
import io.datafx.core.concurrent.DataFxTask;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;
import protocolsearch.ProtocolSearch;
import javafx.concurrent.Task;
import javafx.event.EventType;
import javafx.scene.control.SelectionMode;

/**
 *
 * @author fernandorobles
 */
@ViewController("/Views/PrincipalView.fxml")
public class PrincipalController implements Initializable {

    private @FXML
    Menu mnHerramientas;
    private @FXML
    MenuItem mniPreferencias;
    private @FXML
    Label lblResponsables;
    private @FXML
    ComboBox<Responsable> cbbResponsables;
    private @FXML
    Button btnAgnadir;
    private @FXML
    Button btnQuitarusuario;
    private @FXML
    Button btnLimpiarLista;
    private @FXML
    Button btnBusqueda;
    private @FXML
    TableView tvUsuarios;
    private @FXML
    Label lblMensajeInferior;

    private @FXML
    TableColumn<Responsable, String> col_Nombre;

    ObservableList<Responsable> listaDeResponsables = FXCollections.observableArrayList();
    ObservableList<Responsable> responsablesSeleccionados = FXCollections.observableArrayList();

    @PostConstruct
    public void postConstruct() {
        col_Nombre.setCellValueFactory(cellData -> cellData.getValue().getNombreProperty());
        cbbResponsables.setConverter(new StringConverter<Responsable>() {
            @Override
            public String toString(Responsable object) {
                return object.getNombre();
            }

            @Override
            public Responsable fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        cbbResponsables.valueProperty().addListener((ov, OldObject, NewObject) -> {
            btnAgnadir.setDisable(cbbResponsables.getSelectionModel().isEmpty());
        });
        btnBusqueda.setDisable(true);
        btnLimpiarLista.setDisable(true);
        btnQuitarusuario.setDisable(true);
        btnAgnadir.setDisable(true);

        tvUsuarios.getSelectionModel().selectedItemProperty().addListener((ob, oldObject, newObject) -> {
            btnQuitarusuario.setDisable(newObject == null);

        });

        btnLimpiarLista.setOnAction((event) -> {
            responsablesSeleccionados.clear();
            tvUsuarios.getItems().clear();
            btnLimpiarLista.setDisable(tvUsuarios.getItems()==null || tvUsuarios.getItems().isEmpty());
            btnBusqueda.setDisable(tvUsuarios.getItems()==null || tvUsuarios.getItems().isEmpty());
        });

        mniPreferencias.setOnAction((event) -> {
            AbrirVentanaDeConfiguracion();
            validarConexion();
        });

        btnQuitarusuario.setOnAction((event) -> {
            Responsable r = (Responsable) tvUsuarios.getSelectionModel().selectedItemProperty().get();
            responsablesSeleccionados.remove(r);
            tvUsuarios.setItems(null);

            tvUsuarios.setItems(responsablesSeleccionados);
            tvUsuarios.getSelectionModel().clearSelection();
            
            btnLimpiarLista.setDisable(tvUsuarios.getItems()==null || tvUsuarios.getItems().isEmpty());
            btnBusqueda.setDisable(tvUsuarios.getItems()==null || tvUsuarios.getItems().isEmpty());
        });

        btnAgnadir.setOnAction((event) -> {
            if (!cbbResponsables.getSelectionModel().isEmpty()) {
                //Pendiente Verificar que no se repitan los usuarios.
                Responsable aux = cbbResponsables.getSelectionModel().getSelectedItem();
                boolean existe = false;
                for (Responsable responsable : responsablesSeleccionados) {
                    if (responsable.equals(aux)) {
                        existe = true;
                    }
                }
                if (!existe) {
                    responsablesSeleccionados.add(aux);
                    tvUsuarios.setItems(responsablesSeleccionados);
                }
            }
            
            btnLimpiarLista.setDisable(tvUsuarios.getItems()==null || tvUsuarios.getItems().isEmpty());
            btnBusqueda.setDisable(tvUsuarios.getItems()==null || tvUsuarios.getItems().isEmpty());
        });

        btnBusqueda.setOnAction((event) -> {
            AbrirVentanaDeEjecucion(event);
        });

        try {
            if (ProtocolSearch.ActualConfig.getConexion() != null && !ProtocolSearch.ActualConfig.getConexion().isClosed()) {
                //Deshabiliar controles
                cargarListaDeResponsables();
            }
        } catch (SQLException ex) {

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            Utilerias.leerArchivoDeConfiguracion();
            if (ProtocolSearch.ActualConfig == null || ProtocolSearch.ActualConfig.getConexion() == null || ProtocolSearch.ActualConfig.getConexion().isClosed()) {
                //Abrir panel de configuracion
                AbrirVentanaDeConfiguracion();
                validarConexion();
                //Al cerrar verificar que exista conexion sino pues deshabilitar todas los controles.
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void validarConexion() {
        try {
            if (ProtocolSearch.ActualConfig.getConexion() == null || ProtocolSearch.ActualConfig.getConexion().isClosed()) {
                System.out.println("Se bloquea pantalla");
            } else {
                System.out.println("Se libera pantalla");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void AbrirVentanaDeConfiguracion() {
        Stage stage = null;
        ConfiguracionController ccontroller = new ConfiguracionController();
        stage = Utilerias.nuevaVentana("/Views/ConfiguracionView.fxml", ccontroller, "Configuración");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);
        stage.initOwner(protocolsearch.ProtocolSearch.primary);
        ccontroller.setStage(stage);
        ccontroller.InicializarEventos();
        stage.show();
    }

    private void AbrirVentanaDeEjecucion(Event ev) {
        Stage stage = null;
        ProcesandoController pc = new ProcesandoController(responsablesSeleccionados);
        stage = Utilerias.nuevaVentana("/Views/ProcesandoView.fxml", pc, "Procesando");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);
        stage.initOwner(((Node) ev.getSource()).getScene().getWindow());
        //stage.initOwner(protocolsearch.ProtocolSearch.primary);
        pc.setStage(stage);
        stage.show();

    }

    public void cargarListaDeResponsables() {
        listaDeResponsables = FXCollections.observableArrayList();
        ResponsablesDao.ObtenerlistaDeResponsables().stream().forEach((respon) -> {
            listaDeResponsables.add(respon);
        });
        cbbResponsables.setItems(listaDeResponsables);
    }

    
}
