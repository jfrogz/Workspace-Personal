/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Identities.Responsable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import protocolsearch.ProtocolSearch;

/**
 *
 * @author fernandorobles
 */
public class ResponsablesDao {

    public static ObservableList<Responsable> ObtenerlistaDeResponsables() {
        Statement comando;
        ObservableList<Responsable> ListResp = FXCollections.observableArrayList();
        try {
            
            //comando = ConeccionAccess.conexion.createStatement();
            ConeccionAccess ca = new ConeccionAccess(ProtocolSearch.ActualConfig.getPathAccess(), ProtocolSearch.ActualConfig.getUsario(), ProtocolSearch.ActualConfig.getPassword());
            ProtocolSearch.ActualConfig.setConexion(ca.EstablecerConexion());
            comando = ProtocolSearch.ActualConfig.getConexion().createStatement();
            String consulta = "Select Responsable, nombre from responsables order by nombre;";

            ResultSet resultado = comando.executeQuery(consulta);

            while (resultado.next()) {
                Responsable aux = new Responsable();

                aux.setResponsable(resultado.getInt("responsable"));
                aux.setNombre(resultado.getString("nombre"));
                ListResp.add(aux);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResponsablesDao.class.getName()).log(Level.SEVERE, null, ex);

        }
        return ListResp;
    }
}
