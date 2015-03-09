package mx.com.mindbits.argos.inventory.dao.impl;

import java.util.List;

import mx.com.mindbits.argos.inventory.dao.HibernateBaseDAO;
import mx.com.mindbits.argos.inventory.dao.ProjectDAO;
import mx.com.mindbits.argos.inventory.entity.Project;

import org.springframework.stereotype.Repository;

@Repository
public class ProjectDAOImpl extends HibernateBaseDAO<Integer, Project> implements ProjectDAO {

	private static final long serialVersionUID = 2689545848885510182L;

	@Override
	public Project getProject(Integer id) {
		return find(id);
	}
	
	@Override
	public List<Project> getAllProjects() {
		return list();
	}
	
	@Override
	public Project saveProject(Project newProject) {
		return save(newProject);
	}

	@Override
	public Project updateProject(Project projectToUpdate) {
		return update(projectToUpdate);
	}
	
	@Override
	public void deleteProject(Integer id) {
		delete(id);
	}
	
}
