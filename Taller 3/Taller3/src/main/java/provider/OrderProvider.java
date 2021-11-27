package provider;

import db.MySQL;
import entity.Order;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderProvider {

    public void create(Order order) throws ClassNotFoundException, SQLException {
        MySQL db = new MySQL();
        db.connect();
        String sql = "INSERT INTO ordersA00364415(orderDate, paid, paidDate, usersA00364415ID) VALUES ($ORDERDATE, $PAID, $PDATE, $USERID)";
        long orderTime = System.currentTimeMillis() / 1000L;
        sql = sql.replace("$ORDERDATE", "FROM_UNIXTIME("+orderTime+")");
        sql = sql.replace("$PAID", "FALSE");
        sql = sql.replace("$PDATE", "FROM_UNIXTIME("+1+")");
        sql = sql.replace("$USERID", order.getUserID()+"");

        db.commandSQL(sql);
    }

    public void addProductToOrder(int orderID, int productID, int quantity) throws ClassNotFoundException, SQLException {
        MySQL db = new MySQL();
        db.connect();
        String sql = "INSERT INTO orders_productsA00364415(ordersA00364415ID, productsA00364415ID, productLot) VALUES ($OID,$PID,$QTY)";
        sql = sql.replace("$OID", orderID+"");
        sql = sql.replace("$PID", productID+"");
        sql = sql.replace("$QTY", quantity+"");

        db.commandSQL(sql);
    }


    public ArrayList<Order> getAll() throws ClassNotFoundException, SQLException {
        MySQL db = new MySQL();
        db.connect();
        ArrayList<Order> orders = new ArrayList<>();
        ResultSet resultSet = db.getDataBySQL("SELECT * FROM ordersA00364415");
        while(resultSet.next()){
            //Hola profe, es cómico que aqui invoque a un constructor (el de 5 parámetros)
            // y en el json que se recibe en consola imprima una orden
            // con un constructor diferente, es decir, el más largo y deja los otros parámetros vacíos
            // (a no ser que siempre imprima el constructor más completo pero igual no debería suceder)
            //La información completa de la orden se debería de desplegar al momento de buscarla dado su id, el método está más abajo
            orders.add(new Order(
                    resultSet.getInt(1),
                    resultSet.getDate(2),
                    resultSet.getBoolean(3),
                    resultSet.getDate(4),
                    resultSet.getInt(5)
            ));
        }
        db.close();
        return orders;
    }

    public ArrayList<Order> getOrdersByUserNatID(String userNatID) throws SQLException, ClassNotFoundException {
        MySQL db = new MySQL();
        db.connect();

        ArrayList<Order> orders = new ArrayList<>();
        String sql1 = "SELECT * FROM usersA00364415 WHERE idNat = $NAT";
        sql1 = sql1.replace("$NAT", userNatID);
        ResultSet resultSet1 = db.getDataBySQL(sql1);
        resultSet1.next();

        int userTableID = resultSet1.getInt(1);
        String sql = "SELECT * FROM ordersA00364415 WHERE usersA00364415ID = '$USID'";
        sql = sql.replace("$USID", userTableID+"");

        ResultSet resultSet = db.getDataBySQL(sql);
        while(resultSet.next()){
            orders.add(new Order(
                    resultSet.getInt(1),
                    resultSet.getDate(2),
                    resultSet.getBoolean(3),
                    resultSet.getDate(4),
                    resultSet.getInt(5)
            ));
        }

        db.close();
        return orders;
    }

    public void orderStatus(int orderID) throws SQLException, ClassNotFoundException {
        MySQL db = new MySQL();
        db.connect();

        String sql = "UPDATE ordersA00364415 SET paid = TRUE  WHERE id = $OID";
        sql = sql.replace("$OID", orderID+"");
        db.commandSQL(sql);

        //Actualizar la fecha de pago
        String sql1 = "UPDATE ordersA00364415 SET paidDate = $PDATE WHERE id = $OID";
        long orderTime = System.currentTimeMillis() / 1000L;
        sql1 = sql1.replace("$OID", orderID+"");
        sql1 = sql1.replace("$PDATE", "FROM_UNIXTIME("+orderTime+")");

        //No alcancé a corregir el problema que al actualizar
        //sólo la fecha de pago tambien se está sobre escribiendo la fecha de creación de la orden
        db.commandSQL(sql1);
        db.close();

    }


    public void deleteProductFromOrder(int orderID, int productID, int quantityRest) throws ClassNotFoundException, SQLException {
        MySQL db = new MySQL();
        db.connect();

        String sql1 = "SELECT productLot FROM orders_productsA00364415 WHERE (ordersA00364415ID = $OID AND productsA00364415ID = $PID)";
        sql1 = sql1.replace("$OID", orderID+"");
        sql1 = sql1.replace("$PID", productID+"");
        ResultSet resultSet1 = db.getDataBySQL(sql1);
        resultSet1.next();
        int pquantity = resultSet1.getInt(1);

        String sql = "";

        if(pquantity <= quantityRest){
            sql = "DELETE FROM orders_productsA00364415 WHERE (ordersA00364415ID = $OID AND productsA00364415ID = $PID)";
            sql = sql.replace("$OID", orderID+"");
            sql = sql.replace("$PID", productID+"");
        } else {
            //Restar y actualizar la cantidad del producto
            sql = "UPDATE orders_productsA00364415 SET productLot = $RESULT  WHERE (ordersA00364415ID = $OID AND productsA00364415ID = $PID)";
            int result = pquantity-quantityRest;
            sql = sql.replace("$OID", orderID+"");
            sql = sql.replace("$PID", productID+"");
            sql = sql.replace("$RESULT", result+"");

        }

        db.commandSQL(sql);
        db.close();


    }

    public ArrayList<Order> getOrderByID(int orderId) throws ClassNotFoundException, SQLException {
        MySQL db = new MySQL();
        db.connect();

        ArrayList<Order> orders = new ArrayList<>();
        String sql = "SELECT ordersA00364415.id, usersA00364415.name, usersA00364415.id, usersA00364415.idNat, ordersA00364415.orderDate, ordersA00364415.paid, ordersA00364415.paidDate, orders_productsA00364415.productsA00364415ID, productsA00364415.productName, orders_productsA00364415.productLot, productsA00364415.price FROM ((ordersA00364415 INNER JOIN orders_productsA00364415 ON ordersA00364415.id = orders_productsA00364415.ordersA00364415ID) INNER JOIN productsA00364415 ON orders_productsA00364415.productsA00364415ID = productsA00364415.id) INNER JOIN usersA00364415 ON ordersA00364415.usersA00364415ID = usersA00364415.id WHERE ordersA00364415.id = $OID";
        sql = sql.replace("$OID", orderId+"");

        ResultSet resultSet = db.getDataBySQL(sql);
        while(resultSet.next()){
            double totalPrice = resultSet.getInt(10)*resultSet.getDouble(11);
            orders.add(new Order(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getDate(5),
                    resultSet.getBoolean(6),
                    resultSet.getDate(7),
                    resultSet.getInt(8),
                    resultSet.getString(9),
                    resultSet.getInt(10),
                    resultSet.getDouble(11),
                    totalPrice
            ));
        }

        db.close();
        return orders;
    }
}
