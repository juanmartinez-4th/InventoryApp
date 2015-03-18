package mx.com.mindbits.argos.inventory.bsn.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mx.com.mindbits.argos.inventory.bsn.CatalogManager;
import mx.com.mindbits.argos.inventory.dao.CategoryDAO;
import mx.com.mindbits.argos.inventory.dao.ItemClassificationDAO;
import mx.com.mindbits.argos.inventory.dao.ItemDAO;
import mx.com.mindbits.argos.inventory.dao.ItemLocationDAO;
import mx.com.mindbits.argos.inventory.dao.ItemPictureDAO;
import mx.com.mindbits.argos.inventory.dao.ProductionDAO;
import mx.com.mindbits.argos.inventory.dao.UnitOfMeasureDAO;
import mx.com.mindbits.argos.inventory.entity.Category;
import mx.com.mindbits.argos.inventory.entity.Item;
import mx.com.mindbits.argos.inventory.entity.ItemClassification;
import mx.com.mindbits.argos.inventory.entity.ItemLocation;
import mx.com.mindbits.argos.inventory.entity.ItemPicture;
import mx.com.mindbits.argos.inventory.entity.Production;
import mx.com.mindbits.argos.inventory.entity.UnitOfMeasure;
import mx.com.mindbits.argos.inventory.vo.CategoryVO;
import mx.com.mindbits.argos.inventory.vo.ItemClassificationVO;
import mx.com.mindbits.argos.inventory.vo.ItemLocationVO;
import mx.com.mindbits.argos.inventory.vo.ItemPictureVO;
import mx.com.mindbits.argos.inventory.vo.ItemVO;
import mx.com.mindbits.argos.inventory.vo.ProductionVO;
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
	private ProductionDAO productionDAO;
	
	@Autowired
	private ItemLocationDAO itemLocationDAO;
	
	@Autowired
	private ItemPictureDAO itemPictureDAO;
	
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

	public List<ItemVO> getItemsByDescription(String itemDescription) {
		ArrayList<ItemVO> results;
		
		List<Item> items = itemDAO.findByDescription(itemDescription);
		results = new ArrayList<>(items.size());
		
		for (Item item : items) {
			ItemVO result = mapper.map(item, ItemVO.class);
			results.add(result);
		}
		
		return results;
	}
	
	@Override
	public List<ItemVO> getItemsByCategory(Integer categoryId) {
		ArrayList<ItemVO> results;
		
		List<ItemClassification> classificationItems = itemClassificationDAO.getClassificationItems(categoryId);
		results = new ArrayList<>(classificationItems.size());
		
		for (ItemClassification classification : classificationItems) {
			ItemVO result = mapper.map(classification.getItem(), ItemVO.class);
			results.add(result);
		}
		
		// Get items from descendant categories too
		List<Category> descCategories = categoryDAO.getCategoryDescendants(categoryId);
		for (Category category : descCategories) {
			List<ItemVO> descItems = getItemsByCategory(category.getId());
			results.addAll(descItems);
		}
		
		return results;
	}
	
	@Override
	public ItemVO createItem(ItemVO itemToSave, ItemClassificationVO itemClassification, 
			ItemLocationVO itemLocation, List<ItemPictureVO> itemPictures) throws Exception {
		Item item = mapper.map(itemToSave, Item.class);
		item.setId(null);
		item = itemDAO.saveItem(item);
		
		if(item != null && item.getId() != null) {
			ItemClassification newItemClassification = mapper.map(itemClassification, ItemClassification.class);
			newItemClassification.setItem(item);
			newItemClassification = itemClassificationDAO.saveClassification(newItemClassification);
			
			ItemLocation newItemLocation = mapper.map(itemLocation, ItemLocation.class);
			newItemLocation.setItem(item);
			newItemLocation = itemLocationDAO.saveLocation(newItemLocation);
			
			for (ItemPictureVO itemPicture : itemPictures) {
				ItemPicture newItemPicture = mapper.map(itemPicture, ItemPicture.class);
				newItemPicture.setItem(item);
				newItemPicture = itemPictureDAO.saveItemPicture(newItemPicture);
				
				if(newItemPicture == null || newItemPicture.getId() == null) {
					throw new Exception("No es posible guardar el artículo");
				}
			}
			
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
	public List<CategoryVO> getCategoryHierachy(Integer categoryId) {
		CategoryVO category = getCategory(categoryId);
		List<CategoryVO> hierarchy = new ArrayList<CategoryVO>(1);
		
		if(category != null && category.getParentCategory() != null) {
			CategoryVO parent = category.getParentCategory();
			
			while(parent != null) {
				CategoryVO parentCategory = new CategoryVO();
				parentCategory.setId(parent.getId());
				parentCategory.setName(parent.getName());
				hierarchy.add(parentCategory);
				
				parent = parent.getParentCategory();
			}			
			Collections.reverse(hierarchy);
		}
		
		CategoryVO originCategory = new CategoryVO();
		originCategory.setId(category.getId());
		originCategory.setName(category.getName());
		hierarchy.add(originCategory);
		
		return hierarchy;
	}
	
	@Override
	public List<CategoryVO> getCategoriesTree(Integer categoryId) {
		List<Category> categories;
		
		if(categoryId != null) {
			categories = categoryDAO.getCategoryDescendants(categoryId);
		}else {
			categories = categoryDAO.getMainCategories();
		}
		
		List<CategoryVO> result = new ArrayList<CategoryVO>(categories.size());
		
		for (Category category : categories) {
			CategoryVO leafCategory = new CategoryVO();
			leafCategory.setId(category.getId());
			leafCategory.setName(category.getName());

			List<CategoryVO> descCategories = getCategoriesTree(leafCategory.getId());
			leafCategory.getDescendantCategories().addAll(descCategories);
			
			result.add(leafCategory);
		}
		
		return result;
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
	public List<ItemLocationVO> getItemLocations(Integer itemId) {
		List<ItemLocation> locations = itemLocationDAO.getItemLocations(itemId);
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
		location = itemLocationDAO.saveLocation(location);
		
		return mapper.map(location, ItemLocationVO.class);
	}

	@Override
	public ItemLocationVO updateLocation(ItemLocationVO locationToUpdate) {
		ItemLocation location = mapper.map(locationToUpdate, ItemLocation.class);
		location = itemLocationDAO.updateLocation(location);
		
		return mapper.map(location, ItemLocationVO.class);
	}

	@Override
	public void deleteLocation(Integer locationId) {
		itemLocationDAO.deleteLocation(locationId);
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
	public ProductionVO getProduction(Integer productionId) {
		Production production = productionDAO.getProduction(productionId);
		return mapper.map(production, ProductionVO.class);
	}

	@Override
	public List<ProductionVO> getAllProductions() {
		ArrayList<ProductionVO> results;
		
		List<Production> productions = productionDAO.getAllProductions();
		results = new ArrayList<>(productions.size());
		
		for (Production production : productions) {
			ProductionVO result = mapper.map(production, ProductionVO.class);
			results.add(result);
		}
		
		return results;
	}

	@Override
	public ProductionVO saveProduction(ProductionVO newProduction) {
		Production production = mapper.map(newProduction, Production.class);
		production = productionDAO.saveProduction(production);
		
		return mapper.map(production, ProductionVO.class);
	}

	@Override
	public ProductionVO updateProduction(ProductionVO productionToUpdate) {
		Production production = mapper.map(productionToUpdate, Production.class);
		production = productionDAO.updateProduction(production);
		
		return mapper.map(production, ProductionVO.class);
	}
	
	@Override
	public void deleteProduction(Integer productionToDelete) {		
		productionDAO.deleteProduction(productionToDelete);
	}
	
	@Override
	public ItemClassificationVO getItemClassification(Integer itemId) {
		ItemClassification itemClassification = itemClassificationDAO.getItemClassification(itemId);
		return mapper.map(itemClassification, ItemClassificationVO.class);
	}
	
	@Override
	public ItemClassificationVO saveItemClassification(ItemClassificationVO classificationToSave) {
		ItemClassification classification = mapper.map(classificationToSave, ItemClassification.class);
		classification = itemClassificationDAO.saveClassification(classification);
		
		return mapper.map(classification, ItemClassificationVO.class);
	}

	@Override
	public List<ItemPictureVO> getItemPictures(Integer itemId) {
		List<ItemPicture> pictures = itemPictureDAO.getItemPictures(itemId);
		List<ItemPictureVO> results = new ArrayList<ItemPictureVO>(pictures.size());
		
		for (ItemPicture itemPicture : pictures) {
			ItemPictureVO picture = mapper.map(itemPicture, ItemPictureVO.class);
			results.add(picture);
		}
		
		return results;
	}
	
	@Override
	public BigInteger getNextItemId() {
		return itemDAO.getNextId();
	}
	
}
