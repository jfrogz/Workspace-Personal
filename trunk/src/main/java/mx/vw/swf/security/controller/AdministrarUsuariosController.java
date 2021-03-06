/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.security.controller;

import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.annotation.PostConstruct;
import mx.vw.swf.security.model.SegUsuario;
import mx.vw.swf.security.dao.SegUsuarioDAO;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.StageStyle;
import mx.com.vw.swf.exception.SWFLoginException;
import static mx.vw.swf.cdi.DataFXLauncher.getProperty;
import static mx.vw.swf.cdi.DataFXLauncher.getSecurityProperty;
import mx.vw.swf.security.service.CrearEditarUsuariosService;
import mx.vw.swf.sms.utilerias.Pantallas;
import mx.vw.swf.sms.utilerias.Permisos;
import mx.vw.swf.sms.utilerias.Utilerias;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;

/**
 * FXML Controller class
 *
 * @author fox1x0d
 */
@ViewController("/fxml/security/AdministrarUsuarios.fxml")
public class AdministrarUsuariosController extends MenuController {

    @FXML
    private TableView<SegUsuario> tablaUsuarios;
    @FXML
    private TableColumn<SegUsuario, SegUsuario> colAcciones;
    @FXML
    private TextField flUsuario, flNombre;
    @FXML
    private Button btnNuevoUsuario;
    @FXML
    private Label lbEtiquetaUsuario, lbEtiquetaNombre, lbPaginacion, filtrarLabel;
    @FXML
    private Pagination paginador;
    @FXMLViewFlowContext
    ViewFlowContext viewFlowContext;
    @ActionHandler
    private FlowActionHandler actionHandlerPerfil;
    @FXML
    private ComboBox comboPaginacion;
    private ObservableList<SegUsuario> listaUsuarios;
    private FilteredList<SegUsuario> filteredData;
    private SortedList<SegUsuario> sortedData;
    private int renglonesMostrar = 5;
    private SegUsuario segUsuarioPermisos; 

    @PostConstruct
    public void postConstruct() {
        super.postConstruct();
        super.setEncabezado(getSecurityProperty("AdmininstraUsuario.Header"));
        inicializaControles();
        inicializaColumnasTabla();
        inicializaPatalla();
        inicializaEventos();
        inicializaReportFrame();      
        initialize();
    }

   public void initialize() {
      Platform.runLater(new Runnable() {
         @Override
         public void run() {
            flUsuario.requestFocus();
         }
      });
   }

   private void inicializaReportFrame() {
      repParams.clear();
      reportTitle = getSecurityProperty("AdministraUsuario.ReportTitle");
      reportName = getSecurityProperty("AdministraUsuario.ReportName");
      reportFileName = reportName.replace('.', '_');
      reportData = sortedData;
   }

   public void inicializaControles() {

          
      segUsuarioPermisos = (SegUsuario) viewFlowContext.getRegisteredObject("USERPRINCIPAL"); 
      lbPaginacion.setText(getProperty("General.Paginacion.lbRegistros"));
      comboPaginacion.setItems(Utilerias.comboPaginacion());
      comboPaginacion.getSelectionModel().select(0);
      btnNuevoUsuario.setText(getProperty("General.btnNuevo"));
      lbEtiquetaUsuario.setText(getProperty("Login.UsrLabel") + ":");
      lbEtiquetaNombre.setText(getSecurityProperty("AdmininstraUsuario.lbNombre") + ":");
      filtrarLabel.setText(getProperty("General.Filtrar.Label"));      
      btnNuevoUsuario.setDisable(!segUsuarioPermisos.testPrivilege(Permisos.ADMIN_USR_NUEVO));

   }

   public void inicializaColumnasTabla() {
       
      String nombresHeader[] = {getProperty("General.lbID"),
         getProperty("Login.UsrLabel"),
         getSecurityProperty("AdmininstraUsuario.lbNombre"),
         getSecurityProperty("AdmininstraUsuario.lbCorreo"),
         getProperty("General.lbEstatus"),
         getProperty("General.lbAcciones")};
      String valoresHeader[] = {"id", "userId", "nombreCompleto", "correoE", "etiquetaEstatus"};
      Utilerias.headerValuesColumnTableView(nombresHeader, valoresHeader, tablaUsuarios, false);
      inicializaColumnasControles();

   }

