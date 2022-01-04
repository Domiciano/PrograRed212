package client.view;

import client.comm.TCPConnection;
import client.control.ConnectionController;
import client.events.OnConnectionListener;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ConnectionWindow extends Stage {

	private TextField ipTF;
	private TextField portTF;
	private Button connectBtn;
	
	private ConnectionController controller;
	
	public ConnectionWindow() {
		VBox parent = new VBox();
		
		ipTF = new TextField("127.0.0.1");
		ipTF.setPromptText("Direccion IP");
		portTF = new TextField("5000");
		portTF.setPromptText("Puerto de red");
		connectBtn = new Button("Conectar");
		
		parent.getChildren().add(ipTF);
		parent.getChildren().add(portTF);
		parent.getChildren().add(connectBtn);
		
		Scene scene = new Scene(parent, 600,400);
		setScene(scene);
		
		
		controller = new ConnectionController(this);
		controller.init();
	}

	
	//Getters de los views
	public TextField getIpTF() {
		return ipTF;
	}

	public TextField getPortTF() {
		return portTF;
	}

	public Button getConnectBtn() {
		return connectBtn;
	}


	public void openChatWindow() {
		this.close();
		ChatWindow chat = new ChatWindow();
		chat.show();
	}
	
	

	
	
	
}
