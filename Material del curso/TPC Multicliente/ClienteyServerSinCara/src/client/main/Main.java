package client.main;

import client.view.ConnectionWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	
	

	public static void main(String[] args) {	
		launch(args);
	}

	@Override
	public void start(Stage arg0) {
		ConnectionWindow window = new ConnectionWindow();
		window.show();
	}

}
