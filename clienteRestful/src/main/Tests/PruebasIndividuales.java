import com.jfrogz.cliente.app.ClienteServer;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.fail;

/**
 * Created by Administrador on 30/05/2017.
 */

public class PruebasIndividuales {

    public String host = "http://localhost:8080";
    //public String host = "http://189.164.171.151:8080";
    public ClienteServer clienteServer = new ClienteServer();
    public final String valEsperado = "valEsperado";


    @Test
    public void val_usuario_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            final String parametro1 = "nombre_usuario";
            final String parametro2 = "password";
            final String nomPrueba = "val_usuario";
            //<editor-fold desc="Ejecucion1">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "fernando Robles");
            ejecucion1.put(parametro2, "1234589");
            ejecucion1.put(valEsperado, "0");
            listParametros.add(ejecucion1);
            //</editor-fold>
            //<editor-fold desc="Ejecucion2">
            Map<String, String> ejecucion2 = new HashMap<String, String>();
            ejecucion2.put(parametro1, "null");
            ejecucion2.put(parametro2, "null");
            ejecucion2.put(valEsperado, "0");
            listParametros.add(ejecucion2);
            //</editor-fold>
            //<editor-fold desc="Ejecucion3">
            Map<String, String> ejecucion3 = new HashMap<String, String>();
            ejecucion3.put(parametro1, "test");
            ejecucion3.put(parametro2, "null");
            ejecucion3.put(valEsperado, "0");
            listParametros.add(ejecucion3);
            //</editor-fold>
            //<editor-fold desc="Ejecucion4">
            Map<String, String> ejecucion4 = new HashMap<String, String>();
            ejecucion4.put(parametro1, "null");
            ejecucion4.put(parametro2, "13456789");
            ejecucion4.put(valEsperado, "0");
            listParametros.add(ejecucion4);
            //</editor-fold>

            for (Map<String, String> temp : listParametros){
                String datos64 = clienteServer.obtenerBase64("{\"" + parametro1 + "\": \"" + temp.get(parametro1) +"\", \""
                        + parametro2 + "\":\"" + temp.get(parametro2) +"\"}");
                String url = host + "/validacionUnitaria/"+ nomPrueba + "/datos/" + datos64;
                System.out.println(url);
                String mensaje =clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"\n");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_rfc_receptor_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            final String parametro1 = "rfc_receptor";
            final String nomPrueba = "val_rfc_receptor";

            //<editor-fold desc="Ejecucion1">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "null");
            ejecucion1.put(valEsperado, "1");
            listParametros.add(ejecucion1);
            //</editor-fold>

            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" + parametro1 + "\": \"" + temp.get(parametro1) + "\"" + "}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/"+ nomPrueba + "/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"\n");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_rfc_lco_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            final String parametro1 = "rfc_emisor";
            final String nomPrueba = "val_rfc_receptor";
            final String valEsperado = "valEsperado";

