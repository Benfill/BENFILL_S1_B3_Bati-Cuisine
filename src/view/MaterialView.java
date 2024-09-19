package view;

import model.Material;

public class MaterialView {
	public static void addMaterial() {
		System.out.print("Enter the material name: ");
		// Input material name
		System.out.print("Enter the quantity: ");
		// Input quantity
		System.out.print("Enter the unit cost (€/unit): ");
		// Input unit cost
		System.out.print("Enter the transport cost (€): ");
		// Input transport cost
		System.out.print("Enter the quality coefficient (1.0 = standard, > 1.0 = high quality): ");
		// Input quality coefficient
	}

	public static void displayMaterialDetails(Material material) {
		System.out.println("Material: " + material.getName());
		System.out.println("Quantity: " + material.getQuantity());
		System.out.println("Unit Cost: " + material.getUnitCost());
		System.out.println("Transport Cost: " + material.getTransportCost());
		System.out.println("Quality Coefficient: " + material.getQualityCoefficient());
	}
}
