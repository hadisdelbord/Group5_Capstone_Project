package CapstoneProject;

public class EnergySource {

	private String name;
	private int power; // KW/h

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	@Override
	public String toString() {
		return "EnergySource [name=" + name + ", power=" + power + "]";
	}
	
	

}
