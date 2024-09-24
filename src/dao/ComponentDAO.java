package dao;

import java.util.HashMap;

import model.Component;
import model.Labor;
import model.Material;

public interface ComponentDAO {

	public void create(Component component);

	HashMap<String, Material> getMaterials(int projectId);

	HashMap<String, Labor> getLabors(int projectId);

}
