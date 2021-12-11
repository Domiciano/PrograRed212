package provider;

import model.MainGraphData;
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

        if(list.isEmpty()){
            return 0;
        } else {
            value = ((value*100)/(list.size()-block));
            return value;
        }

    }

    public ArrayList<Integer> clientStatusData(String city) throws SQLException, ParseException{

        String sql = "";
        if(city.equals("all")){
            sql = "SELECT cs.status FROM clientsBuddy c, venuesBuddy v, cityBuddy ct, " +
                    " memberShipBuddy m, clientStatusBuddy cs WHERE c.memberShipBuddyID = m.id AND m.venuesBuddyID = v.id" +
                    " AND v.cityBuddyID = ct.id ";
        } else {
            sql = "SELECT cs.status FROM clientsBuddy c, venuesBuddy v, cityBuddy ct, " +
                    " memberShipBuddy m, clientStatusBuddy cs WHERE c.memberShipBuddyID = m.id AND m.venuesBuddyID = v.id" +
                    " AND v.cityBuddyID = ct.id AND ct.name = '"+city+"'";
        }

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
        result.add("Ingresado");
        result.add("Afuera");
        result.add("Bloqueado");

        return result;
    }

    // ------------------------------------------------> No tested

    public ArrayList<String> planStatusLabels(){
        ArrayList<String> result = new ArrayList<>();
        result.add("Activo");
        result.add("Inactivo");

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

    public ArrayList<Integer> clientsByAge(String city) throws SQLException {
        String sql = "";
        if(city.equals("all")){
            sql = "SELECT c.age FROM clientsBuddy c ";
        }else{
            sql = "SELECT cl.age FROM clientsBuddy cl, memberShipBuddy mb " +
                    "JOIN venuesBuddy vb JOIN cityBuddy cb " +
                    "WHERE mb.venuesBuddyID = vb.id AND vb.cityBuddyID = cb.id " +
                    "AND cb.name = '"+city+"' AND cl.memberShipBuddyID = mb.id ";
        }
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

    public ArrayList<MainGraphData> monthlyEarnings(String city) throws SQLException {

        ArrayList<MainGraphData> list = new ArrayList<>();

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String[] date = simpleDateFormat.format(new Date()).split("-");

        String day = date[2];
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[0]);

            String hM = String.format("%02d", month);
            String hY = String.format("%02d", year);

        for (int i = 0; i < 12; i++) {
            month--;
            if (month <= 0) {
                month = 12;
                year--;
            }
        }
            String sM = String.format("%02d", month);
            String sY = String.format("%02d", year);

        String sql = "";

        if(city.equals("all")){
                sql = "SELECT mb.totalAmount, mb.discount, mb.startDate" +
                        " FROM  memberShipBuddy mb " +
                        " WHERE mb.startDate > " + sY + sM + day + " AND mb.startDate < "
                        + hY + hM + day;
            } else {
                sql = "SELECT mb.totalAmount, mb.discount, mb.startDate " +
                        "FROM memberShipBuddy mb JOIN venuesBuddy vb JOIN cityBuddy cb " +
                        "WHERE mb.startDate > " + sY + sM + day + " AND mb.startDate < " + hY + hM + day +
                        " AND mb.venuesBuddyID = vb.id AND vb.cityBuddyID = cb.id AND cb.name = '"+city+"'";
            }


            MySQL db = SQLAdmin.getInstance().addConnection();

            db.connection();
            ResultSet results = db.getDataMySQL(sql);
            while (results.next()) {
                int amount = results.getInt(results.findColumn("totalAmount"));
                int discount = results.getInt(results.findColumn("discount"));
                Date dateStart = results.getDate(results.findColumn("startDate"));
                int total = amount - discount;
                list.add(new MainGraphData(total, dateStart));
            }
            db.close();

        return  list;
    }
}
