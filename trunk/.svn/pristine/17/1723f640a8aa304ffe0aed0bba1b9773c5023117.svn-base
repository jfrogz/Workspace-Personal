package mx.vw.swf.sms.utilerias;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.controlsfx.validation.ValidationResult;

/**
 * Created by dzm152z on 04/03/2015.
 */
public class DefaultValidatorResultListener implements ChangeListener<ValidationResult> {
    private final VBox messages;

    public DefaultValidatorResultListener(VBox messages) {
        this.messages = messages;
    }
    @Override
    public void changed(ObservableValue<? extends ValidationResult> observable, ValidationResult oldValue, ValidationResult newValue) {
        messages.getChildren().clear();
        if (null!=newValue && !newValue.getErrors().isEmpty()) {
            newValue.getErrors().forEach((error) -> {
                Label label = new Label(error.getText());
                label.setTextFill(Color.RED);
                messages.getChildren().add(label);
            });
        }
    }
}
