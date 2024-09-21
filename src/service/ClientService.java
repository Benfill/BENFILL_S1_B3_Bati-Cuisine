package service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.ClientDAO;
import model.Client;
import util.DBRequest;

public class ClientService implements ClientDAO {

	private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

	@Override
	public void create(Client client) {
		String columns = "name, address, phone, isProfessional";
		Object[] values = { client.getName(), client.getAddress(), client.getPhone(), client.isProfessional() };
		DBRequest.insert("clients", columns, values);
	}

	@Override
	public Client searchForClient(String name) {
		Object[] values = { name };
		ResultSet rSet = DBRequest.getAll("clients", "name=?", values, false);
		Client client = new Client();
		if (DBRequest.hasResults(rSet)) {
			try {
				client.setId(rSet.getInt("id"));
				client.setName(rSet.getString("name"));
				client.setAddress(rSet.getString("address"));
				client.setPhone(rSet.getString("phone"));
				client.setProfessional(rSet.getBoolean("isProfessional"));
				return client;
			} catch (SQLException e) {
				logger.error("error: " + e);
			}
		}
		return null;
	}

}
