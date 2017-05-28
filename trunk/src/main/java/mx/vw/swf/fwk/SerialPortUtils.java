/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.fwk;

import java.util.Arrays;
import java.util.List;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 * @author dzmppdw
 */
public class SerialPortUtils {

   public static List<String> getSerialPortsList() {
      String[] ports = SerialPortList.getPortNames();
      return Arrays.asList(ports);
   }

   public boolean closeSerialPort(SerialPort portToClose) {
      boolean retVal = true;
      if (portToClose.isOpened()) {
         try {
            portToClose.removeEventListener();
            retVal = portToClose.closePort();
         } catch (SerialPortException e) {
            retVal = false;
         }
      }
      return retVal;
   }

   public static SerialPort openSerialPort(String portName, int baudRate, int dataBits, int stopBits, String parityType, boolean isInputPort) {
      SerialPort port = new SerialPort(portName);
      int baud;
      int data;
      int stop;
      int parity;

      switch (baudRate) {
         case SerialPort.BAUDRATE_110:
            baud = SerialPort.BAUDRATE_110;
            break;
         case SerialPort.BAUDRATE_300:
            baud = SerialPort.BAUDRATE_300;
            break;
         case SerialPort.BAUDRATE_600:
            baud = SerialPort.BAUDRATE_600;
            break;
         case SerialPort.BAUDRATE_1200:
            baud = SerialPort.BAUDRATE_1200;
            break;
         case SerialPort.BAUDRATE_4800:
            baud = SerialPort.BAUDRATE_4800;
            break;
         case SerialPort.BAUDRATE_9600:
            baud = SerialPort.BAUDRATE_9600;
            break;
         case SerialPort.BAUDRATE_14400:
            baud = SerialPort.BAUDRATE_14400;
            break;
         case SerialPort.BAUDRATE_19200:
            baud = SerialPort.BAUDRATE_19200;
            break;
         case SerialPort.BAUDRATE_38400:
            baud = SerialPort.BAUDRATE_38400;
            break;
         case SerialPort.BAUDRATE_57600:
            baud = SerialPort.BAUDRATE_57600;
            break;
         case SerialPort.BAUDRATE_115200:
            baud = SerialPort.BAUDRATE_115200;
            break;
         case SerialPort.BAUDRATE_128000:
            baud = SerialPort.BAUDRATE_128000;
            break;
         case SerialPort.BAUDRATE_256000:
            baud = SerialPort.BAUDRATE_256000;
            break;
         default:
            baud = SerialPort.BAUDRATE_9600;
            break;
      }

      switch (dataBits) {
         case SerialPort.DATABITS_5:
            data = SerialPort.DATABITS_5;
            break;
         case SerialPort.DATABITS_6:
            data = SerialPort.DATABITS_6;
            break;
         case SerialPort.DATABITS_7:
            data = SerialPort.DATABITS_7;
            break;
         case SerialPort.DATABITS_8:
            data = SerialPort.DATABITS_8;
            break;
         default:
            data = SerialPort.DATABITS_7;
            break;
      }

      switch (stopBits) {
         case SerialPort.STOPBITS_1:
            stop = SerialPort.STOPBITS_1;
            break;
         case SerialPort.STOPBITS_2:
            stop = SerialPort.STOPBITS_2;
            break;
         case SerialPort.STOPBITS_1_5:
            stop = SerialPort.STOPBITS_1_5;
            break;
         default:
            stop = SerialPort.STOPBITS_1;
            break;
      }

      switch (parityType.toUpperCase()) {
         case "NONE":
            parity = SerialPort.PARITY_NONE;
            break;
         case "EVEN":
            parity = SerialPort.PARITY_EVEN;
            break;
         case "ODD":
            parity = SerialPort.PARITY_ODD;
            break;
         case "MARK":
            parity = SerialPort.PARITY_MARK;
            break;
         case "SPACE":
            parity = SerialPort.PARITY_SPACE;
            break;
         default:
            parity = SerialPort.PARITY_NONE;
            break;
      }

      try {
         port.openPort();
         port.setFlowControlMode(isInputPort ? SerialPort.FLOWCONTROL_RTSCTS_IN : SerialPort.FLOWCONTROL_RTSCTS_OUT);
         port.setParams(baud, data, stop, parity);
      } catch (SerialPortException e) {
      }
      return port;
   }

}
