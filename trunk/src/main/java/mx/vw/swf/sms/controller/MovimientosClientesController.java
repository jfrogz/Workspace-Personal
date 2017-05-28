package mx.vw.swf.sms.controller;

import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FlowActionHandler;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.annotation.PostConstruct;
import mx.vw.swf.cdi.DataFXLauncher;
import mx.vw.swf.sms.dao.SmsMovimientoDAO;
import mx.vw.swf.sms.functionality.controller.MovimientosController;
import mx.vw.swf.sms.model.SmsMovimiento;

/**
 * Created by  fox1yij
 */


@ViewController("/fxml/functionality/Movimientos.fxml")
public class MovimientosClientesController extends MovimientosController{

    @ActionHandler
    FlowActionHandler actionHandler;

    @PostConstruct
    public void postContruct() {
        super.Contruct();
        inicializarVista();
        
    }

    private void inicializarVista() {
        setEncabezado(DataFXLauncher.getProperty("Movimientos.Clientes.Header"));
        Entity_lblEntidad.setText(DataFXLauncher.getProperty("Movimientos.Clientes.lblEntity"));        
        cargarTablaIdentidades(MovimientosController.TipoEntidad.CLIENTE);
        actualizarTablaMovimientos(MovimientosController.TipoEntidad.CLIENTE);
    
    }
}
