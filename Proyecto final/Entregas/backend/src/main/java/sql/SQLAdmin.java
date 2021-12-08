package sql;

import java.sql.SQLException;
import java.util.ArrayList;

public class SQLAdmin {

    private static SQLAdmin instance;

    public static SQLAdmin getInstance(){
        if(instance == null){
            instance = new SQLAdmin();
        }
        return instance;
    }
    private final ArrayList<MySQL> connections;

    private SQLAdmin (){
        connections = new ArrayList<>();
    }

    public MySQL addConnection(){
        MySQL db = new MySQL();
        connections.add(db);

        return db;
    }

    public void closeAllConnections() {
        if (!connections.isEmpty()) {
            for (int i = 0; i < connections.size(); i++) {
                try {
                    connections.get(i).close();
                    connections.remove(i);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
