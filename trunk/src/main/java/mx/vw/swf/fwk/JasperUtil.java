package mx.vw.swf.fwk;

/**
 *
 * @author dzmppdw
 */
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.net.URL;

import java.sql.Connection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.swing.JFrame;
import mx.vw.swf.fwk.dao.FwkCatAdminDAO;
import mx.vw.swf.fwk.dao.FwkCatContentDAO;
import mx.vw.swf.fwk.model.FwkCatAdmin;
import mx.vw.swf.fwk.model.FwkCatContent;
import net.sf.jasperreports.engine.JRDataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.view.JasperViewer;

public class JasperUtil {

   public enum TipoReporte {
      PDF, XLS, HTML, CSV, XML, TXT
   }
   private String rutaReportes = "RutaReportes_Propiedad_Sistema.RutaReportes";
   private String rutaImg = "RutaImagenes_reportes_Sistema.RutaImgReportes";
   private String nombreSistema = "Sistema Propiedad Sistema.Nombre";
   private boolean imprimePortada = true;

   public JasperUtil() {
      this.postConstruct();
   }

   @PostConstruct
   public void postConstruct() {
      List<FwkCatAdmin> catAdmins = new FwkCatAdminDAO().findByDescription("PropiedadesSistema");
      List<FwkCatContent> fwkCatContents = new FwkCatContentDAO().findByProperty(FwkCatContentDAO.ID_ADMIN, catAdmins.iterator().next().getIdAdmin());
      String baseUrl = null, helpFileExtension = null, fullHelpFile = null;

      for (FwkCatContent catContent : fwkCatContents) {
         if ("Sistema.Nombre".equalsIgnoreCase(catContent.getValue())) {
            nombreSistema = catContent.getDescription();
         }
         if ("Sistema.RutaReportes".equalsIgnoreCase(catContent.getValue())) {
            rutaReportes = catContent.getDescription();
         }
         if ("Sistema.RutaImgReportes".equalsIgnoreCase(catContent.getValue())) {
            rutaImg = catContent.getDescription();
         }
      }
   }

   private Map<String, Object> getViewerParameters(String cReporte, Map params) {
      Map operationParamsMap = new HashMap();
      operationParamsMap.putAll(params);
      operationParamsMap.put("cReporte", cReporte);
      operationParamsMap.put("params", operationParamsMap);
      if (!params.containsKey("soloDatos")) {
         params.put("soloDatos", false);
      }
      return operationParamsMap;
   }

   private void viewJasper(JasperPrint theReport, String viewerTitle) {
      JasperViewer jv = new JasperViewer(theReport, false);
      jv.setFitPageZoomRatio();
      jv.setTitle(viewerTitle);
      jv.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      jv.setVisible(true);
   }

   public void viewJasper(String cReporte, Map params, Connection conn) {
      this.viewJasper(generaJasper(cReporte, this.getViewerParameters(cReporte, params), conn), params.containsKey("tituloReporte") ? params.get("tituloReporte").toString() : cReporte);
   }

   public void viewJasper(String cReporte, Map params, JRDataSource data) {
      this.viewJasper(generaJasper(cReporte, this.getViewerParameters(cReporte, params), data), params.containsKey("tituloReporte") ? params.get("tituloReporte").toString() : cReporte);
   }

   public void viewJasper(String cReporte, Map params, List<?> data) {
      JRDataSource beanCollectionDS = new JRBeanCollectionDataSource(data);
      this.viewJasper(generaJasper(cReporte, this.getViewerParameters(cReporte, params), beanCollectionDS), params.containsKey("tituloReporte") ? params.get("tituloReporte").toString() : cReporte);
   }

   private void runReportToFile(TipoReporte iTipo, String cNomReporte, Map paramRep, String fileName, Object connOrData) {
      OutputStream outputStream;
      try {
         outputStream = new FileOutputStream(fileName, false);
         this.ejecutaReporte(iTipo, cNomReporte, paramRep, outputStream, connOrData);
         outputStream.flush();
         outputStream.close();
      } catch (FileNotFoundException e) {
         Utilerias.consoleMsg("runReportToFile", e, this);
      } catch (IOException e) {
         Utilerias.consoleMsg("runReportToFile", e, this);
      }
   }

