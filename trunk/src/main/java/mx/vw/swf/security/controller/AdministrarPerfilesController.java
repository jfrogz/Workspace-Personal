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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.annotation.PostConstruct;
import static mx.vw.swf.cdi.DataFXLauncher.getProperty;
import mx.vw.swf.security.dao.SegPerfilDAO;
import mx.vw.swf.security.model.SegPerfil;
import mx.vw.swf.security.model.SegPerfilUsr;
import mx.vw.swf.security.model.SegPerfilUsrId;
import mx.vw.swf.security.model.SegUsuario;
import mx.vw.swf.security.service.CrearEditarPerfilService;
import mx.vw.swf.security.service.CrearEditarUsuariosService;
import mx.vw.swf.security.service.SegPerfilUsrService;
import mx.vw.swf.sms.utilerias.CheckBoxTableCell;
import mx.vw.swf.sms.utilerias.Pantallas;
import mx.vw.swf.sms.utilerias.Utilerias;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import static mx.vw.swf.cdi.DataFXLauncher.getSecurityProperty;
import mx.vw.swf.sms.utilerias.Permisos;

/**
 * FXML Controller class
 *
 * @author fox1x0d
 */
@ViewController("/fxml/security/AdministrarPerfiles.fxml")
public class AdministrarPerfilesController extends MenuController {

   @FXML
   private TableView<SegPerfil> tablaDatos;
   @FXML
   private TableColumn<SegPerfil, SegPerfil> colSecleccione;
   @FXML
   private TableColumn<SegPerfil, SegPerfil> colAcciones;
   @FXML
   private TextField flPerfil;
   @FXML
   private Label lbUsuario, lbPerfil, lbClaveNombreUsuario, lbPaginacion, filtrarLabel;
   @FXML
   private Button btnNuevo, btnAsignarPerfiles, btnRegresarAtras;
   @FXML
   private GridPane gridInformacion;
   @FXML
   private ComboBox comboPaginacion;
   @FXML
   private Pagination paginador;
   @FXMLViewFlowContext
   ViewFlowContext viewFlowContext;
   @ActionHandler
   private FlowActionHandler actionHandler;
   public SegUsuario segUsuario;
   private ObservableList<SegPerfil> listaPerfiles;
   private FilteredList<SegPerfil> filteredData;
   private SortedList<SegPerfil> sortedData;
   private String claveNombreUsuario;
   private boolean desactivaCheck = false;
   private int renglonesMostrar = 5;
   public SegUsuario segUsuarioPermisos;
  

   @PostConstruct
   public void postConstruct() {

      super.postConstruct();
      super.setEncabezado(getSecurityProperty("AdmininstraPerfiles.Header"));
      inicializaControles();
      inicializaColumnasTabla();
      inicializaPantalla();
      inicializaEventos();
      inicializaReportFrame();
      initialize();
   }

   private void inicializaReportFrame() {
      repParams.clear();
      if (null != segUsuario) {
         repParams.put("userId",segUsuario.getUserId());
         repParams.put("userName", segUsuario.getNombreCompleto());
      }
      reportTitle = getSecurityProperty("AdministraPerfil.ReportTitle");
      reportName = getSecurityProperty("AdministraPerfil.ReportName");
      reportFileName = reportName.replace('.', '_');
      reportData = sortedData;
   }

   public void initialize() {
      Platform.runLater(new Runnable() {
         @Override
         public void run() {
            flPerfil.requestFocus();
         }
      });
   }

