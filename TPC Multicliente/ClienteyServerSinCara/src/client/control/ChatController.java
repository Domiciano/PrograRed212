package client.control;

import java.util.UUID;

import com.google.gson.Gson;

import client.comm.TCPConnection;
import client.events.OnInboxListener;
import client.model.Mensaje;
import client.view.ChatWindow;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ChatController implements OnInboxListener{
	
	
	private ChatWindow view;
	
	public ChatController(ChatWindow view) {
		this.view = view;
		init();
	}
	
	public void init() {
		TCPConnection.getInstance().setOnInboxListener(this);
		
		view.getEnviarBtn().setOnAction(event->{
			String body = view.getMensajeTF().getText();
			Mensaje mensaje = new Mensaje(UUID.randomUUID().toString(), body);
			String json = new Gson().toJson(mensaje);
			TCPConnection.getInstance().sendMessage(json);
			view.getMensajeTF().setText("");
		});
	}
	
	//Asincrono
	@Override
	public void onMessage(String msg) {
		Platform.runLater(() -> {
			view.addTextToArea(msg + "\n");
		});

	}
	

}
