package model;

public class Plan {

    private int id;
    private double amount;
    private String name;
    private boolean active;
    private int time;

    public Plan() {
    }

    public Plan(int id, String name, double amount, int time, boolean active) {
        this.id = id;
        this.amount = amount;
        this.name = name;
        this.active = active;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
