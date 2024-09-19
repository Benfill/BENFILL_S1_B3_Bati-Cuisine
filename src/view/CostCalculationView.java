package view;

public class CostCalculationView {
	public static void displayFinalCost(double totalCost, double margin, double finalCost) {
		System.out.println("Total Cost before margin: " + totalCost);
		System.out.println("Profit Margin: " + margin);
		System.out.println("Final Cost: " + finalCost);
	}
}
