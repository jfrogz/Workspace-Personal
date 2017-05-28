package mx.vw.swf.sms.utilerias;

import javafx.animation.FadeTransition;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;

import java.util.Collection;

/**
 * Created by dzm152z on 05/03/2015.
 */
public class ValidatorHandler {
    private Control errorDisplayControl;
    private ValidationSupport validationSupport;

    public ValidationSupport getValidationSupport() {
        return validationSupport;
    }

    public void setValidationSupport(ValidationSupport validationSupport) {
        this.validationSupport = validationSupport;
    }

    public Control getErrorDisplayControl() {
        return errorDisplayControl;
    }

    public void setErrorDisplayControl(Control errorDisplayControl) {
        this.errorDisplayControl = errorDisplayControl;
    }

    public void clear() {
        validationSupport = new ValidationSupport();
        errorDisplayControl = null;
    }

    public boolean displayValidationResult(Control control, boolean muestraMensaje) {
        ValidationResult result = validationSupport.getValidationResult();
        Collection<ValidationMessage> errors = result.getErrors();
        String mensajes  ="";
        for (ValidationMessage validationMessage : errors) {
           if(!muestraMensaje){
            ((Label) control).setText(validationMessage.getText());
           }
            mensajes+=validationMessage.getText().toString()+"\n";
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1));
            fadeTransition.setNode(control);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.setCycleCount(1);
            fadeTransition.setAutoReverse(true);
            fadeTransition.playFromStart(); 
           if(!muestraMensaje){
               break; 
           }
        }
        if(muestraMensaje && !errors.isEmpty()){
          Utilerias.dialogOk("Validaciones", mensajes);
        }
        return null == errors || errors.isEmpty();
    }
}
