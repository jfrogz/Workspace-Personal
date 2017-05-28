package mx.vw.swf.sms.utilerias;

import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import mx.vw.swf.cdi.DataFXLauncher;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.Validator;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by dzm152z on 04/03/2015.
 */
public class LengthNoEmptyValidator implements Validator<Object> {
    private final int max;
    private final int min;
    private final String message;

    public LengthNoEmptyValidator(int min, int max) {
        this(min,max, null);
    }

    public LengthNoEmptyValidator(int min, int max, String message) {
        this.min = min;
        this.max = max;
        this.message = message;
    }

    @Override
    public ValidationResult apply(Control control, Object o) {
        String fieldName = (String) control.getProperties().get("fieldName");
        TextInputControl textField = (TextInputControl) control;
        String value = textField.getText();
        ValidationResult validationResult = new ValidationResult();
        if(!value.isEmpty()){
        if (value.length() < min || value.length() > max) {
            validationResult.add(new ValidationMessage() {
                @Override
                public String getText() {
                    if (null == LengthNoEmptyValidator.this.message) {
                        return String.format(DataFXLauncher.getProperty("Validations.Longitud.CampoGeneral"), fieldName, min, max);
                    }
                    return LengthNoEmptyValidator.this.message;
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
        }
        return validationResult;
    }

    @Override
    public <V> BiFunction<Control, Object, V> andThen(Function<? super ValidationResult, ? extends V> after) {
        return null;
    }
}
