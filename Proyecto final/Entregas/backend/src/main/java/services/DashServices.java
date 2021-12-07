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

@Stateless
@Path("dash")
public class DashServices {
    private final DashProvider provider = new DashProvider();

    @GET
    @Path("echo")
    public String echo(){
        return "echo";
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
}
