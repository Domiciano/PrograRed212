package com;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;

import model.Attack;
import model.Game;
import model.Generic;
import model.Message;
import model.Surrender;
import model.User;

public class TCPConnectionServer extends Thread implements Receptor.OnMessageListener{

	//SINGLETON
	private static TCPConnectionServer instance = null;
	private TCPConnectionServer() {
		sessions = new ArrayList<>();
		games =new ArrayList<>();
		g = new Game();
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
	private ArrayList<Game> games;
	private Game g;

	@Override
	public void run() {
		try {
			server = new ServerSocket(DISPATCHER_PORT);
			while(true) {
				System.out.println("Esperando en el puerto " + DISPATCHER_PORT);
				Socket socket = server.accept();
				System.out.println("Nuevo cliente conectado");
				Session session = new Session(socket);
				session.setSocket(socket);
				sessions.add(session);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendBroadcast(String msg) {
		for(Session session : sessions) {
			session.getEmisor().sendMessage(msg);
		}
	}

	public void sendDirect(User u, String msg) {

		for(int i=0 ; i<sessions.size() ; i++) {
			if(sessions.get(i).getUser() == u) {
				sessions.get(i).getEmisor().sendMessage(msg);
				break;
			}
		}
	}
	public Session searchSessionFromUser(User u) {
		Session s = null;
		for(int i = 0; i<sessions.size();i++) {
			if(sessions.get(i).getUser() == u) {
				s= sessions.get(i);
				break;
			}
		}
		return s;
	}

	public void initGame(User player1, User player2) {

		Game g = new Game(player1, player2);
		games.add(g);
	}

	public User searchOtherUserOnGame(User p) {	
		User u = null;
		for(int i = 0; i< games.size();i++) {	
			if(games.get(i).getPlayer1() == p || games.get(i).getPlayer2() == p) {			
				if(games.get(i).getPlayer1() == p) {			
					u = games.get(i).getPlayer2();
					break;
				}else {			
					u = games.get(i).getPlayer1();
					break;
				}
			}
		}
		return u;		
	}

	public boolean verifyUser(User u) {
		boolean c = true;	
		if(g.getPlayers().size() >= 1) {
			for(int i = 0; i< g.getPlayers().size();i++) {	
				if(u.getUsername().equalsIgnoreCase(g.getPlayers().get(i).getUsername())) {
					c = false;
				}
			}

		}
		return c;
	}
	@Override
	public void onMessage(Session session, String msg) {
			Gson gson = new Gson();
			Generic obj = gson.fromJson(msg, Generic.class);
			switch(obj.getType()) {
			case "User":
				User user = gson.fromJson(msg,User.class);
				boolean verify = verifyUser(user);

				if(verify == true) {	
					session.setUser(user);
					g.addPlayer(user);	
					int par = g.getPlayers().size() % 2;
					//Cuando hay 2 jugadores, se crea un juego
					if (g.getPlayers().size() > 1 && par == 0) {	
						initGame(g.getPlayers().get(g.searchPlayer(user)-1), user);
						g.getPlayers().get(g.searchPlayer(user)-1).setTurno(true);
						session.getUser().setTurno(false);

						//Cuando se forma la partida(game) de 2 jugadores, se envia a cada jugador que oponente le toca enfrentar
						//Se envia a traves de un mensaje directo a partir del usuario
						String json2 = gson.toJson(user);
						sendDirect(g.getPlayers().get(g.searchPlayer(user)-1), json2);
						String json = gson.toJson(g.getPlayers().get(g.searchPlayer(user)-1));
						sendDirect(user , json);
					}

				}else {
					session.setUser(user);
					Message m = new Message();
					String jsonMs = gson.toJson(m);
					sendDirect(user , jsonMs);	
				}

				break;
			case "Surrender":
				Surrender s = gson.fromJson(msg,Surrender.class);
				String jsonS = gson.toJson(s);
				User us = searchOtherUserOnGame(session.getUser());
				sendDirect(us, jsonS);

				break;	 

			case "Attack":
				Attack a = gson.fromJson(msg,Attack.class);
				String jsonA = gson.toJson(a);
				User u = searchOtherUserOnGame(session.getUser());
				if(a.getResult() == null) {
					session.getUser().setTurno(false);
					u.setTurno(true);
					sendDirect(u,jsonA);

				}else if(a.getResult().equalsIgnoreCase("failure")) {	

					sendDirect(u,jsonA);				
				}else {

					sendDirect(u,jsonA);
				}		
				break;

			case "Disconnect":	
				User ou = searchOtherUserOnGame(session.getUser());
				Session os = searchSessionFromUser(ou);	
				sessions.remove(os);
				sessions.remove(session);
				g.getPlayers().remove(ou);
				g.getPlayers().remove(session.getUser());
				try {		
					os.getSocket().close();
					session.getSocket().close();

				} catch (IOException e) {
					//TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;	
			}
	}

}
