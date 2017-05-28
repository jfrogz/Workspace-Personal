/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.security.service;

import mx.vw.swf.security.dao.SegPerfilDAO;
import mx.vw.swf.security.model.SegPerfil;

/**
 *
 * @author fox1x0d
 */
public class CrearEditarPerfilService {
    
    private SegPerfilDAO  segPerfilDAO = new SegPerfilDAO();
    
    public void guardarPerfil(SegPerfil sp){
        segPerfilDAO.save(sp);
        
    }
    public void actualizarPerfil(SegPerfil sp){
        segPerfilDAO.update(sp);
    }
    
}
