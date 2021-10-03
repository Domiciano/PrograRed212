package com;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;

import model.Accepted;
import model.Enemy;
import model.Generic;
import model.Player1;
import model.Player2;
import model.Rejected;
import model.User;

public class TCPConnectionServer extends Thread implements Receptor.OnMessageListener {

	// SINGLETON
	private static TCPConnectionServer instance = null;

	private TCPConnectionServer() {
		sessionsWaiting = new ArrayList<>();
		sessionsPlaying = new ArrayList<>();
	}

	public static synchronized TCPConnectionServer getInstance() {
		if (instance == null) {
			instance = new TCPConnectionServer();
		}
		return instance;
	}

	public static final int DISPATCHER_PORT = 5000;

	// GLOBAL
	private ServerSocket server;
	private ArrayList<Session> sessionsWaiting;
	private ArrayList<Session> sessionsPlaying;

	@Override
	public void run() {
		try {
			server = new ServerSocket(DISPATCHER_PORT);

			while (true) {
				System.out.println("Esperando en el puerto " + DISPATCHER_PORT);
				Socket socket = server.accept();
				System.out.println("Nuevo cliente conectado");

				Session session = new Session(socket);
				sessionsWaiting.add(session);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendBroadcast(String msg) {
		for (Session session : sessionsWaiting) {
			session.getEmisor().sendMessage(msg);
		}
	}

	public void defineEncounters() {
		if (sessionsWaiting.size() > 1) {
			Gson gson = new Gson();
			int usersReady = 0;
			int first = 0;
			for (int i = 0; i < sessionsWaiting.size(); i++) {
				if (sessionsWaiting.get(i).getUser() != null) {
					usersReady += 1;
					if (usersReady == 1) {
						first = i;
					}
					else if (usersReady == 2) {
						Session user1 = sessionsWaiting.get(i);
						Session user2 = sessionsWaiting.get(first);
						Enemy e1 = new Enemy(user2.getUser().getUsername());
						user1.setEnemy(e1);
						String enemy1 = gson.toJson(e1);
						user1.getEmisor().sendMessage(enemy1);
						Player1 player1 = new Player1();
						String p1 = gson.toJson(player1);
						user1.getEmisor().sendMessage(p1);
						sessionsPlaying.add(user1);


						Enemy e2 = new Enemy(user1.getUser().getUsername());
						user2.setEnemy(e2);
						String enemy2 = gson.toJson(e2);
						user2.getEmisor().sendMessage(enemy2);
						Player2 player2 = new Player2();
						String p2 = gson.toJson(player2);
						user2.getEmisor().sendMessage(p2);
						sessionsPlaying.add(user2);
						sessionsWaiting.remove(user1);
						sessionsWaiting.remove(user2);
						usersReady = 0;
					}
				}
			}
		}
	}

	@Override
	public void onMessage(Session session, String msg) {

		Gson gson = new Gson();
		Generic obj = gson.fromJson(msg, Generic.class);

		switch (obj.getType()) {
		case "User":
			boolean exists = false;
			User user = gson.fromJson(msg, User.class);
			for (int i = 0; i < sessionsWaiting.size(); i++) {
				if (sessionsWaiting.get(i).getUser() != null) {
					if (sessionsWaiting.get(i).getUser().getUsername().equalsIgnoreCase(user.getUsername())) {
						exists = true;
					}
				}
			}
			for (int i = 0; i < sessionsPlaying.size(); i++) {
				if (sessionsPlaying.get(i).getUser() != null) {
					if (sessionsPlaying.get(i).getUser().getUsername().equalsIgnoreCase(user.getUsername())) {
						exists = true;
					}
				}
			}
			if (exists) {	
				Rejected rejected = new Rejected();
				String alreadyExists = gson.toJson(rejected);
				session.getEmisor().sendMessage(alreadyExists);
			}
			else {
				Accepted accepted = new Accepted();
				String notExists = gson.toJson(accepted);
				session.getEmisor().sendMessage(notExists);
				session.setUser(user);
				defineEncounters();
			}
			break;
		case "Surrender":
			for (int i = 0; i < sessionsPlaying.size(); i++) {
				if (session.getEnemy() != null && sessionsPlaying.get(i).getUser() != null) {
					if (session.getEnemy().getUserName().equalsIgnoreCase(sessionsPlaying.get(i).getUser().getUsername())) {
						sessionsPlaying.get(i).getEmisor().sendMessage(msg);
					}
				}
			}
			break;
		case "Attack":
			for (int i = 0; i < sessionsPlaying.size(); i++) {
				if (session.getEnemy() != null && sessionsPlaying.get(i).getUser() != null) {
					if (session.getEnemy().getUserName().equalsIgnoreCase(sessionsPlaying.get(i).getUser().getUsername())) {
						sessionsPlaying.get(i).getEmisor().sendMessage(msg);
					}
				}
			}
			break;
		case "Result":
			for (int i = 0; i < sessionsPlaying.size(); i++) {
				if (session.getEnemy() != null && sessionsPlaying.get(i).getUser() != null) {
					if (session.getEnemy().getUserName().equalsIgnoreCase(sessionsPlaying.get(i).getUser().getUsername())) {
						sessionsPlaying.get(i).getEmisor().sendMessage(msg);
					}
				}
			}

			break;
		case "Disconnect":
			Session session2 = null;
			for (int i = 0; i < sessionsPlaying.size(); i++) {
				if (session.getEnemy() != null && sessionsPlaying.get(i).getUser() != null) {
					if (session.getEnemy().getUserName().equalsIgnoreCase(sessionsPlaying.get(i).getUser().getUsername())) {
						session.getEmisor().sendMessage(msg);
						session2 = sessionsPlaying.get(i);
						sessionsPlaying.get(i).getEmisor().sendMessage(msg);
					}
				}
			}
			sessionsPlaying.remove(session);
			sessionsPlaying.remove(session2);
			break;
		}
	}

}
