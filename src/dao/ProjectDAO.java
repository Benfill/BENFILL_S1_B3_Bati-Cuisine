package dao;

import model.Project;

public interface ProjectDAO {

	public Project create(Project project);

	public Project getProject(Project project);
}
