package com.jfrogz.app;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;



@Path("/hello")

public class HelloWorld {

    @GET
    @Path("/{param}")
    public Response getMsg(@PathParam("param") String msg) {

        String output = "Jersey say : " + msg;

        return Response.status(200).entity(output).build();

    }

    @POST
    @Path("/pruebajson")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response pruebaJson (DatosPrueba datosPrueba){
        String output = "El nombre ingresado es: " + datosPrueba.nombre;
        return Response.status(200).entity(output).build();
    }
}