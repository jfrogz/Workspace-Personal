/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.sms.utilerias;

import javafx.scene.control.Control;
import javafx.scene.control.TextInputControl;
import mx.vw.swf.cdi.DataFXLauncher;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.Validator;

/**
 *
 * @author fox1x0d
 */
public class NumerosValidator implements Validator<Object>{
    
    private final String message;

    public NumerosValidator() {
        this(null);
    }

    public NumerosValidator( String message) {
       
        this.message = message;
    }

    @Override
    public ValidationResult apply(Control control, Object o) {
        String fieldName = (String) control.getProperties().get("fieldName");
        TextInputControl textField = (TextInputControl) control;
        String value = textField.getText().trim();
        boolean cadenaCorrecta=value.matches("[0-9]");
        ValidationResult validationResult = new ValidationResult();
        if (!cadenaCorrecta) {
            validationResult.add(new ValidationMessage() {
                @Override
                public String getText() {
                    if (null == NumerosValidator.this.message) {
                        return String.format(DataFXLauncher.getProperty("General.lbPermiteNumeros"), fieldName);
                    }
                    return NumerosValidator.this.message;
                }
                @Override
                public Severity getSeverity() {
                    return Severity.ERROR;
                }
                @Override
                public Control getTarget() {
                    return control;
                }
            });
        }
        return validationResult;
    }
    
}
