package services;

import model.City;
import provider.CityProvider;
import sql.SQLAdmin;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("cty")
public class CityServices {

    @GET
    @Path("echo")
    public String echo(){return "echo city";}


    @GET
    @Path("cities")
    @Produces("application/json")
    public Response getList(){
        CityProvider provider = new CityProvider();
        try {
            ArrayList<City> res = provider.getData();
            return Response.status(200).entity(res).build();
        } catch (SQLException e) {
            e.printStackTrace();
            SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500).entity(e).build();
        }
    }

    @POST
    @Path("addCity")
    @Consumes("application/json")
    public Response addCity(City city){
        CityProvider provider =  new CityProvider();
        try {
            String o = provider.insert(city);
            return Response.status(200).entity(o).build();
        } catch (SQLException e) {
            e.printStackTrace();
            SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500).entity(e).build();
        }
    }

    @DELETE
    @Path("deleteCity")
    @Consumes("application/json")
    public Response deleteCity(City city){
        try {
            CityProvider provider =  new CityProvider();
            String o = provider.delete(city);
            return Response.status(200).entity(o).build();
        } catch (SQLException e) {
            e.printStackTrace();
            SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500).entity(e).build();
        }
    }

}
