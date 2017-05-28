/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.fwk;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.vw.swf.security.dao.SegUsuarioDAO;
import mx.vw.swf.security.model.SegUsuario;

/**
 *
 * @author dzmppdw
 */
public class TestJasperUtil {
   
   public static List<SegUsuario> getSegUsuario(){
      SegUsuarioDAO segUsuario = new SegUsuarioDAO();
      List<SegUsuario> listSegUsuario = segUsuario.findAll(0);
      return listSegUsuario;
   }
   
   public static void main(String[] args) {
      JasperUtil reports = new JasperUtil();
      
      String repName = "security.Users";
      Map<String,Object> repParams = new HashMap();
      repParams.put("tituloReporte", "Reporte de Usuarios");
//      repParams.put("imprimePortada", false);
      
      //Corriendo con JasperViewer
      reports.viewJasper(repName, repParams, TestJasperUtil.getSegUsuario());
      
      //Corriendo con destino a un archivo
//      reports.runReportToFile(JasperUtil.TipoReporte.XLS, repName, repParams, "d:/Temp/Users.xls", TestJasperUtil.getSegUsuario());
      
      //Corriendo con destino a un Stream
//      reports.runReport(JasperUtil.TipoReporte.XLS, repName, repParams, outputStream, TestJasperUtil.getSegUsuario());
   }
   
}
