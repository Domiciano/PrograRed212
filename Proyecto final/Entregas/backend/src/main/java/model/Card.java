package model;

import java.util.Date;

public class Card {

    private Client client;
    private Date memEndDate;
    private String planName;
    private String status;

    public Card() {
    }

    public Card(Client client, Date memEndDate, String planName, String status) {
        this.client = client;
        this.memEndDate = memEndDate;
        this.planName = planName;
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getMemEndDate() {
        return memEndDate;
    }

    public void setMemEndDate(Date memEndDate) {
        this.memEndDate = memEndDate;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
