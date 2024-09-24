package controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Client;
import model.Labor;
import model.Material;
import model.Project;
import model.Quote;
import model.enums.ProjectStatus;
import service.ClientService;
import service.ProjectService;
import util.Input;
import view.ClientView;
import view.CostCalculationView;
import view.LaborView;
import view.MaterialView;
import view.ProjectView;

public class ProjectController {
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
	private HashMap<String, Material> materials = new HashMap<String, Material>();
	private HashMap<String, Labor> labors = new HashMap<String, Labor>();
	private Project project = null;
	private Client client = null;
	private double materialCost = 0;
	private double laborCost = 0;
	private double totalCost = 0;

	public void createProject() {
		ClientController clientController = new ClientController();
		ComponentController componentController = new ComponentController();
		Boolean validator = false;

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
			Material material = (Material) componentController.addComponentToProject(project.getId(), "material");
			materials.put(material.getName(), material);
			validator = Input.getConfirmation("Do you want to add another material? (y/n)");
		}

		validator = true;

		while (validator) {
			Labor labor = (Labor) componentController.addComponentToProject(project.getId(), "labor");
			labors.put(labor.getName(), labor);
			validator = Input.getConfirmation("Do you want to add another type of labor? (y/n)");
		}

		calculate();
		updateProject(project);

	}

	private Project insertProject(String clientName) {
		ProjectService pService = new ProjectService();
		ClientService cService = new ClientService();
		Project project = new Project();
		Client client = cService.searchForClient(clientName);
		boolean checker = false;
		while (!checker) {
			String name = Input.getString("Project Name", "Enter Project Name", false).get();
			project.setProjectName(name);
			if (pService.getProjectByName(project) == null)
				checker = true;
			else
				System.out.println("This project name is exists. Please try Again...");
		}
		double surface = Input.getDouble("Surface", "Enter the surface area of ​​the kitchen (in m²)", false).get();

		project.setClientId(client.getId());
		project.setSurface(surface);
		project.setStatus(ProjectStatus.InProgress);
		return pService.create(project);
	}

	private void updateProject(Project project) {
		ProjectService pService = new ProjectService();
		pService.update(project);
	}

	public void deleteProject() {
		ProjectService pService = new ProjectService();
		int id = Input.getInteger("Project id", "Enter Project Id", false).get();
		project = pService.getProjectById(id);
		if (project == null) {
			System.out.println("There is no project with this ID #" + id);
			return;
		}

		project.displayHeader();
		project.displayProject();
		System.out.println();
		boolean checker = Input.getConfirmation("Are you sure you want to delete this project? (y/n)");
		if (!checker) {
			System.out.println("Operation Cancelled");
			return;
		}
		pService.delete(id);
		System.out.println(project.getProjectName() + " Has been deleted successfully");
	}

	private void calculate() {
		System.out.println();
		System.out.println("--- Total cost calculation ---");
		System.out.println();

		double taxRate = getTax();
		double margin = getMargin();

		displayText();

		materialCost = MaterialView.displayMaterial(materials);

		String text = String.format("€%.2f**", materialCost);
		System.out.println("**Total cost of materials before VAT: " + text);
		if (taxRate > 0)
			materialCost = materialCost + (materialCost * (taxRate / 100));

		text = String.format("(%.2f%%) : €%.2f**", taxRate, materialCost);
		System.out.println("**Total cost of materials after VAT " + text);

		laborCost = LaborView.displayLabor(labors);

		text = String.format("€%.2f**", laborCost);
		System.out.println("**Total labors cost before VAT: " + text);
		if (taxRate > 0)
			laborCost = laborCost + (laborCost * (taxRate / 100));

		text = String.format("(%.2f%%) : €%.2f**", taxRate, laborCost);
		System.out.println("**Total labors cost after VAT " + text);

		double costExcludingMargin = 0;
		double marginCost = 0;

		costExcludingMargin = laborCost + materialCost;
		marginCost = margin > 0 ? costExcludingMargin * (margin / 100) : costExcludingMargin;
		totalCost = marginCost + costExcludingMargin;

		CostCalculationView.displayFinalCost(costExcludingMargin, margin, totalCost, marginCost);

		project.setProfitMargin(marginCost);
		project.setTotalCost(totalCost);

		new Quote().generate(project.getId(), totalCost);
	}

	private void displayText() {
		System.out.println();
		System.out.println("Calculation of the cost in progress...");
		System.out.println();

		System.out.println("--- Résultat du Calcul ---");
		System.out.println();

		ProjectView.displayDetails(project, client);

		System.out.println();
		System.out.println("--- Cost Details ---");
		System.out.println();

	}

	private double getTax() {
		double taxRate = -1;
		boolean validator = Input.getConfirmation("Do you want to apply VAT to the project? (y/n)");
		if (validator) {
			taxRate = Input.getDouble("Vat Rate", "Enter the VAT percentage (%)", false).get();
			while (taxRate < 0 || taxRate > 100) {
				taxRate = Input.getDouble("Vat Rate", "Please enter a valid VAT percentage (0-100)", false).get();
			}
		}

		return taxRate;

	}

	private double getMargin() {
		double margin = -1;
		boolean validator = Input.getConfirmation("Would you like to apply a profit margin to the project? (y/n)");
		if (validator) {
			margin = Input.getDouble("Profit Margin", "Enter the profit margin percentage (%)", false).get();
			while (margin < 0 || margin > 100)
				margin = Input
						.getDouble("Profit Margin", "Please enter a valid profit margin percentage (0-100)", false)
						.get();
		}
		return margin;
	}

	public void displayProjects() {
		ProjectService pService = new ProjectService();
		HashMap<String, Project> projects = pService.getProjects();
		project = new Project();
		if (projects == null) {
			System.out.println("There is no data yet");
			return;
		}

		System.out.println();
		project.displayHeader();

		projects.forEach((name, p) -> {
			p.displayProject();
		});
		System.out.println();

	}

}
