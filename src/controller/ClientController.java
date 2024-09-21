package controller;

import model.Client;
import service.ClientService;
import util.Input;

public class ClientController {
	ClientService cService = new ClientService();

	public Client search(String name) {
		Client client = cService.searchForClient(name);

		return client;
	}

	public Client addClientToProject(int choice) {
		Client client = null;
		if (choice == 1) {
			System.out.println("--- Search for existing client ---");
			System.out.println();
			String name = Input.getString("Name", "Enter the Client's Name", false).get();
			client = search(name);
		} else {
			String name = "";
			boolean checker = false;
			System.out.println("--- Add new client ---");
			while (!checker) {
				System.out.println();
				name = Input.getString("Name", "Enter the Client's Name", false).get();
				client = cService.searchForClient(name);
				if (client != null)
					System.out.println("Oops, There is client with the same name. Try again...");
				else
					checker = true;
			}
			String address = Input.getString("Address", "Enter the Client's Address", false).get();
			String phone = Input.getPhoneNumber("Phone", "Enter the Client's Phone", false).get();
			String isPro = Input
					.getString("Is Pro", "Enter ( y ) if the Client is Professional else enter anything", true).get();

			client = new Client();
			client.setAddress(address);
			client.setName(name);
			client.setPhone(phone);
			client.setProfessional(isPro.equalsIgnoreCase("y") ? true : false);
			cService.create(client);
		}

		return client;
	}
}
