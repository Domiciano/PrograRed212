package comm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import comm.Receptor.OnMessageListener;

public class TCPConnection extends Thread {

	private static TCPConnection instance = null;

	private TCPConnection() {}

	public static synchronized TCPConnection getInstance() {
		if (instance == null) {
			instance = new TCPConnection();
		}
		return instance;
	}

	private Socket socket;
	private String ip;
	private int puerto;
	private Receptor receptor;
	private Emisor emisor;
	private OnConnectionListener connectionListener;

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public void disconnect() throws IOException {
		socket.close();
	}

	@Override
	public void run() {
		try {
			System.out.println("Conectadome al servidor");
			socket = new Socket(ip, puerto);
			System.out.println("Conectado");
			
			receptor = new Receptor(new BufferedReader(new InputStreamReader(socket.getInputStream())));
			connectionListener.onConnection();
			Thread.sleep(1000);
			receptor.start();
			
			emisor = new Emisor(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setListenerOfMessages(OnMessageListener listener) {
		this.receptor.setListener(listener);
	}

	public Emisor getEmisor() {
		return this.emisor;
	}

	public void setConnectionListener(OnConnectionListener connectionListener) {
		this.connectionListener = connectionListener;
	}

	public interface OnConnectionListener {
		public void onConnection();
	}

}
