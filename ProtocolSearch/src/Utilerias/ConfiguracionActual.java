/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilerias;

import java.io.Serializable;
import java.sql.Connection;

/**
 *
 * @author fernandorobles
 */
public class ConfiguracionActual implements Serializable{

    
    private Connection conexion;
    private String pathProtocolos;
    private String pathReportes;
    private String pathAccess;
    private String usario;
    private String password;
    private boolean licenciaActiva;

    
    public String obtenerCaracterSeparador (String rutaAuxiliar)
    {
        
        if(rutaAuxiliar!= null && !rutaAuxiliar.isEmpty())
        {
            if(rutaAuxiliar.startsWith("/"))
                return "/";
            else 
                return "\\";
        }
        return "";
    }
    
    public String getPathAccess() {
        return pathAccess;
    }

    public void setPathAccess(String pathAccess) {
        this.pathAccess = pathAccess;
    }
          
    public String getUsario() {
        return usario;
    }

    public void setUsario(String usario) {
        this.usario = usario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public String getPathProtocolos() {
        return pathProtocolos;
    }

    public void setPathProtocolos(String pathProtocolos) {
        this.pathProtocolos = pathProtocolos;
    }

    public String getPathReportes() {
        return pathReportes;
    }

    public void setPathReportes(String pathReportes) {
        this.pathReportes = pathReportes;
    }

    public boolean isLicenciaActiva() {
        return licenciaActiva;
    }

    public void setLicenciaActiva(boolean licenciaActiva) {
        this.licenciaActiva = licenciaActiva;
    }
    
}
