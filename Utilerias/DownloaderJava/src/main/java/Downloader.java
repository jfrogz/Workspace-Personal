import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Fernando Robles on 17/04/2017.
 */
public class Downloader {


    public static void main(String[] args) {
//        String url = "http://i.imgur.com/HZ1hq.jpg";
//        String name = "lineaDeCodigo.jpg";
        String url = "https://wallpapersite.com/images/pages/pic_w/6227.jpg";
        String name = "Widowmaker.jpg";
        String folder = "descargas/";

        File dir = new File(folder);
        if(!dir.exists())
        {
            if(!dir.mkdir())
                return;
        }

        try {
            File file = new File(folder + name);
            URLConnection conn = new URL(url).openConnection();
            conn.connect();

            System.out.println("\nempezando descarga: \n");
            System.out.println(">> URL: " + url);
            System.out.println(">> Nombre: " + name);
            System.out.println(">> Tamaño: " + conn.getContentLength() + " bytes");

            InputStream in = conn.getInputStream();
            OutputStream out = new FileOutputStream(file);

            int b =0;
            while (b!= -1){
                b = in.read();
                if(b!= -1){
                    out.write(b);
                }
            }

            out.close();
            in.close();
        } catch (MalformedURLException e) {
            System.out.println("la url: " + url + " no es valida!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception exp){
            System.out.println(exp);
        }
        System.exit(0);
    }
}
