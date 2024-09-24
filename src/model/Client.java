package model;

public class Client {
	private int id;
	private String name;
	private String address;
	private String phone;
	private boolean isProfessional;

	public double applyDiscount() {
		// Method body to be implemented
		return 0.0;
	}

	public void displayClient() {
		System.out.printf("%-10d %-20s %-30s %-15s %-15s\n", this.id, this.name, this.address, this.phone,
				this.isProfessional ? "Yes" : "No");
		System.out
				.println("-------------------------------------------------------------------------------------------");

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isProfessional() {
		return isProfessional;
	}

	public void setProfessional(boolean isProfessional) {
		this.isProfessional = isProfessional;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
