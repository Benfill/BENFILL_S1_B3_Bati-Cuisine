package view;

import model.Quote;

public class QuotationView {
	public static void generateQuotation() {
		System.out.print("Enter the issue date (dd/MM/yyyy): ");
		// Input issue date
		System.out.print("Enter the validity date (dd/MM/yyyy): ");
		// Input validity date
	}

	public static void displayQuotation(Quote quote) {
		Double amount = quote.getEstimatedAmount();
		Double tax = amount * 0.1;
		System.out.println("Estimated Amount: " + amount);
		System.out.println("Tax: " + tax);
		System.out.println("Final Amount: " + amount + tax);
		System.out.println("Accepted: " + (quote.isAccepted() ? "Yes" : "No"));
	}
}