   public void inicializaColumnasControles() {
      colAcciones.setSortable(false);
      colAcciones.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SegUsuario, SegUsuario>, ObservableValue<SegUsuario>>() {
         public ObservableValue<SegUsuario> call(TableColumn.CellDataFeatures<SegUsuario, SegUsuario> p) {
            return new ReadOnlyObjectWrapper(p.getValue());
         }
      });

      colAcciones.setCellFactory(new Callback<TableColumn<SegUsuario, SegUsuario>, TableCell<SegUsuario, SegUsuario>>() {
         @Override
         public TableCell<SegUsuario, SegUsuario> call(TableColumn<SegUsuario, SegUsuario> param) {
            TableCell<SegUsuario, SegUsuario> cell = new TableCell<SegUsuario, SegUsuario>() {

               FlowActionHandler actionHandler = actionHandlerPerfil;
               Button editar = new Button(getProperty("General.btnEditar"));
               Button perfiles = new Button(getSecurityProperty("AdmininstraUsuario.btnAsignarPerfiles"));
               Button desactivar = new Button(getProperty("General.btnActivar"));

               Short desactiva = 0;

               @Override
               public void updateItem(SegUsuario item, boolean empty) {
                  
                  if (null != item) {
                      
                     perfiles.setDisable(!segUsuarioPermisos.testPrivilege(Permisos.ADMIN_USR_ASIGNAR_PERFILES));
                     editar.setDisable(!segUsuarioPermisos.testPrivilege(Permisos.ADMIN_USR_EDITAR));
                     desactivar.setDisable(!segUsuarioPermisos.testPrivilege(Permisos.ADMIN_USR_ACTIVAR_DESACTIVAR));
                     
                     editar.getStyleClass().add("botones");
                     editar.getStyleClass().add("buttonEdit");
                     editar.applyCss();
                     perfiles.getStyleClass().add("botones");
                     perfiles.getStyleClass().add("buttonAdd");
                     desactivar.getStyleClass().add("botones");
                     perfiles.applyCss();
                     desactivar.setPrefWidth(103);
                     editar.setPrefWidth(68);
                     perfiles.setPrefWidth(130);
                     desactivar.setAlignment(Pos.BASELINE_LEFT);
                    
                    
                     if (item.getEstatus() == 0) {                        
                        desactivar.setText(getProperty("General.btnActivar"));                      
                        desactivar.getStyleClass().removeAll("buttonAccept", "buttonInactiva");
                        desactivar.getStyleClass().add("buttonAccept");
                        desactivar.applyCss();
                        desactiva = 1;
                     } else {
                        desactivar.setText(getProperty("General.btnDesactivar"));
                        desactivar.getStyleClass().removeAll("buttonAccept", "buttonInactiva");
                        desactivar.getStyleClass().add("buttonInactiva");
                        desactivar.applyCss();
                        desactiva = 0;
                     }                    
                     editar.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {
                           creaEditaUsuario(item, t);
                        }
                     });
                     desactivar.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {
                           Action response = Utilerias.dialogConfirm(getSecurityProperty("AdmininstraUsuario.Valid.Estatus.Header"),
                                   getSecurityProperty("AdmininstraUsuario.Valid.Estatus.Message"));
                           if (response == Dialog.ACTION_YES) {                              
                              item.setEstatus(desactiva);
                              desactivarUsuario(item);
                           }

                        }
                     });
                     perfiles.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {
                           try {
                              HashMap<Object, Object> mapa = (HashMap<Object, Object>) viewFlowContext.getRegisteredObject("AppMap");
                              mapa.put("USUARIOPERFIL", item);
                              viewFlowContext.register("AppMap", mapa);
                              actionHandler.navigate(AdministrarPerfilesController.class);
                           } catch (VetoException | FlowException e) {

                           }
                                   
                        }
                     });
                     
                    
                    HBox botones = new HBox(editar, desactivar, perfiles);
                     botones.setSpacing(10);
                     setGraphic(botones);
                  } else {
                     setGraphic(null);
                  }
               }
            };
            return cell;
         }
      });

   }

   public void inicializaPatalla() {
      setListaUsuarios(FXCollections.observableArrayList(obtieneListaUsuarios()));
      filteredData = new FilteredList<>(getListaUsuarios(), p -> true);
      sortedData = (SortedList<SegUsuario>) Utilerias.creaFiltro(filteredData, tablaUsuarios);
      inicializaTablaUsuario(sortedData);
      reportData = sortedData;
   }

   public ObservableList<SegUsuario> obtieneListaUsuarios() {
      List<SegUsuario> lista = new SegUsuarioDAO().findAll();
      ObservableList<SegUsuario> listaol = FXCollections.observableArrayList();
      for (SegUsuario item : lista) {
         listaol.add(item);
      }
      return listaol;
   }

   public void inicializaTablaUsuario(SortedList<SegUsuario> lista) {
      renglonesMostrar = Integer.parseInt(comboPaginacion.getValue().toString());
      Utilerias.inicializaPaginador(paginador, lista, renglonesMostrar);
      Utilerias.creaPaginaTableView(0, renglonesMostrar, lista, tablaUsuarios, false);
   }

   public void inicializaEventos() {

      flUsuario.addEventFilter(KeyEvent.KEY_TYPED, Utilerias.permiteLetrasNumeros());
      flNombre.addEventFilter(KeyEvent.KEY_TYPED, Utilerias.permiteLetras());

      Utilerias.addTextLimiter(flUsuario, 20);
      Utilerias.addTextLimiter(flNombre, 227);

      btnNuevoUsuario.setOnAction((event) -> nuevoUsuario(event));

      comboPaginacion.setOnAction((event) -> {
         renglonesMostrar = Integer.parseInt(comboPaginacion.getValue().toString());
         inicializaTablaUsuario(sortedData);
      });

      flUsuario.textProperty().addListener((observable, oldValue, newValue) -> {
         filteredData.setPredicate(usuer -> {

            String user = flUsuario.getText().toLowerCase().trim();
            String nombreCompleto = flNombre.getText().toLowerCase().trim();

            if ((null == user || user.isEmpty()) && (null == nombreCompleto || nombreCompleto.isEmpty())) {
               return true;
            } else if (usuer.getNombreCompleto().toLowerCase().startsWith(nombreCompleto) && usuer.getUserId().toLowerCase().startsWith(user)) {
               return true;

            }

            return false;
         });
         inicializaTablaUsuario(sortedData);
      });
      flNombre.textProperty().addListener((observable, oldValue, newValue) -> {
         filteredData.setPredicate(usuer -> {
            String user = flUsuario.getText().toLowerCase().trim();
            String nombreCompleto = flNombre.getText().toLowerCase().trim();
            if ((null == user || user.isEmpty()) && (null == nombreCompleto || nombreCompleto.isEmpty())) {
               return true;
            } else {
               if (usuer.getNombreCompleto().toLowerCase().startsWith(nombreCompleto) && usuer.getUserId().toLowerCase().startsWith(user)) {
                  return true;
               }

            }
            return false;
         });
         inicializaTablaUsuario(sortedData);
      });

      paginador.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
         @Override
         public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            Utilerias.creaPaginaTableView(newValue.intValue(), renglonesMostrar, sortedData, tablaUsuarios, true);
         }
      });
      reportData = sortedData;
   }

   public void nuevoUsuario(ActionEvent event) {
      creaEditaUsuario(null, event);
   }

   public void desactivarUsuario(SegUsuario usuario) {
      boolean muestraMensaje = new CrearEditarUsuariosService().actualizaUsuario(usuario);
      if (muestraMensaje) {

         Utilerias.dialogOk(getSecurityProperty("AdmininstraUsuario.Valid.DesactivaUser.Header"),
                 getSecurityProperty("AdmininstraUsuario.Valid.DesactivaUser.Message"));
      } else {
         inicializaPatalla();
      }

   }

   public void creaEditaUsuario(SegUsuario usuario, ActionEvent ev) {
      String titulo = null != usuario ? getSecurityProperty("CreaEditaUsuario.Header.Nuevo") : getSecurityProperty("CreaEditaUsuario.Header.Editar");
      Stage stage;

      try {
         CrearEditarUsuariosController controller = new CrearEditarUsuariosController(usuario, this);
         stage = Utilerias.nuevaVentana(Pantallas.FXML_CREAR_EDITAR_USUARIOS, controller, titulo);
         stage.initModality(Modality.WINDOW_MODAL);
         stage.initStyle(StageStyle.UTILITY);
         stage.setResizable(false);
         stage.initOwner(((Node) ev.getSource()).getScene().getWindow());
         stage.showAndWait();
      } catch (IOException ex) {
         Logger.getLogger(AdministrarUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
      }

   }

   public void setListaUsuarios(ObservableList<SegUsuario> listaUsuarios) {
      this.listaUsuarios = listaUsuarios;
   }

   public ObservableList<SegUsuario> getListaUsuarios() {
      return listaUsuarios;
   }

}
