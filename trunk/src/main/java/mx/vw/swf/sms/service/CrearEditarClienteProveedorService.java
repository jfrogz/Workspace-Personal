/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.sms.service;

import mx.vw.swf.sms.dao.SmsCteProvDAO;
import mx.vw.swf.sms.model.SmsCteProv;

/**
 *
 * @author fox1yij
 */
public class CrearEditarClienteProveedorService {
    SmsCteProvDAO smsCteProvDAO = new SmsCteProvDAO();
    
    public void guardarEntidad(SmsCteProv entity)
    {
        
        smsCteProvDAO.save(entity);
      
    }
    
    public void actualizaClienteProveedor(SmsCteProv entity){        
        smsCteProvDAO.update(entity);
       
    }
}
