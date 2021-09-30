package control;

import java.util.UUID;

import com.google.gson.Gson;

import comm.Receptor.OnMessageListener;
import comm.TCPConnection;
import javafx.application.Platform;
import javafx.scene.control.Button;
import model.Attack;
import model.Generic;
import model.Match;
import model.Surrender;
import model.User;
import model.Win;
import view.GameWindow;

public class GameController implements OnMessageListener {

	private GameWindow view;
	private TCPConnection connection;
	private int weakFil;
	private int weakCol;

	public GameController(GameWindow view) {
		this.view = view;
		init();
	}

	public void init() {
		connection = TCPConnection.getInstance();
		connection.setListenerOfMessages(this);

		int fil = (int) (3 * Math.random());
		int col = (int) (3 * Math.random());
		weakFil = fil;
		weakCol = col;
		view.drawWeakPointInRadar(fil, col);

		view.getSendNameBtn().setOnAction(event -> {
			if (!view.getNameTF().getText().equals("")) {
				Gson gson = new Gson();
				User user = new User(UUID.randomUUID().toString(), view.getNameTF().getText());
				String json = gson.toJson(user);
				TCPConnection.getInstance().getEmisor().sendMessage(json);
				Platform.runLater( // run on UI Thread
						() -> {
							view.getSurrenderBtn().setDisable(false);
						}

				);
			}
		});

		view.getSurrenderBtn().setOnAction(event -> {
			Gson gson = new Gson();
			Surrender s = new Surrender();
			String json = gson.toJson(s);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
			view.close();
		});
		for (int i = 0; i < view.getAtaque().length; i++) {
			for (int j = 0; j < view.getAtaque()[i].length; j++) {
				Button currentBtn = view.getAtaque()[i][j];
				int k = i;
				int l = j;
				currentBtn.setOnAction(event -> {
					currentBtn.setVisible(false);
					Gson gson = new Gson();
					Attack a = new Attack(k, l);
					String json = gson.toJson(a);
					TCPConnection.getInstance().getEmisor().sendMessage(json);
					view.disableAttack();
				});
			}
		}
	}

	@Override
	public void onMessage(String msg) {
		Gson gson = new Gson();
		Generic obj = gson.fromJson(msg, Generic.class);

		switch (obj.getType()) {
		case "Win":
			view.win(1);
			disconnect();
			break;
		case "Surrender":
			//Surrender s = gson.fromJson(msg, Surrender.class);
			view.win(2);
			break;
		case "Match":
			Match m = gson.fromJson(msg, Match.class);
			String ou = m.getOponentUsername();
			boolean turn = m.isTurn();
			Platform.runLater( // run on UI Thread
					() -> {
						view.getOpponentLabel().setText(ou);
						if (turn) {
							view.enableAttack();
						}
					}

			);
			break;
		case "Attack":
			Attack a = gson.fromJson(msg, Attack.class);
			if (a.getFil() == weakFil && a.getCol() == weakCol) {
				view.defeat();
				Win w = new Win();
				String json = gson.toJson(w);
				TCPConnection.getInstance().getEmisor().sendMessage(json);
				disconnect();
			} else {
				view.drawAttackInRadar(a.getFil(), a.getCol());
				view.enableAttack();
			}
			break;
		}
		/*
		 * Platform.runLater( // run on UI Thread () -> { Gson gson = new Gson();
		 * Message msjObj = gson.fromJson(msg, Message.class);
		 * System.out.println(msjObj.getBody()); }
		 * 
		 * );
		 */
	}

	private void disconnect() {
		TCPConnection.getInstance().disconnect();
		Platform.runLater( // run on UI Thread
				() -> {
					view.close();
				}

		);
	}

}
