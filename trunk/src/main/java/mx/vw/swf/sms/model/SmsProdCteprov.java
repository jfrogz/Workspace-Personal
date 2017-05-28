package mx.vw.swf.sms.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SmsProdCteprov entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SMS_PROD_CTEPROV", schema = "dbo", catalog = "SMS")
public class SmsProdCteprov extends AbstractSmsProdCteprov implements
        java.io.Serializable {

    // Constructors

    /** default constructor */
    public SmsProdCteprov() {
    }

    /** full constructor */
    public SmsProdCteprov(SmsProdCteprovId id, SmsCteProv smsCteProv,
            SmsProducto smsProducto) {
        super(id, smsCteProv, smsProducto);
    }

}
