/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.fwk;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author dzmppdw
 */
public class SerialPortReader implements SerialPortEventListener {

   private final SerialPort portIn;
   private StringBuilder fullData = new StringBuilder();

   public SerialPortReader(SerialPort portIn) {
      this.portIn = portIn;
   }

   public StringBuilder getFullData() {
      return fullData;
   }

   public void setFullData(StringBuilder fullData) {
      this.fullData = fullData;
   }

   @Override
   public void serialEvent(SerialPortEvent event) {
      String dataIn;
      if (event.isRXCHAR() && event.getEventValue() > 0) {
         if(portIn.isOpened()){
            try {
               dataIn = portIn.readString(event.getEventValue());
               Utilerias.consoleMsg(String.format("\n[reading data] : %S", dataIn), null, this);
               fullData.append(dataIn);
            } catch (SerialPortException e) {
               Utilerias.consoleMsg(String.format("Error al leer del puerto serial %S. Excepcion %S " + portIn.getPortName(), e.getMessage()), e, this);
            }
         }else{
            Utilerias.consoleMsg(String.format("\nPort %S is not opened.", portIn.getPortName()), null, this);
         }
      }
   }
}
