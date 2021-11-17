package services;

import model.Venue;
import provider.VenueProvider;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("venueS")
public class VenueServices {

    @GET
    @Path("echo")
    public String echo(){
        return "echo venue";
    }

    @GET
    @Path("venues")
    @Produces("application/json")
    public Response getList(){
        VenueProvider provider = new VenueProvider();
        try {
            ArrayList<City> res = provider.getData();
            return Response.status(200).entity(res).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @POST
    @Path("addVenue")
    @Consumes("application/json")
    public Response addVenue(Venue venue){
        VenueProvider provider = new VenueProvider();
        try {
            String o = provider.insert(city);
            return Response.status(200).entity(o).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @DELETE
    @Path("deleteVenue")
    @Consumes("application/json")
    public Response deleteVenueVenue venue){
        try {
            VenueProvider provider = new VenueProvider();
            String o = provider.delete(city);
            return Response.status(200).entity(o).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

}
