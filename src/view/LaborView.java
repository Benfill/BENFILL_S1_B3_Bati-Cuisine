package view;

import java.util.HashMap;

import model.Labor;

public class LaborView {
	private static double laborCost = 0;

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

	public static double displayLabor(HashMap<String, Labor> labors) {
		System.out.println();
		System.out.println("2. Labors:");
		System.out.println();
		labors.forEach((name, labor) -> {
			double total = labor.getHourlyRate() * labor.getHoursWorked() * labor.getWorkerProductivity();
			String detail = String.format(
					"- %s: €%.2f (hourly rate: €%.2f/h, hours worked: %.2f h, productivity: %.1f)", name, total,
					labor.getHourlyRate(), labor.getHoursWorked(), labor.getWorkerProductivity());
			System.out.println(detail);
			laborCost += total;
		});
		return laborCost;
	}
}