            //<editor-fold desc="Ejecucion1">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "null");
            ejecucion1.put(valEsperado, "0");
            listParametros.add(ejecucion1);
            //</editor-fold>

            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" + parametro1 + "\": \"" + temp.get(parametro1) + "\"" + "}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/val_rfc_lco/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"\n");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_aduana_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            final String parametro1 = "rfc_emisor";
            final String nomPrueba = "val_aduana";
            //<editor-fold desc="Ejecucion1">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "null");
            ejecucion1.put(valEsperado, "0");
            listParametros.add(ejecucion1);
            //</editor-fold>

            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" + parametro1 + "\": \"" + temp.get(parametro1) + "\"" + "}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/"+ nomPrueba + "/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"\n");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_hash_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            final String parametro1 = "nombre_usuario";
            final String parametro2 = "password";
            final String parametro3 = "hash";
            final String nomPrueba = "val_hash";
            //<editor-fold desc="Ejecucion de valor 'mull'">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "null");
            ejecucion1.put(parametro2, "null");
            ejecucion1.put(parametro3, "null");
            ejecucion1.put(valEsperado, "0");
            listParametros.add(ejecucion1);
            //</editor-fold>

            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" + parametro1 + "\": \"" + temp.get(parametro1) + "\", "
                        + parametro2 + "\": \"" + temp.get(parametro2) + "\", "
                        + parametro3 + "\": \"" + temp.get(parametro3) + "\""+ "}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/"+ nomPrueba + "/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"\n");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_certificado_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            final String parametro1 = "rfc_emisor";
            final String parametro2 = "fecha_emision";
            final String nomPrueba = "val_certificado";
            //<editor-fold desc="Ejecucion de valor 'mull'">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "null");
            ejecucion1.put(parametro2, "null");
            ejecucion1.put(valEsperado, "0");
            listParametros.add(ejecucion1);
            //</editor-fold>

            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" + parametro1 + "\": \"" + temp.get(parametro1) + "\", "
                        + parametro2 + "\": \"" + temp.get(parametro2) + "\""
                        + "}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/"+ nomPrueba + "/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"\n");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_unidad_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            final String parametro1 = "unidad";
            final String nomPrueba = "val_unidad";
            //<editor-fold desc="Ejecucion de valor 'mull'">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "null");
            ejecucion1.put(valEsperado, "1");
            listParametros.add(ejecucion1);
            //</editor-fold>

            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" + parametro1 + "\": \"" + temp.get(parametro1) + "\"" + "}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/"+ nomPrueba + "/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"\n");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_codigo_postal_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            final String parametro1 = "codigo_postal";
            final String nomPrueba = "val_codigo_postal";
            //<editor-fold desc="Ejecucion de valor 'mull'">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "null");
            ejecucion1.put(valEsperado, "1");
            listParametros.add(ejecucion1);
            //</editor-fold>

            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" + parametro1 + "\": \"" + temp.get(parametro1) + "\"" + "}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/"+ nomPrueba + "/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"\n");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_lugar_expedicion_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            final String parametro1 = "codigo_postal";
            final String nomPrueba = "val_lugar_expedicion";
            //<editor-fold desc="Ejecucion de valor 'mull'">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "null");
            ejecucion1.put(valEsperado, "1");
            listParametros.add(ejecucion1);
            //</editor-fold>

            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" + parametro1 + "\": \"" + temp.get(parametro1) + "\"" + "}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/"+ nomPrueba + "/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_producto_serv_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            final String parametro1 = "clave_producto";
            final String nomPrueba = "val_producto_serv";
            //<editor-fold desc="Ejecucion de valor 'mull'">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "null");
            ejecucion1.put(valEsperado, "0");
            listParametros.add(ejecucion1);
            //</editor-fold>

            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" + parametro1 + "\": \"" + temp.get(parametro1) + "\"" + "}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/"+ nomPrueba + "/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"\n");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_pedimento_test() {
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            String parametro1 = "num_pedimento";
            String parametro2 = "val_normal";
            String valEsperado = "valEsperado";

            //<editor-fold desc="Ejecucion1">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "null");
            ejecucion1.put(parametro2, "1");
            ejecucion1.put(valEsperado, "1");
            listParametros.add(ejecucion1);
            //</editor-fold>


            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" + parametro1 + "\": \"" + temp.get(parametro1) + "\", \""
                        + parametro2 + "\":\"" + temp.get(parametro2) + "\"}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/val_pedimento/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"\n");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_pedimento_parte_test() {
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            String parametro1 = "num_pedimento";
            String parametro2 = "val_normal";
            String valEsperado = "valEsperado";

            //<editor-fold desc="Ejecucion1">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "null");
            ejecucion1.put(parametro2, "0");
            ejecucion1.put(valEsperado, "1");
            listParametros.add(ejecucion1);
            //</editor-fold>


            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" + parametro1 + "\": \"" + temp.get(parametro1) + "\", \""
                        + parametro2 + "\":\"" + temp.get(parametro2) + "\"}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/val_pedimento/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"\n");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_forma_pago_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            String parametro1 = "forma_pago";
            String valEsperado = "valEsperado";

