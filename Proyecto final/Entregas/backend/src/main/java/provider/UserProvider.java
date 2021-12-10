package provider;

import model.*;
import sql.MySQL;
import sql.SQLAdmin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserProvider {

    private final MySQL db;

    public UserProvider() {
        db = SQLAdmin.getInstance().addConnection();
    }

    public ArrayList<User> getData() throws SQLException {
        ArrayList<User> respuesta = new ArrayList<>();

        String sql = "SELECT * FROM usersBuddy";
        db.connection();
        ResultSet results = db.getDataMySQL(sql);
        getResponseList(results, respuesta);
        db.close();

        return respuesta;
    }

    public ArrayList<User> getData(String property, String value) throws SQLException {
        ArrayList<User> respuesta = new ArrayList<>();
        LinkedHashMap<String,String> values = getExpression(property,value);
        String filter = makeFilter(values);
        String sql = "SELECT * FROM usersBuddy WHERE "+ filter;

            for (Map.Entry<String, String> entry : values.entrySet()) {
                sql = sql.replace("$"+entry.getValue(),entry.getValue());
                sql = sql.replace("$"+entry.getKey(), entry.getKey());
            }
            db.connection();
            ResultSet results = db.getDataMySQL(sql);
            getResponseList(results, respuesta);
            db.close();

            return respuesta;
        }
    public String makeFilter(LinkedHashMap<String,String> values){
        String expression="";
        String lastkey="";
        String firstkey ="";
        if (!values.isEmpty()) {
            firstkey = values.keySet().iterator().next();
            for (String key : values.keySet()) {
                lastkey = key;
            }
        }
        String lastVal = values.get(lastkey);

        for (Map.Entry<String, String> entry : values.entrySet()) {
            if(values.size()>1) {
                if(entry.getValue().equalsIgnoreCase(lastVal)){

                    if (entry.getKey().equalsIgnoreCase("venuesBuddyID")) {
                        expression += "$venuesBuddyID= $" + entry.getValue();
                    } else {
                        expression += "$" + entry.getKey() + "='$" + entry.getValue() + "'";}

                }else{
                    if (entry.getKey().equalsIgnoreCase("venuesBuddyID")) {
                        expression += "$venuesBuddyID= $" + Integer.parseInt(entry.getValue())+" AND ";
                    } else {
                        expression += "$" + entry.getKey() + "='$"+entry.getValue()+"' AND ";
                    }
                }
            }else{
                if (entry.getKey().equalsIgnoreCase("venuesBuddyID")) {
                    expression += "$venuesBuddyID= $" + entry.getValue();
                } else {
                    expression += "$" + entry.getKey() + "='$" + entry.getValue() + "'";
                }
            }
        }
    return expression;
    }
    public LinkedHashMap<String,String> getExpression(String property, String value){
            String[] newproperty = property.split(",");
            String[] newvalue = value.split(",");
            LinkedHashMap<String, String> m = new LinkedHashMap();
            for(int i = 0; i<newproperty.length;i++){
                    m.put(newproperty[i],newvalue[i]);
            }
            return m;
        }
        private void getResponseList(ResultSet results, ArrayList<User> list ) throws SQLException {
            while (results.next()) {
                int id = Integer.parseInt(results.getString(results.findColumn("id")));
                String lastName = results.getString(results.findColumn("lastName"));
                String name = results.getString(results.findColumn("name"));
                String password = results.getString(results.findColumn("password"));
                int venuesBuddyID = Integer.parseInt(results.getString(results.findColumn("venuesBuddyID")));
                int roleBuddyID = Integer.parseInt(results.getString(results.findColumn("roleBuddyID")));

                User temp = new User(id, name, lastName, password, venuesBuddyID, roleBuddyID);
                list.add(temp);
            }
        }

        public String insert(User user) throws SQLException {
            String sql = "INSERT INTO usersBuddy " +
                    "(id, name, lastName, password, venuesBuddyID, roleBuddyID )" +
                    " VALUES ($id,'$name', '$lastName', '$password', $venuesBuddyID, $roleBuddyID )";

            sql = replace(sql, user);

            db.connection();
            db.comandSQL(sql);
            db.close();
            return "ok";
        }

        public String update(User user) throws SQLException {
            String sql = "UPDATE usersBuddy " +
                    "SET `id` = $id, `name` = '$name', `lastName` = '$lastName', " +
                    "`password` = '$password', `venuesBuddyID` = $venuesBuddyID, `roleBuddyID` = $roleBuddyID " +
                    "WHERE id=$id";

            sql = replace(sql, user);

            db.connection();
            db.comandSQL(sql);
            db.close();
            return "ok";
        }

        private String replace(String sql, User user){
            sql = sql.replace("$name", user.getName());
            sql = sql.replace("$lastName", user.getLastName());
            sql = sql.replace("$password", user.getPassword());
            sql = sql.replace("$id", user.getId()+"");
            sql = sql.replace("$venuesBuddyID", user.getVenuesBuddyID() + "");
            sql = sql.replace("$roleBuddyID", user.getRoleBuddyID() + "");
            return sql;
        }

        public String delete(int id) throws SQLException {
            String sql = "DELETE from usersBuddy WHERE id=$id";
            sql = sql.replace("$id", id+"");

            db.connection();
            db.comandSQL(sql);
            db.close();

            return "ok";
        }
        public LogUser auth(Auth auth) throws SQLException {
            LogUser temp = null;

            String sql = "SELECT * FROM usersBuddy WHERE id=$name AND password='$password'";
            sql = sql.replace("$name", auth.getId()+"");
            sql = sql.replace("$password", auth.getPassword());

            db.connection();
            ResultSet results = db.getDataMySQL(sql);

            while (results.next()) {
                int id = Integer.parseInt(results.getString(results.findColumn("id")));
                String lastName = results.getString(results.findColumn("lastName"));
                String name = results.getString(results.findColumn("name"));
                int venuesBuddyID = Integer.parseInt(results.getString(results.findColumn("venuesBuddyID")));
                int roleBuddyID = Integer.parseInt(results.getString(results.findColumn("roleBuddyID")));

                temp = new LogUser(id, lastName, name, venuesBuddyID, roleBuddyID);
            }

            db.close();

            return temp;

        }
    public ArrayList<UserCard> getVenueManagerCardInfo() throws SQLException {
        MySQL db = SQLAdmin.getInstance().addConnection();
        ArrayList<UserCard> cards = new ArrayList<>();
        db.connection();
        String sql= "SELECT u.*,v.name,c.name FROM usersBuddy u, venuesBuddy v, cityBuddy c, roleBuddy r WHERE r.id=2 AND r.id=u.roleBuddyID AND c.id=v.cityBuddyID AND u.venuesBuddyID=v.id";
        ResultSet results = db.getDataMySQL(sql);
        while(results.next()){
            int id = results.getInt(1);
            String name = results.getString(2);
            String lastName = results.getString(3);
            String pwd = results.getString(4);
            int venuesBuddyID = results.getInt(5);
            int roleBuddyID = results.getInt(6);
            String venueName = results.getString(7);
            String cityName = results.getString(8);
            User user = new User(id, name, lastName, pwd, venuesBuddyID, roleBuddyID);
            cards.add(new UserCard(user, venueName, cityName));
        }
        db.close();
        return cards;
    }
    public ArrayList<UserCard> getVenueManagerCardInfo(String filter) throws SQLException {
        MySQL db = SQLAdmin.getInstance().addConnection();
        ArrayList<UserCard> cards = new ArrayList<>();
        db.connection();
        String sql= "SELECT u.*,v.name,c.name FROM usersBuddy u, venuesBuddy v, cityBuddy c, roleBuddy r WHERE r.id=2 AND r.id=u.roleBuddyID AND c.id=v.cityBuddyID AND u.venuesBuddyID=v.id AND "+filter;
        ResultSet results = db.getDataMySQL(sql);
        while(results.next()){
            int id = results.getInt(1);
            String name = results.getString(2);
            String lastName = results.getString(3);
            String pwd = results.getString(4);
            int venuesBuddyID = results.getInt(5);
            int roleBuddyID = results.getInt(6);
            String venueName = results.getString(7);
            String cityName = results.getString(8);
            User user = new User(id, name, lastName, pwd, venuesBuddyID, roleBuddyID);
            cards.add(new UserCard(user, venueName, cityName));
        }
        db.close();
        return cards;
    }
    public ArrayList<UserCard> getData(String property, String value, int role) throws SQLException {
        LinkedHashMap<String, String> expression = getExpression(property, value);
        String filter = makeFilter(expression);
        System.out.println(filter);
        ArrayList<UserCard> res= getVenueManagerCardInfo(filter);
        return res;
    }
}
