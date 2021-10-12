package com;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.google.gson.Gson;

import model.GameStatus;
import model.Generic;
import model.Hit;
import model.User;
import model.Surrender;

public class TCPConnectionServer extends Thread implements Receptor.OnMessageListener {

	// SINGLETON
	private static TCPConnectionServer instance = null;

	private TCPConnectionServer() {
		sessions = new ArrayList<>();
		clientsTail = new LinkedList<>();
		game = new GameStatus();
		gson = new Gson();
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
	private ArrayList<Session> sessions;

	private Queue<Session> clientsTail;

	@Override
	public void run() {
		try {
			server = new ServerSocket(DISPATCHER_PORT);

			while (true) {
				System.out.println("Esperando en el puerto " + DISPATCHER_PORT);
				Socket socket = server.accept();
				System.out.println("Nuevo cliente conectado");

				Session session = new Session(socket);
				sessions.add(session);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendBroadcast(String msg) {
		for (Session session : sessions) {
			session.getEmisor().sendMessage(msg);
		}
	}

	public void sendDirectTwo(Session sessionTo, Session sessionFrom, String msg) {

		for (Session session : sessions) {
			if (session == sessionTo || session == sessionFrom) {
				session.getEmisor().sendMessage(msg);
			}
		}
	}

	public void sendDirectOne(Session sessionTo, String msg) {

		for (Session session : sessions) {
			if (session.equals(sessionTo)) {
				session.getEmisor().sendMessage(msg);
			}
		}
	}

	@Override
	public void onMessage(Session session, String msg) {

		//System.out.println(msg);
		// Gson gson = new Gson();
		Generic obj = gson.fromJson(msg, Generic.class);

		switch (obj.getType()) {
			case "User":
				User user = gson.fromJson(msg, User.class);
				// System.out.println(user.getUsername());
				session.setUser(user);
				clientsTail.add(session);
				matchPlayers();
				break;
			case "Surrender":
				Surrender s = gson.fromJson(msg, Surrender.class);
				// System.out.println("Se rindió el usuario: "+
				// session.getUser().getUsername());
				break;
			case "Hit":
				Hit hit = gson.fromJson(msg, Hit.class);
				sendDirectOne(findSession(hit.getOpponent()), msg);
				// System.out.println("Se rindió el usuario: "+
				// session.getUser().getUsername());
				break;
			case "GameStatus":
				GameStatus gameIn = gson.fromJson(msg, GameStatus.class);
				Session one = findSession(gameIn.getUsers()[0]);
				Session two = findSession(gameIn.getUsers()[1]);
				sendDirectTwo(one, two, msg);
				// System.out.println("Se rindió el usuario: "+
				// session.getUser().getUsername());
				break;
			default:

				break;
		}
	}

	private GameStatus game;
	private Gson gson;

	private void matchPlayers() {
		while (!clientsTail.isEmpty()) {
			if (game.getUsers()[0] == null) {
				clientsTail.peek().getUser().setGame(game);
				game.getUsers()[0] = clientsTail.peek().getUser();

				String msg = gson.toJson(game);
				// System.out.println(msg);
				sendDirectOne(clientsTail.poll(), msg);

			} else if (game.getUsers()[1] == null) {
				clientsTail.peek().getUser().setGame(game);
				game.getUsers()[1] = clientsTail.peek().getUser();

				String msg = gson.toJson(game);
				// sendDirectTwo(findSession(game.getUsers()[0]),
				// findSession(game.getUsers()[1]), msg);
				sendDirectTwo(findSession(game.getUsers()[0]), clientsTail.poll(), msg);
			}

			if (game.isFull()) {
				game = new GameStatus();
			}
		}
	}

	public Session findSession(User user) {
		boolean validation = false;
		Session temp = null;

		for (int i = 0; i < sessions.size() && !validation; i++) {
			if (sessions.get(i).getUser().getId().equals(user.getId())) {
				temp = sessions.get(i);
			}
		}

		return temp;
	}

}
