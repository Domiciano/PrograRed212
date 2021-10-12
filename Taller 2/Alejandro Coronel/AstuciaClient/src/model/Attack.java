package model;

public class Attack {

	public String type = "Attack";
	private String result;
	
	public Attack(String cell) {
		super();
		this.cell = cell;
	}
	private String cell;
	public String getCell() {
		return cell;
	}
	public void setCell(String cell) {
		this.cell = cell;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
