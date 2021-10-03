package model;

public class User {
	
	
	public String type = "User";
	private String id;
	private String username;	

	private transient GameStatus game;
	
	public User() {}
	
	public User(String id, String username) {
		this.id = id;
		this.username = username;
	}

	// --------------------------------- Getters and Setters
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public GameStatus getGame() {
		return game;
	}

	public void setGame(GameStatus game) {
		this.game = game;
	}
	
	

}
