package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import model.User;

public class Session{
	
	private User user;
	private Session enemy;
	private Receptor receptor;
	private Emisor emisor;
	private Socket socket;
	
	public Session(Socket socket) {	
		try {
			this.socket = socket;
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			receptor = new Receptor(this, reader);
			receptor.setListener(TCPConnectionServer.getInstance());
			receptor.start();
			
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			emisor = new Emisor(writer);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public Emisor getEmisor() {
		return this.emisor;
	}
	
	public Receptor getReceptor() {
		return this.receptor;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

<<<<<<< HEAD:Parcial 1/Julian Riascos/MathChallengeServer/src/com/Session.java
	public Session getEnemy() {
		return enemy;
	}

	public void setEnemy(Session enemy) {
		this.enemy = enemy;
	}

=======
<<<<<<< HEAD:Taller 2/Kevin Mera/AstuciaServer/src/com/Session.java
	public Socket getSocket() {
		return socket;
	}
	
=======
>>>>>>> 1d650def67ef607e4c5ff8e61c5503d5118e6693:Taller 2/AstuciaServer/src/com/Session.java
	
	
>>>>>>> origin/A00364415:Taller 2/Kevin Mera/AstuciaServer/src/com/Session.java
}
