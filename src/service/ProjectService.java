package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.ProjectDAO;
import model.Project;
import model.Quote;
import model.enums.ProjectStatus;
import util.DBRequest;

public class ProjectService implements ProjectDAO {
	private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);

	@Override
	public Project create(Project project) {
		String columns = "name, profit_margin, total_cost, client_id, surface, status";
		String status = project.getStatus().name();
		Object[] values = { project.getProjectName(), project.getProfitMargin(), project.getTotalCost(),
				project.getClientId(), project.getSurface(), status };
		DBRequest.insert("projects", columns, values);

		return getProjectByName(project);
	}

	@Override
	public Project getProjectByName(Project project) {
		Object[] value = { project.getProjectName() };
		ResultSet rs = DBRequest.getAll("projects", "name=?", value, false);

		try {
			if (rs.next())
				project.setId(rs.getInt("id"));
			else
				return null;

		} catch (SQLException e) {
			logger.error("error: " + e);
		}
		return project;
	}

	@Override
	public String acceptQuote(int projectId) {
		LocalDate date = LocalDate.now();
		Object[] value = { projectId, date };
		ResultSet rSet = DBRequest.getAll("quotes", "project_id=? AND validity_date <= ?", value, false);

		if (!DBRequest.hasResults(rSet))
			return "No results found for the given project ID, or the validity date has passed.";

		try {
			rSet.next();
			DBRequest.update("quotes", rSet.getInt("id"), "accepted", new Object[] { true });
		} catch (SQLException e) {
			logger.error("error: " + e);
			return "error: " + e;
		}
		return null;
	}

	@Override
	public void generateQuote(Quote quote) {
		String columns = "estimated_amount, issue_date, validity_date, project_id";
		Object[] values = { quote.getEstimatedAmount(), quote.getIssueDate(), quote.getValidityDate(),
				quote.getProjectId() };
		DBRequest.insert("quotes", columns, values);
	}

	@Override
	public HashMap<String, Project> getProjects() {
		ResultSet rSet = DBRequest.getAll("projects", "", null, false);
		HashMap<String, Project> projects = new HashMap<String, Project>();
		if (!DBRequest.hasResults(rSet))
			return null;
		try {
			boolean checker = true;
			while (checker) {
				Project project = new Project();
				project.setId(rSet.getInt("id"));
				String name = rSet.getString("name");
				project.setProjectName(name);
				project.setClientId(rSet.getInt("client_id"));
				project.setSurface(rSet.getDouble("surface"));
				project.setProfitMargin(rSet.getDouble("profit_margin"));
				project.setTotalCost(rSet.getDouble("total_cost"));
				String status = rSet.getString("status");
				ProjectStatus statusEnum = ProjectStatus.valueOf(status);
				project.setStatus(statusEnum);
				projects.put(name, project);
				checker = rSet.next();
			}
		} catch (SQLException e) {
			logger.error("error: " + e);
			return null;
		}

		return projects;

	}

	@Override
	public void update(Project project) {
		String columns = "name, profit_margin, total_cost, client_id, surface, status";
		String status = project.getStatus().name();
		Object[] values = { project.getProjectName(), project.getProfitMargin(), project.getTotalCost(),
				project.getClientId(), project.getSurface(), status };
		DBRequest.update("projects", project.getId(), columns, values);

	}

	@Override
	public boolean delete(int id) {
		DBRequest.delete("projects", id);
		return true;

	}

	@Override
	public Project getProjectById(int id) {
		ResultSet rSet = DBRequest.getAll("projects", "id=?", new Object[] { id }, false);
		if (!DBRequest.hasResults(rSet))
			return null;

		Project project = new Project();
		try {
			project.setId(rSet.getInt("id"));
			String name = rSet.getString("name");
			project.setProjectName(name);
			project.setClientId(rSet.getInt("client_id"));
			project.setSurface(rSet.getDouble("surface"));
			project.setProfitMargin(rSet.getDouble("profit_margin"));
			project.setTotalCost(rSet.getDouble("total_cost"));
			String status = rSet.getString("status");
			ProjectStatus statusEnum = ProjectStatus.valueOf(status);
			project.setStatus(statusEnum);

		} catch (SQLException e) {
			logger.error("error: " + e);
			return null;
		}

		return project;
	}

	@Override
	public HashMap<Integer, Quote> getQuotes() {
		ResultSet rSet = DBRequest.getAll("quotes", "", null, false);
		if (!DBRequest.hasResults(rSet))
			return null;

		boolean checker = true;
		Quote quote;
		HashMap<Integer, Quote> quotes = new HashMap<Integer, Quote>();
		while (checker) {

			try {
				quote = new Quote();
				LocalDate issueDate = rSet.getDate("issue_date").toLocalDate();
				LocalDate validityDate = rSet.getDate("validity_date").toLocalDate();
				int id = rSet.getInt("id");

				quote.setAccepted(rSet.getBoolean("accepted"));
				quote.setEstimatedAmount(rSet.getDouble("estimated_amount"));
				quote.setIssueDate(issueDate);
				quote.setValidityDate(validityDate);
				quote.setProjectId(rSet.getInt("project_id"));
				quote.setId(id);

				quotes.put(id, quote);
				checker = rSet.next();
			} catch (SQLException e) {
				logger.error("error: " + e);
				return null;
			}
		}
		return quotes;
	}

}
