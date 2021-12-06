package provider;

import model.ClientState;
import sql.MySQL;
import sql.SQLAdmin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientStateProvider {

    public ArrayList<ClientState> getData() throws SQLException {
        ArrayList<ClientState> response = new ArrayList<>();

        String sql = "SELECT * FROM clientStatusBuddy";
        MySQL db = SQLAdmin.getInstance().addConnection();
        db.connection();
        ResultSet results = db.getDataMySQL(sql);
        while (results.next()) {
            int id = results.getInt(results.findColumn("id"));
            String status = results.getString(results.findColumn("status"));
            ClientState cs = new ClientState(id,status);
            response.add(cs);
        }
        db.close();

        return response;
    }

    public ArrayList<ClientState> getDataById(int CID) throws SQLException {
        ArrayList<ClientState> response = new ArrayList<>();

        String sql = "SELECT * FROM clientStatusBuddy WHERE ID =" + CID;
        MySQL db = SQLAdmin.getInstance().addConnection();
        db.connection();
        ResultSet results = db.getDataMySQL(sql);
        while (results.next()) {
            int id = results.getInt(results.findColumn("id"));
            String status = results.getString(results.findColumn("status"));
            ClientState cs = new ClientState(id,status);
            response.add(cs);
        }
        db.close();

        return response;
    }

    public String insert(ClientState clientS) throws SQLException {
        String sql = "INSERT INTO clientStatusBuddy(status)";
        sql += " VALUES ('$status')";
        sql = sql.replace("$status",clientS.getStatus());

        MySQL db = SQLAdmin.getInstance().addConnection();
        db.connection();
        db.comandSQL(sql);
        db.close();
        return "ok";
    }

}
