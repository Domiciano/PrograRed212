package services;

import model.User;
import provider.UserProvider;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("db")
public class UserServices {
    @GET
    @Path("echo")
    public String echo(){
        return "echo";
    }

    @GET
    @Path("users")
    @Produces("application/json")
    public Response getList(){
        UserProvider provider = new UserProvider();
        try {
            ArrayList<User> res = provider.getData();
            return Response.status(200).entity(res).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(200).entity(e).build();
        }

    }

    @POST
    @Path("users")
    @Consumes("application/json")
    public Response addUser(User user){
        UserProvider privder =  new UserProvider();
        try {
            String o = privder.insert(user);
            return Response.status(200).entity(o).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
}
