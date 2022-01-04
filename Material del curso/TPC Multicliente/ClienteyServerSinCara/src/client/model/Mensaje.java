package client.model;

public class Mensaje {
	
	private String type = "Mensaje";
	private String id;
	private String body;
	
	
	public Mensaje() {
		
	}

	public Mensaje(String id, String body) {
		this.id = id;
		this.body = body;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	

}
