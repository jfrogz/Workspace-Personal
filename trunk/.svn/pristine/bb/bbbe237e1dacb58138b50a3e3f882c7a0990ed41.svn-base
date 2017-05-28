/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.security.controller;

import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import mx.vw.swf.cdi.DataFXLauncher;
import mx.vw.swf.fwk.dao.FwkCatContentDAO;
import mx.vw.swf.fwk.model.EntityManagerHelper;
import mx.vw.swf.fwk.model.FwkActionItem;
import mx.vw.swf.fwk.model.FwkCatContent;
import mx.vw.swf.security.event.SMSMenuItemEventHandler;
import mx.vw.swf.security.model.SegUsuario;
import mx.vw.swf.security.service.MenuService;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialogs;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.DirectoryChooser;
import static mx.vw.swf.cdi.DataFXLauncher.getProperty;
import static mx.vw.swf.cdi.DataFXLauncher.primary;
import static mx.vw.swf.cdi.DataFXLauncher.getSecurityProperty;
import mx.vw.swf.fwk.JasperUtil;
import mx.vw.swf.sms.utilerias.Utilerias;

/**
 *
 * @author fox1yij
 */
public class MenuController extends Controller {
   private static final String GENERAL_EXPORTER_INFO_TITLE = "General.Exporter.Info.Title";

   // @FXML @LinkAction(HelpController.class) Button btnAyuda;
   @FXML
   Hyperlink linkVolkswagen1;
   @FXML
   MenuBar welcomeMenuBar;
   @FXML
   Hyperlink linkManualUsuario;
   @FXML
   Label labelMovimientoActual;
   @FXML
   Label labelIdentificadorUsuario;
   @FXML
   Label encabezadoPrincipal;
   @FXML
   ImageView imagenSalida;
   @FXML
   ImageView imgAyuda;
   @FXML
   Hyperlink labelAyuda;
   @FXML
   Hyperlink labelSalida;
   @FXML
   Hyperlink lblSoftwareFactory;
   @FXML
   Label lbVersion;
   @ActionHandler
   FlowActionHandler actionHandler;

   @FXMLViewFlowContext
   ViewFlowContext viewFlowContext;

   @FXML
   public Label exporterLabel;
   @FXML
   public Label exporterPathLabel;
   @FXML
   public Label exporterPathText;
   public String reportName;
   public String reportOutputPath;
   public String reportFileName;
   public String reportTitle;
   public JasperUtil reports = new JasperUtil();
   public Map<String, Object> repParams = new HashMap();
   public List<?> reportData;

   public void irAIntranet(Event event) {
      FwkCatContentDAO actionItemDAO = new FwkCatContentDAO();
      FwkCatContent catContent = actionItemDAO.findById(Long.parseLong("18"));

      System.out.println("Redirecting");
      getHostServices().showDocument(catContent.getDescription());

   }

   public void irASoftwareFactory(Event event) {
      FwkCatContentDAO actionItemDAO = new FwkCatContentDAO();
      FwkCatContent catContent = actionItemDAO.findById(Long.parseLong("19"));
      System.out.println("Redirecting");
      getHostServices().showDocument(catContent.getDescription());
   }


   public void iniciarEventos() {
      imagenSalida.setOnMouseClicked((event) -> cerrarSesion());
      labelSalida.setOnMouseClicked((event) -> cerrarSesion());
   }

