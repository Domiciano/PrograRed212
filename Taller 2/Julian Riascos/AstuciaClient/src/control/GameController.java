package control;

import comm.Receptor.OnMessageListener;

import java.io.IOException;
import java.util.Hashtable;
import java.util.UUID;

import com.google.gson.Gson;

import comm.TCPConnection;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import model.Generic;
import model.Result;
import model.Surrender;
import model.User;
import model.Attack;
import model.Disconnect;
import model.Enemy;
import view.GameWindow;

public class GameController implements OnMessageListener {

	private GameWindow view;
	private TCPConnection connection;
	private int[] weakPoint = new int[2];
	private Hashtable<String, Button> attacks;

	public GameController(GameWindow view) {
		this.view = view;
		init();
	}

	public void init() {
		attacks = new Hashtable<>();
		connection = TCPConnection.getInstance();
		connection.setListenerOfMessages(this);

		int row = (int) (3 * Math.random());
		int col = (int) (3 * Math.random());
		view.drawWeakPointInRadar(row, col);
		weakPoint[0] = row;
		weakPoint[1] = col;

		notUserYet();

		view.getSendNameBtn().setOnAction(event -> {
			if (view.getNameTF().getText().equals("")) {
				view.getWarningLabel().setText("¡Necesitas un nombre de usuario!");
			}
			else {
				Gson gson = new Gson();
				User user = new User(UUID.randomUUID().toString(), view.getNameTF().getText());
				String json = gson.toJson(user);
				TCPConnection.getInstance().getEmisor().sendMessage(json);
			}
		});

		view.getSurrenderBtn().setOnAction(event->{
			Gson gson = new Gson();
			Surrender s = new Surrender();
			String json = gson.toJson(s);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
			view.getSurrenderBtn().setDisable(true);
			notYourTurn();
			view.getResultLabel().setText("¡Perdiste!");
			view.getStatusLabel().setText("Terminado");
		});
	}

	public void readyToStart() {
		view.getAtaque()[0][0].setOnAction(event->{
			Gson gson = new Gson();
			Attack attack = new Attack(0,0);
			String json = gson.toJson(attack);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
			notYourTurn();
			attacks.put("0,0", view.getAtaque()[0][0]);
		});

		view.getAtaque()[0][1].setOnAction(event->{
			Gson gson = new Gson();
			Attack attack = new Attack(0,1);
			String json = gson.toJson(attack);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
			notYourTurn();
			attacks.put("0,1", view.getAtaque()[0][1]);
		});

		view.getAtaque()[0][2].setOnAction(event->{
			Gson gson = new Gson();
			Attack attack = new Attack(0,2);
			String json = gson.toJson(attack);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
			notYourTurn();
			attacks.put("0,2", view.getAtaque()[0][2]);
		});

		view.getAtaque()[1][0].setOnAction(event->{
			Gson gson = new Gson();
			Attack attack = new Attack(1,0);
			String json = gson.toJson(attack);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
			notYourTurn();
			attacks.put("1,0", view.getAtaque()[1][0]);
		});

		view.getAtaque()[1][1].setOnAction(event->{
			Gson gson = new Gson();
			Attack attack = new Attack(1,1);
			String json = gson.toJson(attack);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
			notYourTurn();
			attacks.put("1,1", view.getAtaque()[1][1]);
		});

		view.getAtaque()[1][2].setOnAction(event->{
			Gson gson = new Gson();
			Attack attack = new Attack(1,2);
			String json = gson.toJson(attack);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
			notYourTurn();
			attacks.put("1,2", view.getAtaque()[1][2]);
		});

		view.getAtaque()[2][0].setOnAction(event->{
			Gson gson = new Gson();
			Attack attack = new Attack(2,0);
			String json = gson.toJson(attack);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
			notYourTurn();
			attacks.put("2,0", view.getAtaque()[2][0]);
		});

		view.getAtaque()[2][1].setOnAction(event->{
			Gson gson = new Gson();
			Attack attack = new Attack(2,1);
			String json = gson.toJson(attack);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
			notYourTurn();
			attacks.put("2,1", view.getAtaque()[2][1]);
		});

		view.getAtaque()[2][2].setOnAction(event->{
			Gson gson = new Gson();
			Attack attack = new Attack(2,2);
			String json = gson.toJson(attack);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
			notYourTurn();
			attacks.put("2,2", view.getAtaque()[2][2]);
		});
	}

