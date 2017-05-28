/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocolsearch;

import javafx.application.Application;
import javafx.stage.Stage;
import Controllers.*;
import DAO.ExpedienteDao;
import Utilerias.ConfiguracionActual;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
/**
 *
 * @author fernandorobles
 */
public class ProtocolSearch extends Application {
    public static Stage primary = null;
    public static ConfiguracionActual ActualConfig= new ConfiguracionActual();
    
    @Override
    public void start(Stage primaryStage) throws FlowException {        
        ProtocolSearch.primary = primaryStage;
        Flow flow = new Flow(PrincipalController.class);
        FlowHandler flowHandler = flow.createHandler();
        StackPane stackPane = flowHandler.start();
        primaryStage.setScene(new Scene(stackPane));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
