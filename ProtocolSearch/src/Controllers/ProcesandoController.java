/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.ExpedienteDao;
import Identities.Expediente;
import Identities.Responsable;
import Reportes.AsistenteReporte;
import Utilerias.Utilerias;
import io.datafx.controller.ViewController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author fernandorobles
 */
@ViewController("/Views/ProcesandoView.fxml")
public class ProcesandoController implements Initializable {

    @FXML
    private ProgressBar prbProgreso;
    @FXML
    private Label lblProgreso;

    @FXML
    private Button btnCancelarProceso;
    private Stage stage;
    private ObservableList<Responsable> responsablesSeleccionados;

    private boolean licencia = false;

    public ProcesandoController(ObservableList<Responsable> responsablesSeleccionados) {
        this.responsablesSeleccionados = responsablesSeleccionados;

    }

    public void PostConstructor() {
        btnCancelarProceso.setOnAction((event) -> {
            stage.close();
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public ObservableList<Expediente> ObtenerProtocolosFaltantes(ObservableList<Responsable> responsablesSeleccionados) {
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                //Obtenemos la lista de toos los instrumentos segun la consulta
                ObservableList<Expediente> ObtenerlistaDeExpedientes = ExpedienteDao.ObtenerlistaDeExpedientes(responsablesSeleccionados, licencia);
                //Obtener la lista de todos los archivos en un determinado directorio
                ObservableList<String> listaDeArchivos = Utilerias.listaDeArchivos(protocolsearch.ProtocolSearch.ActualConfig.getPathProtocolos());
                //Realizar la comparacion para descartar y generar una nueva lista
                ObservableList<Expediente> listProtocolosFaltantes = FXCollections.observableArrayList();

                int cantidad = 0;
                double unidad = ObtenerlistaDeExpedientes.size();
                for (Expediente expediente : ObtenerlistaDeExpedientes) {

                    updateProgress(++cantidad, unidad);
                    updateMessage("Buscando protocolo ... " + expediente.getInstrumento());
                    //Thread.sleep(100);

                    boolean agnadir = true;
                    int valoraborrar = 0;
                    for (int i = 0; i < listaDeArchivos.size(); i++) {
                        String value = listaDeArchivos.get(i);

                        if (expediente.toString().equals(value)) {
                            agnadir = false;
                            valoraborrar = i;
                            break;
                        }
                    }
                    if (agnadir) {
                        listProtocolosFaltantes.add(expediente);
                        if (!licencia && listProtocolosFaltantes.size() == 50) {
                            break;
                        }
                    } else {
                        listaDeArchivos.remove(valoraborrar);
                    }
                }
                updateMessage("Generando Reporte ...");
                imprimirReporte(listProtocolosFaltantes);
                updateMessage("Proceso finalizado");
                return listProtocolosFaltantes;
            }
        };

        lblProgreso.textProperty().bind(task.messageProperty());
        prbProgreso.progressProperty().bind(task.progressProperty());
        Thread thread = new Thread(task);
        thread.start();

        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("se inicia con tareas");
        btnCancelarProceso.setDisable(true);
        ObtenerProtocolosFaltantes(responsablesSeleccionados);
        btnCancelarProceso.setDisable(false);
    }

    private void imprimirReporte(ObservableList<Expediente> lista) {
        InputStream inputStream = null;
        JasperPrint jasperPrint = null;
        AsistenteReporte datasource = new AsistenteReporte(lista);

        Map parametros = new HashMap();
        parametros.clear();
        try {
            inputStream = new FileInputStream("src/Reportes/ProtocolosFaltantesResponsables.jrxml");
            parametros.put("ImgPlaneta", this.getClass().getResourceAsStream("/Complementos/Imagenes/Green.gif"));
            //parametros.put("ImgPlanta", this.getClass().getResourceAsStream("/Complementos/Imagenes/images.jpg"));
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al leer el fichero de carga jasper report " + ex);
        }

        try {
            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, datasource);

            //JasperExportManager.exportReportToPdfFile(jasperPrint, "src/reportes/reporte01.pdf");
            JasperViewer.viewReport(jasperPrint);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar fichero jrml jasper report " + e.getMessage());
        }
    }

}
