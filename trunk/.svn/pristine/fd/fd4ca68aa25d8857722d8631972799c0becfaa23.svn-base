/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.sms.service;

import mx.vw.swf.fwk.model.EntityManagerHelper;
import mx.vw.swf.sms.dao.SmsProductoDAO;
import mx.vw.swf.sms.model.SmsProducto;

/**
 *
 * @author fox1x0d
 */
public class CrearEditarProductoService {
    
    SmsProductoDAO smsProductoDAO = new SmsProductoDAO();
    
     public void guardarEntidad(SmsProducto entity)
    {
        EntityManagerHelper.beginTransaction();
        smsProductoDAO.save(entity);
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
    }
    
    public void actualizaProducto(SmsProducto entity){
        EntityManagerHelper.beginTransaction();
        smsProductoDAO.update(entity);
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
    }
    
}
