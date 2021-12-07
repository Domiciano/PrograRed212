package provider;

import model.City;
import model.Membership;
import model.Venue;
import sql.MySQL;
import sql.SQLAdmin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MembershipProvider {


    public ArrayList<Membership> getData() throws SQLException {
        ArrayList<Membership> respuesta = new ArrayList<>();

        String sql = "SELECT * FROM memberShipBuddy";
        MySQL db = SQLAdmin.getInstance().addConnection();
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

            Membership temp = new Membership(id, venueID, planID, totalAmount, discount, startDate, endDate);
            respuesta.add(temp);
        }
        db.close();
        return respuesta;
    }

    public ArrayList<Membership> getData(String city, boolean isYear) throws SQLException, ParseException {
        MySQL db = SQLAdmin.getInstance().addConnection();
        String sql;
        City cityObj = new CityProvider().getData(city);
        ArrayList<Venue> vList = new VenueProvider().getData(cityObj.getId());
        StringBuilder venuesIds = new StringBuilder();

        String pattern = "yyyy-MM-dd";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String[] date = simpleDateFormat.format(new Date()).split("-");

        ArrayList<Membership> respuesta = new ArrayList<>();
        if(isYear){
            date[1] = "01";
        }
        date[2] = "01";

        for (int i = 0; i < vList.size(); i++) {
            if(i == vList.size()-1){
                venuesIds.append(vList.get(i).getCity());
            } else {
                venuesIds.append(vList.get(i).getCity()).append(" OR venuesBuddyID = ");
            }
        }

        if(city.equals("")){
            sql = "SELECT * FROM memberShipBuddy WHERE startDate > "+ date[0]+date[1]+date[2];
        } else {
            sql = "SELECT * FROM memberShipBuddy WHERE venuesBuddyID = " + venuesIds;
            sql += " AND startDate > "+ date[0]+date[1]+date[2];
        }

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

            Membership temp = new Membership(id, venueID, planID, totalAmount, discount, startDate, endDate);
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

        MySQL db = SQLAdmin.getInstance().addConnection();
        db.connection();
        db.comandSQL(sql);
        db.close();
    }

    public ArrayList<Membership> searchMembershipByMemID(int memID) throws SQLException {
        MySQL db = SQLAdmin.getInstance().addConnection();
        ArrayList<Membership> memberships = new ArrayList<>();
        db.connection();

        String sql = "SELECT * FROM memberShipBuddy WHERE id = $MID ";
        sql = sql.replace("$MID", memID+"");
        ResultSet results = db.getDataMySQL(sql);

        while (results.next()) {
            int id = results.getInt(1);
            Double totalAmount = results.getDouble(2);
            Double discount = results.getDouble(3);
            Date startDate = results.getDate(4);
            Date endDate = results.getDate(5);
            int plansBuddyID = results.getInt(6);
            int venuesBuddyID = results.getInt(7);

            Membership membership = new Membership(id, plansBuddyID, venuesBuddyID, totalAmount, discount, startDate, endDate);
            memberships.add(membership);
        }
        db.close();
        return memberships;
    }
}