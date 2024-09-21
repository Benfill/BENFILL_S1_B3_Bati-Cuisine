package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Client;
import model.Project;
import model.enums.ProjectStatus;
import service.ClientService;
import service.ProjectService;
import util.Input;
import view.ClientView;

public class ProjectController {
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	public void createProject() {
		ClientController clientController = new ClientController();
		ComponentController componentController = new ComponentController();
		Boolean validator = false;
		Client client = null;
		Project project = null;

		while (!validator || client == null) {

			int choice = ClientView.showClientMenu();

			while (choice != 1 && choice != 2)
				choice = Input.getInteger("Client Choice", "Try again choose 1 or 2", false).get();

			client = clientController.addClientToProject(choice);

			if (client == null) {
				System.out.println("Client not Found. Try again...");
				continue;
			}
			ClientView.displayClientDetails(client);
			validator = Input.getConfirmation("Would you like to continue with this client? (y/n)");
		}

		System.out.println("--- Creation of a New Project ---");
		System.out.println();

		project = insertProject(client.getName());

		validator = true;

		while (validator) {
			componentController.addComponentToProject(project.getId(), "material");
			validator = Input.getConfirmation("Do you want to add another material? (y/n)");
		}

		validator = true;

		while (validator) {
			componentController.addComponentToProject(project.getId(), "labor");
			validator = Input.getConfirmation("Do you want to add another type of labor? (y/n)");
		}

	}

	private Project insertProject(String clientName) {
		ProjectService pService = new ProjectService();
		ClientService cService = new ClientService();
		Project project = new Project();
		Client client = cService.searchForClient(clientName);
		String name = Input.getString("Project Name", "Enter Project Name", false).get();
		double surface = Input.getDouble("Surface", "Enter the surface area of ​​the kitchen (in m²)", false).get();

		project.setProjectName(name);
		project.setClientId(client.getId());
		project.setSurface(surface);
		project.setStatus(ProjectStatus.InProgress);
		return pService.create(project);
	}
}
