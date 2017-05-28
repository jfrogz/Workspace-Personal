package mx.vw.swf.security.event;

import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import mx.vw.swf.fwk.Utilerias;

/**
 * Created by dzm152z on 16/02/2015.
 */
public class SMSMenuItemEventHandler implements EventHandler<ActionEvent> {
    private final FlowActionHandler actionHandler;
    @FXMLViewFlowContext
    ViewFlowContext viewFlowContext;


    public SMSMenuItemEventHandler(FlowActionHandler actionHandler, ViewFlowContext viewFlowContext) {
        this.actionHandler = actionHandler;
        this.viewFlowContext = viewFlowContext;
    }

    @Override
    public void handle(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        try {
            viewFlowContext.register("AppMap", new HashMap<Object, Object>());
            actionHandler.navigate(Class.forName((String) menuItem.getProperties().get("controller")));          
        } catch (VetoException | FlowException | ClassNotFoundException e) {
            Utilerias.consoleMsg(e.getMessage(), e, this);
        }
    }
}
