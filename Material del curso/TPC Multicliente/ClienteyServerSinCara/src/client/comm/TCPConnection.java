package client.comm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import client.events.OnConnectionListener;
import client.events.OnInboxListener;
import client.model.Mensaje;

public class TCPConnection {

	private static TCPConnection instance = null;

	private TCPConnection() {
	}

	public static synchronized TCPConnection getInstance() {

		if (instance == null) {
			instance = new TCPConnection();
		}
		return instance;
	}

	// Clase normal
	private Socket socket;
	private BufferedReader breader;
	private BufferedWriter bwriter;

	// Listeners
	private OnConnectionListener onConnectionListener = null;
	private OnInboxListener onInboxListener = null;

	public void connect(String ip, int port) {

		
		
		
		new Thread(() -> {
			try {
				socket = new Socket(ip, port);
				onConnectionListener.onConnection(true);

				// Definir el lector y escritor
				InputStream is = socket.getInputStream();
				breader = new BufferedReader(new InputStreamReader(is));

				OutputStream os = socket.getOutputStream();
				bwriter = new BufferedWriter(new OutputStreamWriter(os));

				while (true) {

					String line = breader.readLine();
					
					
					//Deserializar el mensaje
					Gson gson = new Gson();
					Mensaje m = gson.fromJson(line, Mensaje.class);
					
					onInboxListener.onMessage(m.getBody());

				}

			} catch (UnknownHostException e) {
				e.printStackTrace();
				onConnectionListener.onConnection(false);
			} catch (IOException e) {
				e.printStackTrace();
				onConnectionListener.onConnection(false);
			}

		}).start();

	}

	public void sendMessage(String msg) {
		new Thread(() -> {
			try {
				bwriter.write(msg + "\n");
				bwriter.flush();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}).start();
	}

	// Metodos de suscripcion

	public void setConnectionListener(OnConnectionListener onConnectionListener) {
		this.onConnectionListener = onConnectionListener;
	}

	public void setOnInboxListener(OnInboxListener onInboxListener) {
		this.onInboxListener = onInboxListener;
	}

}