	public void yourTurn() {
		view.getResultLabel().setText("Tu turno");
		for (int i = 0; i < view.getAtaque().length; i++) {
			for (int j = 0; j < view.getAtaque()[i].length; j++) {
				if (!attacks.contains(view.getAtaque()[i][j])) {
					view.getAtaque()[i][j].setDisable(false);
				}
				else {
					view.getAtaque()[i][j].setDisable(true);
				}
			}
		}
	}

	public void notYourTurn() {
		view.getResultLabel().setText("Turno del rival");
		for (int i = 0; i < view.getAtaque().length; i++) {
			for (int j = 0; j < view.getAtaque()[i].length; j++) {
				view.getAtaque()[i][j].setDisable(true);
			}
		}
	}

	public void notUserYet() {
		for (int i = 0; i < view.getAtaque().length; i++) {
			for (int j = 0; j < view.getAtaque()[i].length; j++) {
				view.getAtaque()[i][j].setDisable(true);
			}
		}
		view.getSurrenderBtn().setDisable(true);
	}

	@Override
	public void onMessage(String msg) {
		Platform.runLater(() -> {
			try {
				Gson gson = new Gson();
				Generic obj = gson.fromJson(msg, Generic.class);

				switch (obj.getType()) {
				case "Rejected":
					view.getWarningLabel().setFont(new Font("System", 16));
					view.getWarningLabel().setText("¡Ya existe ese nombre!");
					break;
				case "Accepted":
					view.getWarningLabel().setText("");
					view.getNameTF().setDisable(true);
					view.getSendNameBtn().setDisable(true);
					view.getSurrenderBtn().setDisable(false);
					readyToStart();
					break;
				case "Enemy":
					Enemy enemy = gson.fromJson(msg, Enemy.class);
					view.getOpponentLabel().setText(enemy.getUserName());
					break;
				case "Player1":
					view.getWarningLabel().setFont(new Font("System", 16));
					view.getWarningLabel().setText("Jugador 1");
					view.getStatusLabel().setText("En partida");
					yourTurn();
					break;
				case "Player2":
					view.getWarningLabel().setFont(new Font("System", 16));
					view.getWarningLabel().setText("Jugador 2");
					view.getStatusLabel().setText("En partida");
					notYourTurn();
					break;
				case "Attack":
					Attack attack = gson.fromJson(msg, Attack.class);
					view.drawAttackInRadar(attack.getRow(), attack.getColumn());
					if (attack.getRow() == weakPoint[0] && attack.getColumn() == weakPoint[1]) {
						Result result = new Result();
						String json = gson.toJson(result);
						TCPConnection.getInstance().getEmisor().sendMessage(json);
						view.getResultLabel().setText("¡Perdiste!");
						view.getStatusLabel().setText("Terminado");
						view.getSurrenderBtn().setDisable(true);
					} else {
						yourTurn();
					}
					break;
				case "Result":
					view.getResultLabel().setText("¡Ganaste!");
					view.getStatusLabel().setText("Terminado");
					Disconnect disconnect = new Disconnect();
					String disc = gson.toJson(disconnect);
					TCPConnection.getInstance().getEmisor().sendMessage(disc);
					view.getSurrenderBtn().setDisable(true);
					break;
				case "Surrender":
					Disconnect disconnect2 = new Disconnect();
					String disc2 = gson.toJson(disconnect2);
					TCPConnection.getInstance().getEmisor().sendMessage(disc2);
					notYourTurn();
					view.getStatusLabel().setText("Terminado");
					view.getResultLabel().setText("¡Ganaste!");
					view.getSurrenderBtn().setDisable(true);
					break;
				case "Disconnect":
					TCPConnection.getInstance().disconnect();
					break;
				}
			}catch(IOException e) {
				System.out.println("Cliente desconectado");
			}
		});
	}

}
