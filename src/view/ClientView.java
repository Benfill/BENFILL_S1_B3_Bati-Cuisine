package view;

import java.util.HashMap;

import model.Client;
import service.ClientService;
import util.Input;

public class ClientView {
	public static int showClientMenu() {
		System.out.println("1. Search for an existing client");
		System.out.println("2. Add a new client");
		return Input.getInteger("Client Management Choice", "Choose an option: ", false).get();
	}

	public static void displayClientDetails(Client client) {
		System.out.println("Client found!");
		System.out.println("Name: " + client.getName());
		System.out.println("Address: " + client.getAddress());
		System.out.println("Phone Number: " + client.getPhone());
		System.out.println("Professional: " + (client.isProfessional() ? "Yes" : "No"));
	}

	private static void displayHeader() {
		System.out.printf("%-10s %-20s %-30s %-15s %-15s\n", "ID", "Name", "Address", "Phone", "Professional");
		System.out
				.println("-------------------------------------------------------------------------------------------");

	}

	public static int displayAllClient() {
		ClientService cService = new ClientService();
		HashMap<Integer, Client> clients = cService.getClient();

		if (clients == null) {
			System.out.println("There is no data yet");
			Input.getInteger("return", "Enter anything to back home", true);
			return 0;
		}

		displayHeader();
		clients.forEach((id, client) -> {
			client.displayClient();
		});
		Input.getInteger("return", "Enter anything to back home", true);
		System.out.println();
		return 0;
	}
}
