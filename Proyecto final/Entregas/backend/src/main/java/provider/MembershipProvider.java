package provider;

import model.Client;
import model.Membership;
import sql.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class MembershipProvider {


    public ArrayList<Membership> getData() throws SQLException {
        ArrayList<Membership> respuesta = new ArrayList<>();

        String sql = "SELECT * FROM memberShipBuddy";
        MySQL db = new MySQL();
        db.connection();
        ResultSet results = db.getDataMySQL(sql);
        while (results.next()) {
            int id = results.getInt(results.findColumn("id"));
            int venueID = results.getInt(results.findColumn("venuesBuddyID"));
            int planID = results.getInt(results.findColumn("plansBuddyID"));
            double totalAmount = results.getFloat(results.findColumn("totalAmount"));
            double discount = results.getFloat(results.findColumn("discount"));
            Date startDate = results.getDate(results.findColumn("startDate"));
            Date endDate = results.getDate(results.findColumn("endDate"));

            Membership temp = new Membership(id, totalAmount, discount,  startDate, endDate, planID, venueID);
            respuesta.add(temp);
        }
        db.close();
        return respuesta;
    }

    public void insert(Membership membership) throws SQLException {
        String sql = "INSERT INTO memberShipBuddy ( venuesBuddyID, planBuddyID, totalAmount, discount, startDate, endDate)";
        sql += " VALUES ('$venueID','$planID',$totalAmount,$discount, $startDate, $endDate)";
        sql = sql.replace("$venueID", membership.getVenueID()+"");
        sql = sql.replace("$planID", membership.getPlanID()+"");
        sql = sql.replace("$totalAmount", membership.getTotalAmount()+"");
        sql = sql.replace("$discount", membership.getDiscount()+"");
        sql = sql.replace("$startDate", membership.getStartDate()+"");
        sql = sql.replace("$endDate", membership.getEndDate()+"");

        MySQL db = new MySQL();
        db.connection();
        db.comandSQL(sql);
        db.close();
    }

    public ArrayList<Membership> searchMembershipByClientID(int clientID) throws SQLException {
        MySQL db = new MySQL();
        ArrayList<Membership> memberships = new ArrayList<>();
        db.connection();

        String sql = "SELECT * FROM memberShipBuddy WHERE id = $CID ";
        sql = sql.replace("$CID", clientID+"");
        ResultSet results = db.getDataMySQL(sql);

        while (results.next()) {
            int id = results.getInt(1);
            Double totalAmount = results.getDouble(2);
            Double discount = results.getDouble(3);
            Date startDate = results.getDate(4);
            Date endDate = results.getDate(5);
            int plansBuddyID = results.getInt(6);
            int venuesBuddyID = results.getInt(7);

            Membership membership = new Membership(id, totalAmount, discount, startDate, endDate, plansBuddyID, venuesBuddyID);
            memberships.add(membership);
        }
        db.close();
        return memberships;
    }
}