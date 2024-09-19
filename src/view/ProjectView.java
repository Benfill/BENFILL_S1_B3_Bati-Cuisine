package view;

import model.Project;
import util.Input;

public class ProjectView {
	public static void displayProjectCreationForm() {
		System.out.println("=== Create New Renovation/Construction Project ===");

		Input.getInteger("return", "Enter anything to back home", true);
	}

	public static void displayProjectDetails(Project project) {
		System.out.println("=== Project Details ===");
		System.out.println("Project Name: " + project.getProjectName());
		System.out.println("Total Cost: " + String.format("%.2f", project.getTotalCost()) + "MAD");
		System.out.println("Project Name: " + project.getProjectName());
	}

	public static void displayAllProject() {
		System.out.println("Projects");
		Input.getInteger("return", "Enter anything to back home", true);
	}

	public static void deleteProject() {
		System.out.println("Delete Project");
		Input.getInteger("return", "Enter anything to back home", true);
	}
}