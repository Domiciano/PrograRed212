package provider;

import model.User;
import sql.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserProvider {

    public ArrayList<User> getData() throws SQLException {
        ArrayList<User> respuesta = new ArrayList<>();

        String sql = "SELECT * FROM usersBuddy";
        MySQL db = new MySQL();
        db.connection();
        ResultSet results = db.getDataMySQL(sql);
        while (results.next()) {
            int id = Integer.parseInt(results.getString(results.findColumn("id")));
            String lastName = results.getString(results.findColumn("lastName"));
            String name = results.getString(results.findColumn("name"));
            String password = results.getString(results.findColumn("password"));
            int venuesBuddyID = Integer.parseInt(results.getString(results.findColumn("venuesBuddyID")));
            int roleBuddyID = Integer.parseInt(results.getString(results.findColumn("roleBuddyID")));

            User temp = new User(id, lastName, name, password, venuesBuddyID, roleBuddyID);
            respuesta.add(temp);
        }
        db.close();

        return respuesta;

    }

    public String insert(User user) throws SQLException {
        String sql = "INSERT INTO usersBuddy (id, name, lastName, password, venuesBuddyID, roleBuddyID )";
        sql += " VALUES ($id,'$name', '$lastName', '$password', $venuesBuddyID, $roleBuddyID )";
        sql = sql.replace("$name", user.getName());
        sql = sql.replace("$lastName", user.getLastName());
        sql = sql.replace("$password", user.getPassword());
        sql = sql.replace("$id", user.getId()+"");
        sql = sql.replace("$venuesBuddyID", user.getVenuesBuddyID() + "");
        sql = sql.replace("$roleBuddyID", user.getRoleBuddyID() + "");

        MySQL db = new MySQL();
        db.connection();
        db.comandSQL(sql);
        db.close();
        return "ok";
    }
}
