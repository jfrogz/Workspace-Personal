package mx.vw.swf.sms.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import mx.vw.swf.security.controller.MenuController;
import mx.vw.swf.security.model.SegUsuario;
import mx.vw.swf.sms.dao.SmsProductoDAO;
import mx.vw.swf.sms.model.SmsCteProv;
import mx.vw.swf.sms.model.SmsProdCteprov;
import mx.vw.swf.sms.model.SmsProdCteprovId;
import mx.vw.swf.sms.model.SmsProducto;
import mx.vw.swf.sms.service.CrearEditarProductoService;
import mx.vw.swf.sms.service.SmsProdCteprovService;
import mx.vw.swf.sms.utilerias.CheckBoxTableCell;
import mx.vw.swf.sms.utilerias.Pantallas;
import mx.vw.swf.sms.utilerias.Utilerias;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;

/**
 * FXML Controller class
 *
 * @author fox1yij
 */
@ViewController("/fxml/catalogos/RegistroProductosClientesProveedores.fxml")
public class AdministrarProductosController extends MenuController {

   @FXML
   private TableView<SmsProducto> tablaDatos;
   @FXML
   private Button btnNuevo, btnAsignarProductos, btnRegresarAtras;
   @FXML
   private Label lblClave, lblProducto, lbPaginacion, lbCteProv, lbNombreCteProv, filtrarLabel;
   @FXML
   private TableColumn<SmsProducto, SmsProducto> colAcciones, colSeleccione;
   @FXML
   private TextField flClave, flProducto;
   @FXML
   private ComboBox comboPaginacion;
   @FXML
   private Pagination paginador;
   @FXMLViewFlowContext
   ViewFlowContext viewFlowContext;
   @ActionHandler
   private FlowActionHandler actionHandler;
   private ObservableList<SmsProducto> listaProductos;
   private FilteredList<SmsProducto> filteredData;
   private SortedList<SmsProducto> sortedData;
   private int renglonesMostrar = 5;
   private SegUsuario segUsuario;
   private SmsCteProv smsCteProv;

