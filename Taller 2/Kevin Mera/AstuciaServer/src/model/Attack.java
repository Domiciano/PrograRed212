package model;

public class Attack {

	
	private String type = "Attack";
	private String coordinates;
	
	public Attack(String coordinates) {
		this.coordinates = coordinates;
	}
	public Attack() {}

	public String getType() {
		return type;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	
	
}
