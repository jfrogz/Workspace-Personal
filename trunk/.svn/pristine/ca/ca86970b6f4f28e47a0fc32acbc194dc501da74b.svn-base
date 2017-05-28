package mx.vw.swf.cdi;

import io.datafx.controller.ViewConfiguration;
import io.datafx.controller.context.ViewMetadata;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by dzm152z on 12/02/2015.
 */
public class SWFFlow extends Flow {
    public SWFFlow(Class<?> startViewControllerClass, ViewConfiguration viewConfiguration) {
        super(startViewControllerClass, viewConfiguration);
    }

    /**
     * Starts the flow directly in a Stage. This method is useful if an application contains of one main flow. Because
     * this flow can contain several sub-flows this is the preferred way to create a DataFX based application. The title
     * of the Stage will be bound to the title of the flow metadata and will change whenever the flow title fill change.
     * This can happen if a view of the flow defines its own title by using the title attribute of the @FXMLController
     * annotation or the ViewMetadata of an view is changed in code.
     *
     * By using the method a flow based application can be created by only a few lines of code as shown in this example:
     * <code>
     * public class Example extends Application {
     *
     *   public static void main(String[] args) {
     *       launch(args);
     *   }
     *
     *   public void start(Stage primaryStage) throws Exception {
     *       new Flow(SimpleController.class).startInStage(primaryStage);
     *   }
     *}
     *</code>
     * @param stage The stage in that the flow should be displayed.
     * @throws io.datafx.controller.flow.FlowException If the flow can't be created or started
     */
    public void startInStage(Stage stage) throws FlowException {
        SWFFlowHandler handler = new SWFFlowHandler(this, new ViewFlowContext());
        stage.setScene(new Scene(handler.start(new DefaultFlowContainer())));
        handler.getCurrentViewMetadata().addListener((e) -> {
            stage.titleProperty().unbind();
            ViewMetadata metadata = handler.getCurrentViewMetadata().get();
            if (metadata != null) {
                stage.titleProperty().bind(metadata.titleProperty());
            }
        });

        stage.titleProperty().unbind();
        ViewMetadata metadata = handler.getCurrentViewMetadata().get();
        if (metadata != null) {
            stage.titleProperty().bind(metadata.titleProperty());
        }

        stage.show();
    }

}
