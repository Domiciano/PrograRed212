package model;

public class User {

	private String username;
	private boolean turno;
	private String id;
	public String type= "User";
	public User() {
	}
	public User(String username, String id) {
		super();
		this.username = username;
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public boolean isTurno() {
		return turno;
	}
	public void setTurno(boolean turno) {
		this.turno = turno;
	}
	
}
