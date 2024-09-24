package view;

import java.util.HashMap;

import model.Material;

public class MaterialView {
	private static double materialCost = 0;

	public static double displayMaterial(HashMap<String, Material> materials) {
		System.out.println("1. Materials:");
		System.out.println();
		materials.forEach((name, material) -> {
			double total = (material.getQuantity() * material.getUnitCost() * material.getQualityCoefficient())
					+ material.getTransportCost();
			String detail = String.format(
					"- %s: €%.2f (quantity: %.2f m², unit cost: €%.2f/m², quality: %.1f, transport: €%.2f)", name,
					total, material.getQuantity(), material.getUnitCost(), material.getQualityCoefficient(),
					material.getTransportCost());
			System.out.println(detail);
			materialCost += total;
		});
		return materialCost;
	}
}
