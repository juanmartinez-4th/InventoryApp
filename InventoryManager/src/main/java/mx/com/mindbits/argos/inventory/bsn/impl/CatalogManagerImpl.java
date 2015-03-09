package mx.com.mindbits.argos.inventory.bsn.impl;

import java.util.ArrayList;
import java.util.List;

import mx.com.mindbits.argos.inventory.bsn.CatalogManager;
import mx.com.mindbits.argos.inventory.dao.CategoryDAO;
import mx.com.mindbits.argos.inventory.dao.ItemClassificationDAO;
import mx.com.mindbits.argos.inventory.dao.ItemDAO;
import mx.com.mindbits.argos.inventory.dao.LocationDAO;
import mx.com.mindbits.argos.inventory.dao.ProjectDAO;
import mx.com.mindbits.argos.inventory.dao.UnitOfMeasureDAO;
import mx.com.mindbits.argos.inventory.entity.Category;
import mx.com.mindbits.argos.inventory.entity.Item;
import mx.com.mindbits.argos.inventory.entity.ItemClassification;
import mx.com.mindbits.argos.inventory.entity.Location;
import mx.com.mindbits.argos.inventory.entity.Project;
import mx.com.mindbits.argos.inventory.entity.UnitOfMeasure;
import mx.com.mindbits.argos.inventory.vo.CategoryVO;
import mx.com.mindbits.argos.inventory.vo.ItemClassificationVO;
import mx.com.mindbits.argos.inventory.vo.ItemVO;
import mx.com.mindbits.argos.inventory.vo.LocationVO;
import mx.com.mindbits.argos.inventory.vo.ProjectVO;
import mx.com.mindbits.argos.inventory.vo.UnitOfMeasureVO;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CatalogManagerImpl implements CatalogManager {
	@Autowired
	private ItemDAO itemDAO;
	
	@Autowired
	private UnitOfMeasureDAO unitOfMeasureDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private LocationDAO locationDAO;
	
	@Autowired
	private ProjectDAO projectDAO;
	
	@Autowired
	private ItemClassificationDAO itemClassificationDAO;
	
	@Autowired
	private Mapper mapper;
	
	@Override
	public ItemVO getItem(Integer id) {
		Item item = itemDAO.getItem(id);
		return mapper.map(item, ItemVO.class);
	}

	@Override
	public List<ItemVO> getAllItems() {
		ArrayList<ItemVO> results;
		
		List<Item> items = itemDAO.getAllItems();
		results = new ArrayList<>(items.size());
		
		for (Item item : items) {
			ItemVO result = mapper.map(item, ItemVO.class);
			results.add(result);
		}
		
		return results;
	}

	@Override
	public ItemVO saveItem(ItemVO itemToSave) {
		Item item = mapper.map(itemToSave, Item.class);
		item = itemDAO.saveItem(item);
		
		return mapper.map(item, ItemVO.class);
	}
	
	@Override
	public ItemVO updateItem(ItemVO itemToUpdate) {
		Item item = mapper.map(itemToUpdate, Item.class);
		item = itemDAO.updateItem(item);
		
		return mapper.map(item, ItemVO.class);
	}

	@Override
	public CategoryVO getCategory(Integer categoryId) {
		Category category = categoryDAO.getCategory(categoryId);
		return mapper.map(category, CategoryVO.class);
	}

	@Override
	public List<CategoryVO> getAllCategories() {
		ArrayList<CategoryVO> results;
		
		List<Category> categories = categoryDAO.getAllCategories();
		results = new ArrayList<>(categories.size());
		
		for (Category category : categories) {
			CategoryVO result = mapper.map(category, CategoryVO.class);
			results.add(result);
		}
		
		return results;
	}

	@Override
	public CategoryVO saveCategory(CategoryVO newCategory) {
		Category category = mapper.map(newCategory, Category.class);
		category = categoryDAO.saveCategory(category);
		
		return mapper.map(category, CategoryVO.class);
	}

	@Override
	public CategoryVO updateCategory(CategoryVO categoryToUpdate) {
		Category category = mapper.map(categoryToUpdate, Category.class);
		category = categoryDAO.updateCategory(category);
		
		return mapper.map(category, CategoryVO.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		categoryDAO.deleteCategory(categoryId);
	}

	@Override
	public LocationVO getLocation(Integer locationId) {
		Location location = locationDAO.getLocation(locationId);
		return mapper.map(location, LocationVO.class);
	}

	@Override
	public List<LocationVO> getAllLocations() {
		ArrayList<LocationVO> results;
		
		List<Location> locations = locationDAO.getAllLocations();
		results = new ArrayList<>(locations.size());
		
		for (Location location : locations) {
			LocationVO result = mapper.map(location, LocationVO.class);
			results.add(result);
		}
		
		return results;
	}

	@Override
	public LocationVO saveLocation(LocationVO newLocation) {
		Location location = mapper.map(newLocation, Location.class);
		location = locationDAO.saveLocation(location);
		
		return mapper.map(location, LocationVO.class);
	}

	@Override
	public LocationVO updateLocation(LocationVO locationToUpdate) {
		Location location = mapper.map(locationToUpdate, Location.class);
		location = locationDAO.updateLocation(location);
		
		return mapper.map(location, LocationVO.class);
	}

	@Override
	public void deleteLocation(Integer locationId) {
		locationDAO.deleteLocation(locationId);
	}

	@Override
	public ProjectVO getProject(Integer projectId) {
		Project project = projectDAO.getProject(projectId);
		return mapper.map(project, ProjectVO.class);
	}

	@Override
	public List<ProjectVO> getAllProjects() {
		ArrayList<ProjectVO> results;
		
		List<Project> projects = projectDAO.getAllProjects();
		results = new ArrayList<>(projects.size());
		
		for (Project project : projects) {
			ProjectVO result = mapper.map(project, ProjectVO.class);
			results.add(result);
		}
		
		return results;
	}

	@Override
	public ProjectVO saveProject(ProjectVO newProject) {
		Project project = mapper.map(newProject, Project.class);
		project = projectDAO.saveProject(project);
		
		return mapper.map(project, ProjectVO.class);
	}

	@Override
	public ProjectVO updateProject(ProjectVO projectToUpdate) {
		Project project = mapper.map(projectToUpdate, Project.class);
		project = projectDAO.updateProject(project);
		
		return mapper.map(project, ProjectVO.class);
	}

	@Override
	public void deleteProject(Integer projectId) {
		projectDAO.deleteProject(projectId);
	}

	@Override
	public UnitOfMeasureVO getUnitOfMeasure(Integer unitId) {
		UnitOfMeasure unitOfMeasure = unitOfMeasureDAO.getUnitOfMeasure(unitId);
		return mapper.map(unitOfMeasure, UnitOfMeasureVO.class);
	}

	@Override
	public List<UnitOfMeasureVO> getAllUnitsOfMeasure() {
		ArrayList<UnitOfMeasureVO> results;
		
		List<UnitOfMeasure> unitsOfMeasure = unitOfMeasureDAO.getAllUnitsOfMeasure();
		results = new ArrayList<>(unitsOfMeasure.size());
		
		for (UnitOfMeasure unitOfMeasure : unitsOfMeasure) {
			UnitOfMeasureVO result = mapper.map(unitOfMeasure, UnitOfMeasureVO.class);
			results.add(result);
		}
		
		return results;
	}

	@Override
	public UnitOfMeasureVO saveUnitOfMeasure(UnitOfMeasureVO newUnitOfMeasure) {
		UnitOfMeasure unitOfMeasure = mapper.map(newUnitOfMeasure, UnitOfMeasure.class);
		unitOfMeasure = unitOfMeasureDAO.saveUnitOfMeasure(unitOfMeasure);
		
		return mapper.map(unitOfMeasure, UnitOfMeasureVO.class);
	}

	@Override
	public UnitOfMeasureVO updateUnitOfMeasure(UnitOfMeasureVO unitToUpdate) {
		UnitOfMeasure unitOfMeasure = mapper.map(unitToUpdate, UnitOfMeasure.class);
		unitOfMeasure = unitOfMeasureDAO.updateUnitOfMeasure(unitOfMeasure);
		
		return mapper.map(unitOfMeasure, UnitOfMeasureVO.class);
	}
	
	@Override
	public void deleteUnitOfMeasure(Integer unitToDelete) {		
		unitOfMeasureDAO.deleteUnitOfMeasure(unitToDelete);
	}
	
	@Override
	public ItemClassificationVO saveItemClassification(ItemClassificationVO classificationToSave) {
		ItemClassification classification = mapper.map(classificationToSave, ItemClassification.class);
		classification = itemClassificationDAO.saveClassification(classification);
		
		return mapper.map(classification, ItemClassificationVO.class);
	}
	
}
