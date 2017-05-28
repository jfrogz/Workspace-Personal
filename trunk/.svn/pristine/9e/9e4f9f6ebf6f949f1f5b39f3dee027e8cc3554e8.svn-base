/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.sms.utilerias;

import java.awt.SystemColor;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;
import mx.vw.swf.cdi.DataFXLauncher;
import mx.vw.swf.security.controller.Controller;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.Validator;

/**
 *
 * @author fox1yij
 */
public class OrValidator implements Validator<Object> {

    private TextInputControl[] list;

    public OrValidator(TextInputControl... listaDeCampos) {
        list = listaDeCampos;

        for (TextInputControl control : list) {
            
            //El evento se encarga de notificar a las demas cajas para que ejecuten su propio Apply
            control.setOnKeyPressed((KeyEvent event) -> {
                for (TextInputControl textControl : list) {
                    if (!control.equals(textControl)) {
                        String orig = textControl.getText();
                        textControl.setText("A");
                        textControl.setText(orig);
                    }
                }
            });
        }
    }

    @Override
    public ValidationResult apply(Control c, Object u) {
        ValidationResult validationResult = new ValidationResult();
        boolean bandera = false;
        TextInputControl esteControl = (TextInputControl) c;

        if (esteControl.getText().isEmpty()) {
            bandera = true;
            for (TextInputControl control : list) {
                bandera &= control.getText().isEmpty();
            }
        }

        if (bandera) {
            validationResult.add(new ValidationMessage() {
                @Override
                public String getText() {
                    String mensaje = DataFXLauncher.getProperty("Validations.OrText") + " ";
                    boolean ciclo = false;
                    for (TextInputControl control : list) {
                        if (ciclo) {
                            mensaje += " " + DataFXLauncher.getProperty("Validations.OrText.O") + " ";
                        }
                        mensaje += control.getProperties().get("fieldName");
                        ciclo = true;
                    }
                    return mensaje;
                }

                @Override
                public Severity getSeverity() {
                    return Severity.ERROR;
                }

                @Override
                public Control getTarget() {
                    return c;
                }
            });
        }
        return validationResult;
    }

}
