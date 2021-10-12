package model;

public class Winner {
	@SuppressWarnings("unused")
	private String type = "Winner";
	private String coordinates;

	public Winner(String coordinates) {
		this.coordinates = coordinates;
	}

	public Winner() {}
	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

}
