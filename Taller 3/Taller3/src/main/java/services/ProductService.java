package services;


import entity.Message;
import entity.Product;

import provider.ProductProvider;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("products")
@Stateless
public class ProductService {


    @GET
    @Path("index")
    public String index(){
        return "index";
    }

    @OPTIONS
    @Path("create")
    public Response options(Product product){
        return Response.status(200)
                .header("access-control-allow-origin", "*")
                .header("access-control-allow-methods", "*")
                .header("access-control-allow-headers", "*")
                .build();
    }

    @POST
    @Path("create")
    @Consumes("application/json")
    public Response create(Product product){
        try {
            ProductProvider provider = new ProductProvider();
            provider.create(product);
            return Response.status(200)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .entity(new Message("Producto creado correctamente")).build();
        }catch (ClassNotFoundException | SQLException ex){
            return Response.status(500)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .entity(new Message(ex.getMessage())).build();
        }

    }

    @GET
    @Path("getall")
    @Produces("application/json")
    public Response getAll(){
        try {
            ProductProvider provider = new ProductProvider();
            ArrayList<Product> products = provider.getAll();
            return Response.status(200).header("access-control-allow-origin", "*").entity(products).build();
        } catch (SQLException | ClassNotFoundException ex) {
            return Response.status(500).header("access-control-allow-origin", "*").entity(new Message(ex.getMessage())).build();
        }

    }
}