            //<editor-fold desc="Ejecucion1">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "null");
            ejecucion1.put(valEsperado, "1");
            listParametros.add(ejecucion1);
            //</editor-fold>

            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" + parametro1 + "\": \"" + temp.get(parametro1) + "\"" + "}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/val_forma_pago/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"\n");
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
                Assert.assertTrue(!mensaje.contains("Exception"));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_metodo_pago_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            String parametro1 = "metodo_pago";
            String valEsperado = "valorEsperado";

            //<editor-fold desc="Ejecucion1">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "null");
            ejecucion1.put(valEsperado, "1");
            listParametros.add(ejecucion1);
            //</editor-fold>

            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" + parametro1 + "\": \"" + temp.get(parametro1) + "\"}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/val_metodo_pago/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"\n");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_moneda_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            final String parametro1 = "moneda";
            final String parametro2 = "variacion";
            final String nomPrueba = "val_moneda";
            //<editor-fold desc="Ejecucion de valor 'mull'">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "null");
            ejecucion1.put(parametro2, "null");
            ejecucion1.put(valEsperado, "1");
            listParametros.add(ejecucion1);
            //</editor-fold>

            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" + parametro1 + "\": \"" + temp.get(parametro1) + "\", "
                        + parametro2 + "\": \"" + temp.get(parametro2) + "\"}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/"+ nomPrueba + "/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"\n");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_pais_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            final String parametro1 = "residencia_fiscal";
            final String parametro2 = "pais_form_reg";
            final String parametro3 = "rfc_receptor";
            final String nomPrueba = "val_pais";
            //<editor-fold desc="Ejecucion de valor 'mull'">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "null");
            ejecucion1.put(parametro2, "null");
            ejecucion1.put(parametro3, "null");
            ejecucion1.put(valEsperado, "1");
            listParametros.add(ejecucion1);
            //</editor-fold>

            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" + parametro1 + "\": \"" + temp.get(parametro1) + "\", "
                        + parametro2 + "\": \"" + temp.get(parametro2)+ "\", "
                        + parametro3 + "\": \"" + temp.get(parametro3)+ "\"}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/"+ nomPrueba + "/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"\n");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_factor_serv_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            final String parametro1 = "factor";
            final String nomPrueba = "val_factor";
            //<editor-fold desc="Ejecucion de valor 'mull'">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "null");
            ejecucion1.put(valEsperado, "1");
            listParametros.add(ejecucion1);
            //</editor-fold>

            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" + parametro1 + "\": \"" + temp.get(parametro1) + "\"" + "}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/"+ nomPrueba + "/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"\n");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_comprobante_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            final String parametro1 = "comprobante";
            final String nomPrueba = "val_comprobante";
            //<editor-fold desc="Ejecucion de valor 'mull'">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "null");
            ejecucion1.put(valEsperado, "1");
            listParametros.add(ejecucion1);
            //</editor-fold>

            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" + parametro1 + "\": \"" + temp.get(parametro1) + "\"" + "}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/"+ nomPrueba + "/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"\n");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_patente_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            final String parametro1 = "patente";
            final String nomPrueba = "val_patente";
            //<editor-fold desc="Ejecucion de valor 'mull'">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "null");
            ejecucion1.put(valEsperado, "1");
            listParametros.add(ejecucion1);
            //</editor-fold>

            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" + parametro1 + "\": \"" + temp.get(parametro1) + "\"" + "}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/"+ nomPrueba + "/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"\n");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_regimen_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            final String parametro1 = "regimen";
            final String parametro2 = "rfc_emisor";
            final String nomPrueba = "val_regimen";
            //<editor-fold desc="Ejecucion de valor 'mull'">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "null");
            ejecucion1.put(parametro2, "null");
            ejecucion1.put(valEsperado, "1");
            listParametros.add(ejecucion1);
            //</editor-fold>

            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" + parametro1 + "\": \"" + temp.get(parametro1) + "\", "
                        + parametro2 + "\": \"" + temp.get(parametro2) + "\"" + "}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/"+ nomPrueba + "/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"\n");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_relacion_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            final String parametro1 = "relacion";
            final String nomPrueba = "val_relacion";
            //<editor-fold desc="Ejecucion de valor 'mull'">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "null");
            ejecucion1.put(valEsperado, "1");
            listParametros.add(ejecucion1);
            //</editor-fold>

            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" + parametro1 + "\": \"" + temp.get(parametro1)+ "\"" + "}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/"+ nomPrueba + "/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"\n");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_uso_cfdi_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            final String parametro1 = "cfdi";
            final String parametro2 = "rfc_emisor";
            final String nomPrueba = "val_uso_cfdi";
            //<editor-fold desc="Ejecucion de valor 'mull'">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "null");
            ejecucion1.put(parametro2, "null");
            ejecucion1.put(valEsperado, "1");
            listParametros.add(ejecucion1);
            //</editor-fold>

            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" + parametro1 + "\": \"" + temp.get(parametro1) + "\", "
                        + parametro2 + "\": \"" + temp.get(parametro2) + "\"" + "}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/"+ nomPrueba + "/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"\n");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_confirmacion_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            final String parametro1 = "rfc_emisor";
            final String parametro2 = "confirmacion";
            final String parametro3 = "rfc_receptor";
            final String parametro4 = "uuid";
            final String parametro5 = "xml";
            final String nomPrueba = "val_confirmacion";
            //<editor-fold desc="Ejecucion de valor 'mull'">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "null");
            ejecucion1.put(parametro2, "null");
            ejecucion1.put(parametro3, "null");
            ejecucion1.put(parametro4, "null");
            ejecucion1.put(parametro5, "null");
            ejecucion1.put(valEsperado, "1");
            listParametros.add(ejecucion1);
            //</editor-fold>

            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" + parametro1 + "\": \"" + temp.get(parametro1) + "\", "
                        + parametro2 + "\": \"" + temp.get(parametro2) + "\", "
                        + parametro3 + "\": \"" + temp.get(parametro3) + "\", "
                        + parametro4 + "\": \"" + temp.get(parametro4) + "\", "
                        + parametro5 + "\": \"" + temp.get(parametro5) + "\"" + "}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/"+ nomPrueba + "/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_impuestos_retenidos_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            final String parametro1 = "total_impuestos_retenidos";
            final String parametro2 = "moneda";
            final String nomPrueba = "val_impuestos_retenidos";
            //<editor-fold desc="Ejecucion de valor 'mull'">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "null");
            ejecucion1.put(parametro2, "null");
            ejecucion1.put(valEsperado, "1");
            listParametros.add(ejecucion1);
            //</editor-fold>

