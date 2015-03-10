package mx.com.mindbits.argos.inventory.bsn.impl;

import java.util.ArrayList;
import java.util.List;

import mx.com.mindbits.argos.inventory.bsn.CatalogManager;
import mx.com.mindbits.argos.inventory.dao.CategoryDAO;
import mx.com.mindbits.argos.inventory.dao.ItemClassificationDAO;
import mx.com.mindbits.argos.inventory.dao.ItemDAO;
import mx.com.mindbits.argos.inventory.dao.ItemLocationDAO;
import mx.com.mindbits.argos.inventory.dao.UnitOfMeasureDAO;
import mx.com.mindbits.argos.inventory.entity.Category;
import mx.com.mindbits.argos.inventory.entity.Item;
import mx.com.mindbits.argos.inventory.entity.ItemClassification;
import mx.com.mindbits.argos.inventory.entity.ItemLocation;
import mx.com.mindbits.argos.inventory.entity.UnitOfMeasure;
import mx.com.mindbits.argos.inventory.vo.CategoryVO;
import mx.com.mindbits.argos.inventory.vo.ItemClassificationVO;
import mx.com.mindbits.argos.inventory.vo.ItemLocationVO;
import mx.com.mindbits.argos.inventory.vo.ItemVO;
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
	private ItemLocationDAO locationDAO;
	
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
	public ItemVO createItem(ItemVO itemToSave, ItemClassificationVO itemClassification, ItemLocationVO itemLocation) throws Exception {
		Item item = mapper.map(itemToSave, Item.class);
		item.setId(null);
		item = itemDAO.saveItem(item);
		
		if(item != null && item.getId() != null) {
			ItemClassification newItemClassification = mapper.map(itemClassification, ItemClassification.class);
			newItemClassification.setItem(item);
			newItemClassification = itemClassificationDAO.saveClassification(newItemClassification);
			
			ItemLocation newItemLocation = mapper.map(itemLocation, ItemLocation.class);
			newItemLocation.setItem(item);
			newItemLocation = locationDAO.saveLocation(newItemLocation);
			
			if(newItemClassification == null || newItemClassification.getId() == null || 
					newItemLocation == null || newItemLocation.getId() == null) {
				throw new Exception("No es posible guardar el artículo");
			}
		}
		
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
	public List<ItemLocationVO> getItemLocations(ItemVO item) {
		List<ItemLocation> locations = locationDAO.getItemLocations(item.getId());
		List<ItemLocationVO> results = new ArrayList<ItemLocationVO>(locations.size());
		
		for (ItemLocation itemLocation : locations) {
			ItemLocationVO location = mapper.map(itemLocation, ItemLocationVO.class);
			results.add(location);
		}
		
		return results;
	}

	@Override
	public ItemLocationVO saveLocation(ItemLocationVO newLocation) {
		ItemLocation location = mapper.map(newLocation, ItemLocation.class);
		location = locationDAO.saveLocation(location);
		
		return mapper.map(location, ItemLocationVO.class);
	}

	@Override
	public ItemLocationVO updateLocation(ItemLocationVO locationToUpdate) {
		ItemLocation location = mapper.map(locationToUpdate, ItemLocation.class);
		location = locationDAO.updateLocation(location);
		
		return mapper.map(location, ItemLocationVO.class);
	}

	@Override
	public void deleteLocation(Integer locationId) {
		locationDAO.deleteLocation(locationId);
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
