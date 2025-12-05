package webservices;

import entities.Module;
import metiers.ModuleBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/module")
public class ModuleResources {
    ModuleBusiness helper=new ModuleBusiness();

    //getAll : WebService

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {

        return Response.status(200)
                .entity(helper.getAllModules())
                .build();
    }


    //ADD
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Module module) {

        if (helper.addModule(module)) {
            return Response.status(201)
                    .entity("Module created successfully")
                    .build();
        }

        return Response.status(400)
                .entity("Module already exists")
                .build();
    }

    //DELETE
    @Path("/delete/{matricule}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(@PathParam("matricule") String matricule) {

        helper.deleteModule(matricule);
        if (helper.deleteModule(matricule))
        {
            return Response.status(200)
                    .entity("Object deleted successfully")
                    .build();
        }
        else return Response.status(400).entity("Module not exists").build();
    }

    // UPDATE
    @Path("/update/{matricule}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("matricule") String matricule, Module updatedModule) {
        if (helper.updateModule(matricule, updatedModule)) {
            return Response.status(200)
                    .entity("Object updated successfully")
                    .build();
        } else {
            return Response.status(404)
                    .entity("Module not found")
                    .build();
        }
    }



}
