package com.jfrogz.apps;

import javax.ejb.Stateless;
import javax.mail.internet.ContentType;
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
       // String output = "Mensaje del Get: " + datosJson.getMensaje();
        DatosJsonResponse datosJsonResponse = new DatosJsonResponse();
        datosJsonResponse.setMensaje(datosJsonRequest.getMensaje());
        return datosJsonResponse;
    }

}
