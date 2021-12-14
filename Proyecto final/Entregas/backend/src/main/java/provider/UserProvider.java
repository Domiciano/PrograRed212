package provider;

import model.*;
import sql.MySQL;
import sql.SQLAdmin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class UserProvider {

    public UserProvider() {

    }

    public ArrayList<User> getData() throws SQLException {
        ArrayList<User> respuesta = new ArrayList<>();

        String sql = "SELECT * FROM usersBuddy";
        MySQL db = SQLAdmin.getInstance().addConnection();
        db.connection();
        ResultSet results = db.getDataMySQL(sql);
        getResponseList(results, respuesta);
        db.close();

        return respuesta;
    }
    public ArrayList<UserCard> getUserCard(String natId, String name, String lastName, String venuesBuddyID, String role, String cityName) throws SQLException {
        ArrayList<UserCard> usercards = new ArrayList<>();

        String sql = "SELECT u.*, v.name,c.name FROM roleBuddy r, usersBuddy u, venuesBuddy v, cityBuddy c WHERE u.roleBuddyID = r.id AND v.cityBuddyID = c.id AND u.venuesBuddyID=v.id ";
        sql += " AND roleBuddyID= "+role+" ";
        if (!natId.equalsIgnoreCase("null")) {

            sql += " AND u.id = " + Integer.parseInt(natId);
        }
        if (!name.equalsIgnoreCase("null")) {
            sql += " AND u.name = '" + name + "'";

        }
        if (!lastName.equalsIgnoreCase("null")) {
            sql += " AND u.lastName = '" + lastName + "'";


        }
        if (!venuesBuddyID.equalsIgnoreCase("null")) {
            sql += " AND u.venuesBuddyID =" + Integer.parseInt(venuesBuddyID);
        }

        if(!cityName.equalsIgnoreCase("null")){
            sql += " AND c.name = '"+cityName+"'";
        }

        MySQL db = SQLAdmin.getInstance().addConnection();
        db.connection();
        ResultSet results = db.getDataMySQL(sql);
        while (results.next()) {
            int id = results.getInt(results.findColumn("id"));
            String lastname = results.getString(results.findColumn("lastName"));
            String nameu = results.getString(results.findColumn("name"));
            String password = results.getString(results.findColumn("password"));
            int venueID = Integer.parseInt(results.getString(results.findColumn("venuesBuddyID")));
            int roleID = Integer.parseInt(results.getString(results.findColumn("roleBuddyID")));
            User user = new User(id, nameu, lastname, password, venueID, roleID);
            String venue= results.getString(7);
            String city= results.getString(8);
            usercards.add(new UserCard(user,venue,city));
        }

       // getResponseList(results, respuesta);
        db.close();

        return usercards;
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


        MySQL db = SQLAdmin.getInstance().addConnection();
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


        MySQL db = SQLAdmin.getInstance().addConnection();
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
            String sql = "DELETE from usersBuddy" +
                    " WHERE id=$id";
            sql = sql.replace("$id", id+"");

        MySQL db = SQLAdmin.getInstance().addConnection();
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

        MySQL db = SQLAdmin.getInstance().addConnection();
        db.connection();
        ResultSet results = db.getDataMySQL(sql);

            while (results.next()) {
                int id = Integer.parseInt(results.getString(results.findColumn("id")));
                String lastName = results.getString(results.findColumn("lastName"));
                String name = results.getString(results.findColumn("name"));
                int venuesBuddyID = Integer.parseInt(results.getString(results.findColumn("venuesBuddyID")));
                int roleBuddyID = Integer.parseInt(results.getString(results.findColumn("roleBuddyID")));

                temp = new LogUser(id, name, lastName, venuesBuddyID, roleBuddyID);
            }

            db.close();

            return temp;

        }

    }
