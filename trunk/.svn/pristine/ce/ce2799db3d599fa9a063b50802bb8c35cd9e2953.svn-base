/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.security.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import static mx.vw.swf.cdi.DataFXLauncher.getProperty;
import static mx.vw.swf.cdi.DataFXLauncher.getSecurityProperty;
import mx.vw.swf.security.dao.SegPermisoDAO;
import mx.vw.swf.security.model.SegPermiso;
import mx.vw.swf.security.service.CrearEditarFuncionesService;
import mx.vw.swf.sms.utilerias.LengthValidator;
import mx.vw.swf.sms.utilerias.Utilerias;

/**
 * FXML Controller class
 *
 * @author fox1x0d
 */
public class CrearEditarFuncionesController extends Controller  implements Initializable {

    private SegPermiso permiso;
    private AdministrarFuncionesPerfilController administrarFuncionesPerfilController;
    @FXML
    private TextField txtDesPermiso;
    @FXML
    private CheckBox chxEstatusPermiso;
    @FXML
    private Label id, idEtiqueta, lbDesPermiso, lbEstatus;
    @FXML
    private Button btnGuardar, btnCerrar;
    private Short status = 1;

    /**
     * Initializes the controller class.
     */
     public CrearEditarFuncionesController(SegPermiso permiso, AdministrarFuncionesPerfilController administrarFuncionesPerfilController){
        this.permiso = permiso;
        this.administrarFuncionesPerfilController = administrarFuncionesPerfilController;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializaControles();
        inicializaPermiso();
        inicializarEventos();
        initialize();

    }
    public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtDesPermiso.requestFocus();
            }
        });
    }
    public void inicializaControles(){
        txtDesPermiso.addEventFilter(KeyEvent.KEY_TYPED, Utilerias.permiteLetrasNumeros());
        Utilerias.addTextLimiter(txtDesPermiso, 150);
        
        lbDesPermiso.setText(getSecurityProperty("AdministraFuncionesPerfil.lbDesFuncion") + ":");
        lbEstatus.setText(getProperty("General.lbEstatus") + ":");
        btnCerrar.setText(getProperty("General.btnCancelar"));
        id.setText(getProperty("General.lbID"));
        btnGuardar.setText(getProperty("General.botonGuardar"));
        
        txtDesPermiso.getProperties().put("fieldName" , getSecurityProperty("AdministraFuncionesPerfil.lbFuncion"));        
        registerValidator(txtDesPermiso, new LengthValidator(1,50));
    }
    public void inicializaPermiso(){
       
        if (null != permiso) {

            id.setVisible(true);
            idEtiqueta.setVisible(true);
            idEtiqueta.setText(permiso.getId().toString());
            txtDesPermiso.setText(permiso.getPermiso());
            chxEstatusPermiso.setSelected(permiso.getEstatus() != 1 ? false : true);
            

        } else {
            id.setVisible(false);
            idEtiqueta.setVisible(false);
            idEtiqueta.setText("");
        } 
    }
    public void inicializarEventos(){
        btnGuardar.setOnAction((event) -> accionBtnAceptar(event));
        btnCerrar.setOnAction((event) -> Utilerias.cerrar(event));
    }
    
    public void accionBtnAceptar(ActionEvent e){
        if (!displayValidationResult(true))
           return;
        obtenerDatosPantalla();
        guardarActualizarEntidad(e);
        
    }
      public void obtenerDatosPantalla(){
         if (null == permiso) {
             permiso = new SegPermiso();
         }
          permiso.setPermiso(txtDesPermiso.getText().trim());
          permiso.setEstatus(chxEstatusPermiso.isSelected() == true ? status : 0);
    }
     public void guardarActualizarEntidad(ActionEvent e){
          CrearEditarFuncionesService crearEditarFuncionesService = new CrearEditarFuncionesService();
           if(null == permiso.getId() || 0 == permiso.getId()){
               List<SegPermiso> lista = new SegPermisoDAO().findByPermiso(txtDesPermiso.getText().trim());
                if (!lista.isEmpty()) {
                   Utilerias.dialogOk(getSecurityProperty("AdministraFuncionesPerfil.FuncionExistente.Header"), 
                           getSecurityProperty("AdministraFuncionesPerfil.FuncionExistente.Message")); 
                } else {
                    crearEditarFuncionesService.guardarPermiso(permiso);
                    Utilerias.dialogOk(getProperty("General.Save.Header"), getProperty("General.Save.Message"));
                    administrarFuncionesPerfilController.inicializaPantalla();
                    Utilerias.cerrar(e);

                }
               
           }else{
                 crearEditarFuncionesService.actualizarPermiso(permiso);
                 Utilerias.dialogOk(getProperty("General.Update.Header"), getProperty("General.Update.Message"));
                 administrarFuncionesPerfilController.inicializaPantalla();
                 Utilerias.cerrar(e);
           }
              
           
     }    

    public void cerrar(ActionEvent event) {
        Utilerias.cerrar(event);
    }
}
