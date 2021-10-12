package view;

import java.io.IOException;

import control.GameController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GameWindow extends Stage{
	
	
	//UI Elements
	private Scene scene;
	private GameController contol;
	private Label[][] radar;
	private Button[][] ataque;
	private TextField nameTF;
	private Button sendNameBtn, surrenderBtn;
	public Button getSurrenderBtn() {
		return surrenderBtn;
	}

	private Label opponentLabel;
	private Label statusLabel;
	
	
	
	public GameWindow() {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));
			Parent parent = loader.load();
			scene = new Scene(parent, 487,421);
			Font font = Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 14);
			Font fonta = Font.font("Impact", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 14);
			this.setScene(scene);
			
			//Referencias
			
			//Radar
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
			
			radar[0][0].setText("A0");
			radar[0][1].setText("A1");
			radar[0][2].setText("A2");
			radar[1][0].setText("B0");
			radar[1][1].setText("B1");
			radar[1][2].setText("B2");
			radar[2][0].setText("C0");
			radar[2][1].setText("C1");
			radar[2][2].setText("C2");
			
			radar[0][0].setAlignment(Pos.CENTER);
			radar[0][1].setAlignment(Pos.CENTER);
			radar[0][2].setAlignment(Pos.CENTER);
			radar[1][0].setAlignment(Pos.CENTER);
			radar[1][1].setAlignment(Pos.CENTER);
			radar[1][2].setAlignment(Pos.CENTER);
			radar[2][0].setAlignment(Pos.CENTER);
			radar[2][1].setAlignment(Pos.CENTER);
			radar[2][2].setAlignment(Pos.CENTER);
			
		   //Algunos cambios visuales 
			
			radar[0][0].setFont(font);
			radar[0][1].setFont(font);
			radar[0][2].setFont(font);
			radar[1][0].setFont(font);
			radar[1][1].setFont(font);
			radar[1][2].setFont(font);
			radar[2][0].setFont(font);
			radar[2][1].setFont(font);
			radar[2][2].setFont(font);
	
			//Ataque
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
			
			ataque[0][0].setText("A0");
			ataque[0][1].setText("A1");
			ataque[0][2].setText("A2");
			ataque[1][0].setText("B0");
			ataque[1][1].setText("B1");
			ataque[1][2].setText("B2");
			ataque[2][0].setText("C0");
			ataque[2][1].setText("C1");
			ataque[2][2].setText("C2");
			
			ataque[0][0].setFont(fonta);
			ataque[0][1].setFont(fonta);
			ataque[0][2].setFont(fonta);
			ataque[1][0].setFont(fonta);
			ataque[1][1].setFont(fonta);
			ataque[1][2].setFont(fonta);
			ataque[2][0].setFont(fonta);
			ataque[2][1].setFont(fonta);
			ataque[2][2].setFont(fonta);
			
			//Referenciar fxml
			nameTF = (TextField) loader.getNamespace().get("nameTF");
			sendNameBtn = (Button) loader.getNamespace().get("sendNameBtn");
			opponentLabel = (Label) loader.getNamespace().get("opponentLabel");
			statusLabel = (Label) loader.getNamespace().get("statusLabel");
			surrenderBtn = (Button)loader.getNamespace().get("surrenderBtn");
			contol = new GameController(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
	
	//UI Actions
	public void drawAttackInRadar(int fil, int col) {
		radar[fil][col].setStyle("-fx-background-color: red;");
	}
	
	public void drawWeakPointInRadar(int fil, int col) {
		radar[fil][col].setStyle("-fx-background-color: yellow;");
	}
	public void drawAttackInAtaque(int fil, int col) {
		ataque[fil][col].setStyle("-fx-background-color: red;");
		
	}
}
