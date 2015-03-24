package mx.com.mindbits.argos.inventory.bsn;

import java.math.BigInteger;
import java.util.List;

import mx.com.mindbits.argos.inventory.vo.CategoryVO;
import mx.com.mindbits.argos.inventory.vo.ItemClassificationVO;
import mx.com.mindbits.argos.inventory.vo.ItemLocationVO;
import mx.com.mindbits.argos.inventory.vo.ItemPictureVO;
import mx.com.mindbits.argos.inventory.vo.ItemVO;
import mx.com.mindbits.argos.inventory.vo.ProductionVO;
import mx.com.mindbits.argos.inventory.vo.UnitOfMeasureVO;

public interface CatalogManager {
	
	CategoryVO getCategory(Integer categoryId);
	
	List<CategoryVO> getAllCategories();
	
	List<CategoryVO> getCategoriesTree(Integer categoryId);
	
	List<CategoryVO> getCategoryHierachy(Integer categoryId);
	
	List<CategoryVO> getCategoryDescendants(Integer parentCategory);
	
	CategoryVO saveCategory(CategoryVO newCategory);
	
	CategoryVO updateCategory(CategoryVO categoryToUpdate);
	
	void deleteCategory(Integer categoryId);
	
	ItemVO getItem(Integer itemId);
	
	List<ItemVO> getAllItems();
	
	List<ItemVO> getItemsByDescription(String itemDescription);
	
	List<ItemVO> getItemsByCategory(Integer categoryId);
	
	ItemVO createItem(ItemVO itemToSave, ItemClassificationVO itemClassification, ItemLocationVO itemLocation, List<ItemPictureVO> itemPictures) throws Exception;
	
	ItemVO updateItem(ItemVO itemToUpdate);
	
	UnitOfMeasureVO getUnitOfMeasure(Integer unitId);
	
	List<UnitOfMeasureVO> getAllUnitsOfMeasure();
	
	UnitOfMeasureVO saveUnitOfMeasure(UnitOfMeasureVO newUnitOfMeasure);
	
	UnitOfMeasureVO updateUnitOfMeasure(UnitOfMeasureVO unitToUpdate);
	
	void deleteUnitOfMeasure(Integer unitId);

	ProductionVO getProduction(Integer productionId);
	
	List<ProductionVO> getAllProductions();
	
	ProductionVO saveProduction(ProductionVO newProduction);
	
	ProductionVO updateProduction(ProductionVO productionToUpdate);
	
	void deleteProduction(Integer productionId);
	
	ItemClassificationVO saveItemClassification(ItemClassificationVO itemClassification);
	
	ItemClassificationVO getItemClassification(Integer itemId);
	
	List<ItemLocationVO> getItemLocations(Integer itemId);

	ItemLocationVO saveLocation(ItemLocationVO newLocation);

	ItemLocationVO updateLocation(ItemLocationVO locationToUpdate);

	void deleteLocation(Integer locationId);
	
	List<ItemPictureVO> getItemPictures(Integer itemId);
	
	BigInteger getNextItemId();
	
}
