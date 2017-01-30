package com.jfrogz.apps;

import javax.ejb.Stateless;
<<<<<<< HEAD
=======
import javax.mail.internet.ContentType;
>>>>>>> ServiciosRest
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/hello")
public class HolaMundo {

    @GET
    @Path("/{param}")
    public Response getMensaje (@PathParam("param") String mensaje){
        String output = "Mensaje del Get: " + mensaje;
        return Response.status(200).entity(output).build();
    }

    @POST
    @Path("/mensaje")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DatosJsonResponse setMessage (DatosJsonRequest datosJsonRequest){
<<<<<<< HEAD
        // String output = "Mensaje del Get: " + datosJson.getMensaje();
=======
       // String output = "Mensaje del Get: " + datosJson.getMensaje();
>>>>>>> ServiciosRest
        DatosJsonResponse datosJsonResponse = new DatosJsonResponse();
        datosJsonResponse.setMensaje(datosJsonRequest.getMensaje());
        return datosJsonResponse;
    }

<<<<<<< HEAD
    @POST
    @Path("/frutas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DatosJsonResponse setMessageFrutas (DatosJsonRequest datosJsonRequest){
        DatosJsonResponse datosJsonResponse = new DatosJsonResponse();
        datosJsonResponse.setListFrutas(datosJsonRequest.getListFrutas());
        datosJsonResponse.setValor(datosJsonRequest.getParametro().getParametro2().getValor());
        return datosJsonResponse;
    }

=======
>>>>>>> ServiciosRest
}
