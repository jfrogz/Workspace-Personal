package mx.vw.swf.security.service;

import mx.vw.swf.fwk.dao.FwkActionItemDAO;
import mx.vw.swf.fwk.model.FwkActionItem;
import mx.vw.swf.security.model.SegPerfilUsr;
import mx.vw.swf.security.model.SegUsuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import mx.vw.swf.fwk.Utilerias;

/**
 * Created by dzm152z on 06/03/2015.
 */
public class MenuService {
    /**
     * <p>
     *     Given a user construct a collection of FwkActionItem for which the user has access.
     * </p>
     * @param segUsuario
     * @return
     */
    public List<FwkActionItem> getItemsForUser(final SegUsuario segUsuario) {
        List<FwkActionItem> visible = new ArrayList<>();
        if (null == segUsuario || null == segUsuario.getId() || 1 != segUsuario.getEstatus().intValue()
                || null == segUsuario.getSegPerfilUsrs() || segUsuario.getSegPerfilUsrs().isEmpty()) {
            return visible;
        }
        List<FwkActionItem> allItems = new FwkActionItemDAO().findAllForRendering();
        allItems = preserveValidItemsOnly(allItems, segUsuario.getSegPerfilUsrs(), new ArrayList<>());
        return allItems;
    }

    /**
     * <p>
     *     Given a collection of profiles associated to a user, generate a collection of FwkAction items for which
     *     the user has access taking into account the privileges of it's assigned profiles.
     * </p>
     * @param allItems
     * @param segPerfilUsrs
     * @param finalItems
     * @return
     */
    private List<FwkActionItem> preserveValidItemsOnly(List<FwkActionItem> allItems, Set<SegPerfilUsr> segPerfilUsrs, List<FwkActionItem> finalItems) {
        for (FwkActionItem parent : allItems) {
            if (1 == parent.getEnabled().intValue() && SecurityAccessUtil.isItemAccessInRole(segPerfilUsrs, parent.getUrl())) {
                FwkActionItem parentClone = FwkActionItem.clone(parent);
                finalItems.add(parentClone);
                Utilerias.consoleMsg("Added item " + parentClone.getUrl(), null, this);
                for (FwkActionItem child : parent.getChildren()) {
                    if (1 == parent.getEnabled().intValue() && SecurityAccessUtil.isItemAccessInRole(segPerfilUsrs, child.getUrl())) {
                        parentClone.getChildren().add(child);
                    }
                    preserveValidItemsOnly(child.getChildren(), segPerfilUsrs, finalItems);
                }
            }
        }
        return finalItems;
    }


}
