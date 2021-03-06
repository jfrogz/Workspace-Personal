/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.fwk;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author dzmppdw
 */
public class FileUtils {
   /**
     * Método encargado de revisar la existencia de una ruta de archivos, en caso de no existir genera los directorios correspondientes
     * 
     * @param cRuta Ruta que desea evaluar su existencia
     */
    public static void verificaDir( String cRuta) {
        // Verifica existencia de directorios y en caso necesario lo crea
        File verificaDir = new File(cRuta);
        if (verificaDir == null || !verificaDir.exists()) {
            String[] aRutas = cRuta.split("/");
            String cRutaIni = aRutas[0];
            for (int y = 1; y < aRutas.length; y++) {
                cRutaIni += "/" + aRutas[y];
                verificaDir = new File(cRutaIni);
                if (verificaDir == null || !verificaDir.exists()) {
                    verificaDir.mkdir();
                }
            }
        }
    }
    
    
    /**
     * Verifica si existe un archivo en un URL
     * @param URLName Ruta y nombre del archivo a localizar
     * @return true en caso de encontrar el recurso, false en caso contrario
     */
    public static boolean existsUrlFile(String URLName){
        try {
          HttpURLConnection.setFollowRedirects(false);
          HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
          con.setRequestMethod("HEAD");
          return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        }
        catch (Exception e) {
           return false;
        }
      }
}