   public void inicializaControles() {
       
      segUsuarioPermisos = (SegUsuario) viewFlowContext.getRegisteredObject("USERPRINCIPAL"); 

      if (null != viewFlowContext.getRegisteredObject("AppMap")) {
         HashMap<Object, Object> mapa = (HashMap<Object, Object>) viewFlowContext.getRegisteredObject("AppMap");
         segUsuario = (SegUsuario) mapa.get("USUARIOPERFIL");
      } else {
         segUsuario = null;
      }
      if (null != segUsuario) {
         lbClaveNombreUsuario.setVisible(true);
         lbClaveNombreUsuario.setText(segUsuario.getUserId() + " - " + segUsuario.getNombreCompleto());
         lbUsuario.setVisible(true);
         colSecleccione.setVisible(true);
         btnAsignarPerfiles.setVisible(true);
         btnRegresarAtras.setVisible(true);
      } else {
         lbClaveNombreUsuario.setVisible(false);
         lbClaveNombreUsuario.setText("");
         lbUsuario.setVisible(false);
         colSecleccione.setVisible(false);
         btnAsignarPerfiles.setVisible(false);
         btnRegresarAtras.setVisible(false);
         gridInformacion.getRowConstraints().get(2).setPrefHeight(1.0);

      }

      comboPaginacion.setItems(Utilerias.comboPaginacion());
      comboPaginacion.getSelectionModel().select(0);
      lbPaginacion.setText(getProperty("General.Paginacion.lbRegistros"));
      btnNuevo.setText(getProperty("General.btnNuevo"));
      btnAsignarPerfiles.setText(getSecurityProperty("AdmininstraPerfiles.btnAsignarPerfiles"));
      btnRegresarAtras.setText(getProperty("General.botonRegresar"));
      lbUsuario.setText(getProperty("Login.UsrLabel") + ":");
      lbPerfil.setText(getSecurityProperty("AdmininstraPerfiles.lbPerfil") + ":");
      filtrarLabel.setText(getProperty("General.Filtrar.Label"));
      btnNuevo.setDisable(!segUsuarioPermisos.testPrivilege(Permisos.ADMIN_PERFIL_NUEVO));
      btnAsignarPerfiles.setDisable(!segUsuarioPermisos.testPrivilege(Permisos.ADMIN_USR_ASIGNAR_PERFILES));

   }

   public void inicializaColumnasTabla() {

      String nombresHeader[] = {
         getProperty("General.lbSeleccione"),
         getProperty("General.lbID"),
         getSecurityProperty("AdmininstraPerfiles.lbPerfil"),
         getProperty("General.lbEstatus"),
         getProperty("General.lbAcciones")};
      String valoresHeader[] = {"id", "perfil", "etiquetaEstatus"};
      Utilerias.headerValuesColumnTableView(nombresHeader, valoresHeader, tablaDatos, true);
      inicializaColumnasControles();

   }

