package mx.vw.swf.sms.controller;

import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.util.VetoException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javax.annotation.PostConstruct;
import mx.vw.swf.cdi.DataFXLauncher;
import mx.vw.swf.fwk.dao.FwkCatContentDAO;
import mx.vw.swf.fwk.model.FwkCatContent;
import mx.vw.swf.security.controller.MenuController;
import mx.vw.swf.security.controller.WelcomeController;

/**
 * Created by fox1yij
 */


@ViewController("/fxml/PerfilDeLaEmpresa.fxml")
public class PerfilDeEmpresaController extends MenuController {

    
    // <editor-fold defaultstate="collapsed" desc="Labels FXML">
    @FXML
    Label PerfilEmpresa_lblNombreEmpresa;
    @FXML
    Label PerfilEmpresa_lblNombreEmpresaValue;
    @FXML
    Label PerfilEmpresa_lblDireccion;
    @FXML
    Label PerfilEmpresa_lblDireccionValue;
    @FXML
    Label PerfilEmpresa_lblEstadoMunicipio;
    @FXML
    Label PerfilEmpresa_lblEstadoMunicipioValue;
    @FXML
    Label PerfilEmpresa_lblCp;
    @FXML
    Label PerfilEmpresa_lblCpValue;
    @FXML
    Label PerfilEmpresa_lblRfc;
    @FXML
    Label PerfilEmpresa_lblRfcValue;
    @FXML
    Label PerfilEmpresa_lblTelefono;
    @FXML
    Label PerfilEmpresa_lblTelefonoValue;
    @FXML
    Button PerfilEmpresa_btnAceptar;
    @FXML
    ImageView PerfilEmpresa_imgEmpesa;

    //</editor-fold>
    
    @ActionHandler
    FlowActionHandler actionHandler;    
    
    @PostConstruct
    public void postContruct() {
        super.postConstruct();
        inicializarLabels();
        inicializarEventos();
        actualizarCampos();
    }

    private void inicializarLabels() {
        PerfilEmpresa_lblNombreEmpresa.setText(DataFXLauncher.getProperty("PerfilEmpresa.lblNombreEmpresa") + ":");
        PerfilEmpresa_lblDireccion.setText(DataFXLauncher.getProperty("PerfilEmpresa.lblDireccion") + ":");
        PerfilEmpresa_lblEstadoMunicipio.setText(DataFXLauncher.getProperty("PerfilEmpresa.lblEstadoMunicipio") + ":");
        PerfilEmpresa_lblCp.setText(DataFXLauncher.getProperty("PerfilEmpresa.lblCp") + ":");
        PerfilEmpresa_lblRfc.setText(DataFXLauncher.getProperty("PerfilEmpresa.lblRfc") + ":");
        PerfilEmpresa_lblTelefono.setText(DataFXLauncher.getProperty("PerfilEmpresa.lblTelefono") + ":");
        PerfilEmpresa_btnAceptar.setText(DataFXLauncher.getProperty("PerfilEmpresa.btnAceptar"));
        setEncabezado(DataFXLauncher.getProperty("PerfilEmpresa.Header"));
    }

    private void actualizarCampos() {
        FwkCatContentDAO actionItemDAO = new FwkCatContentDAO();
        List<FwkCatContent> findAll = actionItemDAO.findAll(0, 7);
        for (FwkCatContent fwkCatContent : findAll) {
            switch (fwkCatContent.getKeyValue()) {
                case "PROP_EMP_NOM":
                    PerfilEmpresa_lblNombreEmpresaValue.setText(fwkCatContent.getDescription());
                    break;
                case "PROP_EMP_DIR":
                    PerfilEmpresa_lblDireccionValue.setText(fwkCatContent.getDescription());
                    break;
                case "PROP_EMP_EDO":
                    PerfilEmpresa_lblEstadoMunicipioValue.setText(fwkCatContent.getDescription()+", ");
                    break;
                case "PROP_EMP_MUN":
                    PerfilEmpresa_lblEstadoMunicipioValue.setText(
                            PerfilEmpresa_lblEstadoMunicipioValue.getText()+ fwkCatContent.getDescription()
                    );
                    break;
                case "PROP_EMP_CP":
                    PerfilEmpresa_lblCpValue.setText(fwkCatContent.getDescription());
                    break;
                case "PROP_EMP_RFC":
                    PerfilEmpresa_lblRfcValue.setText(fwkCatContent.getDescription());
                    break;
                case "PROP_EMP_TEL":
                    PerfilEmpresa_lblTelefonoValue.setText(fwkCatContent.getDescription());
                    break;
            }
        }
    }
    
    private void inicializarEventos ()
    {
        PerfilEmpresa_btnAceptar.setOnAction((event)->{
            try {
                actionHandler.navigate(WelcomeController.class);
            } catch (VetoException ex) {
                Logger.getLogger(PerfilDeEmpresaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FlowException ex) {
                Logger.getLogger(PerfilDeEmpresaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
