package controller;

import model.Component;
import model.Labor;
import model.Material;
import service.ComponentService;
import util.Input;

public class ComponentController {
	private int projectId;

	public Component addComponentToProject(int id, String type) {
		ComponentService cService = new ComponentService();
		projectId = id;

		System.out.println("");
		if (type == "material") {
			Material material = createMaterial();
			cService.create(material);
			System.out.println("Material added successfully!");
			System.out.println("");
			return material;
		} else {
			Labor labor = createLabor();
			cService.create(labor);
			System.out.println("Labor successfully added!");
			System.out.println("");
			return labor;
		}

	}

	private Material createMaterial() {
		System.out.println("--- --- Add materials --- ---");
		System.out.println("");

		Material material = new Material();
		String name = Input.getString("Name", "Enter the material name", false).get();
		double quantity = Input.getDouble("Quantity", "Enter the quantity of this material (in m² or liters)", false)
				.get();
		double unitCost = Input
				.getDouble("Unit Cost", "Enter the unit cost of this material (€/m²) or (€/liter)", false).get();
		double transport = Input.getDouble("Transport Cost", "Enter the cost of transporting this material (€)", false)
				.get();
		double qualtity = Input.getDouble("Quality Coefficient",
				"Enter the material quality coefficient (1.0 = standard, > 1.0 = high quality)", false).get();

		material.setTransportCost(transport);
		material.setQualityCoefficient(qualtity);
		material.setName(name);
		material.setUnitCost(unitCost);
		material.setQuantity(quantity);
		material.setProjectId(projectId);
		material.setType("Material");
		return material;
	}

	private Labor createLabor() {
		System.out.println("--- --- Add labors --- ---");
		System.out.println("");

		Labor labor = new Labor();

		String name = Input.getString("name", "Enter labor type (e.g., Basic Worker, Specialist)", false).get();
		double hourlyRate = Input.getDouble("Hourly Rate", "Enter the hourly rate of this labor (€/h)", false).get();
		double hoursWorked = Input.getDouble("Hours Worked", "Enter the number of hours worked", false).get();
		double productivity = Input.getDouble("Productivity",
				"Enter the productivity factor (1.0 = standard, > 1.0 = high productivity)", false).get();

		labor.setName(name);
		labor.setHourlyRate(hourlyRate);
		labor.setHoursWorked(hoursWorked);
		labor.setWorkerProductivity(productivity);
		labor.setProjectId(projectId);
		labor.setType("Labor");

		return labor;
	}
}
