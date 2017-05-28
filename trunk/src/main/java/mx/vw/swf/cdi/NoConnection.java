package mx.vw.swf.cdi;

import io.datafx.controller.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.annotation.PostConstruct;

/**
 * Created by dzm152z on 09/03/2015.
 */
@ViewController("/fxml/security/NoConnection.fxml")
public class NoConnection {
    @FXML
    Label info;

    @PostConstruct
    public void postContruct() {
        info.setText("Sin conexión a la base de datos");
    }
}
