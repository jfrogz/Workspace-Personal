/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.sms.utilerias;

import javafx.scene.control.Control;
import javafx.scene.control.TextInputControl;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.Validator;
import mx.vw.swf.cdi.DataFXLauncher;

/**
 *
 * @author fox1x0d
 */
public class LetrasValidator implements Validator<Object>{
    private final String message;

    public LetrasValidator() {
        this(null);
    }

    public LetrasValidator( String message) {
       
        this.message = message;
        
    }

    @Override
    public ValidationResult apply(Control control, Object o) {
        String fieldName = (String) control.getProperties().get("fieldName");
        TextInputControl textField = (TextInputControl) control;
        String value = textField.getText().trim();
        boolean cadenaCorrecta = value.matches("[A-Za-z_ñÑ_.]");
        ValidationResult validationResult = new ValidationResult();
       
        if (!value.isEmpty() && !cadenaCorrecta) {
            validationResult.add(new ValidationMessage() {
                @Override
                public String getText() {
                    if (null == LetrasValidator.this.message) {
                        return String.format(DataFXLauncher.getProperty("General.lbPermiteLetras"), fieldName);
                    }
                    return LetrasValidator.this.message;
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
