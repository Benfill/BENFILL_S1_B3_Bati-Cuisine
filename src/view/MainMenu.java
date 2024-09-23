package view;

import util.Input;

public class MainMenu {
	public static int displayMenu() {
		System.out.println("=== Welcome to BatiCuisine Project Management ===");
		System.out.println("1. Manage Projects");
		System.out.println("2. View Clients");
		System.out.println("3. Exit");
		System.out.print("Choose an option: ");
		return Input.getInteger("Main Menu", "Enter your choice", false).get();
	}

	public static int displayProjectSubMenu() {
		System.out.println("=== Project Sub-Menu ===");
		System.out.println("1. Create a new project");
		System.out.println("2. View projects");
		System.out.println("3. Delete a project");
		System.out.println("4. View quotes");
		System.out.println("5. Return to main menu");
		System.out.print("Choose an option: ");
		return Input.getInteger("Projects Menu", "Enter your choice", false).get();
	}
}
