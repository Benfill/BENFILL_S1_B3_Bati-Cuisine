package util;

public class TableCreation {
	public TableCreation() {

		// Create Clients table
		DBRequest.createTable("clients",
				"id SERIAL PRIMARY KEY, name VARCHAR(255) UNIQUE, address VARCHAR(255), phone VARCHAR(255), isProfessional BOOLEAN",
				"");

		// Create Projects table
		DBRequest.createTable("projects",
				"id SERIAL PRIMARY KEY, name VARCHAR(255) UNIQUE, profit_margin FLOAT, total_cost FLOAT, surface FLOAT, client_id INT references clients(id), status VARCHAR(50)",
				"");

		// Create Components table
		DBRequest.createTable("components",
				"id SERIAL PRIMARY KEY, name VARCHAR(255), unit_cost FLOAT, quantity FLOAT, type VARCHAR(255), tax_rate FLOAT, project_id INT references projects(id)",
				"");

		// Create Quotes table
		DBRequest.createTable("quotes",
				"id SERIAL PRIMARY KEY, project_id INT, estimated_amount float, issue_date DATE, validity_date DATE, accepted BOOLEAN, FOREIGN KEY (project_id) REFERENCES projects(id)",
				"");

		// Create Labors table with inherited structure from Components
		DBRequest.createTable("labors", "hourly_rate FLOAT, hours_worked float, worker_productivity FLOAT",
				"INHERITS (Components)");

		// Create Materials table with inherited structure from Components
		DBRequest.createTable("materials", "transport_cost FLOAT, quality_coefficient FLOAT", "INHERITS (Components)");

	}
}
