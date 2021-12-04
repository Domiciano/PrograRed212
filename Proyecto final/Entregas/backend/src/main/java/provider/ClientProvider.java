package provider;

import model.Client;
import sql.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientProvider {
    public ArrayList<Client> getData() throws SQLException {
        ArrayList<Client> respuesta = new ArrayList<>();

        String sql = "SELECT * FROM clientsBuddy";
        MySQL db = new MySQL();
        db.connection();
        ResultSet results = db.getDataMySQL(sql);
        while (results.next()) {
            int id = results.getInt(results.findColumn("id"));
            String natId = results.getString(results.findColumn("natID"));
            String name = results.getString(results.findColumn("name"));
            String lastName = results.getString(results.findColumn("lastName"));
            int age = results.getInt(results.findColumn("age"));
            float weight = results.getFloat(results.findColumn("weight"));
            float height = results.getFloat(results.findColumn("height"));
            int statusID = results.getInt(results.findColumn("clientStatusBuddyID"));
            int membershipID = results.getInt(results.findColumn("memberShipBuddyID"));

            Client temp = new Client(natId, id, age, name, lastName, weight, height, statusID, membershipID);
            respuesta.add(temp);
        }
        db.close();

        return respuesta;
    }
    public String insert(Client client) throws SQLException {
        String sql = "INSERT INTO clientsBuddy (natID, name, lastName, age,weight,height, clientStatusBuddyID, memberShipBuddyID )";
        sql += " VALUES ('$natId', '$name','$lastName', $age,$weight,$height, $clientStatusBuddyID, $memberShipBuddyID )";
        sql = sql.replace("$natId", client.getNatId());
        sql = sql.replace("$name", client.getName());
        sql = sql.replace("$lastName", client.getLastname());
        sql = sql.replace("$age", ""+client.getAge());
        sql = sql.replace("$weight", ""+client.getWeight());
        sql = sql.replace("$height", ""+client.getHeight());
        sql = sql.replace("$clientStatusBuddyID", client.getStatusID() + "");
        sql = sql.replace("$memberShipBuddyID", client.getMembershipID() + "");

        MySQL db = new MySQL();
        db.connection();
        db.comandSQL(sql);
        db.close();
        return "Cliente creado exitosamente";
    }
    public String edit(Client client) throws ClassNotFoundException, SQLException {
        MySQL db = new MySQL();
        db.connection();
        String sql = "UPDATE clientsBuddy SET name='$name', lastName='$lastName', age=$age,weight=$weight,height=$height, clientStatusBuddyID= $clientStatusBuddyID, memberShipBuddyID=$memberShipBuddyID WHERE natID='$natId'";
        sql = sql.replace("$natId", client.getNatId());
        sql = sql.replace("$name", client.getName());
        sql = sql.replace("$lastName", client.getLastname());
        sql = sql.replace("$age", ""+client.getAge());
        sql = sql.replace("$weight", ""+client.getWeight());
        sql = sql.replace("$height", ""+client.getHeight());
        sql = sql.replace("$clientStatusBuddyID", client.getStatusID() + "");
        sql = sql.replace("$memberShipBuddyID", client.getMembershipID() + "");
        db.comandSQL(sql);
        db.close();
        return "Cliente editado exitosamente";
    }
    public String delete(String natID) throws SQLException {
        MySQL db = new MySQL();
        db.connection();
        String sql = "DELETE FROM clientsBuddy WHERE natID='"+natID+"'";
        db.comandSQL(sql);
        db.close();
        return "Cliente borrado exitosamente";
    }
}
