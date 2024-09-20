package dao;

import model.Client;

public interface ClientDAO {

	public void create(Client client);;

	public Client searchForClient(String name);;
}
