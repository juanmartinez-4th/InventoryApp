package mx.com.mindbits.argos.inventory.bsn;

import java.util.List;

import mx.com.mindbits.argos.inventory.vo.CategoryVO;
import mx.com.mindbits.argos.inventory.vo.ItemClassificationVO;
import mx.com.mindbits.argos.inventory.vo.ItemLocationVO;
import mx.com.mindbits.argos.inventory.vo.ItemVO;
import mx.com.mindbits.argos.inventory.vo.UnitOfMeasureVO;

public interface CatalogManager {
	
	CategoryVO getCategory(Integer categoryId);
	
	List<CategoryVO> getAllCategories();
	
	CategoryVO saveCategory(CategoryVO newCategory);
	
	CategoryVO updateCategory(CategoryVO categoryToUpdate);
	
	void deleteCategory(Integer categoryId);
	
	ItemVO getItem(Integer itemId);
	
	List<ItemVO> getAllItems();
	
	ItemVO createItem(ItemVO itemToSave, ItemClassificationVO itemClassification, ItemLocationVO itemLocation) throws Exception;
	
	ItemVO updateItem(ItemVO itemToUpdate);
	
	UnitOfMeasureVO getUnitOfMeasure(Integer unitId);
	
	List<UnitOfMeasureVO> getAllUnitsOfMeasure();
	
	UnitOfMeasureVO saveUnitOfMeasure(UnitOfMeasureVO newUnitOfMeasure);
	
	UnitOfMeasureVO updateUnitOfMeasure(UnitOfMeasureVO unitToUpdate);
	
	void deleteUnitOfMeasure(Integer unitId);

	ItemClassificationVO saveItemClassification(ItemClassificationVO itemClassification);
	
	List<ItemLocationVO> getItemLocations(ItemVO item);

	ItemLocationVO saveLocation(ItemLocationVO newLocation);

	ItemLocationVO updateLocation(ItemLocationVO locationToUpdate);

	void deleteLocation(Integer locationId);
}
