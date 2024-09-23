package model;

import model.enums.ProjectStatus;

public class Project {
	private int id;
	private String projectName;
	private double profitMargin;
	private double totalCost;
	private ProjectStatus status;
	private int clientId;
	private double surface;

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

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public double getSurface() {
		return surface;
	}

	public void setSurface(double surface) {
		this.surface = surface;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void displayProject() {
		String name = projectName.length() > 30 ? projectName.substring(0, 27) + "..." : projectName;
		String[][] data = {
				{ String.valueOf(id), name, String.format("%.2f", profitMargin), String.format("%.2f", totalCost),
						status.toString(), String.valueOf(clientId), String.format("%.2f", surface) } };
		int[] columnWidths = { 5, 30, 15, 15, 20, 10, 10 };

		// Print data rows
		for (String[] row : data) {
			for (int i = 0; i < row.length; i++) {
				System.out.printf("%-" + columnWidths[i] + "s", row[i]);
			}
			System.out.println();
		}

		// Print separator
		for (int width : columnWidths) {
			System.out.print("-".repeat(width));
		}
		System.out.println();

	}

	public void displayHeader() {
		String[] headers = { "ID", "Project Name", "Profit Margin", "Total Cost", "Status", "Client ID", "Surface" };

		// Define column widths
		int[] columnWidths = { 5, 30, 15, 15, 20, 10, 10 };

		// Print headers
		for (int i = 0; i < headers.length; i++) {
			System.out.printf("%-" + columnWidths[i] + "s", headers[i]);
		}
		System.out.println();

		// Print separator
		for (int width : columnWidths) {
			System.out.print("-".repeat(width));
		}
		System.out.println();
	}

}
