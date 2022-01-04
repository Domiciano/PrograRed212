package client.view;

import client.comm.TCPConnection;
import client.control.ChatController;
import client.events.OnInboxListener;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChatWindow extends Stage  {

	private TextArea mensajesTA;
	private TextField mensajeTF;
	private Button enviarBtn;
	
	private ChatController controller;

	public ChatWindow() {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatWindow.fxml"));
			Parent root = loader.load();

			mensajesTA = (TextArea) loader.getNamespace().get("mensajesTA");
			mensajesTA.setEditable(false);
			mensajeTF = (TextField) loader.getNamespace().get("mensajeTF");
			enviarBtn = (Button) loader.getNamespace().get("enviarBtn");

			Scene scene = new Scene(root, 600, 400);
			setScene(scene);


			controller = new ChatController(this);
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public TextArea getMensajesTA() {
		return mensajesTA;
	}

	public TextField getMensajeTF() {
		return mensajeTF;
	}

	public Button getEnviarBtn() {
		return enviarBtn;
	}

	public void addTextToArea(String text) {
		mensajesTA.appendText(text);
		//...
		//...
		//...
		//...
	}
	
	



	

}
