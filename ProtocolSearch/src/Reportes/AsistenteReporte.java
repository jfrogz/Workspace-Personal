/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import Identities.Expediente;
import javafx.collections.ObservableList;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author fernandorobles
 */
public class AsistenteReporte implements JRDataSource {

    public ObservableList<Expediente> listaDeInstrumentos;
    int contador = -1;
    
    public AsistenteReporte(ObservableList<Expediente> listaDeInstrumentos) {
        this.listaDeInstrumentos = listaDeInstrumentos;
    }

    @Override
    public boolean next() throws JRException {
        return ++contador < listaDeInstrumentos.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        switch (jrf.getName()) {
            case "_instrumento":
                return listaDeInstrumentos.get(contador).getInstrumento();
            case "_volumen":
                return listaDeInstrumentos.get(contador).getVolumen();
            case "_otorgante":
                return listaDeInstrumentos.get(contador).getNombre();
            case "_responsable":
                return listaDeInstrumentos.get(contador).getResponsable().getNombre();
            case "_consecutivo":
                return contador + 1;
            case "_operacion":
                return listaDeInstrumentos.get(contador).getOperacion();

            default:
                return "";
        }
    }

}
