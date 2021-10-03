package com;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import com.google.gson.Gson;
import model.Attack;
import model.Generic;
import model.Message;
import model.NickNameValidation;
import model.Surrender;
import model.User;
import model.Winner;


public class TCPConnectionServer extends Thread implements Receptor.OnMessageListener{

	//SINGLETON
	private static TCPConnectionServer instance = null;
	private TCPConnectionServer() {
		sessions = new ArrayList<>();
	}
	public static synchronized TCPConnectionServer getInstance() {
		if(instance == null) {
			instance = new TCPConnectionServer();
		}
		return instance;
	}


	public static final int DISPATCHER_PORT = 5000;

	//GLOBAL
	private ServerSocket server;
	private ArrayList<Session> sessions;


	@Override
	public void run() {
		try {
			server = new ServerSocket(DISPATCHER_PORT);

			while(true) {
				System.out.println("Esperando en el puerto " + DISPATCHER_PORT);
				Socket socket = server.accept();
				System.out.println("Nuevo cliente conectado");

				Session session = new Session(socket);
				sessions.add(session);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Se desconectó");
		}
	}

	@Override
	public void onMessage(Session session, String msg) {
		Gson gson = new Gson();
		Generic obj = gson.fromJson(msg, Generic.class);

		if(obj != null) {
			switch (obj.getType()) {

			case "User":
				User user = gson.fromJson(msg, User.class);
				String userNameToFind = user.getUserName();
				boolean found = false;
				for (int i = 0; i < sessions.size(); i++) {
					if((!sessions.isEmpty()) && (sessions.get(i).getUser() != null)) {
						if(sessions.get(i).getUser().getUserName().equalsIgnoreCase(userNameToFind)) {
							found = true;
							sessions.remove(sessions.size()-1);
							Gson gsonNick = new Gson();
							NickNameValidation nickName2 = new NickNameValidation(userNameToFind, found);
							String json = gsonNick.toJson(nickName2);
							session.getEmisor().sendMessage(json);
							continue;
						} 
					}  
				} 	
				if(!found){
					session.setUser(user);
					System.out.println("Sesion seteada");
					if((sessions.size() % 2) == 0) {
						setOponents(session);
					}
				} 

				break;

			case "Surrender":
				Gson gsonSur = new Gson();
				Surrender surrender = new Surrender(session.getUser().getUserName());
				String jsonSur = gsonSur.toJson(surrender);
				for (int i = 0; i < sessions.size(); i++) {
					if(sessions.get(i).getUser().getOpponent() != null) {
						if(sessions.get(i).getUser().getOpponent().equals(session.getUser())) {
							sessions.get(i).getEmisor().sendMessage(jsonSur);
						}
					}
				}
				break;		

			case "Attack":
				Attack atk = gson.fromJson(msg, Attack.class);
				Gson gsonAtk = new Gson();
				Attack coordinates = new Attack(atk.getCoordinates());
				String jsonAtk = gsonAtk.toJson(coordinates);
				for (int i = 0; i < sessions.size(); i++) {
					if(sessions.get(i).getUser().getOpponent() != null) {
						if(sessions.get(i).getUser().getOpponent().equals(session.getUser())) {
							sessions.get(i).getEmisor().sendMessage(jsonAtk);
						}	
					}	
				}
				break;	

			case "Winner":
				Winner wins = gson.fromJson(msg, Winner.class);
				Gson gsonWins = new Gson();
				Winner winner = new Winner(wins.getCoordinates());
				String jsonWins = gsonWins.toJson(winner);
				for (int i = 0; i < sessions.size(); i++) {
					if(sessions.get(i).getUser().getOpponent() != null) {
						if(sessions.get(i).getUser().getOpponent().equals(session.getUser())) {
							sessions.get(i).getEmisor().sendMessage(jsonWins);
						}
					}
				}

				break;
			default:
				break;
			}
		} else {
			try {
				sessions.remove(session);
				session.getReceptor().closeReceptor();
				session.getSocket().close();			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void setOponents(Session session) {
		for (int i = 0, m = 1; i < sessions.size(); i+=2, m+=2) {
			if(sessions.get(i).getUser().getOpponent() == null && sessions.get(m).getUser().getOpponent() == null) {
				System.out.println("Se setiaron los enemigos");
				sessions.get(i).getUser().setOpponent(sessions.get(m).getUser());
				Gson gson2 = new Gson();
				Message userEnemy = new Message(sessions.get(m).getUser().getUserName());
				String json = gson2.toJson(userEnemy);
				sessions.get(i).getEmisor().sendMessage(json);

				//Se setea el otro oponente
				sessions.get(m).getUser().setOpponent(sessions.get(i).getUser());	
				Gson gson3 = new Gson();
				Message userEnemy2 = new Message(sessions.get(i).getUser().getUserName());
				String json2 = gson3.toJson(userEnemy2);
				sessions.get(m).getEmisor().sendMessage(json2);
			}
		}
	}

}
