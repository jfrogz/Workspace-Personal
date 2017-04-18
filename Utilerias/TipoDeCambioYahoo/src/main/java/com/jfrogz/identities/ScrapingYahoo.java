package com.jfrogz.identities;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScrapingYahoo {
    public static final String url = "https://finance.yahoo.com/connection/currency-converter-cache?date=";
    public static String[] palabrasAQuitar = {"<head>", "</head>", "<body>", "</body>", "<html>", "</html>", "/**/YAHOO.Finance.CurrencyConverter.addConversionRates(", ");", " "};

    public static void main(String args[]) {

    ScrapingYahoo scrapingYahoo = new ScrapingYahoo();
    scrapingYahoo.listTipoCambio(new Date());

    }

    public void listTipoCambio(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String urlTemp = url + simpleDateFormat.format(date);
        if (getStatusConnectionCode(urlTemp) == 200) {

            JSONObject jsonObject = new JSONObject(getStringJson(urlTemp));
            JSONArray jsonArray = jsonObject.getJSONObject("list").getJSONArray("resources");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i).getJSONObject("resource").getJSONObject("fields");
                System.out.println(jsonObj.getString("price"));
                System.out.println(jsonObj.getString("symbol").replace("=X", ""));
                System.out.println(jsonObj.toString());
            }
        } else {
            System.out.println("El Status Code no es OK es: " + getStatusConnectionCode(urlTemp));
        }
    }

    private String getStringJson(String url1) {
        // Obtengo el HTML de la web en un objeto Document
        Document document = getHtmlDocument(url1);
        System.out.println("El contenido de lo obtenido es: " + document);
        //Obtenemos el json que se va a consultar.
        String jsonmod = document.toString();
        for (String palabra : palabrasAQuitar) {
            jsonmod = jsonmod.replace(palabra, "");
        }
        jsonmod = jsonmod.trim();
        System.out.println("Contenido modificado: " + jsonmod);
        return jsonmod;
    }

    /**
     * Con esta método compruebo el Status code de la respuesta que recibo al hacer la petición
     * EJM:
     * 200 OK					300 Multiple Choices
     * 301 Moved Permanently	305 Use Proxy
     * 400 Bad Request			403 Forbidden
     * 404 Not Found			500 Internal Server Error
     * 502 Bad Gateway			503 Service Unavailable
     *
     * @param url
     * @return Status Code
     */
    public static int getStatusConnectionCode(String url) {
        Connection.Response response = null;
        try {
            response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
        } catch (IOException ex) {
            System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
        }
        return response.statusCode();
    }

    /**
     * Con este método devuelvo un objeto de la clase Document con el contenido del
     * HTML de la web que me permitirá parsearlo con los métodos de la librelia JSoup
     *
     * @param url
     * @return Documento con el HTML
     */
    public static Document getHtmlDocument(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
        } catch (IOException ex) {
            System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
        }
        return doc;
    }

}
