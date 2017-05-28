package mx.vw.swf.fwk.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractFwkActionItem entity provides the base persistence definition of the
 * FwkActionItem entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractFwkActionItem implements java.io.Serializable {

    // Fields

    private Integer id;
    private Short enabled;
    private String iconurl;
    private String label;
    private String url;
    private Integer parentId;
    private Integer orderhint;

    // Constructors

    /** default constructor */
    public AbstractFwkActionItem() {
    }

    /** minimal constructor */
    public AbstractFwkActionItem(Integer id, Short enabled) {
        this.id = id;
        this.enabled = enabled;
    }
     
    /** full constructor */
    public AbstractFwkActionItem(Integer id, Short enabled, String iconurl,
            String label, String url, Integer parentId, Integer orderhint) {
        this.id = id;
        this.enabled = enabled;
        this.iconurl = iconurl;
        this.label = label;
        this.url = url;
        this.parentId = parentId;
        this.orderhint = orderhint;
    }

    // Property accessors
    @Id
    @Column(name = "ID", unique = true, nullable = false, precision = 5, scale = 0)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "ENABLED", nullable = false)
    public Short getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Short enabled) {
        this.enabled = enabled;
    }

    @Column(name = "ICONURL")
    public String getIconurl() {
        return this.iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    @Column(name = "LABEL")
    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Column(name = "URL")
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "PARENT_ID", precision = 5, scale = 0)
    public Integer getParentId() {
        return this.parentId;
    }
  
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Column(name = "ORDERHINT", precision = 5, scale = 0)
    public Integer getOrderhint() {
        return this.orderhint;
    }

    public void setOrderhint(Integer orderhint) {
        this.orderhint = orderhint;
    }

    @Override
    public String toString() {
        return "AbstractFwkActionItem{" +
                "id=" + id +
                ", enabled=" + enabled +
                ", iconurl='" + iconurl + '\'' +
                ", label='" + label + '\'' +
                ", url='" + url + '\'' +
                ", parentId=" + parentId +
                ", orderhint=" + orderhint +
                '}';
    }
}