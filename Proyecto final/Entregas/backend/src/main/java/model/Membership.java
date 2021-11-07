package model;

import java.util.Date;

public class Membership {

    private int id;
    private int venueID;
    private int planID;
    private float totalAmount;
    private float discount;
    private Date startDate;
    private Date endDate;

    public Membership() {
    }

    public Membership(int id, int venueID, int planID, float totalAmount, float discount, Date startDate, Date endDate) {
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

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
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
