package model;

public class Plan {

    private int id;
    private float amount;
    private String name;
    private boolean alive;
    private int time;

    public Plan() {
    }

    public Plan(int id, float amount, String name, boolean alive, int time) {
        this.id = id;
        this.amount = amount;
        this.name = name;
        this.alive = alive;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
