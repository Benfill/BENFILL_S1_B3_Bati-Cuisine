package util;

public class TableCreation {
	public TableCreation() {
		// Create global sequence for Components and related tables
		DBRequest.createTable("global_component_id_seq", "", "SEQUENCE");

		// Create Projects table
		DBRequest.createTable("Projects", "id SERIAL PRIMARY KEY, name VARCHAR(255), startDate DATE, endDate DATE", "");

		// Create Clients table
		DBRequest.createTable("Clients", "id SERIAL PRIMARY KEY, name VARCHAR(255), contactInfo VARCHAR(255)", "");

		// Create Components table
		DBRequest.createTable("Components",
				"id INT DEFAULT nextval('global_component_id_seq') PRIMARY KEY, type VARCHAR(255), description TEXT",
				"");

		// Create Quotes table
		DBRequest.createTable("Quotes",
				"id SERIAL PRIMARY KEY, projectId INT, amount DECIMAL(10, 2), FOREIGN KEY (projectId) REFERENCES Projects(id)",
				"");

		// Create Labors table with inherited structure from Components
		DBRequest.createTable("Labors", "type VARCHAR(255), description TEXT, hoursWorked DECIMAL(10, 2)",
				"INHERITS (Components)");

		// Create Materials table with inherited structure from Components
		DBRequest.createTable("Materials", "name VARCHAR(255), quantity INT, cost DECIMAL(10, 2)",
				"INHERITS (Components)");

	}
}
