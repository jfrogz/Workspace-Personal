package com.jfrogz.capaweb;

import com.jfrogz.capadatos.domain.Persona;
import com.jfrogz.capaservicio.PersonaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PersonaAction extends DispatchAction {
    private static Log logger = LogFactory.getLog(PersonaAction.class);
    //Atributo que será inyectado por Spring
    private PersonaService personaService;

    public void setPersonaService(PersonaService personaService) {
        this.personaService = personaService;
    }

    public ActionForward accionListar(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest req, HttpServletResponse res) throws Exception {
        logger.info("Ejecutando método listar");
        this.setListaPersonas(req);
        return mapping.findForward("listar");
    }

    private void setListaPersonas(HttpServletRequest req) {
        List<Persona> personas = this.personaService.listarPersonas();
        //Compartimos la lista de objetos persona con el JSP
        req.setAttribute("personas", personas);
        //Desplegamos solo para validar
        for(Persona persona : personas){
            logger.info("Persona: " + persona);
        }
    }
}
