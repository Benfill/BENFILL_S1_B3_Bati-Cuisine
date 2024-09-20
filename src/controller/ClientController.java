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

	public void createClient(Client client) {
		cService.create(client);
	}

	public Client addClientToProject(int choice) {
		Client client;
		if (choice == 1) {
			String name = Input.getString("Name", "Enter the Client's Name", false).get();
			client = search(name);
		} else {
			String name = Input.getString("Name", "Enter the Client's Name", false).get();
			String address = Input.getString("Address", "Enter the Client's Address", false).get();
			String phone = Input.getPhoneNumber("Phone", "Enter the Client's Phone", false).get();
			String isPro = Input.getString("Is Pro", "Enter ( y ) if the Client is Professional", true).get();

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
