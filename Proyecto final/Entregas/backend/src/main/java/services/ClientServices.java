package services;
import model.Client;
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
            String o = provider.insert(client);
            return Response.status(200).entity(o).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
}
