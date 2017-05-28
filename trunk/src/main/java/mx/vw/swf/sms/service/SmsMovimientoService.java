/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.sms.service;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.EntityManager;
import mx.vw.swf.fwk.model.EntityManagerHelper;
import mx.vw.swf.sms.dao.SmsMovimientoDAO;
import mx.vw.swf.sms.model.SmsMovimiento;

/**
 *
 * @author fox1yij
 */
public class SmsMovimientoService {

    private SmsMovimientoDAO smdao = new SmsMovimientoDAO();

    public void save(SmsMovimiento entity) {
        EntityManagerHelper.beginTransaction();
        smdao.save(entity);
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
    }

    public void update(SmsMovimiento entity) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Date date = (Date) em.createNativeQuery("select getDate()").getSingleResult();//            
        entity.setFechaSalida(new Timestamp(date.getTime()));
        
        EntityManagerHelper.beginTransaction();
        smdao.update(entity);
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
    }
}
