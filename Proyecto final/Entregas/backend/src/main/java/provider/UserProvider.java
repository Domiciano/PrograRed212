package provider;

import model.*;
import sql.MySQL;
import sql.SQLAdmin;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserProvider {

    private final MySQL db;

    public UserProvider() {
        db = SQLAdmin.getInstance().addConnection();
    }

    public ArrayList<User> getData() throws SQLException {
        ArrayList<User> respuesta = new ArrayList<>();

        String sql = "SELECT * FROM usersBuddy";
        db.connection();
        ResultSet results = db.getDataMySQL(sql);
        getResponseList(results, respuesta);
        db.close();

        return respuesta;
    }

    public ArrayList<User> getData(String property, String value) throws SQLException {
        ArrayList<User> respuesta = new ArrayList<>();

        String sql = "SELECT * FROM usersBuddy WHERE $property=$value";
        sql = sql.replace("$property", property);
        sql = sql.replace("$value", value);

        db.connection();
        ResultSet results = db.getDataMySQL(sql);
        getResponseList(results, respuesta);
        db.close();

        return respuesta;
    }

    private void getResponseList(ResultSet results, ArrayList<User> list ) throws SQLException {
        while (results.next()) {
            int id = Integer.parseInt(results.getString(results.findColumn("id")));
            String lastName = results.getString(results.findColumn("lastName"));
            String name = results.getString(results.findColumn("name"));
            String password = results.getString(results.findColumn("password"));
            int venuesBuddyID = Integer.parseInt(results.getString(results.findColumn("venuesBuddyID")));
            int roleBuddyID = Integer.parseInt(results.getString(results.findColumn("roleBuddyID")));

            User temp = new User(id, lastName, name, password, venuesBuddyID, roleBuddyID);
            list.add(temp);
        }
    }

    public String insert(User user) throws SQLException {
        String sql = "INSERT INTO usersBuddy " +
                "(id, name, lastName, password, venuesBuddyID, roleBuddyID )" +
                " VALUES ($id,'$name', '$lastName', '$password', $venuesBuddyID, $roleBuddyID )";

        sql = replace(sql, user);

        db.connection();
        db.comandSQL(sql);
        db.close();
        return "ok";
    }

    public String update(User user) throws SQLException {
        String sql = "UPDATE usersBuddy " +
                "SET `id` = $id, `name` = '$name', `lastName` = '$lastName', " +
                "`password` = '$password', `venuesBuddyID` = $venuesBuddyID, `roleBuddyID` = $roleBuddyID " +
                "WHERE id=$id";

        sql = replace(sql, user);

        db.connection();
        db.comandSQL(sql);
        db.close();
        return "ok";
    }

    private String replace(String sql, User user){
        sql = sql.replace("$name", user.getName());
        sql = sql.replace("$lastName", user.getLastName());
        sql = sql.replace("$password", user.getPassword());
        sql = sql.replace("$id", user.getId()+"");
        sql = sql.replace("$venuesBuddyID", user.getVenuesBuddyID() + "");
        sql = sql.replace("$roleBuddyID", user.getRoleBuddyID() + "");
        return sql;
    }

    public String delete(int id) throws SQLException {
        String sql = "DELETE from usersBuddy" +
                "WHERE id=$id";
        sql = sql.replace("$id", id+"");

        db.connection();
        db.comandSQL(sql);
        db.close();

        return "ok";
    }
    public LogUser auth(Auth auth) throws SQLException {
        LogUser temp = null;

        String sql = "SELECT * FROM usersBuddy WHERE id=$name AND password='$password'";
        sql = sql.replace("$name", auth.getId()+"");
        sql = sql.replace("$password", auth.getPassword());

        db.connection();
        ResultSet results = db.getDataMySQL(sql);

        while (results.next()) {
            int id = Integer.parseInt(results.getString(results.findColumn("id")));
            String lastName = results.getString(results.findColumn("lastName"));
            String name = results.getString(results.findColumn("name"));
            int venuesBuddyID = Integer.parseInt(results.getString(results.findColumn("venuesBuddyID")));
            int roleBuddyID = Integer.parseInt(results.getString(results.findColumn("roleBuddyID")));

            temp = new LogUser(id, lastName, name, venuesBuddyID, roleBuddyID);
        }

        db.close();

        return temp;

    }

}
