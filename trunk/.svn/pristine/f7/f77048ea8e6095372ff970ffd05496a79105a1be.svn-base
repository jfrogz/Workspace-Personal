package mx.vw.swf.sms.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

/**
 * AbstractCatParametro entity provides the base persistence definition of the CatParametro entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractCatParametro implements java.io.Serializable {

    // Fields
   private CatParametroId id;

    // Constructors
   /**
    * default constructor
    */
   public AbstractCatParametro() {
   }

   /**
    * full constructor
    */
   public AbstractCatParametro(CatParametroId id) {
      this.id = id;
   }

   // Property accessors
   @EmbeddedId
   @AttributeOverrides({
      @AttributeOverride(name = "id",
              column = @Column(name = "Id",
                      nullable = false,
                      precision = 18,
                      scale = 0)),
      @AttributeOverride(name = "idApp",
              column = @Column(name = "idApp",
                      nullable = false,
                      precision = 4,
                      scale = 0)),
      @AttributeOverride(name = "idAdmin",
              column = @Column(name = "idAdmin",
                      nullable = false,
                      precision = 4,
                      scale = 0)),
      @AttributeOverride(name = "keyValue",
              column = @Column(name = "keyValue",
                      length = 25)),
      @AttributeOverride(name = "valor",
              column = @Column(name = "valor",
                      length = 50)),
      @AttributeOverride(name = "descripcion",
              column = @Column(name = "descripcion",
                      length = 250)),
      @AttributeOverride(name = "activo",
              column = @Column(name = "activo",
                      precision = 3,
                      scale = 0))})
   public CatParametroId getId() {
      return this.id;
   }

   public void setId(CatParametroId id) {
      this.id = id;
   }

}