   public void runReportToFile(TipoReporte iTipo, String cNomReporte, Map paramRep, String fileName, Connection conn) {
      this.runReportToFile(iTipo, cNomReporte, paramRep, fileName, (Object) conn);
   }

   public void runReportToFile(TipoReporte iTipo, String cNomReporte, Map paramRep, String fileName, JRDataSource data) {
      this.runReportToFile(iTipo, cNomReporte, paramRep, fileName, (Object) data);
   }

   public void runReportToFile(TipoReporte iTipo, String cNomReporte, Map paramRep, String fileName, List<?> data) {
      JRBeanCollectionDataSource beanCollectionDS = new JRBeanCollectionDataSource(data);
      this.runReportToFile(iTipo, cNomReporte, paramRep, fileName, (Object) beanCollectionDS);
   }

   public void runReportToStream(TipoReporte iTipo, String cNomReporte, Map paramRep, OutputStream outputStream, Connection conn) {
      this.ejecutaReporte(iTipo, cNomReporte, paramRep, outputStream, conn);
   }

   public void runReportToStream(TipoReporte iTipo, String cNomReporte, Map paramRep, OutputStream outputStream, JRDataSource data) {
      this.ejecutaReporte(iTipo, cNomReporte, paramRep, outputStream, data);
   }

   public void runReportToStream(TipoReporte iTipo, String cNomReporte, Map paramRep, OutputStream outputStream, List<?> data) {
      JRBeanCollectionDataSource beanCollectionDS = new JRBeanCollectionDataSource(data);
      this.ejecutaReporte(iTipo, cNomReporte, paramRep, outputStream, beanCollectionDS);
   }

