package services;

import com.google.gson.Gson;
import model.Role;
import provider.RoleProvider;

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
            String op = provider.insert(role);
            return Response.status(200).entity(op).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @Path("updateName/{oldName}-{newName}")
    @POST
    public Response updateName(@PathParam("oldName") String oldName, @PathParam("newName") String newName){
        try {
            RoleProvider provider = new RoleProvider();
            String op = provider.updateName(oldName, newName);
            return Response.status(200).entity(op).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @Path("updateDesc/{oldDesc}-{newDesc}")
    @POST
    public Response updateDesc(@PathParam("oldDesc") String oldDesc, @PathParam("newDesc") String newDesc){
        try {
            RoleProvider provider = new RoleProvider();
            String op = provider.updateDescription(oldDesc, newDesc);
            return Response.status(200).entity(op).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @Path("updateDesc")
    @POST
    @Consumes("application/json")
    public Response delete(Role role){
        try {
            RoleProvider provider = new RoleProvider();
            String op = provider.delete(role);
            return Response.status(200).entity(op).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
}
