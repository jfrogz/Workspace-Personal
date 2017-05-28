/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author fernandorobles
 */
public class ConeccionAccess {

    private final String nombre_bd;
    private final String usuarioBD;
    private final String passwordBD;
 
    public ConeccionAccess(String nombre_bd, String usuarioBD, String passwordBD){
        this.nombre_bd=nombre_bd;
        this.usuarioBD=usuarioBD;
        this.passwordBD=passwordBD;
    }
 
    public Connection EstablecerConexion() {
        try{
            Connection conexion = DriverManager.getConnection("jdbc:ucanaccess://"+this.nombre_bd,this.usuarioBD,this.passwordBD);
            return conexion;
        }catch (SQLException e){ 
            System.out.println("Error al realizar la conexion " + e);
             return null;
        } 
    }    
    
    public static boolean ProbarConexion(String path, String user, String password) {
        try{
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+path,user,password);
            boolean isConnected = !conn.isClosed();
            conn.close();
            return isConnected;
        }catch (SQLException e){ 
            System.out.println("Error al realizar la conexion " + e);
             return false;
        } 
    }
 
    
    
}
