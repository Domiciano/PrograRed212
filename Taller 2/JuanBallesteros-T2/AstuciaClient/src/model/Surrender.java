package model;

public class Surrender {

	public String type = "Surrender";
	private User loserPlayer;

	public void setLoserPlayer(User loserPlayer) {
		this.loserPlayer = loserPlayer;
	}

	public Surrender(User loserPlayer) {
		this.loserPlayer = loserPlayer;
	}
	public User getLoserPlayer() {
		return loserPlayer;
	}
	
}
