package mx.com.mindbits.argos.inventory.dao;

import java.util.List;

import mx.com.mindbits.argos.inventory.entity.Project;


public interface ProjectDAO extends BaseDAO<Integer, Project> {
	
	Project getProject(Integer projectId);
	
	List<Project> getAllProjects();
	
	Project saveProject(Project project);
	
	Project updateProject(Project projectToUpdate);
	
	void deleteProject(Integer projectId);
	
}
