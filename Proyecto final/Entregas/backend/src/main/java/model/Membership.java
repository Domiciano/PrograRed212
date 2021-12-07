package model;

import java.util.Date;

public class Membership {

    private int id;
    private int venueID;
    private int planID;
    private double totalAmount;
    private double discount;
    private Date startDate;
    private Date endDate;

    public Membership() {
    }

    public Membership(int id, double totalAmount, double discount, Date startDate, Date endDate, int planID, int venueID) {
        this.id = id;
        this.venueID = venueID;
        this.planID = planID;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVenueID() {
        return venueID;
    }

    public void setVenueID(int venueID) {
        this.venueID = venueID;
    }

    public int getPlanID() {
        return planID;
    }

    public void setPlanID(int planID) {
        this.planID = planID;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
