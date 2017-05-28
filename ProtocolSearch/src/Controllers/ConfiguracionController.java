/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import DAO.ConeccionAccess;
import io.datafx.controller.ViewController;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import protocolsearch.ProtocolSearch;
import Utilerias.ConfiguracionActual;
import Utilerias.Utilerias;
/**
 *
 * @author fernandorobles
 */
@ViewController ("/Views/ConfiguracionView.fxml")
public class ConfiguracionController implements Initializable{

    
    private @FXML Button btnSeleccionarAccess;
    private @FXML Button btnProbarConexion;
    private @FXML Button btnSeleccionarProtocolos;
    private @FXML Button btnSeleccionarReportes;
    private @FXML Button btnGuardarConfiguracion;
    
    private @FXML TextField txtUbicacionAccess;
    private @FXML TextField txtUsuario;
    private @FXML TextField txtPassword;
    private @FXML TextField txtUbicacionProtocolos;
    private @FXML TextField txtPathReportes;
    
    private @FXML Label lblStatusConexion;
    
    private Stage stage = null;
    
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InicializarEventos();
    }
    
    public void InicializarEventos() {
        btnProbarConexion.setDisable(true);
        txtUbicacionAccess.textProperty().addListener((observable, oldValue, newValue) -> {
            btnProbarConexion.setDisable(txtUbicacionAccess.getText().isEmpty() || txtUsuario.getText().isEmpty());
        });
        txtUsuario.textProperty().addListener((observable, oldValue, newValue) -> {
            btnProbarConexion.setDisable(txtUbicacionAccess.getText().isEmpty() || txtUsuario.getText().isEmpty());
        });
        btnSeleccionarAccess.setOnAction((event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Buscar archivo de Access");

            // Agregar filtros para facilitar la busqueda
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("MDB", "*.mdb")
            );
            File archivo = fileChooser.showOpenDialog(stage);

            // Mostar la imagen
            if (archivo != null) {
                txtUbicacionAccess.setText(archivo.getAbsolutePath());
            }
        });

        btnSeleccionarProtocolos.setOnAction((event) -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Ruta donde se guardan los protocolos");
            File showDialog = directoryChooser.showDialog(stage);
            if (showDialog != null) {
                txtUbicacionProtocolos.setText(showDialog.getAbsolutePath());
            } else {
                txtUbicacionProtocolos.clear();
            }
        });

        btnSeleccionarReportes.setOnAction((event) -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Ruta donde se guardaran los reportes");
            File showDialog = directoryChooser.showDialog(stage);
            if (showDialog != null) {
                txtPathReportes.setText(showDialog.getAbsolutePath());
            } else {
                txtPathReportes.clear();
            }
        });

        btnProbarConexion.setOnAction((event) -> {
            ProbarConexion();
        });

        btnGuardarConfiguracion.setOnAction((event) -> {
            if(guardar())
            {
                establecerUnaConexion();
                stage.close();
            }

        });
    }

    private void establecerUnaConexion(){
        ConeccionAccess access = new ConeccionAccess(ProtocolSearch.ActualConfig.getPathAccess(), ProtocolSearch.ActualConfig.getUsario(), ProtocolSearch.ActualConfig.getPassword());
        ProtocolSearch.ActualConfig.setConexion(access.EstablecerConexion());
    }
    
    private boolean guardar ()    {
        if(ProtocolSearch.ActualConfig == null)
                ProtocolSearch.ActualConfig = new ConfiguracionActual();
            
        //Validar si es es un directorio valido (Protocolos)
        String pathProtocolos = txtUbicacionProtocolos.getText().trim();
        File directorio = new File(pathProtocolos);
        if(directorio!= null && directorio.isDirectory())
        {
            ProtocolSearch.ActualConfig.setPathProtocolos(pathProtocolos);
        }
        //validar si es un directorio válido
        String pathReportes = txtPathReportes.getText().trim();
        directorio = new File(pathReportes);
        if(directorio != null && directorio.isDirectory())
        {
            ProtocolSearch.ActualConfig.setPathReportes(pathReportes);
        }
        //Validar si existe el archivo de access y guardar
        String archivoAccess = txtUbicacionAccess.getText();
        File archivo = new File(archivoAccess);
        if(archivo != null && archivo.isFile())
        {
            ProtocolSearch.ActualConfig.setPathAccess(archivoAccess);
        }
        
        if(!txtUsuario.getText().isEmpty())
            ProtocolSearch.ActualConfig.setUsario(txtUsuario.getText().trim());
        
        ProtocolSearch.ActualConfig.setPassword(txtPassword.getText().trim());
        
        //Guardar archivo de conexion
        
        Utilerias.escribirArchivoDeConfiguracion();
        
        return true;
    }
    
    public boolean ProbarConexion ()    {
        if(ConeccionAccess.ProbarConexion(txtUbicacionAccess.getText(), txtUsuario.getText(), txtPassword.getText()))
        {
            lblStatusConexion.setText("Conexión Exitosa");
            return true;
        }else
        {
            lblStatusConexion.setText("Conexion Fallida");
            return false;
        }        
    }
    
    
    
}
