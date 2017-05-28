/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.sms.utilerias;

import java.util.function.BiFunction;
import java.util.function.Function;
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
public class EmailValidator implements Validator<Object> {    
    private final String message;

    public EmailValidator() {
        this(null);
    }

    public EmailValidator( String message) {
       
        this.message = message;
    }

    @Override
    public ValidationResult apply(Control control, Object o) {
        String fieldName = (String) control.getProperties().get("fieldName");
        TextInputControl textField = (TextInputControl) control;
        String value = textField.getText();
        boolean mailCorrecto=Utilerias.formatoEmail(value.trim());
        ValidationResult validationResult = new ValidationResult();
        if (!value.isEmpty() && !mailCorrecto) {
            validationResult.add(new ValidationMessage() {
                @Override
                public String getText() {
                    if (null == EmailValidator.this.message) {
                        return String.format(DataFXLauncher.getProperty("General.lbCorreoIncorrecto"), fieldName);
                    }
                    return EmailValidator.this.message;
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

    @Override
    public <V> BiFunction<Control, Object, V> andThen(Function<? super ValidationResult, ? extends V> after) {
        return null;
    }
}