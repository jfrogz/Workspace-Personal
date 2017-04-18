package com.jfrogz.identities;

import com.tunyk.currencyconverter.BankUaCom;
import com.tunyk.currencyconverter.api.Currency;
import com.tunyk.currencyconverter.api.CurrencyConverter;
import com.tunyk.currencyconverter.api.CurrencyConverterException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScrapingYahoo {
    public static final String urlDate = "https://finance.yahoo.com/connection/currency-converter-cache?date=";
    public static final String urlParam = "https://es.finance.yahoo.com/divisas/conversor/?bypass=true#from=%s;to=%s;amt=1";
    public static String[] palabrasAQuitar = {"<head>", "</head>", "<body>", "</body>", "<html>", "</html>", "/**/YAHOO.Finance.CurrencyConverter.addConversionRates(", ");", " "};

    public static void main(String args[]) throws ParseException, MalformedURLException, CurrencyConverterException {

        SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
        Date date = d.parse("18-04-2017");
        ScrapingYahoo scrapingYahoo = new ScrapingYahoo();

        List<TipoCambio> tipoCambios = scrapingYahoo.listTipoCambio(date);
        for (TipoCambio tipoCambio : tipoCambios) {
            System.out.println(tipoCambio);
        }
        System.out.println(scrapingYahoo.obtenTipoDeCambio(date));


        /*Ocupamos el WS, lamentablemente no hay conversion para MXN*/
//        CurrencyConverter currencyConverter = new BankUaCom(Currency.USD, Currency.UAH);
//        Float aFloat = currencyConverter.convertCurrency(1.0f);
//        System.out.println(aFloat + "");

        TipoCambio tipoCambio = scrapingYahoo.ObtenTipoDeCambio(PaisCambio.USD, PaisCambio.MXN);


    }

    public TipoCambio ObtenTipoDeCambio(PaisCambio from, PaisCambio to) {
        String urlPage = String.format(urlParam, from, to);

        // Compruebo si me da un 200 al hacer la petición
        if (getStatusConnectionCode(urlPage) == 200) {

            // Obtengo el HTML de la web en un objeto Document2
            Document document = getHtmlDocument(urlPage);

            // Busco todas las historias de meneame que estan dentro de:
            //Elements entradas = document.select("a[href] p");
            Elements entradas = document.select("");


            // Paseo cada una de las entradas
            for (Element elem : entradas) {
                String comandoP = elem.select("p").text();
                System.out.println("\nTitulo: " + comandoP);
            }

        } else {
            System.out.println("El Status Code no es OK es: " + getStatusConnectionCode(urlPage));
        }
        return null;
}

    public TipoCambio obtenTipoDeCambio(Date date) {
        List<TipoCambio> tipoCambios = listTipoCambio(date);
        for (TipoCambio tipoCambio : tipoCambios) {
            if (tipoCambio.getaUnidad().equals("MXN"))
                return tipoCambio;
        }
        return null;
    }

    public List<TipoCambio> listTipoCambio(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String urlTemp = urlDate + simpleDateFormat.format(date);
        List<TipoCambio> tipoCambios = new ArrayList<TipoCambio>();

        if (getStatusConnectionCode(urlTemp) == 200) {

            JSONObject jsonObject = new JSONObject(getStringJson(urlTemp));
            JSONArray jsonArray = jsonObject.getJSONObject("list").getJSONArray("resources");

            for (int i = 0; i < jsonArray.length(); i++) {
                TipoCambio tipoCambio = new TipoCambio();

                JSONObject jsonObj = jsonArray.getJSONObject(i).getJSONObject("resource").getJSONObject("fields");
                tipoCambio.setDeUnidad("USD");
                tipoCambio.setaUnidad(jsonObj.getString("symbol").replace("=X", ""));
                tipoCambio.setValor(jsonObj.getString("price"));
                tipoCambios.add(tipoCambio);
            }
        } else {
            System.out.println("El Status Code no es OK es: " + getStatusConnectionCode(urlTemp));
        }
        return tipoCambios;
    }

    private String getStringJson(String url1) {
        // Obtengo el HTML de la web en un objeto Document
        Document document = getHtmlDocument(url1);
        //Obtenemos el json que se va a consultar.
        String jsonmod = document.toString();
        for (String palabra : palabrasAQuitar) {
            jsonmod = jsonmod.replace(palabra, "");
        }
        jsonmod = jsonmod.trim();
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
