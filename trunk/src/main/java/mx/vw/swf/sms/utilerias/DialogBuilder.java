package mx.vw.swf.sms.utilerias;

import mx.vw.swf.cdi.DataFXLauncher;
import org.controlsfx.dialog.Dialogs;

/**
 * Created by dzm152z on 04/03/2015.
 */
public class DialogBuilder {
    public static Dialogs build() {
        return Dialogs.create()
                .owner(DataFXLauncher.primary)
                .title(DataFXLauncher.getProperty("Sistema.Nombre"))
                .masthead(DataFXLauncher.getProperty("Login.Valid.IncompleteData.Header"))
                .message(DataFXLauncher.getProperty("Login.Valid.IncompleteData.Message"));
    }
}
