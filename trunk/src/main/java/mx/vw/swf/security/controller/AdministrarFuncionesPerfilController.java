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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.annotation.PostConstruct;
import static mx.vw.swf.cdi.DataFXLauncher.getProperty;
import static mx.vw.swf.cdi.DataFXLauncher.getSecurityProperty;
import mx.vw.swf.security.dao.SegPermisoDAO;
import mx.vw.swf.security.model.SegPerfil;
import mx.vw.swf.security.model.SegPermiso;
import mx.vw.swf.security.model.SegPermisoPerfil;
import mx.vw.swf.security.model.SegPermisoPerfilId;
import mx.vw.swf.security.service.CrearEditarFuncionesService;
import mx.vw.swf.security.service.SegPermisoPerfilService;
import mx.vw.swf.sms.utilerias.CheckBoxTableCell;
import mx.vw.swf.sms.utilerias.Pantallas;
import mx.vw.swf.sms.utilerias.Utilerias;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;

/**
 * FXML Controller class
 *
 * @author fox1x0d
 */
@ViewController("/fxml/security/AdministrarFuncionesPerfil.fxml")
public class AdministrarFuncionesPerfilController extends MenuController {

   @FXML
   private TableView<SegPermiso> tablaDatos;
   @FXML
   private TableColumn<SegPermiso, SegPermiso> colSelccione;
   @FXML
   private TableColumn<SegPermiso, SegPermiso> colAcciones;
   @FXML
   private TextField flFuncion;
   @FXML
   private Label lbPerfil, lbFuncion, lbClaveNombrePerfil, lbPaginacion, filtrarLabel;
   @FXML
   private Button btnNuevoPermiso, btnAsignarFuncionesPerfil, btnRegresarAtras;
   @FXML
   private ComboBox comboPaginacion;
   @FXML
   private Pagination paginador;
   @ActionHandler
   private FlowActionHandler actionHandlerPermiso;
   @FXMLViewFlowContext
   ViewFlowContext viewFlowContext;
   private ObservableList<SegPermiso> listaPermisos;
   private FilteredList<SegPermiso> filteredData;
   private SortedList<SegPermiso> sortedData;
   private String claveDescripcionPermiso;
   private int renglonesMostrar = 5;
   public SegPerfil segPerfil;

   @PostConstruct
   public void postConstruct() {

      super.postConstruct();
      super.setEncabezado(getSecurityProperty("AdministraFuncionesPerfil.Header"));
      inicializaControles();
      inicializaColumnasTabla();
      inicializaPantalla();
      inicializaEventos();
      inicializaReportFrame();
      initialize();

   }

   public void initialize() {
      Platform.runLater(new Runnable() {
         @Override
         public void run() {
            flFuncion.requestFocus();
         }
      });
   }

   private void inicializaReportFrame() {
      repParams.clear();
      if (null != segPerfil) {
         repParams.put("roleId", segPerfil.getId().toString());
         repParams.put("roleName", segPerfil.getPerfil());
      }

      reportTitle = getSecurityProperty("AdministraFuncionesPerfil.ReportTitle");
      reportName = getSecurityProperty("AdministraFuncionesPerfil.ReportName");
      reportFileName = reportName.replace('.', '_');
      reportData = sortedData;
   }

   public void inicializaControles() {

      if (null != viewFlowContext.getRegisteredObject("AppMap")) {
         HashMap<Object, Object> mapa = (HashMap<Object, Object>) viewFlowContext.getRegisteredObject("AppMap");
         segPerfil = (SegPerfil) mapa.get("PERFILPERMISO");
      } else {
         segPerfil = null;
      }

      if (null != segPerfil) {
         lbClaveNombrePerfil.setVisible(true);
         lbClaveNombrePerfil.setText(segPerfil.getPerfil());
         lbPerfil.setVisible(true);
         colSelccione.setVisible(true);
         btnAsignarFuncionesPerfil.setVisible(true);
         btnRegresarAtras.setVisible(true);
      } else {
         lbClaveNombrePerfil.setVisible(false);
         lbClaveNombrePerfil.setText("");
         lbPerfil.setVisible(false);
         colSelccione.setVisible(false);
         btnAsignarFuncionesPerfil.setVisible(false);
         btnRegresarAtras.setVisible(false);
      }

      comboPaginacion.setItems(Utilerias.comboPaginacion());
      comboPaginacion.getSelectionModel().select(0);
      lbPaginacion.setText(getProperty("General.Paginacion.lbRegistros"));
      btnNuevoPermiso.setText(getProperty("General.btnNuevo"));
      btnAsignarFuncionesPerfil.setText(getSecurityProperty("AdministraFuncionesPerfil.btnAsignarFunciones"));
      btnRegresarAtras.setText(getProperty("General.botonRegresar"));
      lbPerfil.setText(getSecurityProperty("AdmininstraPerfiles.lbPerfil") + ":");
      lbFuncion.setText(getSecurityProperty("AdministraFuncionesPerfil.lbFuncion") + ":");
      filtrarLabel.setText(getProperty("General.Filtrar.Label"));

   }

