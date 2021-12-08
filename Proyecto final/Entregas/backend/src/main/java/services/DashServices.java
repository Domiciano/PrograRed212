package services;

import provider.DashProvider;
import sql.SQLAdmin;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

@Stateless
@Path("dash")
public class DashServices {
    private final DashProvider provider = new DashProvider();

    @GET
    @Path("echo")
    public String echo(){
        return "echo";
    }

    @GET
    @Path("cities")
    public Response cities(){
        try {
            ArrayList<String> res = provider.citiesNames();
            return Response.status(200).header("access-control-allow-origin", "*").entity(res).build();
        } catch (SQLException e) {
            e.printStackTrace();
            //SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500).header("access-control-allow-origin", "*").entity(e).build();
        }
    }

    @OPTIONS
    @Path("card/{city}-{year}")
    public Response options(@PathParam("city") String city, @PathParam("year") boolean isYear){
        return Response.status(200)
                .header("access-control-allow-origin", "*")
                .header("access-control-allow-methods", "*")
                .header("access-control-allow-headers", "*")
                .build();
    }

    @GET
    @Path("card/{city}/{year}")
    public Response earnings(@PathParam("city") String city, @PathParam("year") boolean isYear){
        try {
            int res = provider.earnings(city,isYear);
            return Response.status(200).header("access-control-allow-origin", "*").entity(res).build();
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500).header("access-control-allow-origin", "*").entity(e).build();
        }
    }

    @GET
    @Path("occupation/{city}")
    public Response occupation(@PathParam("city") String city){
        try {
            int res = provider.occupation(city);
            return Response.status(200).header("access-control-allow-origin", "*").entity(res).build();
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500).header("access-control-allow-origin", "*").entity(e).build();
        }
    }

    @GET
    @Path("clientStatusData")
    public Response clientStatusData(){
        try {
            ArrayList<Integer> res = provider.clientStatusData();
            return Response.status(200).header("access-control-allow-origin", "*").entity(res).build();
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500).header("access-control-allow-origin", "*").entity(e).build();
        }
    }

    @GET
    @Path("clientStatusLabels")
    public Response clientStatusLabels(){
        try {
            ArrayList<String> res = provider.clientStatusLabels();
            return Response.status(200).header("access-control-allow-origin", "*").entity(res).build();
        } catch (NullPointerException e) {
            e.printStackTrace();
            SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500).header("access-control-allow-origin", "*").entity(e).build();
        }
    }

    @GET
    @Path("planStatusData")
    public Response planStatusData(){
        try {
            ArrayList<Integer> res = provider.planStatusData();
            return Response.status(200).header("access-control-allow-origin", "*").entity(res).build();
        } catch ( SQLException e) {
            e.printStackTrace();
            SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500).header("access-control-allow-origin", "*").entity(e).build();
        }
    }

    @GET
    @Path("planStatusLabels")
    public Response planStatusLabels(){
        try {
            ArrayList<String> res = provider.planStatusLabels();
            return Response.status(200).header("access-control-allow-origin", "*").entity(res).build();
        } catch (NullPointerException e) {
            e.printStackTrace();
            SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500).header("access-control-allow-origin", "*").entity(e).build();
        }
    }

    @GET
    @Path("client-ages")
    public Response clientsByAge(){
        try {
            ArrayList<Integer> res = provider.clientsByAge();
            return Response.status(200).header("access-control-allow-origin", "*").entity(res).build();
        } catch ( SQLException e) {
            e.printStackTrace();
            SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500).header("access-control-allow-origin", "*").entity(e).build();
        }
    }

    @GET
    @Path("monthly-earnings")
    public Response monthlyEarnings(){
        try {
            ArrayList<Integer> res = provider.monthlyEarnings();
            return Response.status(200).header("access-control-allow-origin", "*").entity(res).build();
        } catch ( SQLException e) {
            e.printStackTrace();
            SQLAdmin.getInstance().closeAllConnections();
            return Response.status(500).header("access-control-allow-origin", "*").entity(e).build();
        }
    }
}
