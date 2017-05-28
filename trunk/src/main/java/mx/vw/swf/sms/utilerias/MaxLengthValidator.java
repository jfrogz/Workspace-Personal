package mx.vw.swf.sms.utilerias;

import javafx.scene.control.Control;
import javafx.scene.control.TextInputControl;
import mx.vw.swf.cdi.DataFXLauncher;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.Validator;

/**
 * Created by dzm152z on 05/03/2015.
 */
public class MaxLengthValidator implements Validator<Object> {
    private final int max;
    private final String message;

    public MaxLengthValidator(int max) {
        this(max, null);
    }

    public MaxLengthValidator(int max, String message) {
        this.max = max;
        this.message = message;
    }

    @Override
    public ValidationResult apply(Control control, Object o) {
        String fieldName = (String) control.getProperties().get("fieldName");
        TextInputControl textField = (TextInputControl) control;
        String value = textField.getText();
        ValidationResult validationResult = new ValidationResult();
        if (value.length() > max) {
            validationResult.add(new ValidationMessage() {
                @Override
                public String getText() {
                    if (null == MaxLengthValidator.this.message) {
                        return DataFXLauncher.getProperty("Validations.Longitud.Max.CampoGeneral", fieldName,max);
                    }
                    return MaxLengthValidator.this.message;
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