   @PostConstruct
   public void postConstruct() {
      super.postConstruct();
      super.setEncabezado(getProperty("Catalogos.CltsProductos.Header"));
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
            flClave.requestFocus();
         }
      });
   }

   private void inicializaReportFrame() {
      repParams.clear();
      reportTitle = getProperty("Catalogos.CltsProductos.ReportTitle");
      reportName = getProperty("Catalogos.CltsProductos.ReportName");
      reportFileName = reportName.replace('.', '_');
      reportData = sortedData;
   }

   public void inicializaControles() {

      if (null != viewFlowContext.getRegisteredObject("AppMap")) {
         HashMap<Object, Object> mapa = (HashMap<Object, Object>) viewFlowContext.getRegisteredObject("AppMap");
         smsCteProv = (SmsCteProv) mapa.get("CTEPROVPRODUCTOS");
      } else {
         smsCteProv = null;
      }
      if (null != smsCteProv) {
         lbCteProv.setText(smsCteProv.getEsProveedor() == 1 ? getProperty("Catalogos.CltsPrvs.lbProveedor") + ":" : getProperty("Catalogos.CltsPrvs.lbCliente") + ":");
         lbNombreCteProv.setText(smsCteProv.getClave() + " - " + smsCteProv.getNombre());
         lbCteProv.setVisible(true);
         lbNombreCteProv.setVisible(true);
         colSeleccione.setVisible(true);
         btnAsignarProductos.setVisible(true);
         btnRegresarAtras.setVisible(true);
      } else {
         lbCteProv.setVisible(false);
         lbNombreCteProv.setVisible(false);
         lbCteProv.setText("");
         colSeleccione.setVisible(false);
         btnAsignarProductos.setVisible(false);
         btnRegresarAtras.setVisible(false);
      }
      segUsuario = (SegUsuario) viewFlowContext.getRegisteredObject("USERPRINCIPAL");

      comboPaginacion.setItems(Utilerias.comboPaginacion());
      comboPaginacion.getSelectionModel().select(0);
      btnAsignarProductos.setText(getProperty("Catalogos.CltsProductos.btnAsignarProductos"));
      btnRegresarAtras.setText(getProperty("General.botonRegresar"));
      lbPaginacion.setText(getProperty("General.Paginacion.lbRegistros"));
      btnNuevo.setText(getProperty("General.btnNuevo"));
      lblClave.setText(getProperty("Catalogos.CltsPrvs.lbClave") + ":");
      lblProducto.setText(getProperty("Catalogos.CltsProductos.lbProducto") + ":");
      filtrarLabel.setText(getProperty("General.Filtrar.Label"));

   }

   public void inicializaColumnasTabla() {

      String nombresHeader[] = {
         getProperty("General.lbSeleccione"),
         getProperty("General.lbID"),
         getProperty("Catalogos.CltsPrvs.NuevoEditar.lblIdSAP"),
         getProperty("Catalogos.CltsPrvs.NuevoEditar.lblClave"),
         getProperty("Catalogos.CltsProductos.lbProducto"),
         getProperty("General.lbEstatus"),
         getProperty("General.lbAcciones")};
      String valoresHeader[] = {"id", "idSap", "clave", "nombre", "etiquetaEstatus"};
      Utilerias.headerValuesColumnTableView(nombresHeader, valoresHeader, tablaDatos, true);
      inicializaColumnasControles();

   }

   public void inicializaColumnasControles() {
      colAcciones.setSortable(false);
      colSeleccione.setSortable(false);
      colSeleccione.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SmsProducto, SmsProducto>, ObservableValue<SmsProducto>>() {
         public ObservableValue<SmsProducto> call(TableColumn.CellDataFeatures<SmsProducto, SmsProducto> p) {
            return new ReadOnlyObjectWrapper(p.getValue());
         }
      });
      colSeleccione.setCellFactory(new Callback<TableColumn<SmsProducto, SmsProducto>, TableCell<SmsProducto, SmsProducto>>() {
         public TableCell<SmsProducto, SmsProducto> call(TableColumn<SmsProducto, SmsProducto> p) {
            return new CheckBoxTableCell<SmsProducto, SmsProducto>(false);
         }

      });
      colAcciones.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SmsProducto, SmsProducto>, ObservableValue<SmsProducto>>() {
         public ObservableValue<SmsProducto> call(TableColumn.CellDataFeatures<SmsProducto, SmsProducto> p) {
            return new ReadOnlyObjectWrapper(p.getValue());
         }
      });

      colAcciones.setCellFactory(new Callback<TableColumn<SmsProducto, SmsProducto>, TableCell<SmsProducto, SmsProducto>>() {
         @Override
         public TableCell<SmsProducto, SmsProducto> call(TableColumn<SmsProducto, SmsProducto> param) {
            TableCell<SmsProducto, SmsProducto> cell = new TableCell<SmsProducto, SmsProducto>() {

               Button editar = new Button(getProperty("General.btnEditar"));
               Button desactivar = new Button();
               Short desactiva = 0;

               @Override
               public void updateItem(SmsProducto item, boolean empty) {
                  if (null != item) {
                     editar.getStyleClass().add("botones");
                     editar.getStyleClass().add("buttonEdit");
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
                           creaEditaProducto(item, t);
                        }
                     });
                     desactivar.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {
                           Action response = Utilerias.dialogConfirm(getProperty("Catalogos.CltsProductos.Valid.DesAct.Header"),
                                   getProperty("Catalogos.CltsProductos.Valid.DesAct.Message"));
                           if (response == Dialog.ACTION_YES) {
                              item.setEstatus(desactiva);
                              desactivarProducto(item);
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

   public void inicializaPatalla() {
      setListaProductos(FXCollections.observableArrayList(obtieneListaProductos()));
      filteredData = new FilteredList<>(getListaProductos(), p -> true);
      sortedData = (SortedList<SmsProducto>) Utilerias.creaFiltro(filteredData, tablaDatos);
      inicializaTablaProductos(sortedData);
      reportData = sortedData;
   }

   public ObservableList<SmsProducto> obtieneListaProductos() {
      List<SmsProducto> lista = new SmsProductoDAO().findAll();
      ObservableList<SmsProducto> listaol = FXCollections.observableArrayList();
      for (SmsProducto item : lista) {
         item.setSelected(false);
         if (null != smsCteProv) {
            Iterator iter = item.getSmsProdCteprovs().iterator();
            while (iter.hasNext()) {
               SmsProdCteprov h = (SmsProdCteprov) iter.next();
               if (smsCteProv.getId() == h.getSmsCteProv().getId()) {
                  System.out.print(h);
                  item.setSelected(true);
               }

            }
         }
         listaol.add(item);
      }
      return listaol;
   }

   public void inicializaTablaProductos(SortedList<SmsProducto> lista) {
      renglonesMostrar = Integer.parseInt(comboPaginacion.getValue().toString());
      Utilerias.inicializaPaginador(paginador, lista, renglonesMostrar);
      Utilerias.creaPaginaTableView(0, renglonesMostrar, lista, tablaDatos, false);
   }

   public void inicializaEventos() {

      flClave.addEventFilter(KeyEvent.KEY_TYPED, Utilerias.permiteLetrasNumeros());
      Utilerias.addTextLimiter(flProducto, 100);
      Utilerias.addTextLimiter(flClave, 150);

      btnNuevo.setOnAction((event) -> nuevoProducto(event));
      btnRegresarAtras.setOnAction((event) -> regresa(event));
      btnAsignarProductos.setOnAction((event) -> asignarProductos(event));

      comboPaginacion.setOnAction((event) -> {
         renglonesMostrar = Integer.parseInt(comboPaginacion.getValue().toString());
         inicializaTablaProductos(sortedData);
      });
      flClave.textProperty().addListener((observable, oldValue, newValue) -> {
         filteredData.setPredicate(prod -> {
            String clave = flClave.getText().toLowerCase().trim();
            String producto = flProducto.getText().toLowerCase().trim();
            if ((null == clave || clave.isEmpty()) && (null == producto || producto.isEmpty())) {
               return true;
            } else {
               if (prod.getClave().toLowerCase().startsWith(clave) && prod.getNombre().toLowerCase().startsWith(producto)) {
                  return true;
               }
            }

            return false;
         });
         inicializaTablaProductos(sortedData);
      });
      flProducto.textProperty().addListener((observable, oldValue, newValue) -> {
         filteredData.setPredicate(prod -> {
            String clave = flClave.getText().toLowerCase().trim();
            String producto = flProducto.getText().toLowerCase().trim();
            if ((null == clave || clave.isEmpty()) && (null == producto || producto.isEmpty())) {
               return true;
            } else {
               if (prod.getClave().toLowerCase().startsWith(clave) && prod.getNombre().toLowerCase().startsWith(producto)) {
                  return true;
               }
            }

            return false;
         });
         inicializaTablaProductos(sortedData);
      });
      paginador.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
         @Override
         public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            Utilerias.creaPaginaTableView(newValue.intValue(), renglonesMostrar, sortedData, tablaDatos, true);
         }
      });
      reportData = sortedData;
   }

   public void asignarProductos(ActionEvent event) {
      ObservableList<SmsProducto> lista = sortedData;
      boolean bandera = false;
      if (!lista.isEmpty()) {
         List<SmsProdCteprov> listaProdCteprov = new ArrayList<>();
         for (SmsProducto smsProducto : lista) {
            if (smsProducto.getSelected()) {
               bandera = true;
               SmsProdCteprovId smsProdCteprovId = new SmsProdCteprovId();
               smsProdCteprovId.setIdCteProv(smsCteProv.getId());
               smsProdCteprovId.setIdProducto(smsProducto.getId());

               SmsProdCteprov smsProdCteprov = new SmsProdCteprov();
               smsProdCteprov.setId(smsProdCteprovId);
               smsProdCteprov.setSmsCteProv(smsCteProv);
               smsProdCteprov.setSmsProducto(smsProducto);

               listaProdCteprov.add(smsProdCteprov);

            }
         }
         try {
            if (bandera == true) {
               new SmsProdCteprovService().guardaProductosClienteProveedor(listaProdCteprov, smsCteProv);
            } else {
               new SmsProdCteprovService().eliminaProductosClienteProveedor(smsCteProv);
            }
            Utilerias.dialogOk(getProperty("General.Save.Header"), getProperty("General.Save.Message"));
         } catch (Exception ex) {
            Utilerias.dialogOk(getProperty("General.ErrorSave.Header"), getProperty("General.ErrorSave.Message"));
         }
         inicializaPatalla();

      }
   }

   public void nuevoProducto(ActionEvent event) {
      creaEditaProducto(null, event);
   }

   public void desactivarProducto(SmsProducto smsProducto) {
      new CrearEditarProductoService().actualizaProducto(smsProducto);
      inicializaPatalla();
   }

   public void creaEditaProducto(SmsProducto smsProducto, ActionEvent ev) {
      String titulo = null != smsProducto ? getProperty("Catalogos.CltsProductos.NuevoEditar.Header.Update") : getProperty("Catalogos.CltsProductos.NuevoEditar.Header.New");
      Stage stage;
      try {
         CrearEditarProductos controller = new CrearEditarProductos(segUsuario, smsProducto, this);
         stage = Utilerias.nuevaVentana(Pantallas.FXML_CREAR_EDITAR_PRODUCTOS, controller, titulo);
         stage.initModality(Modality.WINDOW_MODAL);
         stage.initStyle(StageStyle.UTILITY);
         stage.setResizable(false);
         stage.initOwner(((Node) ev.getSource()).getScene().getWindow());
         stage.showAndWait();
      } catch (IOException ex) {
         Logger.getLogger(RegistroDeClientesYProveedoresController.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public void regresa(ActionEvent event) {
      try {
         actionHandler.navigate(RegistroDeClientesYProveedoresController.class);
      } catch (VetoException | FlowException e) {
         Utilerias.consoleMsg(e.getMessage(), e, this);
      }
   }

   public void setListaProductos(ObservableList<SmsProducto> listaProductos) {
      this.listaProductos = listaProductos;
   }

   public ObservableList<SmsProducto> getListaProductos() {
      return listaProductos;
   }

}
