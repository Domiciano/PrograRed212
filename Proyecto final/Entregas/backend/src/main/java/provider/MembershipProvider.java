package provider;

import model.Membership;
import sql.MySQL;

public class MembershipProvider {


    public ArrayList<City> getData() throws SQLException {
        ArrayList<City> respuesta = new ArrayList<>();

        String sql = "SELECT * FROM memberShipBuddy";
        MySQL db = new MySQL();
        db.connection();
        ResultSet results = db.getDataMySQL(sql);
        while (results.next()) {
            int id = results.getInt(results.findColumn("id"));
            int venueID = results.getInt(results.findColumn("venuesBuddyID"));
            int planID = results.getInt(results.findColumn("plansBuddyID"));
            float totalAmount = results.getFloat(results.findColumn("totalAmount"));
            float discount = results.getFloat(results.findColumn("discount"));
            Date startDate = results.getDate(results.findColumn("startDate"));
            Date endDate = results.getDate(results.findColumn("endDate"));

            Membership temp = new Membership(id, venueID, planID, totalAmount, discount, startDate, endDate);
            respuesta.add(temp);
        }
        db.close();
        return respuesta;
    }

    public String insert(Membership membership) throws SQLException {
        String sql = "INSERT INTO memberShipBuddy ( venueID, planID, totalAmount, discount, startDate, endDate)";
        sql += " VALUES ('$venueID','$planID',$totalAmount,$discount, $startDate, $endDate)";
        sql = sql.replace("$venueID", membership.getVenueID());
        sql = sql.replace("$planID", membership.getPlanID());
        sql = sql.replace("$totalAmount", membership.getTotalAmount());
        sql = sql.replace("$discount", membership.getDiscount());
        sql = sql.replace("$startDate", membership.getStartDate());
        sql = sql.replace("$endDate", membership.getEndDate());

        MySQL db = new MySQL();
        db.connection();
        db.comandSQL(sql);
        db.close();
        return "ok";
    }

}