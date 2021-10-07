package view;

import java.io.IOException;

import control.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GameWindow extends Stage{
	
	
	//UI Elements
	private Scene scene;
	@SuppressWarnings("unused")
	private GameController control;
	private Label[][] radar;
	private Button[][] ataque;
<<<<<<< HEAD:Taller 2/Kevin Mera/AstuciaClient/src/view/GameWindow.java
	private Label playerLabel;
	private Button queueBtn; 
	private Button surrenderBtn;
=======
	private TextField nameTF;
	private Button sendNameBtn, surrenderBtn;
>>>>>>> 1d650def67ef607e4c5ff8e61c5503d5118e6693:Taller 2/AstuciaClient/src/view/GameWindow.java
	private Label opponentLabel;
	private Label statusLabel;	
	private Label winsLabel;
	
	
	public GameWindow() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));
			Parent parent = loader.load();
			scene = new Scene(parent, 500, 500);
			this.setScene(scene);
			
			//Referencias
			radar = new Label[3][3];
			radar[0][0] = (Label) loader.getNamespace().get("radar00");
			radar[0][1] = (Label) loader.getNamespace().get("radar01");
			radar[0][2] = (Label) loader.getNamespace().get("radar02");
			radar[1][0] = (Label) loader.getNamespace().get("radar10");
			radar[1][1] = (Label) loader.getNamespace().get("radar11");
			radar[1][2] = (Label) loader.getNamespace().get("radar12");
			radar[2][0] = (Label) loader.getNamespace().get("radar20");
			radar[2][1] = (Label) loader.getNamespace().get("radar21");
			radar[2][2] = (Label) loader.getNamespace().get("radar22");
			
			ataque = new Button[3][3];
			ataque[0][0] = (Button) loader.getNamespace().get("ataque00");
			ataque[0][1] = (Button) loader.getNamespace().get("ataque01");
			ataque[0][2] = (Button) loader.getNamespace().get("ataque02");
			ataque[1][0] = (Button) loader.getNamespace().get("ataque10");
			ataque[1][1] = (Button) loader.getNamespace().get("ataque11");
			ataque[1][2] = (Button) loader.getNamespace().get("ataque12");
			ataque[2][0] = (Button) loader.getNamespace().get("ataque20");
			ataque[2][1] = (Button) loader.getNamespace().get("ataque21");
			ataque[2][2] = (Button) loader.getNamespace().get("ataque22");
			
			playerLabel = (Label) loader.getNamespace().get("playerLabel");
			queueBtn = (Button) loader.getNamespace().get("queueBtn");
			opponentLabel = (Label) loader.getNamespace().get("opponentLabel");
			statusLabel = (Label) loader.getNamespace().get("statusLabel");
			surrenderBtn = (Button) loader.getNamespace().get("surrenderBtn");
<<<<<<< HEAD:Taller 2/Kevin Mera/AstuciaClient/src/view/GameWindow.java
			winsLabel = (Label) loader.getNamespace().get("winsLabel");
			control = new GameController(this);
=======
			
			
			contol = new GameController(this);
>>>>>>> 1d650def67ef607e4c5ff8e61c5503d5118e6693:Taller 2/AstuciaClient/src/view/GameWindow.java
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public Label[][] getRadar() {
		return radar;
	}


	public Button[][] getAtaque() {
		return ataque;
	}


	public Label getNameLabel() {
		return playerLabel;
	}


	public Button getQueueBtn() {
		return queueBtn;
	}


	public Label getOpponentLabel() {
		return opponentLabel;
	}


	public Label getStatusLabel() {
		return statusLabel;
	}
	
	public Button getSurrenderBtn() {
		return surrenderBtn;
	}


<<<<<<< HEAD:Taller 2/Kevin Mera/AstuciaClient/src/view/GameWindow.java
	public Label getPlayerLabel() {
		return playerLabel;
	}


	public Label getWinsLabel() {
		return winsLabel;
	}


=======
>>>>>>> 1d650def67ef607e4c5ff8e61c5503d5118e6693:Taller 2/AstuciaClient/src/view/GameWindow.java
	//UI Actions
	public void drawAttackInRadar(int fil, int col, boolean f) {
		if(!f) {
			radar[fil][col].setStyle("-fx-background-color: red;");
		} else {
			radar[fil][col].setStyle("-fx-background-color: green;");
		}
		
	}
	
	public void drawWeakPointInRadar(int fil, int col) {
		radar[fil][col].setStyle("-fx-background-color: yellow;");
	}
}
