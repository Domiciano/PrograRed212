package services;
import model.Client;
import model.Message;
import provider.ClientProvider;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
@Path("cls")
public class ClientServices {
    @GET
    @Path("echo")
    public String echo(){return "echo client";}

    @GET
    @Path("clients")
    @Produces("application/json")
    public Response getList(){
        ClientProvider provider = new ClientProvider();
        try {
            ArrayList<Client> res = provider.getData();
            return Response.status(200).entity(res).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @POST
    @Path("addClient")
    @Consumes("application/json")
    public Response addUser(Client client){
        ClientProvider provider =  new ClientProvider();
        try {
            provider.insert(client);
            return Response.status(200)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .entity(new Message("cliente creado correctamente")).build();
        } catch (SQLException e) {
            return Response.status(500)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .entity(new Message(e.getMessage())).build();
    }
    }
    @PUT
    @Path("editClient")
    @Produces("application/json")
    public Response edit(Client client) {
        try {
            ClientProvider provider = new ClientProvider();
            provider.edit(client);
            return Response.status(200)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .entity(new Message("cliente editado correctamente")).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .entity(new Message(e.getMessage())).build();
        }
    }
    @DELETE
    @Path("deleteClient/{clientId}")
    @Produces("application/json")
    public Response deleteClient(@PathParam("clientId") String natID){
        ClientProvider provider = new ClientProvider();
        try {
            provider.delete(natID);
            return Response.status(200)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .entity(new Message("cliente borrado correctamente")).build();
        } catch (SQLException e) {
            return Response.status(500)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .entity(new Message(e.getMessage())).build();
        }
    }
}
