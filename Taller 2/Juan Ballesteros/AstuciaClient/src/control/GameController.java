package control;

import comm.Receptor.OnMessageListener;

import java.util.UUID;

import com.google.gson.Gson;

import comm.TCPConnection;
import javafx.application.Platform;
import model.*;
import view.GameWindow;

public class GameController implements OnMessageListener, GameActions {

	private GameWindow view;
	private TCPConnection connection;
	private User thisUser;

	private User opponent;
	private AttackGridController attackGrid;
	private boolean isTurn;

	private Gson gson;

	private int weakFil;
	private int weakCol;

	private GameStatus game;

	public GameController(GameWindow view) {

		this.view = view;
		gson = new Gson();
		attackGrid = new AttackGridController(this, view);
		isTurn = false;
		init();
	}

	public void init() {
		connection = TCPConnection.getInstance();
		connection.setListenerOfMessages(this);

		int fil = (int) (3 * Math.random());
		int col = (int) (3 * Math.random());
		view.drawWeakPointInRadar(fil, col);

		weakCol = col;
		weakFil = fil;

		sendNameBtn();
		surrenderBtn();

	}

	@Override
	public void onMessage(String msg) {
		// System.out.println(msg);

		Generic obj = gson.fromJson(msg, Generic.class);

		switch (obj.getType()) {
			case "GameStatus":
				game = gson.fromJson(msg, GameStatus.class);
				OnGameUpdate(game);
				if (game.isFull() && isTurn) {
					attackGrid.gridBtnAction(true);
				} else {
					attackGrid.gridBtnAction(false);
				}
				break;
			case "Hit":
				Hit hit = gson.fromJson(msg, Hit.class);
				OnHit(hit);
				break;
		}
	}

	@Override
	public void OnGameUpdate(GameStatus game) {

		if (game.getWinner() == null) {
			String turn = "";
			// User opponent = null;

			if (game.getUsers()[0].getId().equals(thisUser.getId())) {
				opponent = game.getUsers()[1];
				thisUser.setGame(game);
				if (opponent != null) {
					opponent.setGame(game);
				}
			} else {
				opponent = game.getUsers()[0];
				thisUser.setGame(game);
				if (opponent != null) {
					opponent.setGame(game);
				}
			}

			if (game.getUsers()[0].getId().equals(thisUser.getId()) && game.isTurnPlayer1()) {
				turn = "Es su turno: " + thisUser.getUsername();
				isTurn = true;
			} else if (game.getUsers()[1].getId().equals(thisUser.getId()) && !game.isTurnPlayer1()) {
				turn = "Es su turno: " + thisUser.getUsername();
				isTurn = true;
			} else {
				isTurn = false;
				if (opponent != null) {
					turn = "Turno Oponente: " + opponent.getUsername();
				} else {
					turn = "Esperando un oponente digno...";
				}
			}

			view.changeLabel(view.getStatusLabel(), turn);
			if (opponent != null) {
				view.changeLabel(view.getOpponentLabel(), opponent.getUsername());

			} else {
				view.changeLabel(view.getOpponentLabel(), "Esperando un oponente digno...");
			}

		} else {
			if (game.getUsers()[0].getId().equals(thisUser.getId())) {
				opponent = game.getUsers()[1];
				thisUser.setGame(game);
				if (opponent != null) {
					opponent.setGame(game);
				}
			} else {
				opponent = game.getUsers()[0];
				thisUser.setGame(game);
				if (opponent != null) {
					opponent.setGame(game);
				}
			}

			if (thisUser.getId().equals(game.getWinner().getId())) {
				attackGrid.winnerGame();
			} else  {
				attackGrid.loserGame();
			}
		}

	}

	@Override
	public void OnHit(Hit hit) {
		/*
		 * System.out.println("X: " + hit.getX()); System.out.println("Y: " +
		 * hit.getY());
		 */
		if (weakCol == hit.getX() && weakFil == hit.getY()) {
			attackGrid.paintAttack(hit.getX(), hit.getY(), true);
			attackGrid.successAttack(game, hit.getX(), hit.getY());
		} else {
			attackGrid.paintAttack(hit.getX(), hit.getY(), false);
			attackGrid.failAttack(game, hit.getX(), hit.getY());
		}

	}

	private void sendNameBtn() {
		if (thisUser == null) {
			view.getSendNameBtn().setOnAction(event -> {
				thisUser = new User(UUID.randomUUID().toString(), view.getNameTF().getText());
				String json = gson.toJson(thisUser);
				TCPConnection.getInstance().getEmisor().sendMessage(json);
				Platform.runLater(() -> {
					view.getSendNameBtn().setDisable(true);
					view.getNameTF().setText(thisUser.getUsername());
					view.getNameTF().setDisable(true);
				});

			});
		} else {
			Platform.runLater(() -> {
				view.getSendNameBtn().setDisable(true);
				view.getNameTF().setText(thisUser.getUsername());
				view.getNameTF().setDisable(true);
			});
		}
	}

	private void surrenderBtn() {
		view.getSurrenderBtn().setOnAction(event -> {
			/*
			 * Surrender s = new Surrender(thisUser); String json = gson.toJson(s);
			 * TCPConnection.getInstance().getEmisor().sendMessage(json);
			 */
			game.setWinner(getOpponent());
			String json = gson.toJson(game);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
		});
	}

	public User getThisUser() {
		return thisUser;
	}

	public User getOpponent() {
		return opponent;
	}

}
