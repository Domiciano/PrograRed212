package services;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import model.Message;
import model.Venue;
import provider.VenueProvider;
import sql.SQLAdmin;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("venues")
public class VenueServices {

    @OPTIONS
    @Path("getvenues")
    public Response optionsGetVenues() {
        return Response.status(200)
                .header("access-control-allow-origin", "*")
                .header("access-control-allow-methods", "*")
                .header("access-control-allow-headers", "*")
                .header("Connection", "close")
                .build();
    }

    @POST
    @Path("addVenue")
    @Consumes("application/json")
    public Response addVenue(Venue venue) {

        try {
            VenueProvider provider = new VenueProvider();
            provider.insert(venue);
            return Response.status(200)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .entity(new Message("Sede creada correctamente")).build();
        } catch (SQLException ex) {
            SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .entity(new Message(ex.getMessage())).build();
        }
    }


    @GET
    @Path("getvenues")
    @Produces("application/json")
    public Response getVenues() {
        try {
            VenueProvider provider = new VenueProvider();
            ArrayList<Venue> response = provider.getData();
            return Response.status(200).header("access-control-allow-origin", "*").header("Connection", "close").entity(response).build();
        } catch (SQLException ex) {
            SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500).header("access-control-allow-origin", "*").header("Connection", "close").entity(new Message(ex.getMessage())).build();
        }
    }

    @GET
    @Path("getvenues/{cityId}")
    @Produces("application/json")
    public Response getVenuesByCity(@PathParam("cityId") int cityId) {

        try {
            VenueProvider provider = new VenueProvider();
            ArrayList<Venue> venues = provider.getData(cityId);
            return Response.status(200).header("access-control-allow-origin", "*").entity(venues)
                    .build();
        } catch (SQLException e) {
            SQLAdmin.getInstance().closeAllConnections();
            e.printStackTrace();
            return Response.status(500)
                    .entity(new Message("Operacion fallida"))
                    .header("access-control-allow-origin", "*")
                    .build();
        }
    }


    @OPTIONS
    @Path("getvenues/{cityId}")
    public Response OptionsGetVenuesByCity(@PathParam("cityId")int cityId){
        return Response.status(200)
                .header("access-control-allow-origin", "*")
                .header("access-control-allow-methods", "*")
                .header("access-control-allow-headers", "*")
                .build();
    }

    @DELETE
    @Path("deletevenue/{venueid}")
    @Produces("application/json")
    public Response deleteVenue(@PathParam("venueid") int venueID){
        try {
            VenueProvider provider = new VenueProvider();
            provider.delete(venueID);
            return Response.ok(new Message("Sede eliminada exitosamente"))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Content-Type", "application/json")
                    .build();
        } catch (SQLException e) {
            SQLAdmin.getInstance().closeAllConnections();
            e.printStackTrace();
            return Response.status(500)
                    .entity(new Message("Operacion fallida"))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Content-Type", "application/json")
                    .build();
        }
    }

    @PUT
    @Path("editvenue")
    @Produces("application/json")
    public Response editVenue(Venue venue){
        try {
            VenueProvider provider = new VenueProvider();
            provider.edit(venue);
            return Response.ok(new Message("Sede actualizada"))
                    .header("Content-Type", "application/json")
                    .header("access-control-allow-origin", "*")
                    .build();
        } catch (SQLException ex) {
            SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500).header("access-control-allow-origin", "*").entity(new Message(ex.getMessage())).build();
        }
    }


}
