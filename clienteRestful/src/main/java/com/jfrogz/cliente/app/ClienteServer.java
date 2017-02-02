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
        clienteServer.ejecutarClienteGet();
        clienteServer.ejecutarClientePost();


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
}

