package mx.vw.swf.security.service;

import mx.vw.swf.security.model.AbstractSegPerfilUsr;

import java.util.Set;

/**
 * Created by dzm152z on 09/03/2015.
 */
public class SecurityAccessUtil {
    /**
     * <p>
     *     Given a item access in the form of a String, walk through all privileges for all profiles for the given user
     *     and find if any matches the given access.
     * </p>
     * @param segPerfilUsrs
     * @param itemAccess
     * @return
     *  True if and only if there is at least one privilege in all profiles for a given user,
     *  which value matches the item's value.
     */
    public static Boolean isItemAccessInRole(Set<? extends AbstractSegPerfilUsr> segPerfilUsrs, String itemAccess) {
        return  1L <=
                segPerfilUsrs.stream()
                        .filter((s) -> {
                            return 1 == s.getSegPerfil().getEstatus().intValue()
                                    && 1L <= s.getSegPerfil().getSegPermisoPerfils().stream()
                                    .filter((p) -> {
                                        return 1 == p.getSegPermiso().getEstatus()
                                                && p.getSegPermiso().getPermiso().equals(itemAccess);
                                    })
                                    .count();
                        }).count();
    }
}
