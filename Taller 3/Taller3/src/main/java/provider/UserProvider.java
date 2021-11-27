package provider;

import db.MySQL;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;


public class UserProvider {


    public ArrayList<User> getAll() throws ClassNotFoundException, SQLException {
        MySQL db = new MySQL();
        db.connect();
        ArrayList<User> users = new ArrayList<>();
        ResultSet resultSet = db.getDataBySQL("SELECT * FROM usersA00364415");
        while(resultSet.next()){
            users.add(new User(
                    resultSet.getString(2),
                    resultSet.getString(3)
                    ));
        }
        db.close();
        return users;
    }

    public void create(User user) throws ClassNotFoundException, SQLException {
        MySQL db = new MySQL();
        db.connect();
        String sql = "INSERT INTO usersA00364415(idNat, name) VALUES ('%IDNAT','%NAME')";
        sql = sql.replace("%IDNAT", user.getIdNat());
        sql = sql.replace("%NAME",user.getName());
        db.commandSQL(sql);
    }

}
