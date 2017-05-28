/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.sms.utilerias;



import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextInputControl;
import mx.vw.swf.cdi.DataFXLauncher;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.Validator;
/**
 *
 * @author fox1yij
 */
public class ComboBoxValidatior implements Validator<Object>
{

    @Override
    public ValidationResult apply(Control control, Object u) {
        String fieldName = (String) control.getProperties().get("fieldName");
        ComboBox box = (ComboBox) control;
        ValidationResult validationResult = new ValidationResult();
        
        if(box.getSelectionModel().isEmpty())
        {
            validationResult.add(new ValidationMessage() {
                @Override
                public String getText() {                    
                        return DataFXLauncher.getProperty("Validations.Item.NoSeleccionado", fieldName);
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
