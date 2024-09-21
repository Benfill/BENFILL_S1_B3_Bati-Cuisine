package service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.ProjectDAO;
import model.Project;
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

		return getProject(project);
	}

	@Override
	public Project getProject(Project project) {
		Object[] value = { project.getProjectName() };
		ResultSet rs = DBRequest.getAll("projects", "name=?", value, false);

		try {
			if (rs.next()) {
				project.setId(rs.getInt("id"));
			} else {
				logger.warn("No project found with name: " + project.getProjectName());
				return null;
			}
		} catch (SQLException e) {
			logger.error("error: " + e);
		}
		return project;
	}

}