            //<editor-fold desc="Funcion iterativa que ejecuta el cliente">
            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" +
                        parametro1 + "\": " + temp.get(parametro1) + ", "
                        + "\"" + parametro2 + "\": " + temp.get(parametro2)+ "}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/"+ nomPrueba + "/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
            //</editor-fold>
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_impuestos_trasladados_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            final String parametro1 = "total_impuestos_trasladados";
            final String parametro2 = "moneda";
            final String nomPrueba = "val_impuestos_trasladados";
            //<editor-fold desc="Ejecucion de valor 'mull'">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "null");
            ejecucion1.put(parametro2, "null");
            ejecucion1.put(valEsperado, "1");
            listParametros.add(ejecucion1);
            //</editor-fold>

            //<editor-fold desc="Funcion iterativa que ejecuta el cliente">
            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" +
                        parametro1 + "\": " + temp.get(parametro1) + ", "
                        + "\"" + parametro2 + "\": " + temp.get(parametro2)+ "}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/"+ nomPrueba + "/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
            //</editor-fold>
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_tasa_cuota_json_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            final String parametro1 = "tasacuotaconcepto";
            final String parametro2 = "tasacuotatraslado";
            final String parametro3 = "tasacuotaretencion";
            final String nomPrueba = "val_tasa_cuota_json";
            //<editor-fold desc="Ejecucion de valor 'mull'">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametro1, "{\"tasacuota\": \"null\", \"impuesto\": \"null\", \"tipofactor\": \"null\"}");
            ejecucion1.put(parametro2, "{\"tasacuota\": \"null\", \"impuesto\": \"null\", \"tipofactor\": \"null\"}");
            ejecucion1.put(parametro3, "{\"tasacuota\": \"null\", \"impuesto\": \"null\", \"tipofactor\": \"null\"}");
            ejecucion1.put(valEsperado, "1");
            listParametros.add(ejecucion1);
            //</editor-fold>

