package com.jfrogz.cliente.app;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.codec.CharEncoding.UTF_8;


public class ClienteServer {

    public static void main(String[] args) {

        ClienteServer clienteServer = new ClienteServer();
        //clienteServer.ejecutarClienteGet();
        //clienteServer.ejecutarClientePost();
        clienteServer.ejecutarClientePost2();
        //clienteServer.ejecutarClientePost3();
        //clienteServer.validarUsuario();

    }

    public void ejecutarClienteGet() {
        try {

            URL url = new URL("http://localhost:8080/servidor/service/mensajes/default");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output + "\t" + conn.getResponseCode() + "\t" + conn.getResponseMessage());
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ejecutarClientePost() {
        try {
            URL url = new URL("http://localhost:8080/servidor/service/mensajes/carro");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\n" +

                    "\t\"fabricante\": \"Audi\",\n" +
                    "    \"modelo\": \"A4\",\n" +
                    "  \t\"anio\": \"2018\"\n" +
                    "}";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output + "\t" + conn.getResponseCode()+ "\t" + conn.getResponseMessage()) ;

            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ejecutarClientePost2() {
        try {
            //String importes = obtenerBase64("{|moneda|:|MXN|,|importe1|:{|importe|:|15000.00|,|cantidad|:|1|,|valor_unitario|:|15000.00|},|importe2|:{|importes|:|2400.00,|,|impuestos|:|002,|,|tasas_cuota|:|0.160000,|,|tipos_factor|:|Tasa,|,|bases|:|15000.00,|},|importe3|:{|importes|:||,|impuestos|:|002,002,|,|bases|:||,|tasas_cuota|:||},|importe4|:{|importe|:|3000.00|,|cantidad|:|1|,|valor_unitario|:|15000.00|},|importe5|:{|importes|:|4800.00,|,|impuestos|:|002,|},|importe6|:{|importes|:|2400.00,|,|impuestos|:|002,|,|tasas_cuota|:|0.160000,|,|tipos_factor|:|Tasa,|}}");
            String importes = obtenerBase64("{\"moneda\":\"MXN\",\"importe1\":{\"importe\":\"200.00\",\"cantidad\":\"1\",\"valor_unitario\":\"200.00\"},\"importe2\":{\"importes\":\"32.00,371.20\",\"impuestos\":\"002,003\",\"tasas_cuota\":\"0.160000,1.600000\",\"tipos_factor\":\"Tasa,Tasa\",\"bases\":\"200.00,232.00\"},\"importe3\":{\"importes\":\"null\",\"impuestos\":\"null\",\"bases\":\"null\",\"tasas_cuota\":\"null\"},\"importe4\":{\"importe\":\"1.01\",\"cantidad\":\"1\",\"valor_unitario\":\"0.01\"},\"importe5\":{\"importes\":\"null\",\"impuestos\":\"null\"},\"importe6\":{\"importes\":\"32.00,371.20\",\"impuestos\":\"002,003\",\"tasas_cuota\":\"0.160000,1.600000\",\"tipos_factor\":\"Tasa,Tasa\"}}");
            URL url = new URL("http://localhost:8080/gateway/valida/user/testUser/pass/1234/rfc/LAN8507268IA/numeropedimento/bnVsbA==/numeropedimentoparte/bnVsbA==/hashCFDI/27e5c693667c3a86db62a41a12ed37501a6d415b069193c412f9200ab1b4cb5b/fecha/2017-05-30T10:40:53/unidadclave/H87/codigopostal/null/claveprodserv/50211503/formapago/01/metodopago/PUE/moneda/MXN/pais/null/numregidtrib/null/tipofactortraslado/Tasa/comprobante/I/patente/null/regimen/601/relacion/null/usoCfdi/G03/confirmacion/null/rfcReceptor/AUPM891006D34/tasacuota/e3x0YXNhY3VvdGFjb25jZXB0b3w6e3xpbXB1ZXN0b3w6fDAwMiwwMDMsfCx8dGlwb2ZhY3Rvcnw6fFRhc2EsVGFzYSx8LHx0YXNhY3VvdGF8OnwwLjE2MDAwMCwxLjYwMDAwMCx8fSx8dGFzYWN1b3RhdHJhc2xhZG98Ont8aW1wdWVzdG98OnwwMDIsMDAzLHwsfHRpcG9mYWN0b3J8OnxUYXNhLFRhc2EsfCx8dGFzYWN1b3RhfDp8MC4xNjAwMDAsMS42MDAwMDAsfH0sfHRhc2FjdW90YXJldGVuY2lvbnw6e3xpbXB1ZXN0b3w6fHwsfHRpcG9mYWN0b3J8Onx8LHx0YXNhY3VvdGF8Onx8fX0=/LugarExpedicion/06300/subtotal/200.00/importe/200.00/tipoCambio/1/uuid/null/residenciafiscal/null/validar_importes/" + importes +"/base64/PGNmZGk6Q29tcHJvYmFudGUgVmVyc2lvbj0iMy4zIiBTZXJpZT0iUm9ndWVPbmUiIEZvbGlvPSJITkZLMjMxIiBGZWNoYT0iMjAxNy0wNS0zMFQxMDo0MDo1MyIgU2VsbG89IlU1dzh5bXZkSUpwekxFOGdSUXRkVE5xQ1Vwa0RqRVQyamJFTmZuclhGZmJoNnRXZUhsaU9EVTk4MTNsbndwMm1kUGlKcSt3d21mWUh3L0NmZlRxQThKakh6L1pUSW9velU5WFJXTlVnTlVRUFp0Sk1LblVJVEE4MVkyZW");
            //URL url = new URL("http://192.168.100.5:8181/gateway/valida/user/testUser/pass/1234/rfc/LAN8507268IA/numeropedimento/bnVsbA==/numeropedimentoparte/bnVsbA==/hashCFDI/4cea03cbecadac61d334122c5993e5836bf28f99ae01550d8f5983b2fa70d48c/fecha/2017-05-15T12:03:58/unidadclave/H87/codigopostal/null/claveprodserv/50211503/formapago/01/metodopago/PUE/moneda/MXN/pais/null/numregidtrib/null/tipofactortraslado/Tasa/comprobante/I/patente/null/regimen/601/relacion/null/usoCfdi/null/claveImpuesto/002/confirmacion/null/rfcReceptor/AAA150310NJ0/tasacuota/MC4xNjAwMDAsMS42MDAwMDAsbnVsbCxudWxsLDAuMTYwMDAwLDEuNjAwMDAw/LugarExpedicion/06300/subtotal/200.00/importe/200.00/tipoCambio/1/descuento/0.00/tasacuotaimpuesto/MDAyLDAwMyxudWxsLG51bGwsMDAyLDAwMw==/tasacuotafactor/VGFzYSxUYXNhLG51bGwsbnVsbCxUYXNhLFRhc2E=/uuid/null/residenciafiscal/USA/validar_importes/"+importes+"/base64/PGNmZGk6Q29tcHJvYmFudGUgVmVyc2lvbj0iMy4zIiBTZXJpZT0iUm9ndWVPbmUiIEZvbGlvPSJITkZLMjMxIiBGZWNoYT0iMjAxNy0wNS0xNVQxMjowMzo1OCIgU2VsbG89Ik5TTFZzTWV1UUZ4ekJReVFFdjZ6RHNlTGtSMWlRa1U4UWVUR0FsUlRkNEJhSnZUN3ZmUE1BbytuZ1I0blhyV3dLbldySGJHL0U2eHFhUndWS1cxTHdFbXF4d3Q0SnBtVEdUTyt3eTl4UWJqRGRPU0dRWkx6bFY1N2FKdTRCMHQ4SnB4Z0U2RndyVSs5N3Bpc01oNXpIbm81L0U0VTV0djd5eXpJNittR3F5TzFrOEN0bXU5N1F1V2IvV0FLUDNnS1RrekdkaDk3OHlXOGJzT1h1QlFZRHp6K3RPWFh5V3gwclI5dkJndUN6SGc2UlZGdEhqcDlEcUhnVUNUaFdiMi80MldSK3RlazdPaERvZy9yZTdrcWF4cm4wNk4ycVVHbW1nNnBzSGU3anppZkJ6LzQ3Nkd4YWRuRWFNOTFQNW9jeSsxanJqTG03UW9jdnJRSlBlTWVrQT09IiBGb3JtYVBhZ289IjAxIiBOb0NlcnRpZmljYWRvPSIyMDAwMTAwMDAwMDMwMDAyMjgxNiIgQ2VydGlmaWNhZG89Ik1JSUYwVENDQTdtZ0F3SUJBZ0lVTWpBd01ERXdNREF3TURBek1EQXdNakk0TVRZd0RRWUpLb1pJaHZjTkFRRUxCUUF3Z2dGbU1TQXdIZ1lEVlFRRERCZEJMa011SURJZ1pHVWdjSEoxWldKaGN5ZzBNRGsyS1RFdk1DMEdBMVVFQ2d3bVUyVnlkbWxqYVc4Z1pHVWdRV1J0YVc1cGMzUnlZV05wdzdOdUlGUnlhV0oxZEdGeWFXRXhPREEyQmdOVkJBc01MMEZrYldsdWFYTjBjbUZqYWNPemJpQmtaU0JUWldkMWNtbGtZV1FnWkdVZ2JHRWdTVzVtYjNKdFlXTnB3N051TVNrd0p3WUpLb1pJaHZjTkFRa0JGaHBoYzJsemJtVjBRSEJ5ZFdWaVlYTXVjMkYwTG1kdllpNXRlREVtTUNRR0ExVUVDUXdkUVhZdUlFaHBaR0ZzWjI4Z056Y3NJRU52YkM0Z1IzVmxjbkpsY204eERqQU1CZ05WQkJFTUJUQTJNekF3TVFzd0NRWURW");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            System.out.println("CADENA: " +url);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/xml");
/*
            String input = "{\n" +

                    "\t\"fabricante\": \"Audi\",\n" +
                    "    \"modelo\": \"A4\",\n" +
                    "  \t\"anio\": \"2018\"\n" +
                    "}";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
*/
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output) ;

            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ejecutarClientePost3() {
        try {
            String mensajeBase64 =
                    obtenerBase64("{\n" +
                    "\t\"val_usuario\":{\"nombre_usuario\": \"fernando Robles\", \"password\":\"1234567890\"},\n" +
                    "\t\"val_rfc_receptor\":{\"rfc_receptor\":\"\"},\n" +
                    "\t\"val_rfc_lco\":{\"rfc_emisor\":\"\"},\n" +
                    "\t\"val_aduana\":{\"num_pedimento\":\"\"},\n" +
                    "\t\"val_hash\":{\"nombre_usuario\": \"\", \"password\":\"\", \"hash\": \"\"},\n" +
                    "\t\"val_certificado\": {\"rfc_emisor\":\"\", \"fecha_emision\":\"\"},\n" +
                    "\t\"val_unidad\":{\"unidad\":\"\"},\n" +
                    "\t\"val_codigo_postal\": {\"codigo_postal\":\"\"},\n" +
                    "\t\"val_lugar_expedicion\":{\"lugar_exp\":\"\"},\n" +
                    "\t\"val_producto_serv\": {\"clave_producto\":\"\"},\n" +
                    "\t\"val_pedimento\":{\"num_pedimento\":\"\", \"es_parte\":\"\"}, \n" +
                    "\t\"val_pedimento_parte\":{\"num_pedimento_parte\":\"\", \"es_parte\":\"\"},\n" +
                    "\t\"val_forma_pago\":{\"forma_pago\":\"\"},\n" +
                    "\t\"val_metodo_pago\": {\"metodo_pago\":\"\"},\n" +
                    "\t\"val_moneda\": {\"moneda\": \"\", \"variacion\":\"\" },\n" +
                    "\t\"val_pais\": {\"residencia_fiscal\": \"\", \"pais_form_reg\":\"\", \"rfc_receptor\":\"\" },\n" +
                    "\t\"val_factor\": {\"factor\":\"\"},\n" +
                    "\t\"val_comprobante\":{\"comprobante\":\"\"},\n" +
                    "\t\"val_patente\": {\"patente\": \"\"},\n" +
                    "\t\"val_regimen\":{\"regimen\": \"\", \"rfc_emisor\": \"\"},\n" +
                    "\t\"val_relacion\": {\"relacion\":\"\"},\n" +
                    "\t\"val_uso_cfdi\": {\"cfdi\": \"\"},\n" +
                    "\t\"val_impuesto\": {\"impuesto\": \"\"},\n" +
                    "\t\"val_confirmacion\":{\"rfc_emisor\": \"\", \"confirmacion\":\"\", \"uuid\": \"\", \"xml\":\"\"},\n" +
                    "\t\"val_tasa_cuota_json\": {  \n" +
                    "\t\t\"tasacuotaconcepto\": {\"tasacuota\": \",,\", \"impuesto\": \",,\", \"tipofactor\": \",,\"},  \n" +
                    "\t\t\"tasacuotatraslado\": {\"tasacuota\": \",,\",  \"impuesto\":\",,\", \"tipofactor\":\",,\"},  \n" +
                    "\t\t\"tasacuotaretencion\": {\"tasacuota\": \",,\",  \"impuesto\":\",,\", \"tipofactor\": \",,\"}}, \n" +
                    "\t\"val_importes\": {\n" +
                    "\t\t\"moneda\": \"\", \n" +
                    "\t\t\"importe1\": {\"importe\": \"\", \"cantidad\": \"\", \"valor_unitario\": \"\"},\n" +
                    "\t\t\"importe2\": {\"importes\": \",,,\",  \"impuestos\":\",,,\", \"tasas_cuota\":\",,,\", \"tipos_factor\":\",,,\", \"bases\": \",,,\"},\n" +
                    "\t\t\"importe3\": {\"importes\": \",,,\",  \"impuestos\":\",,,\", \"bases\": \",,,\", \"tasas_cuota\": \",,,\"},\t\n" +
                    "\t\t\"importe4\": {\"importe\": \"\", \"cantidad\": \"\", \"valor_unitario\": \"\"},\t\n" +
                    "\t\t\"importe5\": {\"importes\": \",,,\", \"impuestos\":\",,,\"},\n" +
                    "\t\t\"importe6\": {\"importes\": \",,,\", \"impuestos\":\",,,\", \"tasas_cuota\":\",,,\", \"tipos_factor\":\",,,\"}\n" +
                    "\t},\n" +
                    "\t\"reglas_validacion\": {\"suma_importes\":\"\",\n" +
                    "\t\t\"total_importes\":\"\",\n" +
                    "\t\t\"in_total\":\"\",\n" +
                    "\t\t\"in_compl_tipo_compr\":\"\"}\n" +
                    "}\n" +
                    "\n" +
                    "\n");
            System.out.println("Mensaje enviado: \n" + mensajeBase64);

            URL url = new URL("http://localhost:8080/datosgenerales/" + mensajeBase64);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
/*
            String input = "{\n" +

                    "\t\"fabricante\": \"Audi\",\n" +
                    "    \"modelo\": \"A4\",\n" +
                    "  \t\"anio\": \"2018\"\n" +
                    "}";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
*/
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output) ;

            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void validarUsuario() {
        String datos64 =
                null;
        try {
            //datos64 = obtenerBase64("{\"nombre_usuario\": \"fernando Robles\", \"password\":\"1234567890\"}");

            //datos64 = obtenerBase64("{\"num_pedimento\": \"59  65  6532  1234567\"}");
            //String url = "http://localhost:8080/validacionUnitaria/val_aduana/datos/" + datos64;

            datos64 = obtenerBase64("{\"metodo_pago\": \"PUE\"}");
            String url = "http://189.164.224.31:8080/validacionUnitaria/val_metodo_pago/datos/" + datos64;

            //datos64 = obtenerBase64("{\"forma_pago\": \"null\"}");
            //String url = "http://localhost:8080/validacionUnitaria/val_forma_pago/datos/" + datos64;

            //datos64 = obtenerBase64("{\"rfc_emisor\": \"null\"}");
            //String url = "http://localhost:8080/validacionUnitaria/val_rfc_lco/datos/" + datos64;
            System.out.println(url);
            ejecucionDeCliente(url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String ejecucionDeCliente (String urlString){
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
/*
            String input = "{\n" +

                    "\t\"fabricante\": \"Audi\",\n" +
                    "    \"modelo\": \"A4\",\n" +
                    "  \t\"anio\": \"2018\"\n" +
                    "}";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
*/
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            String salida="";
            System.out.println("Output from Server ....");
            while ((output = br.readLine()) != null) {
                System.out.println(output) ;
                salida+= output;
            }
            conn.disconnect();
            return salida;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String  obtenerBase64 (String cadenaURL) throws UnsupportedEncodingException {
        return org.apache.commons.codec.binary.Base64.encodeBase64String(cadenaURL.getBytes(UTF_8));
    }
}

