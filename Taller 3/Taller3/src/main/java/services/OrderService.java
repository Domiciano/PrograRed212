package services;


import entity.Message;
import entity.Order;
import entity.Product;
import provider.OrderProvider;
import provider.ProductProvider;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("orders")
@Stateless
public class OrderService {

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
    public Response create(Order order){
        try {
            OrderProvider provider = new OrderProvider();
            provider.create(order);
            return Response.status(200)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .entity(new Message("Orden creado correctamente")).build();
        }catch (ClassNotFoundException | SQLException ex){
            return Response.status(500)
                    .header("access-control-allow-origin", "*")
                    .header("access-control-allow-methods", "*")
                    .header("access-control-allow-headers", "*")
                    .entity(new Message(ex.getMessage())).build();
        }

    }

    @GET
    @Path("orderbyid/{orderid}")
    @Produces("application/json")
    public Response getOrderByID(@PathParam("orderid") int orderId){
        try {
            OrderProvider provider = new OrderProvider();
            ArrayList<Order> orders = provider.getOrderByID(orderId);
            return Response.status(200).header("access-control-allow-origin", "*").entity(orders).build();
        } catch (SQLException | ClassNotFoundException ex) {
            return Response.status(500).header("access-control-allow-origin", "*").entity(new Message(ex.getMessage())).build();
        }
    }

    @GET
    @Path("getall")
    @Produces("application/json")
    public Response getAll(){
        try {
            OrderProvider provider = new OrderProvider();
            ArrayList<Order> orders = provider.getAll();
            return Response.status(200).header("access-control-allow-origin", "*").entity(orders).build();
        } catch (SQLException | ClassNotFoundException ex) {
            return Response.status(500).header("access-control-allow-origin", "*").entity(new Message(ex.getMessage())).build();
        }
    }

    @GET
    @Path("orderbyuser/{usernatid}")
    @Produces("application/json")
    public Response getOrdersByUser(@PathParam("usernatid") String userNatId){
        try {
            OrderProvider provider = new OrderProvider();
            ArrayList<Order> orders = provider.getOrdersByUserNatID(userNatId);
            return Response.status(200).header("access-control-allow-origin", "*").entity(orders).build();
        } catch (SQLException | ClassNotFoundException ex) {
            return Response.status(500).header("access-control-allow-origin", "*").entity(new Message(ex.getMessage())).build();
        }
    }

    @GET
    @Path("status/{orderid}")
    @Produces("application/json")
    public Response changeOrderStatus(@PathParam("orderid") int orderID){
        try {
            OrderProvider provider = new OrderProvider();
            provider.orderStatus(orderID);
            return Response.ok(new Message("Orden pagada"))
                    .header("Content-Type", "application/json")
                    .header("access-control-allow-origin", "*")
                    .build();
        } catch (SQLException | ClassNotFoundException ex) {
            return Response.status(500).header("access-control-allow-origin", "*").entity(new Message(ex.getMessage())).build();
        }
    }

    @POST
    @Path("addpdt/{orderid}/{productid}/{quantity}")
    @Produces("application/json")
    public Response addProductToOrder(@PathParam("orderid") int orderID, @PathParam("productid") int productID, @PathParam("quantity") int quantity){
        try {
            OrderProvider provider = new OrderProvider();
            provider.addProductToOrder(orderID, productID, quantity);
            return Response.ok(new Message("Producto agregado a la orden exitosamente"))
                    .header("Content-Type", "application/json")
                    .header("access-control-allow-origin", "*")
                    .build();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(500)
                    .entity(new Message("Operacion fallida"))
                    .header("Content-Type", "application/json")
                    .header("access-control-allow-origin", "*")
                    .build();
        }
    }

    @DELETE
    @Path("deletepdt/{orderid}/{productid}/{quantityrest}")
    @Produces("application/json")
    public Response deleteProduct(@PathParam("orderid") int orderID, @PathParam("productid") int productID, @PathParam("quantityrest") int quantityRest){
        try {
            OrderProvider provider = new OrderProvider();
            provider.deleteProductFromOrder(orderID, productID, quantityRest);
            return Response.ok(new Message("Producto eliminado o cantidad modificado exitosamente"))
                    .header("Content-Type", "application/json")
                    .header("access-control-allow-origin", "*")
                    .build();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(500)
                    .entity(new Message("Operacion fallida"))
                    .header("Content-Type", "application/json")
                    .header("access-control-allow-origin", "*")
                    .build();
        }
    }



}
