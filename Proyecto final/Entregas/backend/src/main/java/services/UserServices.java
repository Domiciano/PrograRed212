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
    @Path("{natId}-{name}-{lastName}-{venuesBuddyID}")
    @Produces("application/json")
    public Response getList(@PathParam("natId") String natId, @PathParam("name") String name,@PathParam("lastName") String lastName, @PathParam("venuesBuddyID") String venueBuddyID){
        try {
            ArrayList<User> res = provider.getData(natId, name, lastName,venueBuddyID);
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
    @Path("{natId}-{name}-{lastName}-{venuesBuddyID}")
    public Response options(@PathParam("natId") String natId, @PathParam("name") String name,@PathParam("lastName") String lastName, @PathParam("venuesBuddyID") String venueBuddyID){
        return Response.status(200)
                .header("access-control-allow-origin", "*")
                .header("access-control-allow-methods", "*")
                .header("access-control-allow-headers", "*")
                .build();
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

}
