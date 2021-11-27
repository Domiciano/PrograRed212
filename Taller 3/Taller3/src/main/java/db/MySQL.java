package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {

    private Connection connection;

    public MySQL() throws ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://200.3.193.22:3306/P09728_1_11","P09728_1_11","ZCSaQGZU");
    }

    public void close() throws SQLException {
        connection.close();
    }


    public void commandSQL(String sql) throws SQLException {
        connection.createStatement().execute(sql);
    }

    public ResultSet getDataBySQL(String sql) throws SQLException {
        return connection.createStatement().executeQuery(sql);
    }
}
