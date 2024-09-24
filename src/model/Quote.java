package model;

import java.time.LocalDate;

import service.ProjectService;
import util.Input;

public class Quote {
	private int id;
	private double estimatedAmount;
	private LocalDate issueDate;
	private LocalDate validityDate;
	private int projectId;
	private boolean accepted = false;

	public boolean generate(int projectId, double estimatedAmount) {
		System.out.println();
		System.out.println("--- Saving the Quote ---");
		System.out.println();
		issueDate = Input.getDate("Issue Date", "Enter the quote issue date (format: dd/mm/yyyy)", false, true).get();
		validityDate = Input
				.getDate("Validity Date", "Enter the quote validity date (format: dd/mm/yyyy)", false, false).get();

		setEstimatedAmount(estimatedAmount);
		setProjectId(projectId);
		setIssueDate(getIssueDate());
		setValidityDate(getValidityDate());

		ProjectService pService = new ProjectService();
		boolean checker = Input.getConfirmation("Would you like to save the quote? (y/n)");
		if (!checker) {
			System.out.println("The quote has been canceled");
			return false;
		} else {
			pService.generateQuote(this);
			System.out.println("The quote has been saved successfully");
			return true;
		}
	}

	public void acceptQuote() {
		System.out.println();
		System.out.println("--- Accepting Quote ---");
		System.out.println();

		boolean checker = true;

		while (checker) {
			int quoteId = Input.getInteger("Quote Id", "Enter quote id", false).get();
			ProjectService pService = new ProjectService();
			String result = pService.acceptQuote(quoteId);

			if (result != null) {
				System.out.println(result);
				checker = Input.getConfirmation("Do You want to try again? (y/n)");
				if (!checker)
					return;
			} else
				break;
		}
		System.out.println();
		System.out.println("Quote is accepted successfully");
		System.out.println();
		Input.getInteger("", "Enter 0 to return home", true).get();
	}

	public void displayQuote() {
		ProjectService pService = new ProjectService();
		Project project = pService.getProjectById(projectId);
		String projectName = project.getProjectName();

		System.out.printf("%-10s %-15s %-15s %-15s %-15s %-10s %-15s%n", "ID", "Project Name", "Amount", "Issue Date",
				"Validity Date", "Accepted", "Validity");

		String validity = (validityDate.isBefore(LocalDate.now())) ? "Expired" : "Valid";

		System.out.printf("%-10d %-15s %-15.2f %-15s %-15s %-10s %-15s%n", id, projectName, estimatedAmount, issueDate,
				validityDate, accepted ? "Yes" : "No", validity);
	}

	public double getEstimatedAmount() {
		return estimatedAmount;
	}

	public void setEstimatedAmount(double estimatedAmount) {
		this.estimatedAmount = estimatedAmount;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public LocalDate getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(LocalDate validityDate) {
		this.validityDate = validityDate;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
