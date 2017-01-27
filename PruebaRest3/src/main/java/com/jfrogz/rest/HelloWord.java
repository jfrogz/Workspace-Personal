package com.jfrogz.rest;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
@Stateless
public class HelloWord {

    @GET
    @Path("/{param}")
    public Response getMessage (@PathParam("param") String mensaje){
        String output = "Jersey say : " + mensaje;
        return Response.status(200).entity(output).build();
    }


    @POST
    @Path("/mensajes")
    @Consumes({"application/json"})
    @Produces(MediaType.TEXT_PLAIN)
    public Response pruebaJson (DatosJson datosJson){
        String output = "Frtr say : " + datosJson.mensaje;
        return Response.status(200).entity(output).build();
    }
}
