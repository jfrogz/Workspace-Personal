package mx.vw.swf.security.service;

import mx.com.vw.swf.exception.SWFLoginException;
import mx.vw.fwk.security.ldap.AutenticacionActiveDirectory;
import mx.vw.swf.cdi.DataFXLauncher;
import mx.vw.swf.security.dao.SegUsuarioDAO;
import mx.vw.swf.security.model.SegUsuario;

import javax.naming.NamingException;
import java.util.List;

/**
 * Created by dzm152z on 16/02/2015.
 */
public class LoginService extends SWFStandaloneService {

    String ldapdomain = "NA.VWG";

    SegUsuarioDAO segUsuarioDAO = new SegUsuarioDAO();

    /**
     * <p>
     * Will attempt to perform an LDAP login
     * </p>
     *
     * @param username
     * @param password
     */
    public SegUsuario tryLogin(String username, String password) throws NamingException, SWFLoginException {
        SegUsuario segUsuario = null;
        AutenticacionActiveDirectory aad = new AutenticacionActiveDirectory(ldapdomain);
        try {
            if (aad.autenticar(username, password)) {
                // Retrieve from database
                List<SegUsuario> segUsuarios = segUsuarioDAO.findByUserId(username);
                if (null == segUsuarios || segUsuarios.isEmpty()) {
                    throw new SWFLoginException(DataFXLauncher.getSecurityProperty("usuarioInexistente"));
                }
                if (1 < segUsuarios.stream()
                        .filter((user) -> {
                            return 1 == user.getEstatus().intValue();
                        }).count()) {
                    throw new SWFLoginException(DataFXLauncher.getSecurityProperty("multiplesUsuarios"));
                }
                segUsuarios.stream().filter((user) -> {
                    return 1 == user.getEstatus().intValue();
                });
                segUsuario = segUsuarios.iterator().next();
            }
        } catch (NamingException ne) {
            throw new SWFLoginException(DataFXLauncher.getSecurityProperty("errorAuthLDAP"));
        }
        return segUsuario;
    }
}
