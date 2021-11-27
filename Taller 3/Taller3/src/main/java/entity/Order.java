package entity;

import java.time.Instant;
import java.util.Date;

public class Order {

    private int orderID;
    private Date orderDate;
    private boolean paid;
    private Date paidDate;
    private int userID;
    private String userName;
    private String userNatId;
    private int productID;
    private String productName;
    private int productLot;
    private double price;
    private double totalOrderPrice;

    public Order() {
    }

    public Order(Date orderDate, boolean paid, Date paidDate, int userID) {
        this.orderDate = orderDate;
        this.paid = paid;
        this.paidDate = paidDate;
        this.userID = userID;
    }

    public Order(int orderID, Date orderDate, boolean paid, Date paidDate, int userID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.paid = paid;
        this.paidDate = paidDate;
        this.userID = userID;
    }

    public Order(int orderID, String userName, int userID, String userNatId, Date orderDate, boolean paid, Date paidDate, int productID, String productName, int productLot, double price, double totalOrderPrice) {
        this.orderID = orderID;
        this.userName = userName;
        this.userID = userID;
        this.userNatId = userNatId;
        this.orderDate = orderDate;
        this.paid = paid;
        this.paidDate = paidDate;
        this.productID = productID;
        this.productName = productName;
        this.productLot = productLot;
        this.price = price;
        this.totalOrderPrice = totalOrderPrice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNatId() {
        return userNatId;
    }

    public void setUserNatId(String userNatId) {
        this.userNatId = userNatId;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductLot() {
        return productLot;
    }

    public void setProductLot(int productLot) {
        this.productLot = productLot;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public double getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(double totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }
}
