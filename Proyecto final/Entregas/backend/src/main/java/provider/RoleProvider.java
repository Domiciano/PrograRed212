package provider;

import model.Role;
import sql.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoleProvider {

    public ArrayList<Role> getData() throws SQLException {
        ArrayList<Role> respuesta = new ArrayList<>();

        String sql = "SELECT * FROM roleBuddy";
        MySQL db = new MySQL();
        db.connection();
        ResultSet results = db.getDataMySQL(sql);
        while (results.next()) {
            int id = Integer.parseInt(results.getString(results.findColumn("id")));
            String name = results.getString(results.findColumn("name"));
            String description = results.getString(results.findColumn("description"));

            Role temp = new Role(id, name, description);
            respuesta.add(temp);
        }
        db.close();

        return respuesta;
    }

    public String insert(Role role) throws SQLException {
        String sql = "INSERT INTO roleBuddy (id, name, description)";
        sql += " VALUES ($id,'$name', '$description')";
        sql = sql.replace("$name", role.getName());
        sql = sql.replace("$description", role.getDescription());
        sql = sql.replace("$id", role.getId()+"");

        MySQL db = new MySQL();
        db.connection();
        db.comandSQL(sql);
        db.close();
        return "ok";
    }

    public String updateName(String oldName, String newName) throws SQLException {
        String sql = "UPDATE roleBuddy SET name = " + newName + " WHERE name = " + oldName;

        MySQL db = new MySQL();
        db.connection();
        db.comandSQL(sql);
        db.close();
        return "ok";
    }

    public String updateDescription(String oldDesc, String newDesc) throws SQLException {
        String sql = "UPDATE roleBuddy SET description = " + newDesc + " WHERE description = " + oldDesc;

        MySQL db = new MySQL();
        db.connection();
        db.comandSQL(sql);
        db.close();
        return "ok";
    }

    public String delete(String name) throws SQLException {
        String sql = "DELETE FROM roleBuddy WHERE name = " + name;

        MySQL db = new MySQL();
        db.connection();
        db.comandSQL(sql);
        db.close();
        return "ok";
    }
}
