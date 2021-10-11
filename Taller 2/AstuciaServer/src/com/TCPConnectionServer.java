package com;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

import model.Generic;
import model.User;
import model.Surrender;

public class TCPConnectionServer extends Thread implements Receptor.OnMessageListener {

	// SINGLETON
	private static TCPConnectionServer instance = null;

	private TCPConnectionServer() {
		sessions = new ArrayList<>();
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

	@Override
	public void onMessage(Session session, String msg) {
		
		System.out.println(msg);
		Gson gson = new Gson();
		Generic obj = gson.fromJson(msg, Generic.class);
		
		switch (obj.getType()) {
		case "User":
			User user = gson.fromJson(msg, User.class);
			System.out.println(user.getUsername());
			session.setUser(user);
			break;
		case "Surrender":
			Surrender s = gson.fromJson(msg, Surrender.class);
			System.out.println("Se rindió el usuario: "+session.getUser().getUsername());
			break;
		}
	}

}
