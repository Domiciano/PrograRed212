package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import model.User;

public class Session{
	
<<<<<<< HEAD:Taller 2/AlejandroCoronel/AstuciaServer/src/com/Session.java
=======
	private User user;
>>>>>>> 1d650def67ef607e4c5ff8e61c5503d5118e6693:Taller 2/AstuciaServer/src/com/Session.java
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

<<<<<<< HEAD:Taller 2/AlejandroCoronel/AstuciaServer/src/com/Session.java
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

=======
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
>>>>>>> 1d650def67ef607e4c5ff8e61c5503d5118e6693:Taller 2/AstuciaServer/src/com/Session.java
}
