package provider;


import db.MySQL;
import entity.Product;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductProvider {

    public void create(Product product) throws ClassNotFoundException, SQLException {
        MySQL db = new MySQL();
        db.connect();
        String sql = "INSERT INTO productsA00364415(productName, price) VALUES ('%NAME','%PRICE')";
        sql = sql.replace("%NAME", product.getProductName());
        sql = sql.replace("%PRICE",product.getPrice()+"");
        db.commandSQL(sql);
    }

    public ArrayList<Product> getAll() throws ClassNotFoundException, SQLException {
        MySQL db = new MySQL();
        db.connect();
        ArrayList<Product> products = new ArrayList<>();
        ResultSet resultSet = db.getDataBySQL("SELECT * FROM productsA00364415");
        while(resultSet.next()){
            products.add(new Product(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3)
            ));
        }
        db.close();
        return products;
    }

}
