package model;

public class NickNameValidation {

	public String type = "NickNameValidation";
	private String body;
	private boolean nickStatus;
	
	public NickNameValidation() {}
	
	public NickNameValidation(String body, boolean nickStatus) {
		this.body = body;
		this.nickStatus = nickStatus;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}

	public boolean getNickStatus() {
		return nickStatus;
	}

	public void setNickStatus(boolean nickStatus) {
		this.nickStatus = nickStatus;
	}
	
}
