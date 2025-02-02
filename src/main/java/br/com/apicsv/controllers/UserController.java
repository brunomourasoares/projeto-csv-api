package br.com.apicsv.controllers;

import br.com.apicsv.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;

@Path("/data")
public class UserController {

    @Inject
    private UserService userService;

    @POST
    @Path("/import")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response importData(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Arquivo não encontrado: " + filePath).build();
        }
        try {
            userService.importDataFromCSV(filePath);
            return Response.ok("CSV importado com sucesso!").build();
        }
        catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao criar o arquivo: " + e.getMessage()).build();
        }
        catch (SQLException g) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao acessar o banco de dados: " + g.getMessage()).build();
        }
    }

    @GET
    @Path("/export")
    @Produces(MediaType.TEXT_PLAIN)
    public Response exportData(@QueryParam("filePath") String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Caminho do arquivo não pode ser vazio!").build();
        }
        try {
            userService.exportDataToTXT(filePath);
            return Response.status(Response.Status.OK).entity("Dados exportados com sucesso!").build();
        }
        catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao criar o arquivo: " + e.getMessage()).build();
        }
        catch (SQLException g) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao acessar o banco de dados: " + g.getMessage()).build();
        }
    }
}