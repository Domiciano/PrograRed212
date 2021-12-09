package services;
import model.Card;
import model.Client;
import model.Message;
import provider.ClientProvider;
import sql.SQLAdmin;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
@Path("cls")
public class ClientServices {
    @GET
    @Path("close")
    public Response closeConnections(){
        SQLAdmin.getInstance().closeAllConnections();
        return Response.status(200)
                .header("access-control-allow-origin", "*")
                .header("access-control-allow-methods", "*")
                .header("access-control-allow-headers", "*")
                .header("Connection", "close")
                .entity(new Message("Conexiones cerradas desde cliente")).build();
    }

    @OPTIONS
    @Path("close")
    public Response optionsClose(){
        return Response.status(200)
                .header("access-control-allow-origin", "*")
                .header("access-control-allow-methods", "*")
                .header("access-control-allow-headers", "*")
                .header("Connection", "close")
                .build();
    }


    @OPTIONS
    @Path("editclientstatusbyid/{natID}/{status}")
    public Response optionsEditStatusByNatId(@PathParam("natID") String natID, @PathParam("status") int status) {
        return Response.status(200)
                .header("access-control-allow-origin", "*")
                .header("access-control-allow-methods", "*")
                .header("access-control-allow-headers", "*")
                .build();
    }

    @OPTIONS
    @Path("searchclient/{natID}")
    public Response optionsSearch(@PathParam("natID") String natID){
        return Response.status(200)
                .header("access-control-allow-origin", "*")
                .header("access-control-allow-methods", "*")
                .header("access-control-allow-headers", "*")
                .build();
    }

    @OPTIONS
    @Path("addClient")
    public Response optionsAddClient(Client client) {
        return Response.status(200)
                .header("access-control-allow-origin", "*")
                .header("access-control-allow-methods", "*")
                .header("access-control-allow-headers", "*")
                .header("Content-Type", "application/json")
                .header("Connection", "close")
                .build();
    }

    @OPTIONS
    @Path("filter/{natID}/{name}/{lastName}/{age}/{plan}/{status}")
    public Response optionsFilter(@PathParam("natID") String natID, @PathParam("name") String name,
                                  @PathParam("lastName") String lastName, @PathParam("age") String age,
                                  @PathParam("plan") String plan, @PathParam("status") String status){
        return Response.status(200)
        .header("access-control-allow-origin", "*")
                .header("access-control-allow-methods", "*")
                .header("access-control-allow-headers", "*")
                .header("Content-Type", "application/json")
                .build();
    }


    @GET
    @Path("getclients")
    @Produces("application/json")
    public Response getList(){
        try {
            ClientProvider provider = new ClientProvider();
            ArrayList<Client> clients = provider.getData();
            return Response.status(200).header("access-control-allow-origin", "*").entity(clients).build();
        } catch (SQLException e) {
            e.printStackTrace();
            SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500).header("access-control-allow-origin", "*").entity(new Message(e.getMessage())).build();
        }
    }

    @POST
    @Path("addClient")
    @Consumes("application/json")
    public Response addClient(Client client){
        try {
        ClientProvider provider =  new ClientProvider();
            provider.insert(client);
            return Response.status(200)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .header("Content-Type", "application/json")
                    .header("Connection", "close")
                    .entity(new Message("cliente creado correctamente")).build();
        } catch (SQLException e) {
            SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .header("Connection", "close")
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
            SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .entity(new Message(e.getMessage())).build();
        }
    }

    @PUT
    @Path("editclientstatusbyid/{natID}/{status}")
    @Produces("application/json")
    public Response editStatusByNatId(@PathParam("natID") String natID, @PathParam("status") int status) {
        try {
            ClientProvider provider = new ClientProvider();
            provider.editStatusByNatId(natID, status);
            return Response.status(200)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .entity(new Message("Estado del cliente ha sido cambiado")).build();
        } catch (SQLException e) {
            SQLAdmin.getInstance().closeAllConnections();
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
            SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .entity(new Message(e.getMessage())).build();
        }
    }

    @GET
    @Path("searchclient/{natID}")
    @Produces("application/json")
    public Response getByNatId(@PathParam("natID") String natID){
        try {
            ClientProvider provider = new ClientProvider();
            ArrayList<Client> client = provider.searchClient(natID);
            return Response.status(200).header("access-control-allow-origin", "*").entity(client).build();
        } catch (SQLException ex) {
            SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500).header("access-control-allow-origin", "*").entity(new Message(ex.getMessage())).build();
        }
    }

    @OPTIONS
    @Path("cardInfo/{natID}")
    public Response optionsCardInfo(@PathParam("natID") String natID){
        return Response.status(200)
                .header("access-control-allow-origin", "*")
                .header("access-control-allow-methods", "*")
                .header("access-control-allow-headers", "*")
                .build();
    }

    @GET
    @Path("cardInfo/{natID}")
    @Produces("application/json")
    public Response getCardInfo(@PathParam("natID") String natID){
        try {
            ClientProvider provider = new ClientProvider();
            ArrayList<Card> client = provider.getCardInfo(natID);
            return Response.status(200).header("access-control-allow-origin", "*").entity(client).build();
        } catch (SQLException ex) {
            SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500).header("access-control-allow-origin", "*").entity(new Message(ex.getMessage())).build();
        }
    }

    @GET
    @Path("filter/{natID}/{name}/{lastName}/{age}/{plan}/{status}")
    @Produces("application/json")
    public Response getClientFiltered(@PathParam("natID") String natID, @PathParam("name") String name,
                                      @PathParam("lastName") String lastName, @PathParam("age") String age,
                                      @PathParam("plan") String plan, @PathParam("status") String status){
        try {
            ClientProvider provider = new ClientProvider();
            ArrayList<Card> client = provider.filter(natID, name, lastName, age, plan, status);
            return Response.status(200).header("access-control-allow-origin", "*").entity(client).build();
        } catch (SQLException ex) {
            SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500).header("access-control-allow-origin", "*").entity(new Message(ex.getMessage())).build();
        }

    }
}
