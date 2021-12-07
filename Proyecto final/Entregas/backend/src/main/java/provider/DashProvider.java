package provider;

import model.Membership;
import sql.MySQL;
import sql.SQLAdmin;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class DashProvider {

    private final MySQL db;

    public DashProvider() {
       db = SQLAdmin.getInstance().addConnection();;
    }

    public int earnings(String city, boolean isYear) throws SQLException, ParseException {
        MembershipProvider mp = new MembershipProvider();
        ArrayList<Membership> list = mp.getData(city, isYear);
        int value = 0;
        for (Membership membership : list ) {
            value += (int) membership.getTotalAmount() - membership.getDiscount();
        }
        return value;
    }
}
