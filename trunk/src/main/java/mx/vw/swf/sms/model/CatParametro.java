package mx.vw.swf.sms.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CatParametro entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CAT_PARAMETRO",
        schema = "dbo",
        catalog = "SMS")
public class CatParametro extends AbstractCatParametro implements java.io.Serializable {

    // Constructors
   /**
    * default constructor
    */
   public CatParametro() {
   }

   /**
    * full constructor
    */
   public CatParametro(CatParametroId id) {
      super(id);
   }

}