   public void inicializaColumnasControles() {
      colAcciones.setSortable(false);
      colSecleccione.setSortable(false);
      colSecleccione.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SegPerfil, SegPerfil>, ObservableValue<SegPerfil>>() {
         public ObservableValue<SegPerfil> call(TableColumn.CellDataFeatures<SegPerfil, SegPerfil> p) {
            return new ReadOnlyObjectWrapper(p.getValue());
         }
      });
      colSecleccione.setCellFactory(new Callback<TableColumn<SegPerfil, SegPerfil>, TableCell<SegPerfil, SegPerfil>>() {
         public TableCell<SegPerfil, SegPerfil> call(TableColumn<SegPerfil, SegPerfil> p) {
            return new CheckBoxTableCell<SegPerfil, SegPerfil>(desactivaCheck);
         }

      });
      colAcciones.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SegPerfil, SegPerfil>, ObservableValue<SegPerfil>>() {
         public ObservableValue<SegPerfil> call(TableColumn.CellDataFeatures<SegPerfil, SegPerfil> p) {
            return new ReadOnlyObjectWrapper(p.getValue());

         }
      });

      colAcciones.setCellFactory(new Callback<TableColumn<SegPerfil, SegPerfil>, TableCell<SegPerfil, SegPerfil>>() {
         @Override
         public TableCell<SegPerfil, SegPerfil> call(TableColumn<SegPerfil, SegPerfil> param) {
            TableCell<SegPerfil, SegPerfil> cell = new TableCell<SegPerfil, SegPerfil>() {
               FlowActionHandler actionHandlerPerfil = actionHandler;
               Button editar = new Button(getProperty("General.btnEditar"));
               Button funciones = new Button(getSecurityProperty("AdministraFuncionesPerfil.btnAsignarFunciones"));
               Button desactivar = new Button();
               Short desactiva = 0;

               @Override
               public void updateItem(SegPerfil item, boolean empty) {
                  if (null != item) {
                     
                     //funciones.setDisable(!segUsuarioPermisos.testPrivilege(Permisos.ADMIN_PERFIL_ASIGNAR_PERMISOS));
                     editar.setDisable(!segUsuarioPermisos.testPrivilege(Permisos.ADMIN_PERFIL_EDITAR));
                     desactivar.setDisable(!segUsuarioPermisos.testPrivilege(Permisos.ADMIN_PERFIL_ACTIVAR_DESACTIVAR));
                      
                     editar.getStyleClass().add("botones");
                     editar.getStyleClass().add("buttonEdit");
                     editar.applyCss();
                     funciones.getStyleClass().add("botones");
                     funciones.getStyleClass().add("buttonAdd");
                     desactivar.getStyleClass().add("botones");
                     desactivar.setPrefWidth(100);
                     funciones.setPrefWidth(142.0);
                     desactivar.setAlignment(Pos.BASELINE_LEFT);
                     HBox botones = new HBox(editar, desactivar, funciones);
                     botones.setSpacing(10);
                     setGraphic(botones);

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
                           creaEditaPerfil(item, t);

                        }
                     });
                     desactivar.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {
                           Action response = Utilerias.dialogConfirm(getSecurityProperty("AdmininstraPerfiles.Valid.DesAct.Header"),
                                   getSecurityProperty("AdmininstraPerfiles.Valid.DesAct.Message"));
                           if (response == Dialog.ACTION_YES) {
                              item.setEstatus(desactiva);
                              desactivarPerfil(item);
                              inicializaPantalla();
                           }

                        }
                     });
                     funciones.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {
                           try {
                              HashMap<Object, Object> mapa = (HashMap<Object, Object>) viewFlowContext.getRegisteredObject("AppMap");
                              mapa.put("PERFILPERMISO", item);
                              actionHandlerPerfil.navigate(AdministrarFuncionesPerfilController.class);
                           } catch (VetoException | FlowException e) {
                              Utilerias.consoleMsg(e.getMessage(), e, this);
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
      setListaPerfiles(FXCollections.observableArrayList(obtieneListaPerfiles()));
      filteredData = new FilteredList<>(getListaPerfiles(), p -> true);
      sortedData = (SortedList<SegPerfil>) Utilerias.creaFiltro(filteredData, tablaDatos);
      inicializaTablaPerfil(sortedData);
      reportData = sortedData;
   }

   public void inicializaTablaPerfil(SortedList<SegPerfil> lista) {
      renglonesMostrar = Integer.parseInt(comboPaginacion.getValue().toString());
      Utilerias.inicializaPaginador(paginador, lista, renglonesMostrar);
      Utilerias.creaPaginaTableView(0, renglonesMostrar, lista, tablaDatos, false);
   }

   public ObservableList<SegPerfil> obtieneListaPerfiles() {
      List<SegPerfil> lista = new SegPerfilDAO().findAll();
      boolean revisarUsuariosManagement = false;
      String cadena = "management".toLowerCase();
      Integer idPerfil = 0;
      ObservableList<SegPerfil> listaol = FXCollections.observableArrayList();
      for (SegPerfil item : lista) {
         item.setSelected(false);
         if (null != segUsuario) {
            Iterator iter = item.getSegPerfilUsrs().iterator();
            while (iter.hasNext()) {
               SegPerfilUsr h = (SegPerfilUsr) iter.next();
               if (segUsuario.getId() == h.getSegUsuario().getId()) {

                  if (h.getSegPerfil().getPerfil().toLowerCase().contains(cadena)) {
                     revisarUsuariosManagement = true;
                     idPerfil = h.getSegPerfil().getId();
                  }

                  item.setSelected(true);
               }

            }
         }
         listaol.add(item);
      }
      //Revisa que exista mas de un usuario con el perfil de User Management
      if (revisarUsuariosManagement) {
         List<SegPerfilUsr> listaUsers = new CrearEditarUsuariosService().listaUsersManagement(segUsuario.getId(), idPerfil);
         desactivaCheck = listaUsers.isEmpty() ? true : false;
      }
      return listaol;
   }

   public ObservableList<SegPerfil> getListaPerfiles() {
      return listaPerfiles;
   }

   public void setListaPerfiles(ObservableList<SegPerfil> listaPerfiles) {
      this.listaPerfiles = listaPerfiles;
   }

   public void inicializaEventos() {
      flPerfil.addEventFilter(KeyEvent.KEY_TYPED, Utilerias.permiteLetrasNumeros());
      Utilerias.addTextLimiter(flPerfil, 50);

      btnNuevo.setOnAction((event) -> nuevoPerfil(event));
      btnAsignarPerfiles.setOnAction((event) -> asignarPerfiles(event));
      btnRegresarAtras.setOnAction((event) -> regresa(event));

      comboPaginacion.setOnAction((event) -> {
         renglonesMostrar = Integer.parseInt(comboPaginacion.getValue().toString());
         inicializaTablaPerfil(sortedData);
      });

      flPerfil.textProperty().addListener((observable, oldValue, newValue) -> {
         filteredData.setPredicate(perfil -> {
            if (null == newValue || newValue.isEmpty()) {
               return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();

            if (perfil.getPerfil().toLowerCase().startsWith(lowerCaseFilter)) {
               return true;
            }
            return false;
         });
         inicializaTablaPerfil(sortedData);
      });
      paginador.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
         @Override
         public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            Utilerias.creaPaginaTableView(newValue.intValue(), renglonesMostrar, sortedData, tablaDatos, true);
         }
      });
      reportData = sortedData;
   }

   public void creaEditaPerfil(SegPerfil perfil, ActionEvent ev) {
      String titulo = null != perfil ? getSecurityProperty("CreaEditaPerfil.Header.Nuevo") : getSecurityProperty("CreaEditaPerfil.Header.Nuevo");
      Stage stage;
      try {

         CrearEditarPerfilController controller = new CrearEditarPerfilController(perfil, this);
         stage = Utilerias.nuevaVentana(Pantallas.FXML_CREAR_EDITAR_PERFILES, controller, titulo);
         stage.initModality(Modality.WINDOW_MODAL);
         stage.initStyle(StageStyle.UTILITY);
         stage.setResizable(false);
         stage.initOwner(((Node) ev.getSource()).getScene().getWindow());
         stage.showAndWait();
      } catch (IOException ex) {
         Logger.getLogger(AdministrarPerfilesController.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public void desactivarPerfil(SegPerfil perfil) {
      new CrearEditarPerfilService().actualizarPerfil(perfil);
   }

   public void nuevoPerfil(ActionEvent event) {
      creaEditaPerfil(null, event);
   }

   public void regresa(ActionEvent event) {
      try {
         actionHandler.navigate(AdministrarUsuariosController.class);
      } catch (VetoException | FlowException e) {
         Utilerias.consoleMsg(e.getMessage(), e, this);
      }
   }

   public void asignarPerfiles(ActionEvent event) {
      ObservableList<SegPerfil> lista = sortedData;
      boolean bandera = false;
      if (!lista.isEmpty()) {
         List<SegPerfilUsr> perfilesUsuario = new ArrayList<>();
         for (SegPerfil segPerfil : lista) {
            if (segPerfil.getSelected()) {
               bandera = true;
               SegPerfilUsrId segPerfilUsrId = new SegPerfilUsrId();
               segPerfilUsrId.setIdUsuario(segUsuario.getId());
               segPerfilUsrId.setIdPerfil(segPerfil.getId());

               SegPerfilUsr segPerfilUsr = new SegPerfilUsr();
               segPerfilUsr.setId(segPerfilUsrId);
               segPerfilUsr.setSegPerfil(segPerfil);
               segPerfilUsr.setSegUsuario(segUsuario);
               perfilesUsuario.add(segPerfilUsr);

            }
         }
         try {
            if (bandera == true) {
               new SegPerfilUsrService().guardaPerfilesUsuario(perfilesUsuario, segUsuario);
            } else {
               new SegPerfilUsrService().eliminaPerfilesUsuario(segUsuario);
            }
            Utilerias.dialogOk(getProperty("General.Save.Header"), getProperty("General.Save.Message"));
         } catch (Exception ex) {
            Utilerias.dialogOk(getProperty("General.ErrorSave.Header"), getProperty("General.ErrorSave.Message"));
         }
         inicializaPantalla();

      }
   }

   public String getClaveNombreUsuario() {
      return claveNombreUsuario;
   }

   public void setClaveNombreUsuario(String claveNombreUsuario) {
      this.claveNombreUsuario = claveNombreUsuario;
   }

}
