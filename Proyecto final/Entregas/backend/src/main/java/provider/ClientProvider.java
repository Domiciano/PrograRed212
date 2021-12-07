package provider;

import model.*;
import sql.MySQL;
import sql.SQLAdmin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ClientProvider {
    public ArrayList<Client> getData() throws SQLException {
        MySQL db = SQLAdmin.getInstance().addConnection();
        ArrayList<Client> respuesta = new ArrayList<>();

        String sql = "SELECT * FROM clientsBuddy";

        db.connection();
        ResultSet results = db.getDataMySQL(sql);
        while (results.next()) {
            int id = results.getInt(results.findColumn("id"));
            String natId = results.getString(results.findColumn("natID"));
            String name = results.getString(results.findColumn("name"));
            String lastName = results.getString(results.findColumn("lastName"));
            int age = results.getInt(results.findColumn("age"));
            double weight = results.getFloat(results.findColumn("weight"));
            double height = results.getFloat(results.findColumn("height"));
            int statusID = results.getInt(results.findColumn("clientStatusBuddyID"));
            int membershipID = results.getInt(results.findColumn("memberShipBuddyID"));

            Client temp = new Client(natId, id, age, name, lastName, weight, height, statusID, membershipID);
            respuesta.add(temp);
        }
        db.close();

        return respuesta;
    }
    public void insert(Client client) throws SQLException {
        MySQL db = SQLAdmin.getInstance().addConnection();
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


        db.connection();
        db.comandSQL(sql);
        db.close();
    }
    public void edit(Client client) throws ClassNotFoundException, SQLException {
        MySQL db = SQLAdmin.getInstance().addConnection();
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
    }
    public void delete(String natID) throws SQLException {
        MySQL db = SQLAdmin.getInstance().addConnection();
        db.connection();
        String sql = "DELETE FROM clientsBuddy WHERE natID='"+natID+"'";
        db.comandSQL(sql);
        db.close();
    }

    public ArrayList<Client> searchClient(String natID) throws SQLException {
        MySQL db = SQLAdmin.getInstance().addConnection();
        ArrayList<Client> client = new ArrayList<>();
        db.connection();

        String sql = "SELECT * FROM clientsBuddy WHERE natID = '$NATID' ";
        sql = sql.replace("$NATID", natID);
        ResultSet results = db.getDataMySQL(sql);

        while (results.next()) {
            int id = results.getInt(results.findColumn("id"));
            String natId = results.getString(results.findColumn("natID"));
            String name = results.getString(results.findColumn("name"));
            String lastName = results.getString(results.findColumn("lastName"));
            int age = results.getInt(results.findColumn("age"));
            double weight = results.getFloat(results.findColumn("weight"));
            double height = results.getFloat(results.findColumn("height"));
            int statusID = results.getInt(results.findColumn("clientStatusBuddyID"));
            int membershipID = results.getInt(results.findColumn("memberShipBuddyID"));
            Client found = new Client(natId, id, age, name, lastName, weight, height, statusID, membershipID);
            client.add(found);
        }
        db.close();
        return client;
    }

    public void editStatusByNatId(String natID, int status) throws SQLException {
        MySQL db = SQLAdmin.getInstance().addConnection();
        ArrayList<Client> client = new ArrayList<>();
        db.connection();
        String sql= "UPDATE clientsBuddy SET clientStatusBuddyID = $STATUS WHERE natID = '$NATID'";
        sql = sql.replace("$NATID", natID);
        sql = sql.replace("$STATUS",status+"");
        db.comandSQL(sql);
        db.close();
    }

    public ArrayList<Card> getCardInfo(String natID) throws SQLException {
        MySQL db = SQLAdmin.getInstance().addConnection();
        ArrayList<Card> cards = new ArrayList<>();
        db.connection();
        String sql= "SELECT c.*, p.name, m.endDate, cs.status FROM plansBuddy p, clientsBuddy c, memberShipBuddy m, clientStatusBuddy cs WHERE c.memberShipBuddyID=m.id AND m.plansBuddyID=p.id AND c.clientStatusBuddyID=cs.id AND c.natID='"+natID+"'";
        ResultSet results = db.getDataMySQL(sql);
        while(results.next()){
            int id = results.getInt(results.findColumn("id"));
            String natId = results.getString(results.findColumn("natID"));
            String name = results.getString(3);
            String lastName = results.getString(results.findColumn("lastName"));
            int age = results.getInt(results.findColumn("age"));
            double weight = results.getFloat(results.findColumn("weight"));
            double height = results.getFloat(results.findColumn("height"));
            int statusID = results.getInt(results.findColumn("clientStatusBuddyID"));
            int membershipID = results.getInt(results.findColumn("memberShipBuddyID"));
            String planName = results.getString(10);
            Date date = results.getDate(results.findColumn("endDate"));
            String status = results.getString(results.findColumn("status"));
            Client client = new Client(natId, id, age, name, lastName, weight, height, statusID, membershipID);
            cards.add(new Card(client, date, planName, status));
        }
        db.close();
        return cards;
    }

}
