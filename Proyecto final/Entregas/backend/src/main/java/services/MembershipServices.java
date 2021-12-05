package services;

import model.Membership;
import model.Message;
import provider.MembershipProvider;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("ms")
public class MembershipServices {

    @GET
    @Path("echo")
    public String echo(){
        return "echo membership";
    }

    @OPTIONS
    @Path("addmembership")
    public Response options(Membership membership){
        return Response.status(200)
                .header("access-control-allow-origin", "*")
                .header("access-control-allow-methods", "*")
                .header("access-control-allow-headers", "*")
                .build();
    }

    @OPTIONS
    @Path("searchmembership")
    public Response optionsSearch(Membership membership){
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("access-control-allow-methods", "*")
                .header("access-control-allow-headers", "*")
                .build();
    }

    @POST
    @Path("addmembership")
    @Consumes("application/json")
    public Response addMembership(Membership membership){
        try {
            MembershipProvider provider = new MembershipProvider();
            provider.insert(membership);
            return Response.status(200)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .entity(new Message("membresia creada correctamente")).build();
        } catch (SQLException e) {
            return Response.status(500)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .entity(new Message(e.getMessage())).build();
        }
    }

    @GET
    @Path("getmemberships")
    @Produces("application/json")
    public Response getList(){
        MembershipProvider provider = new MembershipProvider();
        try {
            ArrayList<Membership> memberships = provider.getData();
            return Response.status(200).header("access-control-allow-origin", "*").entity(memberships).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(500).header("access-control-allow-origin", "*").entity(new Message(e.getMessage())).build();
        }
    }

    @GET
    @Path("searchmembership/{memshipID}")
    @Produces("application/json")
    public Response getMembershipsByMemId(@PathParam("memshipID") int memshipID){
        try {
            MembershipProvider provider = new MembershipProvider();
            ArrayList<Membership> memberships = provider.searchMembershipByMemID(memshipID);
            return Response.status(200).header("access-control-allow-origin", "*").entity(memberships).build();
        } catch (SQLException ex) {
            return Response.status(500).header("access-control-allow-origin", "*").entity(new Message(ex.getMessage())).build();
        }
    }


}
