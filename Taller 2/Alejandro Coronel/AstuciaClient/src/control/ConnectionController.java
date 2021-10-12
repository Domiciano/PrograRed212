package control;

import comm.TCPConnection;
import javafx.application.Platform;
import view.GameWindow;
import view.ConnectionWindow;

<<<<<<< HEAD:Taller 2/Anderson Cardenas/AstuciaClient/src/control/ConnectionController.java
public class ConnectionController implements TCPConnection.OnConnectionListener {

=======
public class ConnectionController implements TCPConnection.OnConnectionListener{	
>>>>>>> origin/Taller2A00365049:Taller 2/AlejandroCoronel/AstuciaClient/src/control/ConnectionController.java
	private ConnectionWindow view;
	private TCPConnection connection;

	public ConnectionController(ConnectionWindow view) {
		this.view = view;
		init();
	}
<<<<<<< HEAD:Taller 2/Anderson Cardenas/AstuciaClient/src/control/ConnectionController.java

	public void init() {
		connection = TCPConnection.getInstance();
		connection.setConnectionListener(this);

		view.getBtnConnect().setOnAction(

=======
	public void init() {
		connection = TCPConnection.getInstance();
		connection.setConnectionListener(this);	
		view.getBtnConnect().setOnAction(			
>>>>>>> origin/Taller2A00365049:Taller 2/AlejandroCoronel/AstuciaClient/src/control/ConnectionController.java
				e -> {
					String ip = view.getIpTF().getText();
					String puerto = view.getPortTF().getText();
					int puertoInt = Integer.parseInt(puerto);
					connection.setIp(ip);
					connection.setPuerto(puertoInt);
					connection.start();
<<<<<<< HEAD:Taller 2/Anderson Cardenas/AstuciaClient/src/control/ConnectionController.java
				});

=======
				}			
		);	
>>>>>>> origin/Taller2A00365049:Taller 2/AlejandroCoronel/AstuciaClient/src/control/ConnectionController.java
	}

	@Override
	public void onConnection() {
		Platform.runLater(
				() -> {
					GameWindow window = new GameWindow();
					window.show();
					view.close();
				}

		);
	}
<<<<<<< HEAD:Taller 2/Anderson Cardenas/AstuciaClient/src/control/ConnectionController.java

=======
	
>>>>>>> origin/Taller2A00365049:Taller 2/AlejandroCoronel/AstuciaClient/src/control/ConnectionController.java
}
