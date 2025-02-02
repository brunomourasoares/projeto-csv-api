package br.com.apicsv.controller;

import br.com.apicsv.model.User;
import br.com.apicsv.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Path("/data")
public class UserController {

    @Inject
    private UserService userService;

    @POST
    @Path("/import")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response importData(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("File path is required").build();
        }
        try {
            userService.importDataFromCSV(filePath);
            return Response.ok("CSV importado com sucesso!").build();
        } catch (FileNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Arquivo não encontrado: " + filePath).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao ler o arquivo: " + e.getMessage()).build();
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
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao criar o arquivo: " + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findAll() {
        return userService.findAll();
    }
}