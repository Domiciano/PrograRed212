package services;

import entity.Message;
import entity.User;
import provider.UserProvider;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("users")
@Stateless
public class UserService {

    @GET
    @Path("index")
    public String index(){
        return "index";
    }

    @OPTIONS
    @Path("create")
    public Response options(User user){
        return Response.status(200)
                .header("access-control-allow-origin", "*")
                .header("access-control-allow-methods", "*")
                .header("access-control-allow-headers", "*")
                .build();
    }

    @POST
    @Path("create")
    @Consumes("application/json")
    public Response create(User user){
        try {
            UserProvider provider = new UserProvider();
            provider.create(user);
            return Response.status(200)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .entity(new Message("Usuario creado correctamente")).build();
        }catch (ClassNotFoundException | SQLException ex){
            return Response.status(500)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .entity(new Message(ex.getMessage())).build();
        }

    }

    @GET
    @Path("getall")
    @Produces("application/json")
    public Response getAll(){
        try {
            UserProvider provider = new UserProvider();
            ArrayList<User> users = provider.getAll();
            return Response.status(200).header("access-control-allow-origin", "*").entity(users).build();
        } catch (SQLException | ClassNotFoundException ex) {
            return Response.status(500).header("access-control-allow-origin", "*").entity(new Message(ex.getMessage())).build();
        }

    }




}
