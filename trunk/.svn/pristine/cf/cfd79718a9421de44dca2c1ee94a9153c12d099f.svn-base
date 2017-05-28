package mx.vw.swf.sms.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import io.datafx.controller.ViewController;
import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javax.annotation.PostConstruct;
import static mx.vw.swf.cdi.DataFXLauncher.getProperty;
import mx.vw.swf.security.controller.MenuController;
import mx.vw.swf.sms.model.SmsMovimiento;
import mx.vw.swf.sms.utilerias.Utilerias;

/**
 * FXML Controller class
 *
 * @author fox1x0d
 */
@ViewController("/fxml/functionality/ReporteMovimientos.fxml")
public class ReporteMovimientosController extends MenuController {

 
    @FXML
    private ComboBox comboPaginacion;
    @FXML
    private Label lbPaginacion, lbFechaDe, lbFechaHasta, lbFolio, lbIdSap, lbClave, lbNombre, lbMaterial, filtrarLabel;
    @FXML
    private TableView<SmsMovimiento> tablaDatos;
    @FXML
    private Pagination paginador;
    @FXML
    private TextField flFolio;
    @FXML
    private RadioButton rdTodos, rdClientes, rdProveedores;
    
    
    @PostConstruct
    public void postConstruct() {

        super.postConstruct();
        super.setEncabezado(getProperty("ReporteMovimientos.CltsProv.Header"));
        inicializaControles();
        inicializaColumnasTabla();
        initialize();
        
            
    }
    public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                flFolio.requestFocus();
            }
        });
    }
    public void inicializaControles(){
        lbPaginacion.setText(getProperty("General.Paginacion.lbRegistros"));
        comboPaginacion.setItems(Utilerias.comboPaginacion());
        comboPaginacion.getSelectionModel().select(0);
        lbFechaDe.setText(getProperty("ReporteMovimientos.CltsProv.lbFechaDe")+":");
        lbFechaHasta.setText(getProperty("ReporteMovimientos.CltsProv.lbFechaHasta")+":");
        rdTodos.setText(getProperty("ReporteMovimientos.CltsProv.lbTodos"));
        rdClientes.setText(getProperty("Movimientos.Clientes.lblEntity"));
        rdProveedores.setText(getProperty("Movimientos.Proveedores.lblEntity"));
        lbFolio.setText(getProperty("Movimientos.General.Entradas.lblFolio"));
        lbIdSap.setText(getProperty("Catalogos.CltsPrvs.NuevoEditar.lblIdSAP"));
        lbClave.setText(getProperty("Movimientos.General.Entity.lblClave"));
        lbNombre.setText(getProperty("Movimientos.General.Entity.lblNombre"));
        lbMaterial.setText(getProperty("ReporteMovimientos.CltsProv.lbMaterial"));
        filtrarLabel.setText(getProperty("General.Filtrar.Label"));
    }
    public void inicializaColumnasTabla() {
        String nombresHeader[] = {
            getProperty("Movimientos.General.Entradas.lblFolio"),
            getProperty("Catalogos.CltsPrvs.NuevoEditar.lblIdSAP"),
            getProperty("ReporteMovimientos.CltsProv.folbCltsProv"),
            getProperty("Movimientos.General.Entity.lblClave"),
            getProperty("Movimientos.General.Entity.lblNombre"),
            getProperty("Movimientos.General.Entradas.lblPlacas"),
            getProperty("ReporteMovimientos.CltsProv.lbMaterial"),
            getProperty("ReporteMovimientos.CltsProv.Entrada"),
            getProperty("ReporteMovimientos.CltsProv.Salida"),
            getProperty("ReporteMovimientos.CltsProv.PesoBruto"),
            getProperty("ReporteMovimientos.CltsProv.Tara"),
            getProperty("ReporteMovimientos.CltsProv.PesoNeto")};
        String valoresHeader[] = {"id", "idCteProvSap", "smsCteProv.esClienteProveedor", "smsCteProv.clave", "id","id", "id", "id", "id", "id","id","id"};
        Utilerias.headerValuesColumnTableView(nombresHeader, valoresHeader, tablaDatos, false);

    }
  
   
}
