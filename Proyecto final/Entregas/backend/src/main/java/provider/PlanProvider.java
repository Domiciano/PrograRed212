package provider;

import model.Card;
import model.Client;
import model.Plan;
import sql.MySQL;
import sql.SQLAdmin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class PlanProvider {

    public ArrayList<Plan> getData() throws SQLException {
        ArrayList<Plan> response = new ArrayList<>();
        MySQL db = SQLAdmin.getInstance().addConnection();
        String sql = "SELECT * FROM plansBuddy";
        db.connection();
        ResultSet results = db.getDataMySQL(sql);
        getResponseList(results, response);
        db.close();

        return response;
    }

    private void getResponseList(ResultSet results, ArrayList<Plan> list) throws SQLException {
        while (results.next()) {
            int id = results.getInt(results.findColumn("id"));
            String name = results.getString(results.findColumn("name"));
            double amount = results.getDouble(results.findColumn("amount"));
            int time = results.getInt(results.findColumn("time"));
            boolean active = results.getBoolean(results.findColumn("active"));
            Plan temp = new Plan(id, name, amount, time, active);
            list.add(temp);
        }
    }

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
        String sql = "INSERT INTO plansBuddy (name, amount, time, active) VALUES ('$name', $amount, $time, $active)";
        sql = sql.replace("$name", plan.getName());
        sql = sql.replace("$amount", plan.getAmount() + "");
        sql = sql.replace("$time", plan.getTime() + "");
        sql = sql.replace("$active", plan.isActive() + "");

        MySQL db = SQLAdmin.getInstance().addConnection();
        db.connection();
        db.comandSQL(sql);
        db.close();
    }

    public void update(Plan plan) throws SQLException {
        String sql = "UPDATE plansBuddy SET name = '$name', amount = $amount, time = $time, active = '$active' WHERE id = " + plan.getId();
        sql = sql.replace("$name", plan.getName());
        sql = sql.replace("$amount", plan.getAmount()+"");
        sql = sql.replace("$time", plan.getTime()+"");
        int active = 0;
        if (plan.isActive()) active = 1;
        sql = sql.replace("$active", active +"");

        MySQL db = SQLAdmin.getInstance().addConnection();
        db.connection();
        db.comandSQL(sql);
        db.close();
    }

    private String replace(String sql, Plan plan) {
        sql = sql.replace("$name", plan.getName());
        sql = sql.replace("$amount", plan.getAmount() + "");
        sql = sql.replace("$time", plan.getTime() + "");
        sql = sql.replace("$active", plan.isActive() + "");
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
        sql = sql.replace("$MID", PlanID + "");
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

    public ArrayList<Plan> filter(String name, String amountFrom, String amountTo, String status) throws SQLException {
        MySQL db = SQLAdmin.getInstance().addConnection();
        ArrayList<Plan> plans = new ArrayList<>();
        db.connection();
        String sql = "SELECT * FROM plansBuddy";
        if (!(name.equalsIgnoreCase("null")) || !(amountFrom.equalsIgnoreCase("null")) || !(amountTo.equalsIgnoreCase("null")) || !(status.equalsIgnoreCase("null"))) {
            sql += " WHERE";
            if (!(name.equalsIgnoreCase("null"))) {
                sql += " name = '" + name + "'";
            }
            if (!(amountFrom.equalsIgnoreCase("null")) && !(amountTo.equalsIgnoreCase("null"))) {
                String lastWord = sql.substring(sql.lastIndexOf(" ") + 1);
                if (lastWord.equalsIgnoreCase("WHERE")) {
                    sql += " amount >= $from AND amount <= $to";
                } else {
                    sql += " AND amount >= $from AND amount <= $to";
                }
                sql = sql.replace("$from", amountFrom);
                sql = sql.replace("$to", amountTo);

            } else {
                String lastWord = sql.substring(sql.lastIndexOf(" ") + 1);
                if (!(amountFrom.equalsIgnoreCase("null"))) {
                    if (lastWord.equalsIgnoreCase("WHERE")) {
                        sql += " amount >= $from";
                    } else {
                        sql += " AND amount >= $from";
                    }
                    sql = sql.replace("$from", amountFrom);
                }

                lastWord = sql.substring(sql.lastIndexOf(" ") + 1);
                if (!(amountTo.equalsIgnoreCase("null"))) {
                    if (lastWord.equalsIgnoreCase("WHERE")) {
                        sql += " amount <= $to";
                    } else {
                        sql += " AND amount <= $to";
                    }
                    sql = sql.replace("$to", amountTo);
                }
            }
            String lastWord = sql.substring(sql.lastIndexOf(" ") + 1);
            if (!(status.equalsIgnoreCase("null"))) {
                if (lastWord.equalsIgnoreCase("WHERE")) {
                    sql += " active = " + status + "";
                } else {
                    sql += " AND active = " + status + "";
                }
            }
        }
        ResultSet results = db.getDataMySQL(sql);
        while (results.next()) {
            int id = results.getInt(results.findColumn("id"));
            String nameR = results.getString(results.findColumn("name"));
            double amountR = results.getDouble(results.findColumn("amount"));
            int time = results.getInt(results.findColumn("time"));
            boolean active = results.getBoolean(results.findColumn("active"));
            Plan plan = new Plan(id, nameR, amountR, time, active);
            plans.add(plan);
        }
        db.close();
        return plans;
    }
}
