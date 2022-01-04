package client.control;

import client.comm.TCPConnection;
import client.events.OnConnectionListener;
import client.view.ChatWindow;
import client.view.ConnectionWindow;
import javafx.application.Platform;

public class ConnectionController implements OnConnectionListener {

	
	private ConnectionWindow view;
	
	public ConnectionController(ConnectionWindow view) {
		this.view = view;
	}
	
	public void init() {
		view.getConnectBtn().setOnAction(event->{
			TCPConnection con = TCPConnection.getInstance();
			con.setConnectionListener(this);
			con.connect(view.getIpTF().getText(), Integer.parseInt(view.getPortTF().getText()));
			
		});
		
	
	}
	
	
	@Override
	public void onConnection(boolean success) {
		//Obligar a correr un proceso en el hilo principal MAIN UI
		Platform.runLater(()->{
			if(success) {
				view.openChatWindow();
			}else {
				System.out.println("Falló la conexión");
			}
		});
		
		
	}
	
}
