package model;

import model.enums.ProjectStatus;

public class Project {
	private String projectName;
	private double profitMargin;
	private double totalCost;
	private ProjectStatus status;

	public double calculateTotalCost() {
		// Method body to be implemented
		return 0.0;
	}

	public void addClient(Client client) {
		// Method body to be implemented
	}

	public void addComponent(Component component) {
		// Method body to be implemented
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public double getProfitMargin() {
		return profitMargin;
	}

	public void setProfitMargin(double profitMargin) {
		this.profitMargin = profitMargin;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}
}
