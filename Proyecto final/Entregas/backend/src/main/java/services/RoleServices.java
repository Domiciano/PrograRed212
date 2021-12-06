package services;

import com.google.gson.Gson;
import model.Message;
import model.Role;
import provider.RoleProvider;
import sql.SQLAdmin;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("rs")
public class RoleServices {

    @Path("echo")
    @GET
    public String echo(){
        return "echo role";
    }

    @Path("getAll")
    @GET
    @Produces("application/json")
    public Response getAllRoles(){
        try {
            RoleProvider provider = new RoleProvider();
            ArrayList<Role> op = provider.getAllRoles();
            Gson gson = new Gson();
            String list = gson.toJson(op);
            return Response.status(200).entity(list).build();
        } catch (SQLException e) {
            SQLAdmin.getInstance().closeAllConnections();
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @Path("insert")
    @POST
    @Consumes("application/json")
    public Response insertRole(Role role){
        try {
            RoleProvider provider = new RoleProvider();
            provider.insert(role);
            return Response.status(200).entity(new Message("Role inserted")).build();
        } catch (SQLException e) {
            SQLAdmin.getInstance().closeAllConnections();
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @Path("update")
    @PUT
    @Consumes("application/json")
    public Response update(Role role){
        try {
            RoleProvider provider = new RoleProvider();
            provider.update(role);
            return Response.status(200).entity(new Message("Role updated")).build();
        } catch (SQLException e) {
            SQLAdmin.getInstance().closeAllConnections();
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @Path("delete")
    @DELETE
    @Consumes("application/json")
    public Response delete(Role role){
        try {
            RoleProvider provider = new RoleProvider();
            provider.delete(role);
            return Response.status(200).entity(new Message("Role deleted")).build();
        } catch (SQLException e) {
            SQLAdmin.getInstance().closeAllConnections();
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
}
