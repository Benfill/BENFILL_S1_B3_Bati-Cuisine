package service;

import view.ClientView;
import view.MainMenu;
import view.ProjectView;

public class AppService {

	public static Boolean running = true;
	private int choice = 0;

	public AppService() {

		while (running) {

			switch (choice) {

			case 0:
				choice = MainMenu.displayMenu();
				break;
			case 1:
				choice = handleSubMenu();
				break;
			case 2:
				choice = ClientView.displayAllClient();
				break;
			case 3:
				running = false;
				System.out.println("Exiting the application...");
				break;
			}

		}
	}

	public int handleSubMenu() {
		int input = MainMenu.displayProjectSubMenu();

		switch (input) {

		case 1:
			ProjectView.displayProjectCreationForm();
			break;
		case 2:
			ProjectView.displayAllProject();
			break;
		case 3:
			ProjectView.deleteProject();
			break;
		case 4:
			return 0;
		}
		return 0;
	}

}
