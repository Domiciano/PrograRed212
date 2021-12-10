package sql;

import java.sql.*;

public class MySQL {

    private Connection connection;

     MySQL(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void connection() throws SQLException {

        //connection = DriverManager.getConnection("jdbc:mysql://200.3.193.22:3306/P09728_1_11","P09728_1_11","ZCSaQGZU");
        //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/icesi","root","");
        connection = DriverManager.getConnection("jdbc:mysql://200.3.193.22:3306/P09728_1_2","P09728_1_2","ajvRnEIa");
        //connection = DriverManager.getConnection("jdbc:mysql://200.3.193.22:3306/P09728_1_11","P09728_1_11","ZCSaQGZU");
        //connection = DriverManager.getConnection("jdbc:mysql://200.3.193.22:3306/P09728_1_6","P09728_1_6","p1OnJm69");
        //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/icesi","root","");

    }

    public void close() throws SQLException {
        connection.close();
        System.out.println("Conexión cerrada desde back");
    }
    // select
    public ResultSet getDataMySQL(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    //Comandos: DELETE, EDIT, INSERT
    public void comandSQL(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

}
