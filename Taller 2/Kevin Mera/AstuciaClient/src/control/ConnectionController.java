package control;

import comm.TCPConnection;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import view.GameWindow;
import view.ConnectionWindow;

public class ConnectionController implements TCPConnection.OnConnectionListener{

	private ConnectionWindow view;
	private TCPConnection connection;

	public ConnectionController(ConnectionWindow view) {
		this.view = view;
		init();
	}

	public void init() {
		connection = TCPConnection.getInstance();
		connection.setConnectionListener(this);

		view.getBtnConnect().setOnAction(	
				e -> {
					if(!view.getUserName().getText().isEmpty()) {
						String ip = view.getIpTF().getText();
						String puerto = view.getPortTF().getText();
						int puertoInt = Integer.parseInt(puerto);
						connection.setIp(ip);
						connection.setPuerto(puertoInt);
						connection.start();
					} else {
						Alert a = new Alert(Alert.AlertType.WARNING);
						a.setContentText("Porfavor introduzca un nombre de usuario");
						a.show();

					}
				}
				);

	}

	@Override
	public void onConnection() {
		//Estamos conectados
		Platform.runLater(

				()->{
					GameWindow window = new GameWindow();
					window.getPlayerLabel().setText(view.getUserName().getText());
					window.show();
					view.close();
				}

				);
	}
}
