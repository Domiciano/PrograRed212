package model;

public class Hit {

    public String type = "Hit";
    private int x;
    private int y;
    private User opponent;

    public Hit(){}
    
    public Hit(int x, int y, User opponent) {
        this.x = x;
        this.y = y;
        this.opponent = opponent;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public User getOpponent() {
        return opponent;
    }

}
