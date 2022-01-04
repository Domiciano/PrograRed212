package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Session{
	
	
	private Receptor receptor;
	private Emisor emisor;
	
	
	public Session(Socket socket) {	
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			receptor = new Receptor(this, reader);
			receptor.setListener(TCPConnectionServer.getInstance());
			receptor.start();
			
			
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			emisor = new Emisor(writer);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public Emisor getEmisor() {
		return this.emisor;
	}
	
	public Receptor getReceptor() {
		return this.receptor;
	}

}
