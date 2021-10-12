package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import model.User;

public class Session{
	
<<<<<<< HEAD:Taller 2/Anderson Cardenas/AstuciaServer/src/com/Session.java
	private User user;
	private Receptor receptor;
	private Emisor emisor;
	private Session opponent;
	
=======
	private Receptor receptor;
	private Emisor emisor;
	private User user;
	private Socket socket;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

>>>>>>> origin/Taller2A00365049:Taller 2/AlejandroCoronel/AstuciaServer/src/com/Session.java
	public Session(Socket socket) {	
		try {
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

<<<<<<< HEAD:Taller 2/Anderson Cardenas/AstuciaServer/src/com/Session.java
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Session getOpponent() {
		return opponent;
	}

	public void setOpponent(Session opponent) {
		this.opponent = opponent;
	}

	
	
=======
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

>>>>>>> origin/Taller2A00365049:Taller 2/AlejandroCoronel/AstuciaServer/src/com/Session.java
}
