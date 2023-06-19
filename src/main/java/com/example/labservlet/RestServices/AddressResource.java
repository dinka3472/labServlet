package com.example.labservlet.RestServices;
import com.example.labservlet.ServiceBeanses.SelectBean;
import com.example.labservlet.ServiceBeanses.UpdateBean;
import com.example.labservlet.models.entitys.Address;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;


@Path("/addresses")
public class AddressResource {
    @Inject
    private UpdateBean updateBean;
    @Inject
    private SelectBean selectBean;


    @DELETE
    @Path("/{id}")
    public Response deleteAddress(@PathParam("id") Integer id) {
        updateBean.deleteAddress(id);
        return Response.ok("Client deleted").build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAddresses() {
        return Response.ok().entity(selectBean.getAllAddresses()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAddress(@PathParam("id") Integer id) {
        return Response.ok().entity(selectBean.getAddressById(id)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAddress(Address address) {
        return Response.ok().entity(updateBean.updateAddress(address)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveAddress(Address address) {
        updateBean.createAddress(address);
        return Response.ok("Address saved").build();
    }
}
