package model;

import java.util.ArrayList;

public class Game {

	private User player1;
	private User player2;
	private ArrayList<User> players;

	public Game(User p, User p2) {
		super();
		player1 = p;
		player2 = p2;
	}

	public Game() {
		if(players == null) {
			players = new ArrayList<>();
		}
	}

	public User getPlayer1() {
		return player1;
	}
	public void setPlayer1(User player1) {
		this.player1 = player1;
	}
	public User getPlayer2() {
		return player2;
	}
	public void setPlayer2( User player2) {
		this.player2 = player2;

	}

	public ArrayList<User> getPlayers() {
		return players;
	}

}