   private void cerrarSesion() {
      try {
         Action showConfirm = Dialogs.create()
                 .owner(DataFXLauncher.primary)
                 .title(DataFXLauncher.getProperty("Sistema.Nombre"))
                 .masthead(DataFXLauncher.getProperty("General.Logout.Header"))
                 .message(DataFXLauncher.getProperty("General.Logout.Message"))
                 .actions(org.controlsfx.dialog.Dialog.ACTION_YES, org.controlsfx.dialog.Dialog.ACTION_NO)
                 .showConfirm();

         if (showConfirm == org.controlsfx.dialog.Dialog.ACTION_YES) {
            actionHandler.navigate(LoginController.class);
         }
      } catch (VetoException ex) {
         Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
      } catch (FlowException ex) {
         Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   @PostConstruct
   public void postConstruct() {
      System.out.println("@MenuController.postConstruct() " + viewFlowContext.getCurrentViewContext());
      iniciarEventos();
      //Se generan los menus de acuerdo al permiso
      SegUsuario loggedUser = (SegUsuario) viewFlowContext.getRegisteredObject("USERPRINCIPAL");
      if (null != loggedUser) {
         if (!loggedUser.actionItemsSet()) {
            List<FwkActionItem> items = new MenuService().getItemsForUser(loggedUser);
            loggedUser.setActionItems(items);
            loggedUser.setItemActionsSet(true);
         }
         renderUserActionItems(loggedUser);
      }

      encabezadoPrincipal.setText(DataFXLauncher.getProperty("Sistema.Nombre"));
      labelSalida.setText(DataFXLauncher.getProperty("General.Logout"));
      linkManualUsuario.setText(DataFXLauncher.getProperty("General.ManualUsr"));
      lblSoftwareFactory.setText(DataFXLauncher.getProperty("General.SWF"));
      labelAyuda.setText(DataFXLauncher.getProperty("General.Ayuda"));

      SegUsuario usuario = (SegUsuario) viewFlowContext.getRegisteredObject("USERPRINCIPAL");
      if (usuario != null) {
         labelIdentificadorUsuario.setText("("
                 + usuario.getUserId() + ") " + usuario.getNombreCompleto());
      }

      //Se actualiza la versión del sistema
      FwkCatContentDAO actionItemDAO = new FwkCatContentDAO();
      FwkCatContent catContent = actionItemDAO.findById(Long.parseLong("17"));
      lbVersion.setText("Versión " + catContent.getDescription());

      if (null != exporterLabel) {
         exporterLabel.setText(getProperty("General.Exporter.Label"));
      }
      if (null != exporterPathLabel) {
         exporterPathLabel.setText(getProperty("General.Exporter.Path.Label"));
      }

      if (null != exporterPathText) {
         reportOutputPath = "d:\\Temp\\SMS\\";
         exporterPathText.setText(reportOutputPath);
      }

   }

   /**
    * <p>
    * Given a user paint all action items it has access to.
    * </p>
    *
    * @param loggedUser
    */
   private void renderUserActionItems(SegUsuario loggedUser) {
      welcomeMenuBar.getMenus().clear();
      loggedUser.getActionItems().stream().map((item) -> {
         Menu menu = new Menu(getSecurityProperty(item.getLabel()));
         item.getChildren().stream().forEach((child) -> {
            MenuItem menuItem = new MenuItem(getSecurityProperty(child.getLabel()));
            menuItem.getProperties().put("controller", child.getIconurl());
            menuItem.setOnAction(new SMSMenuItemEventHandler(actionHandler, viewFlowContext));
            menu.getItems().add(menuItem);
         });
         return menu;
      }).forEach((menu) -> {
         welcomeMenuBar.getMenus().add(menu);
      });
   }

   @Override
   public void start(Stage primaryStage) throws Exception {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

   }

   public void setEncabezado(String encabezado) {
      labelMovimientoActual.setText(encabezado);
   }

   @PreDestroy
   public void preDestroy() {
      EntityManagerHelper.closeEntityManager();
   }

   public void searchHelp(Event event) {
      String viewName = getFXMLViewName();
      if (null == viewName) {
         
      }
   }

   @FXML
   public void exportView(ActionEvent event) {
      repParams.put("tituloReporte", reportTitle);
      repParams.put("imprimePortada", true);
      try{
         reports.viewJasper(reportName, repParams, reportData);
      }catch (Exception e){
         Utilerias.consoleMsg(e.getMessage(), e, this);
         showError("Error en ejecución de reporte", e.getMessage());
      }
   }
   
   @FXML
   public void exportPrintConfigDialogs(ActionEvent event) {
      repParams.put("tituloReporte", reportTitle);
      repParams.put("imprimePortada", true);
      reports.printReport(JasperUtil.TipoReporte.XLS, reportName, repParams, reportData, false, true);
   }
   
   @FXML
   public void exportPrintPageConfigDialogs(ActionEvent event) {
      repParams.put("tituloReporte", reportTitle);
      repParams.put("imprimePortada", true);
      reports.printReport(JasperUtil.TipoReporte.XLS, reportName, repParams, reportData, true, true);
   }
   
   @FXML
   public void exportPrint(ActionEvent event) {
      repParams.put("tituloReporte", reportTitle);
      repParams.put("imprimePortada", true);
      reports.printReport(JasperUtil.TipoReporte.XLS, reportName, repParams, reportData);
   }

   @FXML
   public void exportXLS(ActionEvent event) {
      repParams.put("tituloReporte", reportTitle);
      repParams.put("imprimePortada", false);
      reports.runReportToFile(JasperUtil.TipoReporte.XLS, reportName, repParams, reportOutputPath + reportFileName + ".xls", reportData);
      showInfo(getProperty(GENERAL_EXPORTER_INFO_TITLE), getProperty("General.Exporter.Info.Message"), reportFileName + ".xls");
   }

   @FXML
   public void exportPDF(ActionEvent event) {
      repParams.put("tituloReporte", reportTitle);
      repParams.put("imprimePortada", true);
      repParams.put("soloDatos", false);
      reports.runReportToFile(JasperUtil.TipoReporte.PDF, reportName, repParams, reportOutputPath + reportFileName + ".pdf", reportData);
      showInfo(getProperty(GENERAL_EXPORTER_INFO_TITLE), getProperty("General.Exporter.Info.Message"), reportFileName + ".pdf");
   }

   @FXML
   public void exporterDirChooser(ActionEvent event) {
      DirectoryChooser dirChoose = new DirectoryChooser();
      dirChoose.setTitle(getProperty("General.Exporter.Path.Chooser.Title"));
      dirChoose.setInitialDirectory(new File(this.reportOutputPath));
      File selectedDir = dirChoose.showDialog(primary);
      this.reportOutputPath = selectedDir.getAbsolutePath() + "\\";
      if (null != exporterPathText) {
         exporterPathText.setText(this.reportOutputPath);
      }
   }
}
