/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.security.service;

import mx.vw.swf.security.dao.SegPermisoDAO;
import mx.vw.swf.security.model.SegPermiso;

/**
 *
 * @author fox1x0d
 */
public class CrearEditarFuncionesService {
    
    private SegPermisoDAO segPermisoDAO= new SegPermisoDAO();
    
    public void guardarPermiso(SegPermiso sp){
        segPermisoDAO.save(sp);
        
    }
    public void actualizarPermiso(SegPermiso sp){
        segPermisoDAO.update(sp);
    }
    
}
