package view;

import model.Labor;

public class LaborView {
	public static void addLabor() {
		System.out.print("Enter labor type (e.g., Basic Worker, Specialist): ");
		// Input labor type
		System.out.print("Enter hourly rate (€/h): ");
		// Input hourly rate
		System.out.print("Enter hours worked: ");
		// Input hours worked
		System.out.print("Enter productivity factor (1.0 = standard, > 1.0 = high productivity): ");
		// Input productivity factor
	}

	public static void displayLaborDetails(Labor labor) {
		System.out.println("Hourly Rate: " + labor.getHourlyRate());
		System.out.println("Hours Worked: " + labor.getHoursWorked());
		System.out.println("Productivity: " + labor.getWorkerProductivity());
	}
}