            //<editor-fold desc="Funcion iterativa que ejecuta el cliente">
            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" +
                        parametro1 + "\": " + temp.get(parametro1) + ", "
                        + "\"" + parametro2 + "\": " + temp.get(parametro2) + ", "
                        + "\"" + parametro3 + "\": " + temp.get(parametro3) + "}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/"+ nomPrueba + "/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
            //</editor-fold>
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void val_importes_test (){
        try {
            List<Map<String, String>> listParametros = new ArrayList<Map<String, String>>();
            final String parametroMoneda = "moneda";
            final String parametro1 = "importe1";
            final String parametro2 = "importe2";
            final String parametro3 = "importe3";
            final String parametro4 = "importe4";
            final String parametro5 = "importe5";
            final String parametro6 = "importe6";
            final String nomPrueba = "val_importes";
            //<editor-fold desc="Ejecucion de valor 'mull'">
            Map<String, String> ejecucion1 = new HashMap<String, String>();
            ejecucion1.put(parametroMoneda, "null");
            ejecucion1.put(parametro1, "{\"importe\": \"null\", \"cantidad\": \"null\", \"valor_unitario\": \"null\"}");
            ejecucion1.put(parametro2, "{\"importes\": \"null\",  \"impuestos\":\"null\", \"tasas_cuota\":\"null\", \"tipos_factor\":\"null\", \"bases\": \"null\"}");
            ejecucion1.put(parametro3, "{\"importes\": \"null\",  \"impuestos\":\"null\", \"bases\": \"null\", \"tasas_cuota\": \"null\"}");
            ejecucion1.put(parametro4, "{\"importe\": \"null\", \"cantidad\": \"null\", \"valor_unitario\": \"null\"}");
            ejecucion1.put(parametro5, "{\"importes\": \"null\", \"impuestos\":\"null\"}");
            ejecucion1.put(parametro6, "{\"importes\": \"null\", \"impuestos\":\"null\", \"tasas_cuota\":\"null\", \"tipos_factor\":\"null\"}");
            ejecucion1.put(valEsperado, "1");
            listParametros.add(ejecucion1);
            //</editor-fold>

            //<editor-fold desc="Funcion iterativa que ejecuta el cliente">
            for (Map<String, String> temp : listParametros) {
                String datos = "{\"" + parametroMoneda + "\": \"" + temp.get(parametroMoneda) + "\", "
                        + "\"" + parametro1 + "\": " + temp.get(parametro1) + ", "
                        + "\"" + parametro2 + "\": " + temp.get(parametro2) + ", "
                        + "\"" + parametro3 + "\": " + temp.get(parametro3) + ", "
                        + "\"" + parametro4 + "\": " + temp.get(parametro4) + ", "
                        + "\"" + parametro5 + "\": " + temp.get(parametro5) + ", "
                        + "\"" + parametro6 + "\": " + temp.get(parametro6) + "}";
                String datos64 = clienteServer.obtenerBase64(datos);
                String url = host + "/validacionUnitaria/"+ nomPrueba + "/datos/" + datos64;
                System.out.println(url + "\nDatos enviados: " + datos);
                String mensaje = clienteServer.ejecucionDeCliente(url);
                System.out.println("\"Esperado\":\"" + temp.get(valEsperado) + "\"");
                Assert.assertTrue(!mensaje.contains("Exception"));
                Assert.assertTrue(mensaje.contains("\"resultado\":\"" + temp.get(valEsperado) + "\""));
            }
            //</editor-fold>
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
}
