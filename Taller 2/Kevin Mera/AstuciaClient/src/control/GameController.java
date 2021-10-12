package control;

import comm.Receptor.OnMessageListener;

import java.util.UUID;


import com.google.gson.Gson;
import comm.TCPConnection;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import model.Attack;
import model.Generic;
import model.Message;
import model.NickNameValidation;
import model.Surrender;
import model.User;
import model.Winner;
import view.GameWindow;

public class GameController implements OnMessageListener{

	private GameWindow view;
	private TCPConnection connection;

	public GameController(GameWindow view) {
		this.view = view;
		initializeAttackMatrix(true);
		init();


	}

	public void init() {
		connection = TCPConnection.getInstance();
		connection.setListenerOfMessages(this);

		int fil = (int) (3 * Math.random());
		int col = (int) (3 * Math.random());
		view.drawWeakPointInRadar(fil, col);

		view.getQueueBtn().setOnAction(
				event ->{
					Gson gson = new Gson();
					User user = new User(UUID.randomUUID().toString(), view.getNameLabel().getText());
					String json = gson.toJson(user);
					connection.getEmisor().sendMessage(json);
					view.getQueueBtn().setDisable(true);
					view.getStatusLabel().setText("Esperando oponente...");

				});

		view.getSurrenderBtn().setOnAction(event->{
			initializeAttackMatrix(true);
			view.getWinsLabel().setTextFill(Color.color(1, 0, 0));
			view.getWinsLabel().setText(">> ¡PERDEDOR! <<");
			view.getSurrenderBtn().setDisable(true);
			view.getStatusLabel().setText("Partida finalizada");
			view.getStatusLabel().setTextFill(Color.color(1, 0, 0));
			Gson gson = new Gson();
			Surrender s = new Surrender();
			String json = gson.toJson(s);
			connection.getEmisor().sendMessage(json);
			connection.interrupt();
		});

		view.getAtaque()[0][0].setOnAction(event->{
			initializeAttackMatrix(true);
			Gson gson = new Gson();
			Attack atk = new Attack("0 0");
			String json = gson.toJson(atk);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
		});

		view.getAtaque()[0][1].setOnAction(event->{
			initializeAttackMatrix(true);
			Gson gson = new Gson();
			Attack atk = new Attack("0 1");
			String json = gson.toJson(atk);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
		});

		view.getAtaque()[0][2].setOnAction(event->{
			initializeAttackMatrix(true);
			Gson gson = new Gson();
			Attack atk = new Attack("0 2");
			String json = gson.toJson(atk);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
		});
		view.getAtaque()[1][0].setOnAction(event->{
			initializeAttackMatrix(true);
			Gson gson = new Gson();
			Attack atk = new Attack("1 0");
			String json = gson.toJson(atk);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
		});
		view.getAtaque()[1][1].setOnAction(event->{
			initializeAttackMatrix(true);
			Gson gson = new Gson();
			Attack atk = new Attack("1 1");
			String json = gson.toJson(atk);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
		});
		view.getAtaque()[1][2].setOnAction(event->{
			initializeAttackMatrix(true);
			Gson gson = new Gson();
			Attack atk = new Attack("1 2");
			String json = gson.toJson(atk);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
		});
		view.getAtaque()[2][0].setOnAction(event->{
			initializeAttackMatrix(true);
			Gson gson = new Gson();
			Attack atk = new Attack("2 0");
			String json = gson.toJson(atk);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
		});
		view.getAtaque()[2][1].setOnAction(event->{
			initializeAttackMatrix(true);
			Gson gson = new Gson();
			Attack atk = new Attack("2 1");
			String json = gson.toJson(atk);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
		});
		view.getAtaque()[2][2].setOnAction(event->{
			initializeAttackMatrix(true);
			Gson gson = new Gson();
			Attack atk = new Attack("2 2");
			String json = gson.toJson(atk);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
		});
		
		view.setOnCloseRequest(event->{

			connection.getEmisor().sendMessage(null);

		});
	}

