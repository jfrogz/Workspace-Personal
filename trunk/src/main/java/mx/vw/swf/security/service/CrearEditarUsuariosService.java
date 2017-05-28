/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.security.service;

import java.util.List;
import mx.vw.swf.security.dao.SegPerfilDAO;
import mx.vw.swf.security.dao.SegPerfilUsrDAO;
import mx.vw.swf.security.dao.SegUsuarioDAO;
import mx.vw.swf.security.model.SegPerfilUsr;
import mx.vw.swf.security.model.SegUsuario;

/**
 *
 * @author fox1x0d
 */
public class CrearEditarUsuariosService {

    private SegUsuarioDAO segUsuarioDAO = new SegUsuarioDAO();
    private SegPerfilUsrDAO segPerfilUsrDAO = new SegPerfilUsrDAO();
    private SegPerfilDAO segPerfil = new SegPerfilDAO();

    public void guardaUsuario(SegUsuario usuario) {
        segUsuarioDAO.save(usuario);
    }

    public boolean actualizaUsuario(SegUsuario usuario) {

        //Revisamos si se quiere activar o desactivar
        boolean userMng = false;
        boolean mensaje = false;
        int idPerfil = 0;
        
        String cadena = "management".toLowerCase();
        if (usuario.getEstatus() != 1) {
            // Verificamos si el usuario a desactivar tiene perfil de User Management
            List<SegPerfilUsr> listaPerfilesUsuario = segPerfilUsrDAO.findByProperty("segUsuario.id", usuario.getId());
            for (SegPerfilUsr segPerfilUsr : listaPerfilesUsuario) {
                if (segPerfilUsr.getSegPerfil().getPerfil().toLowerCase().contains(cadena)) {
                    userMng = true;
                    idPerfil = segPerfilUsr.getSegPerfil().getId();
                }
            }
        }
        if (userMng) {
            //Si el usuario tiene perfil  User Management debemos revisar que haya mas usuarios con ese perfil para poder desactivarlo
            List<SegPerfilUsr> listaUsers = listaUsersManagement(usuario.getId(), idPerfil);
            if (listaUsers.isEmpty()) {
                mensaje = true;
            }

        } 
        if(userMng == false || mensaje==false)
        {
            //Si el usuario no tiene el perfil de User Management actualiza el estatus
             segUsuarioDAO.update(usuario);
        }
        
        return mensaje;
    }

    public List<SegPerfilUsr> listaUsersManagement(Long idUsuario, Integer idPerfil){
         List<SegPerfilUsr> listaUsersManagement = segPerfilUsrDAO.findUsersManagement(idUsuario, idPerfil);
         return listaUsersManagement;
        
    }
}