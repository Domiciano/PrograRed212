package provider;

import model.Venue;
import sql.MySQL;

public class VenueProvider {

    public ArrayList<City> getData() throws SQLException {
        ArrayList<City> respuesta = new ArrayList<>();

        String sql = "SELECT * FROM venueBuddy";
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

    public String insert(Venue venue) throws SQLException {
        String sql = "INSERT INTO venueBuddy (cityID, name)";
        sql += " VALUES ('$cityID', '$name')";
        sql = sql.replace("$cityID", venue.getCity());
        sql = sql.replace("$name", venue.getName());

        MySQL db = new MySQL();
        db.connection();
        db.comandSQL(sql);
        db.close();
        return "ok";
    }

    public String delete(Venue venue) throws SQLException {
        String sql = "DELETE FROM venueBuddy WHERE name = '"+venue.getName()+"'";

        MySQL db = new MySQL();
        db.connection();
        db.comandSQL(sql);
        db.close();
        return "ok";
    }

}