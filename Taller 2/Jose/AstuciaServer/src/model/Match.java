package model;

public class Match {

	public String type = "Match";
	private String oponentUsername;
	private boolean turn;
	
	public Match() {}
	public Match(String oponentUsername,boolean turn) {
		this.setOponentUsername(oponentUsername);
		this.setTurn(turn);
	}
	public String getOponentUsername() {
		return oponentUsername;
	}
	public void setOponentUsername(String oponentUsername) {
		this.oponentUsername = oponentUsername;
	}
	public boolean isTurn() {
		return turn;
	}
	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	
}
