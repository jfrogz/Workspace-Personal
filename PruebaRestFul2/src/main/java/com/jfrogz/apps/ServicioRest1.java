package com.jfrogz.apps;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Fernando Robles on 30/01/2017.
 */
@Path("/mensajes")
public class ServicioRest1 {

    @Path("hello")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HelloResponse getHelloResponse (){
        HelloResponse helloResponse = new HelloResponse();
        helloResponse.setMensaje("Hola mundo");
        return helloResponse;
    }

    @Path("/hello/{parametro}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HelloResponse getHelloResponseDinamico (@PathParam("parametro") String mensajePersonal){
        HelloResponse helloResponse = new HelloResponse();
        helloResponse.setMensaje("Hola mundo, este es tu mensaje: \n" + mensajePersonal);
        return helloResponse;
    }

    @Path("/nombreCompleto/{nombre}-{apellidoPaterno}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HelloResponse getHelloResponseDinamico (@PathParam("nombre") String nombre, @PathParam ("apellidoPaterno") String apellidoPaterno){
        HelloResponse helloResponse = new HelloResponse();
        helloResponse.setMensaje("Tu nombre es: " + apellidoPaterno.toUpperCase() + ", " + nombre.toUpperCase());
        return helloResponse;
    }

    
}
