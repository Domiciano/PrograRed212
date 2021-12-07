package provider;

import model.Plan;
import sql.MySQL;
import sql.SQLAdmin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlanProvider {

    public ArrayList<Plan> getAllPlans() throws SQLException {
        ArrayList<Plan> respuesta = new ArrayList<>();

        String sql = "SELECT * FROM plansBuddy";
        MySQL db = SQLAdmin.getInstance().addConnection();
        db.connection();
        ResultSet results = db.getDataMySQL(sql);
        while (results.next()) {
            int id = results.getInt(results.findColumn("id"));
            String name = results.getString(results.findColumn("name"));
            double amount = results.getDouble(results.findColumn("amount"));
            int time = results.getInt(results.findColumn("time"));
            boolean active = results.getBoolean(results.findColumn("active"));
            
            Plan plan = new Plan(id, name, amount, time, active);
            respuesta.add(plan);
        }
        db.close();
        return respuesta;
    }

    public ArrayList<String> getActivePlans() throws SQLException {
        ArrayList<String> nombres = new ArrayList<>();

        String sql = "SELECT name FROM plansBuddy WHERE active = 1";
        MySQL db = SQLAdmin.getInstance().addConnection();
        db.connection();
        ResultSet results = db.getDataMySQL(sql);
        while (results.next()) {
            String name = results.getString(1);
            nombres.add(name);
        }
        db.close();
        return nombres;
    }

    public void insert(Plan plan) throws SQLException {
        String sql = "INSERT INTO plansBuddy (name, amount, time, active)";
        sql += " VALUES ('$name', $amount, $time, $active)";
        sql = replace(sql, plan);

        MySQL db = SQLAdmin.getInstance().addConnection();
        db.connection();
        db.comandSQL(sql);
        db.close();
    }

    public void update(Plan plan) throws SQLException {
        String sql = "UPDATE plansBuddy SET name = '$name', amount = $amount, time = $time, active = '$active' WHERE id = " + plan.getId();
        sql = replace(sql, plan);

        MySQL db = SQLAdmin.getInstance().addConnection();
        db.connection();
        db.comandSQL(sql);
        db.close();
    }

    private String replace(String sql, Plan plan){
        sql = sql.replace("$name", plan.getName());
        sql = sql.replace("$amount", plan.getAmount()+"");
        sql = sql.replace("$time", plan.getTime()+"");
        sql.replace("$active", plan.isActive()+"");
        return sql;
    }

    public void delete(Plan plan) throws SQLException {
        String sql = "DELETE FROM plansBuddy WHERE ID = " + plan.getId();

        MySQL db = SQLAdmin.getInstance().addConnection();
        db.connection();
        db.comandSQL(sql);
        db.close();
    }

    public ArrayList<Plan> searchPlanByPlanID(int PlanID) throws SQLException {
        MySQL db = SQLAdmin.getInstance().addConnection();
        ArrayList<Plan> plans = new ArrayList<>();
        db.connection();

        String sql = "SELECT * FROM plansBuddy WHERE id = $MID ";
        sql = sql.replace("$MID", PlanID+"");
        ResultSet results = db.getDataMySQL(sql);

        while (results.next()) {
            int id = results.getInt(results.findColumn("id"));
            String name = results.getString(results.findColumn("name"));
            double amount = results.getDouble(results.findColumn("amount"));
            int time = results.getInt(results.findColumn("time"));
            boolean active = results.getBoolean(results.findColumn("active"));

            Plan plan = new Plan(id, name, amount, time, active);
            plans.add(plan);
        }
        db.close();
        return plans;
    }
}
