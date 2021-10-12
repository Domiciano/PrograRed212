package control;

import comm.Receptor.OnMessageListener;

import java.io.File;
import java.util.UUID;
import com.google.gson.Gson;
import comm.TCPConnection;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.Surrender;
import model.User;
import model.Attack;
import model.Disconnect;
import model.Generic;
import view.GameWindow;

public class GameController implements OnMessageListener{
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
		attack();
		view.getSendNameBtn().setOnAction(
				event ->{
					Gson gson = new Gson();
					User user = new User(view.getNameTF().getText(),UUID.randomUUID().toString());
					String json = gson.toJson(user);
					TCPConnection.getInstance().getEmisor().sendMessage(json);
				});

		view.getSurrenderBtn().setOnAction(
				event ->{
					Gson gson = new Gson();
					Surrender s  = new Surrender();
					String json = gson.toJson(s);
					TCPConnection.getInstance().getEmisor().sendMessage(json);
					showAlert(true);
					disconnectfromServer();

				});

	}
	@Override
	public void onMessage(String msg) {
		Platform.runLater( // run on UI Thread
				() -> {
					Gson gson = new Gson();
					Generic msjObj = gson.fromJson(msg, Generic.class);
					switch(msjObj.getType()) {
					case "Message":
						Alert alert = new Alert(Alert.AlertType.ERROR);
						view.getNameTF().clear();
						alert.setHeaderText("Nickname repetido");
						alert.setContentText("El nombre de usuario que ingreso esta repetido, por favor ingrese otro");
						alert.show();
						break;
					case "User":
						User u = gson.fromJson(msg, User.class);
						view.getOpponentLabel().setText(u.getUsername());
						view.getSendNameBtn().setDisable(true);
						view.getNameTF().setDisable(true);
						if(u.isTurno() == true) {
							enableAttack(false);
						}
						break;
					case "Attack":
						Attack a = gson.fromJson(msg, Attack.class);
						if(a.getResult() == null) {
							boolean r = checkRadar(a.getCell());
							enableAttack(true);
							if(r == true) {
								a.setResult("success");
								showAlert(true);
								disconnectfromServer();
							}else {
								a.setResult("failure");
							}
							String json = gson.toJson(a);
							TCPConnection.getInstance().getEmisor().sendMessage(json);

						}else if(a.getResult().equalsIgnoreCase("success")){		
							showAlert(false);
							view.close();
							disconnectfromServer();
							Disconnect d = new Disconnect();
							String json2 = gson.toJson(d);
							TCPConnection.getInstance().getEmisor().sendMessage(json2);

						}else if(a.getResult().equalsIgnoreCase("failure")){
							changeView(a.getCell());
						}  
						break;
					case "Surrender":	
						showAlert(false);
						view.close();
						Disconnect d = new Disconnect();
						String json2 = gson.toJson(d);
						TCPConnection.getInstance().getEmisor().sendMessage(json2);
						disconnectfromServer();
						break;
					}
				}
				);
	}
	public void attack() {
		for(int i = 0; i<3;i++) {
			for(int j = 0; j<3;j++) {
				String cell = view.getAtaque()[i][j].getText();
				view.getAtaque()[i][j].addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					// Anular el método de implementación de la interfaz EventHandler
					@Override
					public void handle(MouseEvent event) {
						// Ejecuta la acción después del evento
						String value = cell;
						Gson gson = new Gson();
						Attack attack = new Attack(value);
						String json = gson.toJson(attack);
						TCPConnection.getInstance().getEmisor().sendMessage(json);
						enableAttack(false);
					}
				});
			}
		}
	}
	public boolean checkRadar(String text) {
		boolean b = false;
		boolean c = false;
		for(int i = 0; i<3 && c == false; i++) {	
			for(int j = 0; j<3; j++) {	
				if(view.getRadar()[i][j].getText().equalsIgnoreCase(text)) {
					Color color = (Color) view.getRadar()[i][j].getBackground().getFills().get(0).getFill();
					String col = color.toString();
					if(col.equalsIgnoreCase("0xffff00ff")) {		
						b = true;
						c=true;

					}else {
						view.drawAttackInRadar(i, j);
						c=true;
					}
				}
			}
		}
		return b;
	}

	public void changeView(String cell) {
		for(int i = 0; i<3; i++) {	
			for(int j = 0; j<3; j++) {	
				if(view.getAtaque()[i][j].getText().equalsIgnoreCase(cell)) {						
					view.drawAttackInAtaque(i, j);
					break;
				}
			}
		}
	}
	public void enableAttack(boolean b) {
		if(b == true) {
			for(int i = 0; i<3;i++) {
				for(int j = 0; j<3;j++) {			
					view.getAtaque()[i][j].setDisable(false);
				}
			}
		}else if(b == false) {		
			for(int i = 0; i<3;i++) {
				for(int j = 0; j<3;j++) {

					view.getAtaque()[i][j].setDisable(true);		
				}
			}
		}

	}

	public void showAlert(boolean defeated) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		Image image;
		ImageView imageView;
		File f;
		if(defeated == true) {
			alert.setHeaderText("Has perdido el juego\n "+view.getNameTF().getText()+ "!");
			alert.setContentText("Has sido derrotado por: "+view.getOpponentLabel().getText());
			f = new File("extra\\necoperdiste.png");
			image = new Image(f.toURI().toString());
			imageView = new ImageView(image);
			imageView.setFitHeight(200);
			imageView.setFitWidth(200);
			alert.setGraphic(imageView);

		}else {
			alert.setHeaderText("Has ganado el juego:\n "+view.getNameTF().getText()+ "!");	
			alert.setContentText("Has derrotado a: "+view.getOpponentLabel().getText());
			f = new File("extra\\necogif.gif");
			image = new Image(f.toURI().toString());
			imageView = new ImageView(image);
			imageView.setFitHeight(200);
			imageView.setFitWidth(200);
			alert.setGraphic(imageView);

		}
		alert.show();

	}

	public void disconnectfromServer() {
		Platform.runLater( // run on UI Thread
				() -> {
					view.close();
				}

				);

	}

}
