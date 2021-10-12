package control;

import com.google.gson.Gson;

import comm.TCPConnection;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import model.*;
import view.GameWindow;

public class AttackGridController {
    private GameController controller;
    private GameWindow view;

    private Gson gson;

    private Button[][] attackBtns;

    public AttackGridController(GameController controller, GameWindow view) {
        this.controller = controller;
        this.view = view;

        attackBtns = view.getAtaque();
        gson = new Gson();

    }

    public void paintAttack(int y, int x, boolean kill) {
        if (kill) {
            view.getRadar()[x][y].setStyle("-fx-Background-color: #800000 ;");
        } else {
            view.getRadar()[x][y].setStyle("-fx-Background-color: #BEBEBE ;");
        }
    }

    public void gridBtnAction(boolean turn) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                final int innerI = (i);
                final int innerJ = (j);

                attackBtns[i][j].setOnAction((event) -> {
                    if (turn) {
                        Hit hit = new Hit(innerJ, innerI, controller.getOpponent());
                        String json = gson.toJson(hit);
                        TCPConnection.getInstance().getEmisor().sendMessage(json);
                        attackBtns[innerI][innerJ].setDisable(true);
                    } else {
                        turnAlert();
                    }
                    ;
                });
            }
        }
    }

    private void turnAlert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro en el turno");
        alert.setHeaderText(null);
        alert.setContentText("No es tu turno, espera a que tu oponente ataque");

        alert.showAndWait();
    }

    public void failAttack(GameStatus game, int x, int y) {
        game.setTurnPlayer1(!game.isTurnPlayer1());
        game.setLastX(x);
        game.setLastY(y);
        String json = gson.toJson(game);
        TCPConnection.getInstance().getEmisor().sendMessage(json);
    }

    public void successAttack(GameStatus game, int x, int y){
        game.setWinner(controller.getOpponent());
        game.setLastX(x);
        game.setLastY(y);
        String json = gson.toJson(game);
        TCPConnection.getInstance().getEmisor().sendMessage(json);
    }

    public void winnerGame(){
        view.changeLabel(view.getStatusLabel(), "Ganaste!!");
        exit();
    }

    public void loserGame(){
        view.changeLabel(view.getStatusLabel(), "Perdiste... :(");
        exit();
    }

    private void exit(){
        new Thread(()->{
            try {
                Thread.sleep(4000);
                Platform.runLater(()->{
                    System.exit(0);
                });
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }).start();
    }

}
