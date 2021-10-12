package control;

import comm.Receptor.OnMessageListener;

import java.util.Calendar;
import java.util.UUID;

import com.google.gson.Gson;

import comm.TCPConnection;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import model.Attack;
import model.Game;
import model.Generic;
import model.Surrender;
import model.User;
import view.GameWindow;

public class GameController implements OnMessageListener {

	private GameWindow view;
	private TCPConnection connection;
	private int cFil;
	private int cCol;

	public GameController(GameWindow view) {
		this.view = view;
		init();
	}

	public void init() {
		connection = TCPConnection.getInstance();
		connection.setListenerOfMessages(this);
		int fil = (int) (3 * Math.random());
		int col = (int) (3 * Math.random());
		cFil = fil;
		cCol = col;
		view.drawWeakPointInRadar(fil, col);
		view.getSendNameBtn().setOnAction(event -> {
			Gson gson = new Gson();
			User user = new User(UUID.randomUUID().toString(), view.getNameTF().getText());
			String json = gson.toJson(user);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
			Platform.runLater(() -> {
				view.getSurrenderBtn().setDisable(false);
			});
		});

		view.getSurrenderBtn().setOnAction(event -> {
			Gson gson = new Gson();
			Surrender s = new Surrender();
			String json = gson.toJson(s);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
			Platform.runLater(() -> {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Defeat");
				alert.setHeaderText("You surrendered");
				alert.setContentText(null);
				System.exit(0);
			});
		});

		for (int i = 0; i < view.getAtaque().length; i++) {
			for (int j = 0; j < view.getAtaque()[i].length; j++) {
				Button currentBtn = view.getAtaque()[i][j];
				int f = i;
				int c = j;
				currentBtn.setOnAction(e -> {
					currentBtn.setDisable(false);
					Gson gson = new Gson();
					Attack a = new Attack(f, c);
					String json = gson.toJson(a);
					TCPConnection.getInstance().getEmisor().sendMessage(json);
					Platform.runLater(() -> {
						for (int m = 0; m < view.getAtaque().length; m++) {
							for (int n = 0; n < view.getAtaque()[m].length; n++) {
								view.getAtaque()[m][n].setDisable(false);
							}
						}
					});
				});
			}
		}
	}

	@Override
	public void onMessage(String msg) {
		Gson gson = new Gson();
		Generic obj = gson.fromJson(msg, Generic.class);

		switch (obj.getType()) {
		case "Attack":
			Attack a = gson.fromJson(msg, Attack.class);
			if (a.getF() == cFil && a.getC() == cCol) {
				Platform.runLater(() -> {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Defeat");
					alert.setHeaderText("your opponent hit you");
					alert.showAndWait();
				});
			}
			break;

		case "Surrender":
			Platform.runLater(() -> {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Victory");
				alert.setHeaderText("Your opponent win");
				alert.setContentText("your opponent hit you");
				alert.showAndWait();
				System.exit(0);
			});
			break;

		case "Win":
			Platform.runLater(() -> {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Victory");
				alert.setHeaderText("You win");
				alert.setContentText("You hit your opponent");
				alert.showAndWait();
				System.exit(0);
			});
			break;

		case "Game":
			Game g = gson.fromJson(msg, Game.class);
			String o = g.getOpponent();
			boolean turn = g.isTurn();
			Platform.runLater(() -> {
						view.getOpponentLabel().setText(o);
						if (turn) {
							Platform.runLater(() -> {
								for (int m = 0; m < view.getAtaque().length; m++) {
									for (int n = 0; n < view.getAtaque()[m].length; n++) {
										view.getAtaque()[m][n].setDisable(false);
									}
								}
							});
						}
					}

			);
			break;
		}
	}

}
