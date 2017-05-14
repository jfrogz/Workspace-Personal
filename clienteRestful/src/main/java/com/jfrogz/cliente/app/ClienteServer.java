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
            URL url = new URL("http://localhost:8080/gateway/valida/user/testUser/pass/1234/rfc/AAA010101AAA/numeropedimento/bnVsbA==/numeropedimentoparte/bnVsbA==/hashCFDI/b8f2119743ea86fb5f85f288ea956ca7e04c05dd866cdb7ab04929d36828d6f4/fecha/2017-05-06T10:47:08/unidadclave/H87/codigopostal/null/claveprodserv/50211503/formapago/01/metodopago/PUE/moneda/MXN/pais/null/numregidtrib/null/tipofactortraslado/Tasa/comprobante/I/patente/null/regimen/601/relacion/null/usoCfdi/null/claveImpuesto/002/confirmacion/null/rfcReceptor/AUPM891006D34/tasacuota/bnVsbCxudWxsLG51bGwsbnVsbCwwLjE2MDAwMCwxLjYwMDAwMA==/LugarExpedicion/06300/subtotal/200.00/importe/200.00/tipoCambio/1/descuento/0.00/tasacuotaimpuesto/MDAyLDAwMyxudWxsLG51bGwsMDAyLDAwMw==/tasacuotafactor/VGFzYSxUYXNhLG51bGwsbnVsbCxUYXNhLFRhc2E=/uuid/null/residenciafiscal/mxn/base64/PGNmZGk6Q29tcHJvYmFudGUgVmVyc2lvbj0iMy4zIiBTZXJpZT0iUm9ndWVPbmUiIEZvbGlvPSJITkZLMjMxIiBGZWNoYT0iMjAxNy0wNS0wNlQxMDo0NzowOCIgU2VsbG89IkdldVV0Qkl0ckQ0RVEyMEU3Y0M1TzhZeU9LVDMzNklWSVlhTGR6UHJPSityZWYxallqZDZ3RHlIWnZrbkR3YmdyTi9DOEpYaWxKNFlTSiswd201VmJaNjhjTFIxcXdKV2tYWGRtWlhyRVhNN0taMkdjcmt3bEwySEpQVGV0alJOZ1BVTXprMHJORTNpU1A3ZDBxbVhqRmVJdVdtejR4Qld4eXhNT3d2OG5FOD0iIEZvcm1hUGFnbz0iMDEiIE5vQ2VydGlmaWNhZG89IjIwMDAxMDAwMDAwMjAwMDAxNDM3IiBDZXJ0aWZpY2Fkbz0iTUlJRVlUQ0NBMG1nQXdJQkFnSVVNakF3TURFd01EQXdNREF5TURBd01ERTBNemN3RFFZSktvWklodmNOQVFFRkJRQXdnZ0ZjTVJvd0dBWURWUVFEREJGQkxrTXVJRElnWkdVZ2NISjFaV0poY3pFdk1DMEdBMVVFQ2d3bVUyVnlkbWxqYVc4Z1pHVWdRV1J0YVc1cGMzUnlZV05wdzdOdUlGUnlhV0oxZEdGeWFXRXhPREEyQmdOVkJBc01MMEZrYldsdWFYTjBjbUZqYWNPemJpQmtaU0JUWldkMWNtbGtZV1FnWkdVZ2JHRWdTVzVtYjNKdFlXTnB3N051TVNrd0p3WUpLb1pJaHZjTkFRa0JGaHBoYzJsemJtVjBRSEJ5ZFdWaVlYTXVjMkYwTG1kdllpNXRlREVtTUNRR0ExVUVDUXdkUVhZdUlFaHBaR0ZzWjI4Z056Y3NJRU52YkM0Z1IzVmxjbkpsY204eERqQU1CZ05WQkJFTUJUQTJNekF3TVFzd0NRWURWUVFHRXdKTldERVpNQmNHQTFVRUNBd1FSR2x6ZEhKcGRHOGdSbVZrWlhKaGJERVNNQkFHQTFVRUJ3d0pRMjk1YjJGanc2RnVNVFF3TWdZSktvWklodmNOQVFrQ0RDVlNaWE53YjI1ellXSnNaVG9nUVhKaFkyVnNhU0JIWVc1a1lYSmhJRUpoZFhScGMzUmhNQjRYRFRFek1EVXdOekUzTkRBd04xb1hEVEUzTURVd056RTNOREF3TjFvd2dkc3hLVEFuQmdOVkJB/suma_importes/null/total_importes/null/valor_cantidad/null/valor_unitario/null/valor_base/null/valor_cuota/null/nivel/null/in_total/null/in_compl_tipo_compr/null/");
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
}

