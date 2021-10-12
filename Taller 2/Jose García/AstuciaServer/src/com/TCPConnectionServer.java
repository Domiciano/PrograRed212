package com;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;

import model.Generic;
import model.User;
//import model.Surrender;

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
				checkMatch();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void checkMatch() {
		if(sessions.size()%2==0) {
			Session firstAlone,secondAlone;
			for (int i = 0; i < sessions.size(); i++) {
				if(sessions.get(i).getOpponent()==null&&sessions.get(i).getUser()!=null) {
					firstAlone=sessions.get(i);
					for (int j = i+1; j < sessions.size(); j++) {
						if(sessions.get(j).getOpponent()==null&&sessions.get(j).getUser()!=null) {
							secondAlone=sessions.get(j);
							firstAlone.setOpponent(secondAlone);
							secondAlone.setOpponent(firstAlone);
							firstAlone.startMatch(true);
							secondAlone.startMatch(false);
						}
					}
				}
			}
		}
	}

	/*public void sendBroadcast(String msg) {
		for (Session session : sessions) {
			session.getEmisor().sendMessage(msg);
		}
	}*/

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
			checkMatch();
			break;
		case "Surrender":
			//Surrender s = gson.fromJson(msg, Surrender.class);
			if(session.getOpponent()!=null) {
				session.getOpponent().getEmisor().sendMessage(msg);
				sessions.remove(session.getOpponent());
			}			
			System.out.println("Se rindió el usuario: "+session.getUser().getUsername());
			sessions.remove(session);
			break;
		case "Attack":
			session.getOpponent().getEmisor().sendMessage(msg);
			break;
		case "Win":
			session.getOpponent().getEmisor().sendMessage(msg);
			break;
		}
	}

}
