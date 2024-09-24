package view;

public class CostCalculationView {
	public static void displayFinalCost(double costExcludingMargin, double margin, double finalCost,
			double marginCost) {
		String text = String.format("%.2f €", costExcludingMargin);
		System.out.println();
		System.out.println("3. Total cost before margin: " + text);
		text = String.format("4. Profit margin (%.2f%%): €%.2f", margin, marginCost);
		System.out.println(text);
		text = String.format("**Final total project cost : %.2f €**", finalCost);
		System.out.println(text);
	}
}