	public void initializeAttackMatrix(boolean MatrixState) {
		Platform.runLater( // run on UI Thread
				() -> {
					for (int i = 0; i < view.getAtaque().length; i++) {
						for (int j = 0; j < view.getAtaque().length; j++) {
							view.getAtaque()[i][j].setDisable(MatrixState);

						}
					}
				}

				);
	}

	@Override
	public void onMessage(String msg) {
		Platform.runLater( // run on UI Thread
				() -> {
					Gson gson = new Gson();
					Generic obj = gson.fromJson(msg, Generic.class);
					switch (obj.getType()) {
					case "Message":
						Message msgInbound = gson.fromJson(msg, Message.class);
						view.getOpponentLabel().setText(msgInbound.getBody());
						view.getOpponentLabel().setTextFill(Color.color(1, 0, 0));
						view.getStatusLabel().setText("En partida");
						view.getStatusLabel().setTextFill(Color.color(0, 1, 0));
						initializeAttackMatrix(false);
						break;

					case "NickNameValidation":
						NickNameValidation nickVal = gson.fromJson(msg, NickNameValidation.class);
						if(nickVal.getNickStatus()) {
							Alert a = new Alert(Alert.AlertType.WARNING);
							a.setContentText("Nombre de usuario repetido, por favor cambielo e intente nuevamente");
							a.show();
							view.close();
							connection.getEmisor().sendMessage(null);
						}
						break;
					case "Attack":
						Attack atkInbound = gson.fromJson(msg, Attack.class);
						String[] cord = (atkInbound.getCoordinates().split(" "));
						int fil = Integer.parseInt(cord[0]);
						int col = Integer.parseInt(cord[1]);
						if (view.getRadar()[fil][col].getStyle().equalsIgnoreCase("-fx-background-color: yellow;")) {
							Gson gsonWin = new Gson();
							Winner winner = new Winner(atkInbound.getCoordinates());
							String jsonWin = gsonWin.toJson(winner);
							connection.getEmisor().sendMessage(jsonWin);
							initializeAttackMatrix(true);
							view.drawAttackInRadar(fil, col, false);
							view.getStatusLabel().setText("Partida finalizada");
							view.getStatusLabel().setTextFill(Color.color(1, 0, 0));
							view.getWinsLabel().setTextFill(Color.color(1, 0, 0));
							view.getWinsLabel().setText(">> ¡PERDEDOR! <<");
							view.getSurrenderBtn().setDisable(true);
						} else {
							view.drawAttackInRadar(fil, col, false);
							initializeAttackMatrix(false);
						}
						break;

					case "Winner":
						Winner iWon = gson.fromJson(msg, Winner.class);
						String[] cordWins = (iWon.getCoordinates().split(" "));
						int filW = Integer.parseInt(cordWins[0]);
						int colW = Integer.parseInt(cordWins[1]);
						view.drawAttackInRadar(filW, colW, true);
						view.getSurrenderBtn().setDisable(true);
						view.getStatusLabel().setText("Partida finalizada");
						view.getStatusLabel().setTextFill(Color.color(1, 0, 0));
						view.getWinsLabel().setTextFill(Color.color(0, 1, 0));
						view.getWinsLabel().setText(">> ¡GANADOR! <<");	
						initializeAttackMatrix(true);
						break;

					case "Surrender":
						Surrender sWon = gson.fromJson(msg, Surrender.class);
						view.getSurrenderBtn().setDisable(true);
						view.getWinsLabel().setTextFill(Color.color(0, 1, 0));
						view.getStatusLabel().setTextFill(Color.color(1, 0, 0));
						view.getStatusLabel().setText("Partida finalizada");
						view.getWinsLabel().setText(">> ¡GANADOR! <<");	
						Alert a = new Alert(Alert.AlertType.WARNING);
						a.setContentText("El jugador: "+sWon.getMsg()+" se ha rendido.\n¡Ganas el juego!");
						a.show();
						initializeAttackMatrix(true);
						break;

					default:
						break;
					}

				}

				);
	}
}
