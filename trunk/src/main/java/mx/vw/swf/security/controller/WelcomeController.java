package mx.vw.swf.security.controller;

import io.datafx.controller.ViewController;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import mx.vw.swf.cdi.DataFXLauncher;
import mx.vw.swf.security.dao.SegUsuarioDAO;
import mx.vw.swf.security.model.SegPerfilUsr;
import mx.vw.swf.security.model.SegPermisoPerfil;
import mx.vw.swf.security.model.SegUsuario;

/**
 * Created by dzm152z on 12/02/2015.
 */
//@FXMLController("/fxml/security/Main.fxml")
@ViewController("/fxml/security/Main.fxml")
public class WelcomeController extends MenuController implements Serializable{
    Logger logger = Logger.getLogger(MenuController.class.getName());

    @FXMLViewFlowContext
    ViewFlowContext viewFlowContext;
   
    @PostConstruct
    public void postContruct () {
        super.postConstruct();  
        
        setEncabezado(DataFXLauncher.getProperty("Welcome.Header"));
        
        SegUsuario segUsuario = (SegUsuario) viewFlowContext.getRegisteredObject("USERPRINCIPAL");
        SegUsuarioDAO segUsuarioDAO = new SegUsuarioDAO();
        segUsuario = segUsuarioDAO.loadProfileAndPrivileges(segUsuario);
        for (SegPerfilUsr segPerfilUsr : segUsuario.getSegPerfilUsrs()) {
            logger.info("Got profile " + segPerfilUsr.getSegPerfil().getPerfil());
            for (SegPermisoPerfil permisoPerfil : segPerfilUsr.getSegPerfil().getSegPermisoPerfils()) {
                logger.info("with privilege " + permisoPerfil.getSegPermiso().getPermiso());
            }
        }

    }
}
