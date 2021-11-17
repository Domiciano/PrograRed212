package services;

import model.Membership;
import provider.MembershipProvider;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("memShipS")
public class MembershipServices {

    @GET
    @Path("echo")
    public String echo(){
        return "echo membership";
    }

    @GET
    @Path("memberships")
    @Produces("application/json")
    public Response getList(){
        MembershipProvider provider = new MembershipProvider();
        try {
            ArrayList<City> res = provider.getData();
            return Response.status(200).entity(res).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @POST
    @Path("addMembership")
    @Consumes("application/json")
    public Response addMembership(Membership membership){
        MembershipProvider provider = new MembershipProvider();
        try {
            String o = provider.insert(city);
            return Response.status(200).entity(o).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
}
