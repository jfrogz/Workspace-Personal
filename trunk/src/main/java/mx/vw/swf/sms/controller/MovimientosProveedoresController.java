package mx.vw.swf.sms.controller;

import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FlowActionHandler;
import static java.util.Collections.list;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.annotation.PostConstruct;
import mx.vw.swf.cdi.DataFXLauncher;
import mx.vw.swf.sms.dao.SmsMovimientoDAO;
import mx.vw.swf.sms.functionality.controller.MovimientosController;
import mx.vw.swf.sms.model.SmsMovimiento;

/**
 * Created by fox1yij
 */
@ViewController("/fxml/functionality/Movimientos.fxml")
public class MovimientosProveedoresController extends MovimientosController {

    @ActionHandler
    FlowActionHandler actionHandler;

    @PostConstruct
    public void postContruct() {
        super.Contruct();
        inicializarVista();
    }

    protected void inicializarVista() {
        setEncabezado(DataFXLauncher.getProperty("Movimientos.Proveedores.Header"));
        Entity_lblEntidad.setText(DataFXLauncher.getProperty("Movimientos.Proveedores.lblEntity"));
        btnEntradaCustodia.setVisible(false);
        btnSalidaCustodia.setVisible(false);
        cargarTablaIdentidades(MovimientosController.TipoEntidad.PROVEEDOR);
        actualizarTablaMovimientos(MovimientosController.TipoEntidad.PROVEEDOR);
        
        
    }

}
