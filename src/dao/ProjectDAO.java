package dao;

import java.util.HashMap;

import model.Project;
import model.Quote;

public interface ProjectDAO {

	public Project create(Project project);

	public void update(Project project);

	public boolean delete(int id);

	public Project getProjectByName(Project project);

	public Project getProjectById(int id);

	public String acceptQuote(int projectId);

	public void generateQuote(Quote quote);

	public HashMap<String, Project> getProjects();

	public HashMap<Integer, Quote> getQuotes();
}
