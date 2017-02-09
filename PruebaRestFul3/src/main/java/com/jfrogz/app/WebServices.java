package com.jfrogz.app;


import com.jfrogz.response.MensajeResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/pruebas")
public class WebServices {

    @GET
    @Path("/default")
    @Produces(MediaType.APPLICATION_JSON)
    public MensajeResponse getMessageDefault (){
        MensajeResponse mensajeResponse = new MensajeResponse();
        mensajeResponse.setMensaje("Hola Mundo");
        return mensajeResponse;
    }

    @GET
    @Path("/custom/{mensaje}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public MensajeResponse getMensajePersonalizado (@PathParam("mensaje") String mensajeObtenido){
        MensajeResponse mensajeResponse = new MensajeResponse();
        mensajeResponse.setMensaje(mensajeObtenido);
        return mensajeResponse;
    }

}
