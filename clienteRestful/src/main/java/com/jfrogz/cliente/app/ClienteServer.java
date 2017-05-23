package com.jfrogz.cliente.app;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ClienteServer {

    public static void main(String[] args) {

        ClienteServer clienteServer = new ClienteServer();
        //clienteServer.ejecutarClienteGet();
        //clienteServer.ejecutarClientePost();
        clienteServer.ejecutarClientePost2();

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
            String importes = obtenerBase64("{|moneda|:|MXN|,|importe1|:{|importe|:|15000.00|,|cantidad|:|1|,|valor_unitario|:|15000.00|},|importe2|:{|importes|:|2400.00,|,|impuestos|:|002,|,|tasas_cuota|:|0.160000,|,|tipos_factor|:|Tasa,|,|bases|:|15000.00,|},|importe3|:{|importes|:||,|impuestos|:|002,002,|,|bases|:||,|tasas_cuota|:||},|importe4|:{|importe|:|3000.00|,|cantidad|:|1|,|valor_unitario|:|15000.00|},|importe5|:{|importes|:|4800.00,|,|impuestos|:|002,|},|importe6|:{|importes|:|2400.00,|,|impuestos|:|002,|,|tasas_cuota|:|0.160000,|,|tipos_factor|:|Tasa,|}}");

            //URL url = new URL("http://localhost:8080/gateway/valida/user/testUser/pass/1234/rfc/AAA010101AAA/numeropedimento/bnVsbA==/numeropedimentoparte/bnVsbA==/hashCFDI/b8f2119743ea86fb5f85f288ea956ca7e04c05dd866cdb7ab04929d36828d6f4/fecha/2017-05-06T10:47:08/unidadclave/H87/codigopostal/null/claveprodserv/50211503/formapago/01/metodopago/PUE/moneda/MXN/pais/null/numregidtrib/null/tipofactortraslado/Tasa/comprobante/I/patente/null/regimen/601/relacion/null/usoCfdi/null/claveImpuesto/002/confirmacion/null/rfcReceptor/AUPM891006D34/tasacuota/bnVsbCxudWxsLG51bGwsbnVsbCwwLjE2MDAwMCwxLjYwMDAwMA==/LugarExpedicion/06300/subtotal/200.00/importe/200.00/tipoCambio/1/descuento/0.00/tasacuotaimpuesto/MDAyLDAwMyxudWxsLG51bGwsMDAyLDAwMw==/tasacuotafactor/VGFzYSxUYXNhLG51bGwsbnVsbCxUYXNhLFRhc2E=/uuid/null/residenciafiscal/mxn/validar_importes/"+importes+"/base64/PGNmZGk6Q29tcHJvYmFudGUgVmVyc2lvbj0iMy4zIiBTZXJpZT0iUm9ndWVPbmUiIEZvbGlvPSJITkZLMjMxIiBGZWNoYT0iMjAxNy0wNS0wNlQxMDo0NzowOCIgU2VsbG89IkdldVV0Qkl0ckQ0RVEyMEU3Y0M1TzhZeU9LVDMzNklWSVlhTGR6UHJPSityZWYxallqZDZ3RHlIWnZrbkR3YmdyTi9DOEpYaWxKNFlTSiswd201VmJaNjhjTFIxcXdKV2tYWGRtWlhyRVhNN0taMkdjcmt3bEwySEpQVGV0alJOZ1BVTXprMHJORTNpU1A3ZDBxbVhqRmVJdVdtejR4Qld4eXhNT3d2OG5FOD0iIEZvcm1hUGFnbz0iMDEiIE5vQ2VydGlmaWNhZG89IjIwMDAxMDAwMDAwMjAwMDAxNDM3IiBDZXJ0aWZpY2Fkbz0iTUlJRVlUQ0NBMG1nQXdJQkFnSVVNakF3TURFd01EQXdNREF5TURBd01ERTBNemN3RFFZSktvWklodmNOQVFFRkJRQXdnZ0ZjTVJvd0dBWURWUVFEREJGQkxrTXVJRElnWkdVZ2NISjFaV0poY3pFdk1DMEdBMVVFQ2d3bVUyVnlkbWxqYVc4Z1pHVWdRV1J0YVc1cGMzUnlZV05wdzdOdUlGUnlhV0oxZEdGeWFXRXhPREEyQmdOVkJBc01MMEZrYldsdWFYTjBjbUZqYWNPemJpQmtaU0JUWldkMWNtbGtZV1FnWkdVZ2JHRWdTVzVtYjNKdFlXTnB3N051TVNrd0p3WUpLb1pJaHZjTkFRa0JGaHBoYzJsemJtVjBRSEJ5ZFdWaVlYTXVjMkYwTG1kdllpNXRlREVtTUNRR0ExVUVDUXdkUVhZdUlFaHBaR0ZzWjI4Z056Y3NJRU52YkM0Z1IzVmxjbkpsY204eERqQU1CZ05WQkJFTUJUQTJNekF3TVFzd0NRWURWUVFHRXdKTldERVpNQmNHQTFVRUNBd1FSR2x6ZEhKcGRHOGdSbVZrWlhKaGJERVNNQkFHQTFVRUJ3d0pRMjk1YjJGanc2RnVNVFF3TWdZSktvWklodmNOQVFrQ0RDVlNaWE53YjI1ellXSnNaVG9nUVhKaFkyVnNhU0JIWVc1a1lYSmhJRUpoZFhScGMzUmhNQjRYRFRFek1EVXdOekUzTkRBd04xb1hEVEUzTURVd056RTNOREF3TjFvd2dkc3hLVEFuQmdOVkJB");
            URL url = new URL("http://localhost:8080/gateway/valida/user/testUser/pass/1234/rfc/LAN8507268IA/numeropedimento/bnVsbA==/numeropedimentoparte/bnVsbA==/hashCFDI/4cea03cbecadac61d334122c5993e5836bf28f99ae01550d8f5983b2fa70d48c/fecha/2017-05-15T12:03:58/unidadclave/H87/codigopostal/null/claveprodserv/50211503/formapago/01/metodopago/PUE/moneda/MXN/pais/null/numregidtrib/null/tipofactortraslado/Tasa/comprobante/I/patente/null/regimen/601/relacion/null/usoCfdi/null/claveImpuesto/002/confirmacion/null/rfcReceptor/AAA150310NJ0/tasacuota/MC4xNjAwMDAsMS42MDAwMDAsbnVsbCxudWxsLDAuMTYwMDAwLDEuNjAwMDAw/LugarExpedicion/06300/subtotal/200.00/importe/200.00/tipoCambio/1/descuento/0.00/tasacuotaimpuesto/MDAyLDAwMyxudWxsLG51bGwsMDAyLDAwMw==/tasacuotafactor/VGFzYSxUYXNhLG51bGwsbnVsbCxUYXNhLFRhc2E=/uuid/null/residenciafiscal/USA/validar_importes/"+importes+"/base64/PGNmZGk6Q29tcHJvYmFudGUgVmVyc2lvbj0iMy4zIiBTZXJpZT0iUm9ndWVPbmUiIEZvbGlvPSJITkZLMjMxIiBGZWNoYT0iMjAxNy0wNS0xNVQxMjowMzo1OCIgU2VsbG89Ik5TTFZzTWV1UUZ4ekJReVFFdjZ6RHNlTGtSMWlRa1U4UWVUR0FsUlRkNEJhSnZUN3ZmUE1BbytuZ1I0blhyV3dLbldySGJHL0U2eHFhUndWS1cxTHdFbXF4d3Q0SnBtVEdUTyt3eTl4UWJqRGRPU0dRWkx6bFY1N2FKdTRCMHQ4SnB4Z0U2RndyVSs5N3Bpc01oNXpIbm81L0U0VTV0djd5eXpJNittR3F5TzFrOEN0bXU5N1F1V2IvV0FLUDNnS1RrekdkaDk3OHlXOGJzT1h1QlFZRHp6K3RPWFh5V3gwclI5dkJndUN6SGc2UlZGdEhqcDlEcUhnVUNUaFdiMi80MldSK3RlazdPaERvZy9yZTdrcWF4cm4wNk4ycVVHbW1nNnBzSGU3anppZkJ6LzQ3Nkd4YWRuRWFNOTFQNW9jeSsxanJqTG03UW9jdnJRSlBlTWVrQT09IiBGb3JtYVBhZ289IjAxIiBOb0NlcnRpZmljYWRvPSIyMDAwMTAwMDAwMDMwMDAyMjgxNiIgQ2VydGlmaWNhZG89Ik1JSUYwVENDQTdtZ0F3SUJBZ0lVTWpBd01ERXdNREF3TURBek1EQXdNakk0TVRZd0RRWUpLb1pJaHZjTkFRRUxCUUF3Z2dGbU1TQXdIZ1lEVlFRRERCZEJMa011SURJZ1pHVWdjSEoxWldKaGN5ZzBNRGsyS1RFdk1DMEdBMVVFQ2d3bVUyVnlkbWxqYVc4Z1pHVWdRV1J0YVc1cGMzUnlZV05wdzdOdUlGUnlhV0oxZEdGeWFXRXhPREEyQmdOVkJBc01MMEZrYldsdWFYTjBjbUZqYWNPemJpQmtaU0JUWldkMWNtbGtZV1FnWkdVZ2JHRWdTVzVtYjNKdFlXTnB3N051TVNrd0p3WUpLb1pJaHZjTkFRa0JGaHBoYzJsemJtVjBRSEJ5ZFdWaVlYTXVjMkYwTG1kdllpNXRlREVtTUNRR0ExVUVDUXdkUVhZdUlFaHBaR0ZzWjI4Z056Y3NJRU52YkM0Z1IzVmxjbkpsY204eERqQU1CZ05WQkJFTUJUQTJNekF3TVFzd0NRWURW");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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

    public String  obtenerBase64 (String cadenaURL){
        return org.apache.commons.codec.binary.Base64.encodeBase64String(cadenaURL.getBytes());
    }
}

