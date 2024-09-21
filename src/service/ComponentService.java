package service;

import dao.ComponentDAO;
import model.Component;
import model.Labor;
import model.Material;
import util.DBRequest;

public class ComponentService implements ComponentDAO {

	@Override
	public void create(Component component) {

		String name = component.getName();
		double cost = component.getUnitCost();
		double quantity = component.getQuantity();
		String type = component.getType().toString();
		double taxRate = component.getTaxRate();
		int projectId = component.getProjectId();
		String columns = "name, unit_cost, quantity, type, tax_rate, project_id, ";
		Object[] values;

		if (component instanceof Labor) {
			Labor labor = (Labor) component;
			columns = columns + "hourly_rate, hours_worked, worker_productivity";
			values = new Object[] { name, cost, quantity, type, taxRate, projectId, labor.getHourlyRate(),
					labor.getHoursWorked(), labor.getWorkerProductivity() };
			DBRequest.insert("labors", columns, values);
		} else {
			Material material = (Material) component;
			columns = columns + "transport_cost, quality_coefficient";
			values = new Object[] { name, cost, quantity, type, taxRate, projectId, material.getTransportCost(),
					material.getQualityCoefficient() };
			DBRequest.insert("materials", columns, values);
		}

	}

}
