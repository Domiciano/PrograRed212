package provider;

import model.City;
import sql.MySQL;
import sql.SQLAdmin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CityProvider {
    public ArrayList<City> getData() throws SQLException {
        ArrayList<City> respuesta = new ArrayList<>();

        String sql = "SELECT * FROM cityBuddy";
        MySQL db = SQLAdmin.getInstance().addConnection();
        db.connection();
        ResultSet results = db.getDataMySQL(sql);
        while (results.next()) {
            int id = results.getInt(results.findColumn("id"));
            String name = results.getString(results.findColumn("name"));

            City temp = new City(id, name);
            respuesta.add(temp);
        }
        db.close();

        return respuesta;
    }

    public City getData(String cityName) throws SQLException {
        City respuesta = null;
        String sql = "SELECT * FROM cityBuddy WHERE name='"+cityName+"'";
        MySQL db = SQLAdmin.getInstance().addConnection();
        db.connection();
        ResultSet results = db.getDataMySQL(sql);
        while (results.next()) {
            int id = results.getInt(results.findColumn("id"));
            String name = results.getString(results.findColumn("name"));

            respuesta = new City(id, name);
        }
        db.close();

        return respuesta;
    }

    public String insert(City city) throws SQLException {
        String sql = "INSERT INTO cityBuddy (name)";
        sql += " VALUES ('$name')";
        sql = sql.replace("$name", city.getName());

        MySQL db = SQLAdmin.getInstance().addConnection();
        db.connection();
        db.comandSQL(sql);
        db.close();
        return "ok";
    }

    public String delete(City city) throws SQLException {
        String sql = "DELETE FROM cityBuddy WHERE name = '"+city.getName()+"'";

        MySQL db = SQLAdmin.getInstance().addConnection();
        db.connection();
        db.comandSQL(sql);
        db.close();

        return "ok";
    }

}
