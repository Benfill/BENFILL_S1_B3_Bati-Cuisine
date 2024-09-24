package dao;

import java.util.HashMap;

import model.Client;

public interface ClientDAO {

	public void create(Client client);;

	public Client searchForClient(String name);;

	public HashMap<Integer, Client> getClient();

	Client getClient(int id);
}
