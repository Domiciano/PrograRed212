package model;

public class User {

	private String id;
	private String userName;
	private User opponent;
	@SuppressWarnings("unused")
	private String type = "User";
	
	
	
	
	public User(String id, String userName) {
		this.id = id;
		this.userName = userName;
	}
	public User() {}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public User getOpponent() {
		return opponent;
	}
	public void setOpponent(User opponent) {
		this.opponent = opponent;
	}	
}
