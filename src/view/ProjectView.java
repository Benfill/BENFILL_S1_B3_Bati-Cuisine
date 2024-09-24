package view;

import java.util.HashMap;

import controller.ProjectController;
import model.Quote;
import service.ProjectService;
import util.Input;

public class ProjectView {
	public static void displayProjectCreationForm() {
		System.out.println("--- Client search ---");
		System.out.println();
		ProjectController pController = new ProjectController();
		pController.createProject();

		Input.getInteger("return", "Enter anything to back home", true);
	}

	public static void displayAllProject() {
		ProjectController pController = new ProjectController();

		pController.displayProjects();
		Input.getInteger("return", "Enter anything to back home", true);
	}

	public static void deleteProject() {
		ProjectController pController = new ProjectController();
		pController.deleteProject();
		Input.getInteger("return", "Enter anything to back home", true);
	}

	public static void displayQuotes() {
		ProjectService pService = new ProjectService();
		HashMap<Integer, Quote> quotes = pService.getQuotes();
		if (quotes == null) {
			System.out.println("There is no quote yet");
			return;
		}

		System.out.println();
		System.out.println("-- Quotes --");
		System.out.println();
		quotes.forEach((id, quote) -> {
			quote.displayQuote();
		});
		System.out.println();
		System.out.println("1. Accept quote");
		System.out.println("2. Return back");
		int choice = Input.getInteger("Quote Menu", "Enter your choice", false).get();
		System.out.println();
		if (choice == 2)
			return;

		Quote quote = new Quote();
		quote.acceptQuote();

		Input.getInteger("return", "Enter anything to back home", true);
		System.out.println();

	}
}