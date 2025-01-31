package br.com.apicsv.controller;

import br.com.apicsv.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/data")
public class UserController {

    @Inject
    private UserService userService;

    @POST
    @Path("/import")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response importData(String filePath) {
        try {
            userService.importDataFromCSV(filePath);
            return Response.ok("CSV importado com sucesso!").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/export")
    @Produces(MediaType.TEXT_PLAIN)
    public Response exportData(@QueryParam("filePath") String filePath) {
        try {
            userService.exportDataToTXT(filePath);
            return Response.ok("Dados exportados com sucesso!").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}