   private void printReport(TipoReporte iTipo, String cNomReporte, Map paramRep, Object connOrData, boolean showPageDialog, boolean showPrintDialog) {
//      PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
//      printRequestAttributeSet.add(MediaSizeName.ISO_A4);

//      PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
//      printServiceAttributeSet.add(new PrinterName("Epson Stylus 820 ESC/P 2", null));
//      printServiceAttributeSet.add(new PrinterName("hp LaserJet 1320 PCL 6", null));
//      printServiceAttributeSet.add(new PrinterName("PDFCreator", null));
      JRPrintServiceExporter exporter = new JRPrintServiceExporter();

//      exporter.setExporterInput(new SimpleExporterInput("build/reports/PrintServiceReport.jrprint"));
      SimplePrintServiceExporterConfiguration configuration = new SimplePrintServiceExporterConfiguration();
//      configuration.setPrintRequestAttributeSet(printRequestAttributeSet);
//      configuration.setPrintServiceAttributeSet(printServiceAttributeSet);
      configuration.setDisplayPageDialog(showPageDialog);
      configuration.setDisplayPrintDialog(showPrintDialog);
      exporter.setConfiguration(configuration);
      exporter.setExporterInput(new SimpleExporterInput(generaJasper(cNomReporte, this.getViewerParameters(cNomReporte, paramRep), connOrData)));
      try {
         exporter.exportReport();
      } catch (JRException ex) {
         Logger.getLogger(JasperUtil.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public void printReport(TipoReporte iTipo, String cNomReporte, Map paramRep, Connection conn, boolean showPageDialog, boolean showPrintDialog) {
      this.printReport(iTipo, cNomReporte, paramRep, (Object) conn, showPageDialog, showPrintDialog);
   }

   public void printReport(TipoReporte iTipo, String cNomReporte, Map paramRep, Connection conn) {
      this.printReport(iTipo, cNomReporte, paramRep, conn, false, false);
   }

   public void printReport(TipoReporte iTipo, String cNomReporte, Map paramRep, JRDataSource data, boolean showPageDialog, boolean showPrintDialog) {
      this.printReport(iTipo, cNomReporte, paramRep, (Object) data, showPageDialog, showPrintDialog);
   }

   public void printReport(TipoReporte iTipo, String cNomReporte, Map paramRep, JRDataSource data) {
      this.printReport(iTipo, cNomReporte, paramRep, data, false, false);
   }

   public void printReport(TipoReporte iTipo, String cNomReporte, Map paramRep, List<?> data, boolean showPageDialog, boolean showPrintDialog) {
      JRBeanCollectionDataSource beanCollectionDS = new JRBeanCollectionDataSource(data);
      this.printReport(iTipo, cNomReporte, paramRep, (Object) beanCollectionDS, showPageDialog, showPrintDialog);
   }

   public void printReport(TipoReporte iTipo, String cNomReporte, Map paramRep, List<?> data) {
      this.printReport(iTipo, cNomReporte, paramRep, data, false, false);
   }

   private void ejecutaReporte(TipoReporte iTipo, String cNomReporte, Map params, OutputStream outputStream, Object connOrData/*, ServletResponse response*/) {
      Map operationParamsMap = new HashMap();
      operationParamsMap.putAll(params);
      operationParamsMap.put("cReporte", cNomReporte);
      operationParamsMap.put("params", operationParamsMap);
      JasperPrint reportPrint;
      ByteArrayOutputStream theReport = null;

      switch (iTipo) {
         case XLS:
         case CSV:
         case XML:
            params.put("soloDatos", true);
            break;
         default:
            params.put("soloDatos", false);
            break;
      }

      reportPrint = this.generaJasper(cNomReporte, operationParamsMap, connOrData);

      try {
         switch (iTipo) {
            case PDF:
               theReport = JasperUtil.exportaPDF(reportPrint);
//               response.setContentType("application/pdf");
//               response.setHeader("Content-disposition", "attachment; filename=" + cNomReporte + ".pdf");
//               response.setHeader("Content-disposition", "inline; filename=" + cNomReporte + ".pdf");
               break;
            case XLS:
               theReport = JasperUtil.exportaXLS(reportPrint);
//               response.setContentType("application/x-excel");
//               response.setHeader("Content-disposition", "attachment; filename=" + cNomReporte + ".pdf");
//               response.setHeader("Content-disposition", "inline; filename=" + cNomReporte + ".pdf");
               break;
            case HTML:
               theReport = JasperUtil.exportaHTML(reportPrint);
//               response.setContentType("text/html");
//               response.setHeader("Content-disposition", "attachment; filename=" + cNomReporte + ".pdf");
//               response.setHeader("Content-disposition", "inline; filename=" + cNomReporte + ".pdf");
               break;
            case CSV:
               theReport = JasperUtil.exportaCSV(reportPrint);
//               response.setContentType("application/x-excel");
//               response.setHeader("Content-disposition", "attachment; filename=" + cNomReporte + ".pdf");
//               response.setHeader("Content-disposition", "inline; filename=" + cNomReporte + ".pdf");
               break;
            case XML:
               theReport = JasperUtil.exportaXML(reportPrint);
//               response.setContentType("application/xml");
//               response.setHeader("Content-disposition", "attachment; filename=" + cNomReporte + ".pdf");
//               response.setHeader("Content-disposition", "inline; filename=" + cNomReporte + ".pdf");
               break;
            case TXT:
               theReport = JasperUtil.exportaTXT(reportPrint);
//               response.setContentType("text/plain");
//               response.setHeader("Content-disposition", "attachment; filename=" + cNomReporte + ".pdf");
//               response.setHeader("Content-disposition", "inline; filename=" + cNomReporte + ".pdf");
               break;
            default: //PDF
               theReport = JasperUtil.exportaPDF(reportPrint);
//               response.setContentType("application/pdf");
//               response.setHeader("Content-disposition", "attachment; filename=" + cNomReporte + ".pdf");
//               response.setHeader("Content-disposition", "inline; filename=" + cNomReporte + ".pdf");
               break;
         }

         if (theReport == null) {
            Utilerias.consoleMsg("theReport Nulo", null, this);
         } else if (outputStream == null) {
            Utilerias.consoleMsg("outputStream Nulo", null, this);
         } else {
            try {
               theReport.writeTo(outputStream);
               outputStream.flush();
            } catch (IOException e) {
               Utilerias.consoleMsg("otuputStreamError", e, this);
            }
         }
      } catch (Exception e) {
         Utilerias.consoleMsg("Error al ejecutar el reporte: " + cNomReporte, e, null);
      }
   }

   private static ByteArrayOutputStream exportaPDF(JasperPrint documento) {
      if (documento != null) {
         JRPdfExporter exporter = new JRPdfExporter();
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         exporter.setExporterInput(new SimpleExporterInput(documento));
         exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
         try {
            exporter.exportReport();
         } catch (JRException e) {
            Utilerias.consoleMsg("exportaPDF", e, null);
         }
         return baos;
      } else {
         return null;
      }
   }

   private static ByteArrayOutputStream exportaXLS(JasperPrint documento) {
      if (documento != null) {
         JRXlsExporter exporter = new JRXlsExporter();
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         exporter.setExporterInput(new SimpleExporterInput(documento));
         exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
         try {
            exporter.exportReport();
         } catch (JRException e) {
            Utilerias.consoleMsg("exportaXLS", e, null);
         }
         return baos;
      } else {
         return null;
      }
   }

   private static ByteArrayOutputStream exportaTXT(JasperPrint documento) {
      if (documento != null) {
         JRTextExporter exporter = new JRTextExporter();
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         exporter.setExporterInput(new SimpleExporterInput(documento));
         exporter.setExporterOutput(new SimpleWriterExporterOutput(baos));
         try {
            exporter.exportReport();
         } catch (JRException e) {
            Utilerias.consoleMsg("exportaTXT", e, null);
         }
         return baos;
      } else {
         return null;
      }
   }

   private static ByteArrayOutputStream exportaHTML(JasperPrint documento) {
      if (documento != null) {
         HtmlExporter exporter = new HtmlExporter();
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         exporter.setExporterInput(new SimpleExporterInput(documento));
//         exporter.setExporterOutput();
         exporter.setParameter(JRHtmlExporterParameter.OUTPUT_STREAM, baos);
         try {
            exporter.exportReport();
         } catch (JRException e) {
            Utilerias.consoleMsg("exportaHTML", e, null);
         }
         return baos;
      } else {
         return null;
      }
   }

   private static ByteArrayOutputStream exportaCSV(JasperPrint documento) {
      if (documento != null) {
         JRCsvExporter exporter = new JRCsvExporter();
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         exporter.setExporterInput(new SimpleExporterInput(documento));
         exporter.setExporterOutput(new SimpleWriterExporterOutput(baos));
         try {
            exporter.exportReport();
         } catch (JRException e) {
            Utilerias.consoleMsg("exportaCSV", e, null);
         }
         return baos;
      } else {
         return null;
      }
   }

   private static ByteArrayOutputStream exportaXML(JasperPrint documento) {
      if (documento != null) {
         JRXmlExporter exporter = new JRXmlExporter();
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         exporter.setExporterInput(new SimpleExporterInput(documento));
         exporter.setExporterOutput(new SimpleWriterExporterOutput(baos));
         try {
            exporter.exportReport();
         } catch (JRException e) {
            Utilerias.consoleMsg("exportaXML", e, null);
         }
         return baos;
      } else {
         return null;
      }
   }

   private JasperPrint generaJasper(String cReporte, Map params, Object connOrData) {
      JasperPrint print = null;
      try {
         String report = this.getRutaReportes() + cReporte;
         params.put("SUBREPORT_DIR", this.getRutaReportes());
         if (this.getRutaImg().startsWith("/")) {
            params.put("IMAGE_DIR", getClass().getResource(this.getRutaImg() + "logoEmpresa.png").getPath().replaceAll("logoEmpresa.png", ""));
         } else {
            params.put("IMAGE_DIR", this.getRutaImg());
         }
         params.put("FECHA_EJECUTA", new java.util.Date());
         //Parametros que deben existir desde el llamado:
         params.put("nombreReporte", cReporte);
         params.put("formatoISO", "");
         params.put("nombreSistema", this.getNombreSistema());
         if (!params.containsKey("imprimePortada")) {
            params.put("imprimePortada", this.getImprimePortada());
         }
         Map otherParams = new HashMap();

         Utilerias.consoleMsg("Buscando método de parámetros: param" + cReporte.replace('.', '_'), null, this);
         Class[] args1 = new Class[1];
         args1[0] = Map.class;
         JasperParameters jasperParams = new JasperParameters();
         Utilerias.consoleMsg("Class: " + jasperParams.getClass().toString(), null, this);
         Method theMethod = null;
         try {
            theMethod = jasperParams.getClass().getMethod("param" + cReporte.replace('.', '_'), args1);
         } catch (NoSuchMethodException e) {
            Utilerias.consoleMsg("El metodo: " + "param" + cReporte.replace('.', '_') + " no pudo ser encontrado", null, this);
         } catch (SecurityException e) {
            Utilerias.consoleMsg("generaJasper", e, this);
         }
         if (null != theMethod) {
            Utilerias.consoleMsg("Se encontró método de parámetros de reporte", null, this);
            try {
               otherParams = (Map) theMethod.invoke(this, params);
            } catch (IllegalAccessException ex) {
               Utilerias.consoleMsg("No se puede tener acceso a la clase desconocida", ex, this);
            } catch (IllegalArgumentException ex) {
               Utilerias.consoleMsg("Los argumentos para invocar al metodo no son correctos", ex, this);
            } catch (InvocationTargetException ex) {
               Utilerias.consoleMsg("El metodo invocado lanzo una excepcion", ex, this);
            } catch (SecurityException ex) {
               Utilerias.consoleMsg("Excepción desconocida", ex, this);
            }
         } else {
            Utilerias.consoleMsg("No hay método de parámetros de reporte... continúa ejecución", null, this);
         }
         if (null != otherParams) {
            Utilerias.consoleMsg("Parametros Jasper: " + new HashMap(params).toString(), null, this);
            Utilerias.consoleMsg("Otros Parametros Jasper: " + otherParams.toString(), null, this);
            params.putAll(otherParams);

            try {
               InputStream isFile;
               if (report.toLowerCase().startsWith("http")) {
                  isFile = new URL(report.replace('.', '/') + ".jasper").openStream();
               } else if (report.toLowerCase().startsWith("file:")) {
                  isFile = new FileInputStream(new File(report.replace('.', '/') + ".jasper"));
               } else {
                  isFile = getClass().getResourceAsStream(report.replace('.', '/') + ".jasper");
               }

               if (null != connOrData) {
                  if (connOrData instanceof Connection) {
                     Connection conn = (Connection) connOrData;
                     if (!conn.isClosed()) {
                        print = JasperFillManager.fillReport(isFile, params, conn);
                     }
                  } else if (connOrData instanceof JRBeanCollectionDataSource) {
                     JRBeanCollectionDataSource beanData = (JRBeanCollectionDataSource) connOrData;
                     print = JasperFillManager.fillReport(isFile, params, beanData);
                  } else if (connOrData instanceof JRDataSource) {
                     JRDataSource data = (JRDataSource) connOrData;
                     print = JasperFillManager.fillReport(isFile, params, data);
                  }
               }
            } catch (IOException | JRException e) {
               Utilerias.consoleMsg("generaJasper", e, this);
            }
         }
      } catch (Exception e) {
         Utilerias.consoleMsg("generaJasper", e, this);
      }
      return print;
   }

   public void setRutaReportes(String rutaReportes) {
      this.rutaReportes = rutaReportes;
   }

   public String getRutaReportes() {
      return rutaReportes;
   }

   public void setRutaImg(String rutaImg) {
      this.rutaImg = rutaImg;
   }

   public String getRutaImg() {
      return rutaImg;
   }

   public void setNombreSistema(String nombreSistema) {
      this.nombreSistema = nombreSistema;
   }

   public String getNombreSistema() {
      return nombreSistema;
   }

   public void setImprimePortada(Boolean imprimePortada) {
      this.imprimePortada = imprimePortada;
   }

   public Boolean getImprimePortada() {
      return imprimePortada;
   }

}
