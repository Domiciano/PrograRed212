package services;

import model.Role;
import provider.RoleProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("rs")
public class RoleServices {

    @Path("echo")
    @GET
    public String echo(){
        return "echo role";
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

    @Path("updateName")
    @POST
    public Response updateName(String oldName, String newName){
        try {
            RoleProvider provider = new RoleProvider();
            String op = provider.updateName(oldName, newName);
            return Response.status(200).entity(op).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @Path("updateDesc")
    @POST
    public Response updateDesc(String oldDesc, String newDesc){
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
