package com.jfrogz.services;

import com.jfrogz.services.Identities.Carro;
import com.jfrogz.services.Request.CarroRequest;
import com.jfrogz.services.response.CarroResponse;
import com.jfrogz.services.response.MensajeResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Fernando Robles on 02/02/2017.
 */
@Path("/mensajes")
public class MensajesService {

    @GET
    @Path("/default")
    @Produces(MediaType.APPLICATION_JSON)
    public MensajeResponse getMensajeDefault (){
        MensajeResponse mensajeResponse = new MensajeResponse();
        mensajeResponse.setMensaje("Este es el mensaje por default");
        return mensajeResponse;
    }

    @POST
    @Path("/carro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CarroResponse getCarroNuevo(CarroRequest carroRequest){
        CarroResponse carroResponse = new CarroResponse();
        carroResponse.setFabricante(carroRequest.getFabricante());
        carroResponse.setModelo(carroRequest.getModelo());
        carroResponse.setAnio(carroRequest.getAnio());
        return carroResponse;
    }

}
