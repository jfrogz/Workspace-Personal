package mx.vw.swf.sms.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CatPuertoId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class CatPuertoId implements java.io.Serializable {

    // Fields
   private Long id;
   private Short idApp;
   private Short idAdmin;
   private String keyValue;
   private String valor;
   private String descripcion;
   private Short activo;

    // Constructors
   /**
    * default constructor
    */
   public CatPuertoId() {
   }

   /**
    * minimal constructor
    */
   public CatPuertoId(Long id,
           Short idApp,
           Short idAdmin) {
      this.id = id;
      this.idApp = idApp;
      this.idAdmin = idAdmin;
   }

   /**
    * full constructor
    */
   public CatPuertoId(Long id,
           Short idApp,
           Short idAdmin,
           String keyValue,
           String valor,
           String descripcion,
           Short activo) {
      this.id = id;
      this.idApp = idApp;
      this.idAdmin = idAdmin;
      this.keyValue = keyValue;
      this.valor = valor;
      this.descripcion = descripcion;
      this.activo = activo;
   }

    // Property accessors
   @Column(name = "Id",
           nullable = false,
           precision = 18,
           scale = 0)
   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   @Column(name = "idApp",
           nullable = false,
           precision = 4,
           scale = 0)
   public Short getIdApp() {
      return this.idApp;
   }

   public void setIdApp(Short idApp) {
      this.idApp = idApp;
   }

   @Column(name = "idAdmin",
           nullable = false,
           precision = 4,
           scale = 0)
   public Short getIdAdmin() {
      return this.idAdmin;
   }

   public void setIdAdmin(Short idAdmin) {
      this.idAdmin = idAdmin;
   }

   @Column(name = "keyValue",
           length = 25)
   public String getKeyValue() {
      return this.keyValue;
   }

   public void setKeyValue(String keyValue) {
      this.keyValue = keyValue;
   }

   @Column(name = "valor",
           length = 50)
   public String getValor() {
      return this.valor;
   }

   public void setValor(String valor) {
      this.valor = valor;
   }

   @Column(name = "descripcion",
           length = 250)
   public String getDescripcion() {
      return this.descripcion;
   }

   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }

   @Column(name = "activo",
           precision = 3,
           scale = 0)
   public Short getActivo() {
      return this.activo;
   }

   public void setActivo(Short activo) {
      this.activo = activo;
   }

   public boolean equals(Object other) {
      if ((this == other)) {
         return true;
      }
      if ((other == null)) {
         return false;
      }
      if (!(other instanceof CatPuertoId)) {
         return false;
      }
      CatPuertoId castOther = (CatPuertoId) other;

      return ((this.getId() == castOther.getId()) || (this.getId() != null && castOther.getId() != null && this.getId().equals(castOther.getId()))) && ((this.getIdApp() == castOther.getIdApp()) || (this.getIdApp() != null && castOther.getIdApp() != null && this.getIdApp().equals(castOther.getIdApp()))) && ((this.getIdAdmin() == castOther.getIdAdmin()) || (this.getIdAdmin() != null && castOther.getIdAdmin() != null && this.getIdAdmin().equals(castOther.getIdAdmin())))
              && ((this.getKeyValue() == castOther.getKeyValue()) || (this.getKeyValue() != null && castOther.getKeyValue() != null && this.getKeyValue().equals(castOther.getKeyValue()))) && ((this.getValor() == castOther.getValor()) || (this.getValor() != null && castOther.getValor() != null && this.getValor().equals(castOther.getValor())))
              && ((this.getDescripcion() == castOther.getDescripcion()) || (this.getDescripcion() != null && castOther.getDescripcion() != null && this.getDescripcion().equals(castOther.getDescripcion()))) && ((this.getActivo() == castOther.getActivo()) || (this.getActivo() != null && castOther.getActivo() != null && this.getActivo().equals(castOther.getActivo())));
   }

   public int hashCode() {
      int result = 17;

      result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
      result = 37 * result + (getIdApp() == null ? 0 : this.getIdApp().hashCode());
      result = 37 * result + (getIdAdmin() == null ? 0 : this.getIdAdmin().hashCode());
      result = 37 * result + (getKeyValue() == null ? 0 : this.getKeyValue().hashCode());
      result = 37 * result + (getValor() == null ? 0 : this.getValor().hashCode());
      result = 37 * result + (getDescripcion() == null ? 0 : this.getDescripcion().hashCode());
      result = 37 * result + (getActivo() == null ? 0 : this.getActivo().hashCode());
      return result;
   }

}
