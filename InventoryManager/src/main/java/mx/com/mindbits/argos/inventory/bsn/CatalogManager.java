package mx.com.mindbits.argos.inventory.bsn;

import java.util.List;

import mx.com.mindbits.argos.inventory.vo.CategoryVO;
import mx.com.mindbits.argos.inventory.vo.ItemClassificationVO;
import mx.com.mindbits.argos.inventory.vo.ItemVO;
import mx.com.mindbits.argos.inventory.vo.LocationVO;
import mx.com.mindbits.argos.inventory.vo.ProjectVO;
import mx.com.mindbits.argos.inventory.vo.UnitOfMeasureVO;

public interface CatalogManager {
	
	CategoryVO getCategory(Integer categoryId);
	
	List<CategoryVO> getAllCategories();
	
	CategoryVO saveCategory(CategoryVO newCategory);
	
	CategoryVO updateCategory(CategoryVO categoryToUpdate);
	
	void deleteCategory(Integer categoryId);
	
	LocationVO getLocation(Integer locationId);
	
	List<LocationVO> getAllLocations();
	
	LocationVO saveLocation(LocationVO newLocation);
	
	LocationVO updateLocation(LocationVO locationToUpdate);
	
	void deleteLocation(Integer locationId);
	
	ProjectVO getProject(Integer projectId);
	
	List<ProjectVO> getAllProjects();
	
	ProjectVO saveProject(ProjectVO newProject);
	
	ProjectVO updateProject(ProjectVO projectToUpdate);
	
	void deleteProject(Integer projectId);
	
	ItemVO getItem(Integer itemId);
	
	List<ItemVO> getAllItems();
	
	ItemVO saveItem(ItemVO newItem);
	
	ItemVO updateItem(ItemVO itemToUpdate);
	
	UnitOfMeasureVO getUnitOfMeasure(Integer unitId);
	
	List<UnitOfMeasureVO> getAllUnitsOfMeasure();
	
	UnitOfMeasureVO saveUnitOfMeasure(UnitOfMeasureVO newUnitOfMeasure);
	
	UnitOfMeasureVO updateUnitOfMeasure(UnitOfMeasureVO unitToUpdate);
	
	void deleteUnitOfMeasure(Integer unitId);

	ItemClassificationVO saveItemClassification(ItemClassificationVO itemClassification);
	
}
