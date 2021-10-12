package view;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
<<<<<<< HEAD:Taller 2/Julian Riascos/AstuciaClient/src/view/GameWindow.java
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
=======

>>>>>>> origin/A00306456:Taller 2/JuanBallesteros-T2/AstuciaClient/src/view/GameWindow.java
import javafx.stage.Stage;

public class GameWindow extends Stage{
	
	
	//UI Elements
	private Scene scene;
<<<<<<< HEAD:Taller 2/Julian Riascos/AstuciaClient/src/view/GameWindow.java
	private GameController control;
=======
>>>>>>> origin/A00306456:Taller 2/JuanBallesteros-T2/AstuciaClient/src/view/GameWindow.java
	private Label[][] radar;
	private Button[][] ataque;
	private TextField nameTF;
	private Button sendNameBtn, surrenderBtn;
	private Label opponentLabel;
	private Label statusLabel;	
	private Label resultLabel;
	private Label warningLabel;
	
	public GameWindow() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));
			Parent parent = loader.load();
			scene = new Scene(parent, 487,421);
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
			
			nameTF = (TextField) loader.getNamespace().get("nameTF");
			sendNameBtn = (Button) loader.getNamespace().get("sendNameBtn");
			opponentLabel = (Label) loader.getNamespace().get("opponentLabel");
			statusLabel = (Label) loader.getNamespace().get("statusLabel");
			surrenderBtn = (Button) loader.getNamespace().get("surrenderBtn");
			resultLabel = (Label) loader.getNamespace().get("resultLabel");
			warningLabel = (Label) loader.getNamespace().get("warningLabel");
			
<<<<<<< HEAD:Taller 2/Julian Riascos/AstuciaClient/src/view/GameWindow.java
			control = new GameController(this);
=======
>>>>>>> origin/A00306456:Taller 2/JuanBallesteros-T2/AstuciaClient/src/view/GameWindow.java
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


	public TextField getNameTF() {
		return nameTF;
	}


	public Button getSendNameBtn() {
		return sendNameBtn;
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

	public Label getResultLabel() {
		return resultLabel;
	}

	public void setResultLabel(Label resultLabel) {
		this.resultLabel = resultLabel;
	}
	
	public Label getWarningLabel() {
		return warningLabel;
	}

	public void setWarningLabel(Label warningLabel) {
		this.warningLabel = warningLabel;
	}

	//UI Actions
	public void drawAttackInRadar(int row, int col) {
		radar[row][col].setStyle("-fx-background-color: red;");
	}
	
	public void drawWeakPointInRadar(int row, int col) {
		radar[row][col].setStyle("-fx-background-color: yellow;");
	}
	
	public void win() {
		this.close();
		Alert alert = new Alert(AlertType.CONFIRMATION, "¡Ganaste!");
		alert.show();
	}
	
	public void lose() {
		this.close();
		Alert alert = new Alert(AlertType.CONFIRMATION, "¡Perdiste!");
		alert.show();
	}

	public void changeLabel(Label label, String msg){
		Platform.runLater(()->{
			label.setText(msg);
		});
	}
}
