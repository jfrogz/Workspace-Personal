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
import Identities.Expediente;
import Utilerias.Utilerias;

/**
 *
 * @author fernandorobles
 */
public class ExpedienteDao {
    
    public static ObservableList<Expediente> ObtenerlistaDeExpedientes(ObservableList<Responsable> listaRespo,  boolean conLicencia) {
        Statement comando;
        ObservableList<Expediente> ListExpedientes = FXCollections.observableArrayList();
        try {
            
            //comando = ConeccionAccess.conexion.createStatement();
            ConeccionAccess ca = new ConeccionAccess(ProtocolSearch.ActualConfig.getPathAccess(), ProtocolSearch.ActualConfig.getUsario(), ProtocolSearch.ActualConfig.getPassword());
            ProtocolSearch.ActualConfig.setConexion(ca.EstablecerConexion());
            comando = ProtocolSearch.ActualConfig.getConexion().createStatement();
            String consulta = "select instrumento, volumen, nombre, descripcion, Responsables.responsable, Responsables.nombre "
                    + "from expedientes, operaciones, Responsables "
                    + "where expedientes.operacion=operaciones.operacion "
                    + "and expedientes.responsable = responsables.responsable "
                    + "and instrumento!=0 and Expedientes.responsable in (";
            
            boolean bandera = false;
            for (Responsable responsable: listaRespo)
            {
                if(bandera)
                {
                    consulta+= ",";
                }
                consulta+= responsable.getResponsable();
                bandera = true;
            }
            consulta += ") order by volumen, instrumento asc;";

            ResultSet resultado = comando.executeQuery(consulta);

            while (resultado.next()) {
                Expediente auxExp = new Expediente();
                if (conLicencia) {
                    auxExp.setInstrumento(resultado.getInt("instrumento"));
                    if (Utilerias.isAInt(resultado.getString("volumen"))) {
                        auxExp.setVolumen(resultado.getInt("volumen"));
                    }
                    auxExp.setNombre(resultado.getString("nombre"));
                    auxExp.setOperacion(resultado.getString("descripcion"));
                    
                    Responsable auxResp = new Responsable();
                    auxResp.setNombre(resultado.getString("Responsables.nombre"));
                    auxResp.setResponsable(resultado.getInt("Responsables.responsable"));
                    auxExp.setResponsable(auxResp);

                    ListExpedientes.add(auxExp);
                } else {
                    int temp = resultado.getInt("instrumento");
                    if (temp % 2 == 1) {
                        auxExp.setInstrumento(temp);
                        if (Utilerias.isAInt(resultado.getString("volumen"))) {
                            auxExp.setVolumen(resultado.getInt("volumen"));
                        }
                        auxExp.setNombre(resultado.getString("nombre"));
                        auxExp.setOperacion(resultado.getString("descripcion"));
                        Responsable auxResp = new Responsable();
                        auxResp.setNombre(resultado.getString("Responsables.nombre"));
                        auxResp.setResponsable(resultado.getInt("Responsables.responsable"));
                        auxExp.setResponsable(auxResp);
                        ListExpedientes.add(auxExp);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("" +  ex);
        }
        return ListExpedientes;
    }
    
    
}
