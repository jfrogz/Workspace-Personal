/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.security.service;

import java.util.List;
import java.util.logging.Level;
import mx.vw.swf.fwk.model.EntityManagerHelper;
import mx.vw.swf.security.dao.SegPermisoPerfilDAO;
import mx.vw.swf.security.model.SegPerfil;
import mx.vw.swf.security.model.SegPermisoPerfil;

/**
 *
 * @author fox1x0d
 */
public class SegPermisoPerfilService {

    SegPermisoPerfilDAO segPermisoPerfilDAO = new SegPermisoPerfilDAO();

    public void guardaPermisoPerfil(List<SegPermisoPerfil> permisosPerfiles, SegPerfil segPermiso) {
        EntityManagerHelper.log("saving SegPermisoPerfil instance", Level.INFO, null);
        try {

            segPermisoPerfilDAO.deleteQuery(segPermiso.getId());
            for (SegPermisoPerfil segPermisoPerfil : permisosPerfiles) {
                segPermisoPerfilDAO.save(segPermisoPerfil);
            }
        } catch (RuntimeException re) {
            EntityManagerHelper.log("save failed", Level.SEVERE, re);
            throw re;
        } catch (Exception re) {
            EntityManagerHelper.log("Fallo", Level.SEVERE, re);

        }
    }

    public void eliminarPermisosPerfil(SegPerfil segPermiso) {
        EntityManagerHelper.log("saving SegPermisoPerfil instance", Level.INFO, null);
        try {
            segPermisoPerfilDAO.deleteQuery(segPermiso.getId());
        } catch (RuntimeException re) {
            EntityManagerHelper.log("save failed", Level.SEVERE, re);
            throw re;
        } catch (Exception re) {
            EntityManagerHelper.log("Fallo", Level.SEVERE, re);

        }
    }

}
