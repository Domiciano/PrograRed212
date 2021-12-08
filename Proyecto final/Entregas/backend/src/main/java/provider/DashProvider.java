package provider;

import model.Membership;
import sql.MySQL;
import sql.SQLAdmin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class DashProvider {

    public DashProvider() {}

    public int earnings(String city, boolean isYear) throws SQLException, ParseException {
        MembershipProvider mp = new MembershipProvider();
        ArrayList<Membership> list = mp.getData(city, isYear);
        int value = 0;
        for (Membership membership : list ) {
            value += (int) membership.getTotalAmount() - membership.getDiscount();
        }
        return value;
    }

    public int occupation(String city) throws SQLException, ParseException {
        int value = 0;
        int block = 0;
        String sql;

        ArrayList<String> list = new ArrayList<>();
        MySQL db = SQLAdmin.getInstance().addConnection();

        db.connection();

        if(city.equals("all")){
            sql= "SELECT cs.status FROM clientsBuddy c, venuesBuddy v, cityBuddy ct, " +
                    " memberShipBuddy m, clientStatusBuddy cs WHERE c.memberShipBuddyID = m.id AND m.venuesBuddyID = v.id" +
                    " AND v.cityBuddyID = ct.id ";
        } else {
            sql= "SELECT cs.status FROM clientsBuddy c, venuesBuddy v, cityBuddy ct, " +
                    " memberShipBuddy m, clientStatusBuddy cs WHERE c.memberShipBuddyID = m.id AND m.venuesBuddyID = v.id" +
                    " AND v.cityBuddyID = ct.id AND ct.name = '" + city + "'";
        }

        ResultSet results = db.getDataMySQL(sql);
        while(results.next()){
            String status = results.getString(results.findColumn("status"));
            //status = new String(status);
            list.add(status);
        }
        db.close();
        for (String cs : list  ) {
            if(cs.equals("In")){
                value++;
            }
            if(cs.equals("Block")){
                block++;
            }
        }

        value = ((value*100)/(list.size()-block));
        return value;
    }

    public ArrayList<Integer> clientStatusData() throws SQLException, ParseException{

        String sql = "SELECT cs.status FROM clientsBuddy c, venuesBuddy v, cityBuddy ct, " +
                " memberShipBuddy m, clientStatusBuddy cs WHERE c.memberShipBuddyID = m.id AND m.venuesBuddyID = v.id" +
                " AND v.cityBuddyID = ct.id ";
        ArrayList<String> list = new ArrayList<>();
        MySQL db = SQLAdmin.getInstance().addConnection();

        db.connection();
        ResultSet results = db.getDataMySQL(sql);
        while(results.next()){
            String status = results.getString(results.findColumn("status"));
            list.add(status);
        }
        db.close();

        int in = 0;
        int out = 0;
        int block = 0;

        for (String cs : list  ) {
            if(cs.equals("In")){
                in++;
            }
            if(cs.equals("Out")){
                out++;
            }
            if(cs.equals("Block")){
                block++;
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        result.add(in);
        result.add(out);
        result.add(block);

        return result;
    }

    public ArrayList<String> clientStatusLabels(){
        ArrayList<String> result = new ArrayList<>();
        result.add("In");
        result.add("Out");
        result.add("Block");

        return result;
    }

    // ------------------------------------------------> No tested

    public ArrayList<String> planStatusLabels(){
        ArrayList<String> result = new ArrayList<>();
        result.add("Active");
        result.add("Inactive");

        return result;
    }

    public ArrayList<Integer> planStatusData() throws SQLException {

        String sql = "SELECT pb.active FROM  plansBuddy pb";
        ArrayList<Boolean> list = new ArrayList<>();
        MySQL db = SQLAdmin.getInstance().addConnection();

        db.connection();
        ResultSet results = db.getDataMySQL(sql);
        while(results.next()){
            boolean status = results.getBoolean(results.findColumn("active"));
            list.add(status);
        }
        db.close();

        int active = 0;
        int inactive = 0;

        for (boolean cs : list  ) {
            if(cs){
                active++;
            }else{
                inactive++;
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        result.add(active);
        result.add(inactive);

        return result;
    }

    public ArrayList<String> citiesNames() throws SQLException {
            String sql = "SELECT ct.name FROM cityBuddy ct ";
        ArrayList<String> list = new ArrayList<>();
        MySQL db = SQLAdmin.getInstance().addConnection();

        db.connection();
        ResultSet results = db.getDataMySQL(sql);
        while(results.next()){
            String city = results.getString(results.findColumn("name"));
            list.add(city);
        }
        db.close();
        return list;
    }

    public ArrayList<Integer> clientsByAge() throws SQLException {
        String sql = "SELECT c.age FROM clientsBuddy c ";
        ArrayList<Integer> list = new ArrayList<>();
        MySQL db = SQLAdmin.getInstance().addConnection();

        db.connection();
        ResultSet results = db.getDataMySQL(sql);
        while(results.next()){
            int temp = results.getInt(results.findColumn("age"));
            list.add(temp);
        }
        db.close();

        int ten = 0;
        int twenty = 0;
        int threty = 0;
        int forthy = 0;
        int fivety = 0;

        for (Integer n : list  ) {
            if(n < 20 ){
                ten++;
            } else if(n < 30){
                twenty++;
            }else if(n < 40){
                threty++;
            }else if(n < 50){
                forthy++;
            } else {
                fivety++;
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        result.add(ten);
        result.add(twenty);
        result.add(threty);
        result.add(forthy);
        result.add(fivety);

        return result;
    }

    public ArrayList<Integer> monthlyEarnings() throws SQLException {

        ArrayList<Integer> list = new ArrayList<>();

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String[] date = simpleDateFormat.format(new Date()).split("-");

        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[0]);

        for (int i = 1; i < 13; i++) {
            String hM = String.format("%02d", month);
            String hY = String.format("%02d", year);

            month--;
            if (month <= 0) {
                month = 12;
                year--;
            }

            String sM = String.format("%02d", month);
            String sY = String.format("%02d", year);

            String sql = "SELECT mb.totalAmount, mb.discount, mb.startDate" +
                    " FROM  memberShipBuddy mb " +
                    " WHERE mb.startDate > " + sY + sM + "01 AND mb.startDate < "
                    + hY + hM + "01";

            MySQL db = SQLAdmin.getInstance().addConnection();

            db.connection();
            ResultSet results = db.getDataMySQL(sql);
            while (results.next()) {
                int amount = results.getInt(results.findColumn("totalAmount"));
                int discount = results.getInt(results.findColumn("discount"));
                int total = amount - discount;
                list.add(total);
            }
            db.close();
        }
        return  list;
    }
}
