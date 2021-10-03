package model;

public class Surrender {

	public String type = "Surrender";
	private String msg;

	public Surrender() {};

	public Surrender(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}



}
