package services;

import model.*;
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
            return Response.status(200).header("access-control-allow-origin", "*").entity(res).build();
        } catch (SQLException e) {
            SQLAdmin.getInstance().closeAllConnections();
            e.printStackTrace();
            return Response.status(500).header("access-control-allow-origin", "*").entity(e).build();
        }
    }

    @GET
    @Path("{property}-{value}")
    @Produces("application/json")
    public Response getList(@PathParam("property") String property, @PathParam("value") String value){
        try {
            ArrayList<User> res = provider.getData(property, value);
            return Response.status(200).header("access-control-allow-origin", "*").entity(res).build();
        } catch (SQLException e) {
            SQLAdmin.getInstance().closeAllConnections();
            e.printStackTrace();
            return Response.status(500).header("access-control-allow-origin", "*").entity(e).build();
        }
    }

    @POST
    @Path("")
    @Consumes("application/json")
    public Response addUser(User user){
        try {
            String o = provider.insert(user);
            return Response.status(200)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*").entity(o).build();
        } catch (SQLException e) {
            SQLAdmin.getInstance().closeAllConnections();
            e.printStackTrace();
            return Response.status(500)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*").entity(e).build();
        }
    }

    @PUT
    @Path("")
    @Consumes("application/json")
    public Response updateUser(User user){
        try {
            String o = provider.update(user);
            return Response.status(200)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .entity(o).build();
        } catch (SQLException e) {
            SQLAdmin.getInstance().closeAllConnections();
            e.printStackTrace();
            return Response.status(500)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .entity(e).build();
        }
    }
    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") int id){
        try {
            String o = provider.delete(id);
            return Response.status(200)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .entity(o).build();
        } catch (SQLException e) {
            SQLAdmin.getInstance().closeAllConnections();
            e.printStackTrace();
            return Response.status(500)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .entity(e).build();
        }
    }

    @POST
    @Path("auth")
    @Consumes("application/json")
    @Produces("application/json")
    public Response auth(Auth auth){

        try {
            LogUser lu = provider.auth(auth);
            if( lu != null){
                return Response.status(200)
                        .header("access-control-allow-origin", "*")
                        .entity(lu).build();
            } else {
                return Response.status(200)
                        .header("access-control-allow-origin", "*")
                        .entity("no found").build();
            }
        } catch (SQLException e) {
            return Response.status(500)
                    .header("access-control-allow-origin", "*")
                    .entity(e).build();
        }
    }

    @OPTIONS
    @Path("{id}")
    public Response options(@PathParam("id") String id){
        return Response.status(200)
                .header("access-control-allow-origin", "*")
                .header("access-control-allow-methods", "*")
                .header("access-control-allow-headers", "*")
                .build();
    }

    @OPTIONS
    @Path("")
    public Response options(){
        return Response.status(200)
                .header("access-control-allow-origin", "*")
                .header("access-control-allow-methods", "*")
                .header("access-control-allow-headers", "*")
                .build();
    }

    @OPTIONS
    @Path("auth")
    public Response optionsAuth(){
        return Response.status(200)
                .header("access-control-allow-origin", "*")
                .header("access-control-allow-methods", "*")
                .header("access-control-allow-headers", "*")
                .build();
    }
    @GET
    @Path("cardInfo")
    @Produces("application/json")
    public Response getCardInfo(){
        try {
            UserProvider provider = new UserProvider();
            ArrayList<UserCard> managers = provider.getVenueManagerCardInfo();
            return Response.status(200).header("access-control-allow-origin", "*").entity(managers).build();
        } catch (SQLException ex) {
            SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500).header("access-control-allow-origin", "*").entity(new Message(ex.getMessage())).build();
        }
    }
}
