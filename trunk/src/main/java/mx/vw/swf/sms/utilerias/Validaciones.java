/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.sms.utilerias;

import javax.swing.JOptionPane;

/**
 *
 * @author fox1x0d
 */
public class Validaciones {
    
    public boolean validaLongitud(String texto,int max){
        if(texto.length()>max){            
            JOptionPane.showMessageDialog(null,"El campo debe tener sólo 20 caracteres.");
            return false;
        }
        
        return true;
    }
    public boolean validaCampoVacio(String texto, String nombre){
        if(texto.length()==0){  
             JOptionPane.showMessageDialog(null,"Debe proporcionar el  "+nombre+".");
            return false;
        }        
        return true;
    }
    
}
