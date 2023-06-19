package com.example.labservlet.RestServices;

import com.example.labservlet.ServiceBeanses.SelectBean;
import com.example.labservlet.ServiceBeanses.UpdateBean;
import com.example.labservlet.models.entitys.Client;
import com.example.labservlet.validation.ClientValidator;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/clients")
public class ClientResource {
    @Inject
    private UpdateBean updateBean;
    @Inject
    private SelectBean selectBean;
    @Inject
    private ClientValidator clientValidator;


    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Integer id) {
        clientValidator.clientIsExistById(id);
        updateBean.deleteClient(id);
        return Response.ok("Client deleted").build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        return Response.ok().entity(selectBean.getAllClients()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id) {
       clientValidator.clientIsExistById(id);
       return Response.ok().entity(selectBean.getClientById(id)).build();
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(Client client) {
        clientValidator.clientIsExistById(client.getClientId());
        return Response.ok().entity(updateBean.updateClient(client)).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveUser(Client client) {
        updateBean.createClient(client);
        return Response.ok("Client saved").build();
    }
}