   public void inicializaColumnasTabla() {
      String nombresHeader[] = {
         getProperty("General.lbSeleccione"),
         getProperty("General.lbID"),
         getSecurityProperty("AdministraFuncionesPerfil.lbFuncion"),
         getProperty("General.lbEstatus"),
         getProperty("General.lbAcciones")};
      String valoresHeader[] = {"id", "permiso", "etiquetaEstatus"};
      Utilerias.headerValuesColumnTableView(nombresHeader, valoresHeader, tablaDatos, true);

      inicializaColumnasControles();

   }

   public void inicializaColumnasControles() {
      colAcciones.setSortable(false);
      colSelccione.setSortable(false);
      colSelccione.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SegPermiso, SegPermiso>, ObservableValue<SegPermiso>>() {
         public ObservableValue<SegPermiso> call(TableColumn.CellDataFeatures<SegPermiso, SegPermiso> p) {
            return new ReadOnlyObjectWrapper(p.getValue());
         }
      });

      colSelccione.setCellFactory(new Callback<TableColumn<SegPermiso, SegPermiso>, TableCell<SegPermiso, SegPermiso>>() {
         public TableCell<SegPermiso, SegPermiso> call(TableColumn<SegPermiso, SegPermiso> p) {
            return new CheckBoxTableCell<SegPermiso, SegPermiso>(false);
         }
      });

