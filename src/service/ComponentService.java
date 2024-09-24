package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

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

	@Override
	public HashMap<String, Material> getMaterials(int projectId) {
		Object[] values = { projectId };
		HashMap<String, Material> materials = new HashMap<>();
		ResultSet rSet = DBRequest.getAll("materials", "project_id=?", values, false);

		if (!DBRequest.hasResults(rSet))
			return null;

		try {
			boolean checker = true;
			while (checker) {
				Material material = new Material();
				material.setName(rSet.getString("name"));
				material.setUnitCost(rSet.getDouble("unit_cost"));
				material.setQuantity(rSet.getDouble("quantity"));
				material.setType(rSet.getString("type"));
				material.setTaxRate(rSet.getDouble("tax_rate"));
				material.setProjectId(projectId);
				material.setQualityCoefficient(rSet.getDouble("quality_coefficient"));
				material.setTransportCost(rSet.getDouble("transport_cost"));
				materials.put(rSet.getString("name"), material);
				checker = rSet.next();
			}
		} catch (SQLException e) {
			return null;
		}

		return materials;
	}

	@Override
	public HashMap<String, Labor> getLabors(int projectId) {
		Object[] values = { projectId };
		HashMap<String, Labor> labors = new HashMap<>();
		ResultSet rSet = DBRequest.getAll("labors", "project_id=?", values, false);

		if (!DBRequest.hasResults(rSet))
			return null;

		try {
			boolean checker = true;
			while (checker) {
				Labor labor = new Labor();
				labor.setName(rSet.getString("name"));
				labor.setUnitCost(rSet.getDouble("unit_cost"));
				labor.setQuantity(rSet.getDouble("quantity"));
				labor.setType(rSet.getString("type"));
				labor.setTaxRate(rSet.getDouble("tax_rate"));
				labor.setProjectId(projectId);
				labor.setHourlyRate(rSet.getDouble("hourly_rate"));
				labor.setHoursWorked(rSet.getDouble("hours_worked"));
				labor.setWorkerProductivity(rSet.getDouble("worker_productivity"));
				labors.put(rSet.getString("name"), labor);
				checker = rSet.next();
			}
		} catch (SQLException e) {
			return null;
		}

		return labors;
	}

}
