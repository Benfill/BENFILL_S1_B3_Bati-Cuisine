package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private String url = System.getenv("DB_URL");
	private String username = System.getenv("DB_USERNAME");
	private String password = System.getenv("DB_PASSWORD");
	private Connection connection;
	private static DBConnection single_instance = null;

	public static synchronized DBConnection getInstance() {
		if (single_instance == null)
			single_instance = new DBConnection();

		return single_instance;

	}

	public Connection connect() {
		try {
			connection = DriverManager.getConnection(url, username, password);
			if (connection != null) {
				System.out.println("Connection established successfully !");
			} else {
				System.out.println("Connection failed !!");
			}
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
		return connection;
	}
}