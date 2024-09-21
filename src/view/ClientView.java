package view;

import model.Client;
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

	public static int displayAllClient() {
		Input.getInteger("return", "Enter anything to back home", true);
		return 0;
	}
}
