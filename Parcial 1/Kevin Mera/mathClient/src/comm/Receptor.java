package comm;

import java.io.BufferedReader;
import java.io.IOException;

public class Receptor extends Thread{

	private BufferedReader breader;
	private OnMessageListener listener;
<<<<<<< HEAD:Parcial 1/Julian Riascos/MathChallengeClient/src/comm/Receptor.java


=======
	private boolean alive = true;
	
>>>>>>> origin/A00364415:Parcial 1/Kevin Mera/mathClient/src/comm/Receptor.java
	public Receptor(BufferedReader breader) {
		setDaemon(true);
		this.breader = breader;
	}

	@Override
	public void run() {
		try {
			while(alive) {	
				String msg = breader.readLine();
<<<<<<< HEAD:Parcial 1/Julian Riascos/MathChallengeClient/src/comm/Receptor.java
				if (msg == null) {
					break;
				}else {
					listener.onMessage(msg);
=======
				if(msg != null) {
					listener.onMessage(msg);
				} else {
					alive = false;
					return;
>>>>>>> origin/A00364415:Parcial 1/Kevin Mera/mathClient/src/comm/Receptor.java
				}
			}
		} catch (IOException e) {
			System.out.println("Desconexión manejada");
		}

	}
	//Metodo suscripcion
	public void setListener(OnMessageListener listener) {
		this.listener = listener;
	}


	public interface OnMessageListener{
		void onMessage(String msg);
	}
}
