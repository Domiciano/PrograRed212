package model;

public class Game {

	private String type = "Game";
	private String opponent;
	private boolean turn;
	
	public Game(String opponent, boolean turn) {
		this.setOpponent(opponent);
		turn = true;
	}
	
	public Game() {
		
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOpponent() {
		return opponent;
	}
	
	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}
	
	public boolean isTurn() {
		return turn;
	}
	
	public void setTurn(boolean turn) {
		this.turn = turn;
	}
}
