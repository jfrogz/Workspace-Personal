package mx.vw.swf.fwk.model;

import mx.vw.swf.fwk.model.AbstractFwkActionItem;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FwkActionItem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FWK_ACTION_ITEM", schema = "dbo", catalog = "SMS")
public class FwkActionItem extends AbstractFwkActionItem implements
        java.io.Serializable {

    private List<FwkActionItem> children = new ArrayList<FwkActionItem>();
    private FwkActionItem parent;
    // Constructors

    /**
     * default constructor
     */
    public FwkActionItem() {
    }

    /**
     * minimal constructor
     */
    public FwkActionItem(Integer id, Short enabled) {
        super(id, enabled);
    }

    /**
     * full constructor
     */
    public FwkActionItem(Integer id, Short enabled, String iconurl,
                         String label, String url, Integer parentId, Integer orderhint) {
        super(id, enabled, iconurl, label, url, parentId, orderhint);
    }

    @OneToMany(targetEntity = FwkActionItem.class)
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    public List<FwkActionItem> getChildren() {
        return children;
    }

    public void setChildren(List<FwkActionItem> children) {
        this.children = children;
    }

    @ManyToOne(targetEntity = FwkActionItem.class)
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    public FwkActionItem getParent() {
        return parent;
    }

    public void setParent(FwkActionItem parent) {
        this.parent = parent;
    }

    public static FwkActionItem clone(FwkActionItem parent) {
        return new FwkActionItem(parent.getId(), parent.getEnabled(), parent.getIconurl(),
                parent.getLabel(), parent.getUrl(), parent.getParentId(), parent.getOrderhint());
    }
}
