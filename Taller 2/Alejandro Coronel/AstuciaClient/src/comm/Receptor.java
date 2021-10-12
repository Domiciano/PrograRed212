package comm;

import java.io.BufferedReader;
import java.io.IOException;

public class Receptor extends Thread{
	
	private BufferedReader breader;
	private OnMessageListener listener;
	private boolean alive = true;
<<<<<<< HEAD:Taller 2/Anderson Cardenas/AstuciaClient/src/comm/Receptor.java
=======
	
>>>>>>> origin/Taller2A00365049:Taller 2/AlejandroCoronel/AstuciaClient/src/comm/Receptor.java
	
	public Receptor(BufferedReader breader) {
		this.breader = breader;
	}
	
	@Override
	public void run() {
		try {
<<<<<<< HEAD:Taller 2/Anderson Cardenas/AstuciaClient/src/comm/Receptor.java
				String msg = "";
				while(alive && msg!=null) {
					msg = breader.readLine();
					listener.onMessage(msg);	
				}
=======
			while(alive) {	
				String msg = breader.readLine();
				if(msg != null) {
				listener.onMessage(msg);			
				}else {			
					alive = false;
					return;
				}
			}
>>>>>>> origin/Taller2A00365049:Taller 2/AlejandroCoronel/AstuciaClient/src/comm/Receptor.java
		} catch (IOException e) {
			System.out.println("Se desconecto");
		}
		
	}
	//Metodo suscripcion
	public void setListener(OnMessageListener listener) {
		this.listener = listener;
	}
	
	
	public interface OnMessageListener{
		void onMessage(String msg);
	}


	public boolean getAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	

}
