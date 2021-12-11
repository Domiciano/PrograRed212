package services;

import model.ClientState;
import provider.ClientStateProvider;
import sql.SQLAdmin;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("css")
public class ClientStateServices {

    @GET
    @Path("echo")
    public String echo(){return "echo";}

    @OPTIONS
    @Path("clientsState")
    public Response optionsClientState(){
        return Response.status(200)
                .header("access-control-allow-origin", "*")
                .header("access-control-allow-methods", "*")
                .header("access-control-allow-headers", "*")
                .build();
    }

    @GET
    @Path("clientsState")
    @Produces("application/json")
    public Response getClientsStateList(){
        ClientStateProvider provider = new ClientStateProvider();
        try {
            ArrayList<ClientState> res = provider.getData();
            return Response.status(200)
                    .header("access-control-allow-origin", "*")
                    .entity(res).build();
        } catch (SQLException e) {
            e.printStackTrace();
            SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500).entity(e)
                    .header("access-control-allow-origin", "*")
                    .build();
        }
    }

    @POST
    @Path("addClientState")
    @Consumes("application/json")
    public Response addClientState(ClientState clientS){
        ClientStateProvider provider =  new ClientStateProvider();
        try {
            String o = provider.insert(clientS);
            return Response.status(200).entity(o).build();
        } catch (SQLException e) {
            e.printStackTrace();
            SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500).entity(e).build();
        }
    }
}