      colAcciones.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SegPermiso, SegPermiso>, ObservableValue<SegPermiso>>() {
         public ObservableValue<SegPermiso> call(TableColumn.CellDataFeatures<SegPermiso, SegPermiso> p) {
            return new ReadOnlyObjectWrapper(p.getValue());
         }
      });

      colAcciones.setCellFactory(new Callback<TableColumn<SegPermiso, SegPermiso>, TableCell<SegPermiso, SegPermiso>>() {
         @Override
         public TableCell<SegPermiso, SegPermiso> call(TableColumn<SegPermiso, SegPermiso> param) {
            TableCell<SegPermiso, SegPermiso> cell = new TableCell<SegPermiso, SegPermiso>() {
               FlowActionHandler actionHandler = actionHandlerPermiso;
               Button editar = new Button(getProperty("General.btnEditar"));
               Button desactivar = new Button();
               Short desactiva = 0;

               @Override
               public void updateItem(SegPermiso item, boolean empty) {

                  if (null != item) {
                     editar.getStyleClass().add("botones");
                     editar.getStyleClass().add("buttonEdit");
                     editar.applyCss();
                     desactivar.getStyleClass().add("botones");
                     desactivar.setPrefWidth(100);
                     desactivar.setAlignment(Pos.BASELINE_LEFT);
                     HBox botones = new HBox(editar, desactivar);
                     botones.setSpacing(10);
                     setGraphic(botones);

                     if (item.getEstatus() == 0) {
                        desactivar.setText(getProperty("General.btnActivar"));
                        desactivar.getStyleClass().clear();
                        desactivar.getStyleClass().add("botones");
                        desactivar.getStyleClass().add("buttonAccept");
                        desactivar.applyCss();
                        desactiva = 1;
                     } else {
                        desactivar.setText(getProperty("General.btnDesactivar"));
                        desactivar.getStyleClass().clear();
                        desactivar.getStyleClass().add("botones");
                        desactivar.getStyleClass().add("buttonInactiva");
                        desactivar.applyCss();
                        desactiva = 0;
                     }

                     editar.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {
                           creaEditaPermiso(item, t);
                        }
                     });
                     desactivar.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {

                           Action response = Utilerias.dialogConfirm(getSecurityProperty("AdministraFuncionesPerfil.Valid.Estatus.Header"),
                                   getSecurityProperty("AdministraFuncionesPerfil.Valid.Estatus.Message"));
                           if (response == Dialog.ACTION_YES) {
                              item.setEstatus(desactiva);
                              desactivarFuncion(item);
                              inicializaPantalla();
                           }

                        }
                     });
                  } else {
                     setGraphic(null);
                  }
               }
            };
            return cell;
         }
      });

   }

   public void inicializaPantalla() {
      setListaPermisos(FXCollections.observableArrayList(obtieneListaPermisos()));
      filteredData = new FilteredList<>(getListaPermisos(), p -> true);
      sortedData = (SortedList<SegPermiso>) Utilerias.creaFiltro(filteredData, tablaDatos);
      inicializaTablaPermisos(sortedData);
      reportData = sortedData;

   }

   public void inicializaTablaPermisos(SortedList<SegPermiso> lista) {
      renglonesMostrar = Integer.parseInt(comboPaginacion.getValue().toString());
      Utilerias.inicializaPaginador(paginador, lista, renglonesMostrar);
      Utilerias.creaPaginaTableView(0, renglonesMostrar, lista, tablaDatos, false);
   }

   public ObservableList<SegPermiso> obtieneListaPermisos() {
      List<SegPermiso> lista = new SegPermisoDAO().findAll();
      ObservableList<SegPermiso> listaol = FXCollections.observableArrayList();
      for (SegPermiso item : lista) {
         item.setSelected(false);
         if (null != segPerfil) {
            Iterator iter = item.getSegPermisoPerfils().iterator();
            while (iter.hasNext()) {
               SegPermisoPerfil h = (SegPermisoPerfil) iter.next();
               if (segPerfil.getId() == h.getSegPerfil().getId()) {
                  item.setSelected(true);
               }

            }
         }
         listaol.add(item);
      }
      return listaol;
   }

   public void inicializaEventos() {

      flFuncion.addEventFilter(KeyEvent.KEY_TYPED, Utilerias.permiteLetrasNumeros());
      Utilerias.addTextLimiter(flFuncion, 150);

      btnNuevoPermiso.setOnAction((event) -> nuevoPermiso(event));
      btnAsignarFuncionesPerfil.setOnAction((event) -> asignarFunciones(event));
      btnRegresarAtras.setOnAction((event) -> regresa(event));

      comboPaginacion.setOnAction((event) -> {
         renglonesMostrar = Integer.parseInt(comboPaginacion.getValue().toString());
         inicializaTablaPermisos(sortedData);
      });

      flFuncion.textProperty().addListener((observable, oldValue, newValue) -> {
         filteredData.setPredicate(permiso -> {
            if (null == newValue || newValue.isEmpty()) {
               return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();

            if (permiso.getPermiso().toLowerCase().startsWith(lowerCaseFilter)) {
               return true;
            }
            return false;
         });
         inicializaTablaPermisos(sortedData);
      });
      paginador.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
         @Override
         public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            Utilerias.creaPaginaTableView(newValue.intValue(), renglonesMostrar, sortedData, tablaDatos, true);
         }
      });
      reportData = sortedData;
   }

   public void creaEditaPermiso(SegPermiso permiso, ActionEvent ev) {
      String titulo = null != permiso ? getSecurityProperty("CreaEditaFuncion.Header.Nuevo") : getSecurityProperty("CreaEditaFuncion.Header.Editar");
      Stage stage;
      try {

         CrearEditarFuncionesController controller = new CrearEditarFuncionesController(permiso, this);
         stage = Utilerias.nuevaVentana(Pantallas.FXML_CREAR_EDITAR_PERMISOS, controller, titulo);
         stage.initModality(Modality.WINDOW_MODAL);
         stage.initStyle(StageStyle.UTILITY);
         stage.setResizable(false);
         stage.initOwner(((Node) ev.getSource()).getScene().getWindow());
         stage.showAndWait();
      } catch (IOException ex) {
         Logger.getLogger(AdministrarFuncionesPerfilController.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public void desactivarFuncion(SegPermiso permiso) {
      new CrearEditarFuncionesService().actualizarPermiso(permiso);
   }

   public void asignarFunciones(ActionEvent event) {
      ObservableList<SegPermiso> lista = sortedData;
      boolean bandera = false;
      if (!lista.isEmpty()) {
         List<SegPermisoPerfil> permisosPerfiles = new ArrayList<>();
         for (SegPermiso segPermiso : lista) {
            if (segPermiso.getSelected()) {
               bandera = true;
               SegPermisoPerfilId segPermisoPerfilId = new SegPermisoPerfilId();
               segPermisoPerfilId.setIdPerfil(segPerfil.getId());
               segPermisoPerfilId.setIdPermiso(segPermiso.getId());
               SegPermisoPerfil segPermisoPerfil = new SegPermisoPerfil();
               segPermisoPerfil.setId(segPermisoPerfilId);
               segPermisoPerfil.setSegPermiso(segPermiso);
               segPermisoPerfil.setSegPerfil(segPerfil);
               permisosPerfiles.add(segPermisoPerfil);

            }
         }
         try {
            if (bandera == true) {
               new SegPermisoPerfilService().guardaPermisoPerfil(permisosPerfiles, segPerfil);
            } else {
               new SegPermisoPerfilService().eliminarPermisosPerfil(segPerfil);
            }
            Utilerias.dialogOk(getProperty("General.Save.Header"), getProperty("General.Save.Message"));
         } catch (Exception ex) {
            Utilerias.dialogOk(getProperty("General.ErrorSave.Header"), getProperty("General.ErrorSave.Message"));
         }
         inicializaPantalla();
      }

   }

   public void nuevoPermiso(ActionEvent event) {
      creaEditaPermiso(null, event);
   }

   public void regresa(ActionEvent event) {
      try {
         actionHandler.navigate(AdministrarPerfilesController.class);
      } catch (VetoException | FlowException e) {
         Utilerias.consoleMsg(e.getMessage(), e, this);
      }
   }

   public String getClaveDescripcionPermiso() {
      return claveDescripcionPermiso;
   }

   public void setClaveDescripcionPermiso(String claveDescripcionPermiso) {
      this.claveDescripcionPermiso = claveDescripcionPermiso;
   }

   public ObservableList<SegPermiso> getListaPermisos() {
      return listaPermisos;
   }

   public void setListaPermisos(ObservableList<SegPermiso> listaPermisos) {
      this.listaPermisos = listaPermisos;
   }

}
