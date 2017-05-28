/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.fwk.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.annotation.PostConstruct;
import static mx.vw.swf.cdi.DataFXLauncher.getProperty;
import mx.vw.swf.security.controller.Controller;


/**
 * FXML Controller class
 *
 * @author dzmppdw
 */
public class ExporterController extends Controller implements Initializable {

   @FXML
    private Label exporterLabel;

   /**
    * Initializes the controller class.
    * @param url
    * @param rb
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      
   }
   
   @PostConstruct
   public void postConstruct(){
      exporterLabel.setText(getProperty("General.Exporter.Label"));
   }

   @FXML
   private void exportXLS(ActionEvent event) {
   }

   @FXML
   private void exportPDF(ActionEvent event) {
   }

   @FXML
   private void exportView(ActionEvent event) {
   }

}
