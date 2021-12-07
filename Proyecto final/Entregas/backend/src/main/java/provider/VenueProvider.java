package provider;

import model.Venue;
import sql.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VenueProvider {

    public ArrayList<Venue> getData() throws SQLException {
        ArrayList<Venue> respuesta = new ArrayList<>();

        String sql = "SELECT * FROM venueBuddy ";
        MySQL db = new MySQL();
        db.connection();
        ResultSet results = db.getDataMySQL(sql);
        while (results.next()) {
            int id = results.getInt(results.findColumn("id"));
            int cityID = results.getInt(results.findColumn("cityID"));
            String name = results.getString(results.findColumn("name"));

            Venue temp = new Venue(id, cityID, name);
            respuesta.add(temp);
        }
        db.close();
        return respuesta;
    }

    public ArrayList<Venue> getData(int cityId) throws SQLException {
        ArrayList<Venue> respuesta = new ArrayList<>();

        String sql = "SELECT * FROM `venuesBuddy` WHERE cityBuddyID = "+cityId;
        MySQL db = new MySQL();
        db.connection();
        ResultSet results = db.getDataMySQL(sql);
        while (results.next()) {
            int id = results.getInt(results.findColumn("id"));
            int cityID = results.getInt(results.findColumn("cityBuddyID"));
            String name = results.getString(results.findColumn("name"));

            Venue temp = new Venue(id, cityID, name);
            respuesta.add(temp);
        }
        db.close();
        return respuesta;
    }

    public void insert(Venue venue) throws SQLException {
        String sql = "INSERT INTO venueBuddy (cityID, name)";
        sql += " VALUES ($cityID, '$name')";
        sql = sql.replace("$cityID", venue.getCity()+"");
        sql = sql.replace("$name", venue.getName());

        MySQL db = new MySQL();
        db.connection();
        db.comandSQL(sql);
        db.close();
    }

    public void edit(Venue venue) throws SQLException {
        String sql = "UPDATE venueBuddy " +
                "SET `name` = '"+venue.getName()+"' WHERE id = "+venue.getId();
        MySQL db = new MySQL();
        db.connection();
        db.comandSQL(sql);
        db.close();
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM venueBuddy WHERE id = "+id;

        MySQL db = new MySQL();
        db.connection();
        db.comandSQL(sql);
        db.close();
    }

}