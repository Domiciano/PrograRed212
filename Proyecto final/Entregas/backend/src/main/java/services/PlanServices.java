package services;

import com.google.gson.Gson;
import model.Message;
import model.Plan;
import provider.PlanProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("ps")
public class PlanServices {

    @Path("echo")
    @GET
    public String echo(){return "echo Plan";}

    @Path("getAll")
    @GET
    @Produces("application/json")
    public javax.ws.rs.core.Response getAllRoles(){
        try {
            PlanProvider provider = new PlanProvider();
            ArrayList<Plan> op = provider.getAllPlans();
            Gson gson = new Gson();
            String list = gson.toJson(op);
            return javax.ws.rs.core.Response.status(200).entity(list).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return javax.ws.rs.core.Response.status(500).entity(e).build();
        }
    }

    @Path("insert")
    @POST
    @Consumes("application/json")
    public javax.ws.rs.core.Response insertRole(Plan plan){
        try {
            PlanProvider provider = new PlanProvider();
            provider.insert(plan);
            return javax.ws.rs.core.Response.status(200).entity(new Message("Plan inserted")).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return javax.ws.rs.core.Response.status(500).entity(e).build();
        }
    }

    @Path("update")
    @PUT
    @Consumes("application/json")
    public javax.ws.rs.core.Response update(Plan plan){
        try {
            PlanProvider provider = new PlanProvider();
            provider.update(plan);
            return javax.ws.rs.core.Response.status(200).entity(new Message("Plan updated")).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return javax.ws.rs.core.Response.status(500).entity(e).build();
        }
    }

    @Path("delete")
    @DELETE
    @Consumes("application/json")
    public javax.ws.rs.core.Response delete(Plan plan){
        try {
            PlanProvider provider = new PlanProvider();
            provider.delete(plan);
            return javax.ws.rs.core.Response.status(200).entity(new Message("Plan deleted")).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
}
