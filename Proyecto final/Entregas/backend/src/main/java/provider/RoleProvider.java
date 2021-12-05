package provider;

import model.Role;
import sql.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoleProvider {

    public ArrayList<Role> getAllRoles() throws SQLException {
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

    public void insert(Role role) throws SQLException {
        String sql = "INSERT INTO roleBuddy (name, description)";
        sql += " VALUES ('$name', '$description')";
        sql = sql.replace("$name", role.getName());
        sql = sql.replace("$description", role.getDescription());

        MySQL db = new MySQL();
        db.connection();
        db.comandSQL(sql);
        db.close();
    }

    public void update(Role role) throws SQLException {
        String sql = "UPDATE roleBuddy SET name = '$name', description = '$description' WHERE id = " + role.getId();
        sql = sql.replace("$name", role.getName());
        sql = sql.replace("$description", role.getDescription());

        MySQL db = new MySQL();
        db.connection();
        db.comandSQL(sql);
        db.close();
    }

    public void delete(Role role) throws SQLException {
        String sql = "DELETE FROM roleBuddy WHERE ID = " + role.getId();

        MySQL db = new MySQL();
        db.connection();
        db.comandSQL(sql);
        db.close();
    }
}
