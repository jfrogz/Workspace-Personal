package testclientews;

import clientews.servicio.ServicioSumarImplService;
import clientews.servicio.ServicioSumarWS;

public class TestServicioSumarWS {

    public static void main(String[] args) {
        ServicioSumarWS sumarWS = new ServicioSumarImplService().getServicioSumarImplPort();
        System.out.println("Ejecutando Servicio Sumar WS");

        int x = 1;
        int y = 2;

        System.out.println("Sumar: x: " + x + " + y: " + y);
        System.out.println("Resultado: " + sumarWS.sumar(x,y));
        System.out.println("Fin servicio sumar WS");
    }

}
