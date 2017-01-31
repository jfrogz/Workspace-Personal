package com.jfrogz.services;

import com.jfrogz.response.ListaFrutasResponse;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Fernando on 28/01/2017.
 */

@Path("frutas")
public class FurtaService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/lista")
    public ListaFrutasResponse getListaFrutas (){
        ListaFrutasResponse listaFrutasResponse = new ListaFrutasResponse();
    return listaFrutasResponse;
    }
}
