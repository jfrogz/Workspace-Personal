package mx.vw.swf.security.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * SegPermiso entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SEG_PERMISO", schema = "dbo", catalog = "SMS")
public class SegPermiso extends AbstractSegPermiso implements
        java.io.Serializable{
    
    private String etiquetaEstatus;
    private Boolean selected;

    

    // Constructors

    /** default constructor */
    public SegPermiso() {
    }

    /** minimal constructor */
    public SegPermiso(Integer id, Short estatus) {
        super(id, estatus);
    }

    /** full constructor */
    public SegPermiso(Integer id, String permiso, Short estatus,
            Set<SegPermisoPerfil> segPermisoPerfils) {
        super(id, permiso, estatus, segPermisoPerfils);
    }
    
    @Transient
    public String getEtiquetaEstatus(){        
        if(this.getEstatus()!=null){
           etiquetaEstatus= this.getEstatus()==1?"Activo":"Inactivo";
        }
          return etiquetaEstatus; 
    }
     @Transient
    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

}
