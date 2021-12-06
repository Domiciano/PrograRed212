package services;

import model.User;
import provider.UserProvider;
import sql.SQLAdmin;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;


@Stateless
@Path("users")
public class UserServices {

    private final UserProvider provider;

    public UserServices(){
        provider = new UserProvider();
    }

    @GET
    @Path("echo")
    public String echo(){
        return "echo";
    }

    @GET
    @Path("")
    @Produces("application/json")
    public Response getList(){
        try {
            ArrayList<User> res = provider.getData();
            return Response.status(200).entity(res).build();
        } catch (SQLException e) {
            SQLAdmin.getInstance().closeAllConnections();
            e.printStackTrace();
            return Response.status(200).entity(e).build();
        }
    }

    @GET
    @Path("{property}-{value}")
    @Produces("application/json")
    public Response getList(@PathParam("property") String property, @PathParam("value") String value){
        try {
            ArrayList<User> res = provider.getData(property, value);
            return Response.status(200).entity(res).build();
        } catch (SQLException e) {
            SQLAdmin.getInstance().closeAllConnections();
            e.printStackTrace();
            return Response.status(200).entity(e).build();
        }
    }

    @POST
    @Path("")
    @Consumes("application/json")
    public Response addUser(User user){
        try {
            String o = provider.insert(user);
            return Response.status(200).entity(o).build();
        } catch (SQLException e) {
            SQLAdmin.getInstance().closeAllConnections();
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PUT
    @Path("")
    @Consumes("application/json")
    public Response updateUser(User user){
        try {
            String o = provider.update(user);
            return Response.status(200).entity(o).build();
        } catch (SQLException e) {
            SQLAdmin.getInstance().closeAllConnections();
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    @DELETE
    @Path("")
    public Response deleteUser(int id){
        try {
            String o = provider.delete(id);
            return Response.status(200).entity(o).build();
        } catch (SQLException e) {
            SQLAdmin.getInstance().closeAllConnections();
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

}
