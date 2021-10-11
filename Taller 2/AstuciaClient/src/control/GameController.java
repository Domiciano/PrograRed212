package control;

import comm.Receptor.OnMessageListener;

import java.util.Calendar;
import java.util.UUID;

import com.google.gson.Gson;

import comm.TCPConnection;
import javafx.application.Platform;
import model.Message;
import model.Surrender;
import model.User;
import view.GameWindow;

public class GameController implements OnMessageListener {

	private GameWindow view;
	private TCPConnection connection;

	public GameController(GameWindow view) {
		this.view = view;
		init();
	}

	public void init() {
		connection = TCPConnection.getInstance();
		connection.setListenerOfMessages(this);

		int fil = (int) (3 * Math.random());
		int col = (int) (3 * Math.random());
		view.drawWeakPointInRadar(fil, col);

		view.getSendNameBtn().setOnAction(event -> {
			Gson gson = new Gson();
			User user = new User(UUID.randomUUID().toString(), view.getNameTF().getText());
			String json = gson.toJson(user);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
		});
		
		
		view.getSurrenderBtn().setOnAction(event->{
			Gson gson = new Gson();
			Surrender s = new Surrender();
			String json = gson.toJson(s);
			TCPConnection.getInstance().getEmisor().sendMessage(json);
		});

	}

	@Override
	public void onMessage(String msg) {
		Platform.runLater( // run on UI Thread
				() -> {
					Gson gson = new Gson();
					Message msjObj = gson.fromJson(msg, Message.class);
					System.out.println(msjObj.getBody());
				}

		);
	}

}
