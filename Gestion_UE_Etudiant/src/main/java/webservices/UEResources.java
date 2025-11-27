package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ue")
public class UEResources {

    static UniteEnseignementBusiness helper = new UniteEnseignementBusiness();

    // GET LIST
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.status(200).entity(helper.getListeUE()).build();
    }

    // ADD
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addUe(UniteEnseignement ue) {
        if (helper.addUniteEnseignement(ue)) {
            return Response.status(201).entity("object added successfully").build();
        }
        return Response.status(400).entity("object not added").build();
    }

    // UPDATE
    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUe(@PathParam("id") int id, UniteEnseignement ue) {
        ue.setCode(id); // BECH N7OTTOU L'ID
        if (helper.updateUniteEnseignement(id,ue)) {
            return Response.status(200).entity("object updated successfully").build();
        }
        return Response.status(400).entity("update failed").build();
    }

    // DELETE
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUe(@PathParam("id") int id) {
        if (helper.deleteUniteEnseignement(id)) {
            return Response.status(200).entity("object deleted successfully").build();
        }
        return Response.status(400).entity("delete failed").build();
    }
}
