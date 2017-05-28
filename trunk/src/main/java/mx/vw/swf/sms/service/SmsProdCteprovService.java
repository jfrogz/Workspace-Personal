/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.sms.service;

import java.util.List;
import java.util.logging.Level;
import mx.vw.swf.fwk.model.EntityManagerHelper;
import mx.vw.swf.sms.dao.SmsProdCteprovDAO;
import mx.vw.swf.sms.model.SmsCteProv;
import mx.vw.swf.sms.model.SmsProdCteprov;

/**
 *
 * @author fox1x0d
 */
public class SmsProdCteprovService {
    SmsProdCteprovDAO smsProdCteprovDAO = new SmsProdCteprovDAO();
    
    public void guardaProductosClienteProveedor(List<SmsProdCteprov> listaProdCteprov,SmsCteProv smsCteProv) {
       
        EntityManagerHelper.log("saving SegPerfilUsr instance", Level.INFO, null);
        try {
            
            smsProdCteprovDAO.deleteQuery(smsCteProv.getId());
            
            for (SmsProdCteprov prodCteprov : listaProdCteprov) {
                smsProdCteprovDAO.save(prodCteprov);
            }
           
        } catch (RuntimeException re) {
            EntityManagerHelper.log("save failed", Level.SEVERE, re);
            throw re;
        } catch (Exception re) {
            EntityManagerHelper.log("Fallo", Level.SEVERE, re);

        }
    } 
    
     public void eliminaProductosClienteProveedor(SmsCteProv smsCteProv) {
        EntityManagerHelper.log("saving SegPerfilUsr instance", Level.INFO, null);
        try {
         smsProdCteprovDAO.deleteQuery(smsCteProv.getId());
      
         } catch (RuntimeException re) {
            EntityManagerHelper.log("save failed", Level.SEVERE, re);
            throw re;
        } catch (Exception re) {
            EntityManagerHelper.log("Fallo", Level.SEVERE, re);

        }
    }
    
}
