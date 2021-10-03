package model;

public class GameStatus {

    public String type = "GameStatus";

    private User[] users;  
    private User winner;

    private boolean turnPlayer1;
    private boolean isFull;

    private int lastX;
    private int lastY;

    public GameStatus() {
        users = new User[2];
        turnPlayer1 = true;
        isFull = false;
    }

    // --------------------------------- Getters and Setters

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public User getWinner() {
        return winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    public boolean isTurnPlayer1() {
        return turnPlayer1;
    }

    public void setTurnPlayer1(boolean turnPlayer1) {
        this.turnPlayer1 = turnPlayer1;
    }

    public boolean isFull() {
        if(users[0] != null && users[1] != null){
            isFull=true;
        }
        return isFull;
    } 
    
    public int getLastX() {
        return lastX;
    }

    public void setLastX(int lastX) {
        this.lastX = lastX;
    }

    public int getLastY() {
        return lastY;
    }

    public void setLastY(int lastY) {
        this.lastY = lastY;
    } 
    
}
