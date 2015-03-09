package mx.com.mindbits.argos.inventory.dao.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import mx.com.mindbits.argos.inventory.dao.CategoryDAO;
import mx.com.mindbits.argos.inventory.dao.ItemClassificationDAO;
import mx.com.mindbits.argos.inventory.dao.ItemDAO;
import mx.com.mindbits.argos.inventory.entity.Category;
import mx.com.mindbits.argos.inventory.entity.Item;
import mx.com.mindbits.argos.inventory.entity.ItemClassification;
import mx.com.mindbits.argos.inventory.entity.UnitOfMeasure;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations="classpath:META-INF/test-context.xml")
public class InventoryDAOTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private ItemDAO itemDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ItemClassificationDAO itemClassificationDAO;
	
	@Test
	public void testSaveItem() {
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setName("KG");
		
		Item item = new Item();
		item.setCode("COD0000001234ABC");
		item.setDescription("Some new item");
		item.setDetail("Some new item detail");
		item.setUnitOfMeasure(uom);
		
		item = itemDAO.saveItem(item);
		assertNotNull(item);
		assertTrue(item.getId() > 0);
		
		item = new Item();
		item.setCode("COD0000001235ABC");
		item.setDescription("Some new item");
		item.setDetail("Some new item detail");
		item.setUnitOfMeasure(uom);
		
		item = itemDAO.saveItem(item);
		assertNotNull(item);
		assertTrue(item.getId() > 0);
	}
	
	@Test
	public void testSaveCategory() {
		Category category = new Category();
		category.setName("Sillas");
		category.setDescription("Categoría de sillas");
		categoryDAO.saveCategory(category);
		
		assertNotNull(category);
		assertTrue(category.getId() > 0);
	}
	
	@Test
	public void testSaveItemClassification() {
		ItemClassification itemClassification = new ItemClassification();
		Item item = itemDAO.getAllItems().get(0);
		Category category = categoryDAO.getAllCategories().get(0);
		itemClassification.setItem(item);
		itemClassification.setCategory(category);
		itemClassificationDAO.saveClassification(itemClassification);
		
		assertNotNull(itemClassification);
		assertTrue(itemClassification.getId() > 0);
	}

	@Test
	public void testGetItemClassification() {
		ItemClassification itemClassification = itemClassificationDAO.getItemClassification(Integer.valueOf(1));
		
		assertNotNull(itemClassification);
		assertNotNull(itemClassification.getItem());
		assertNotNull(itemClassification.getCategory());
	}
	
	@Test
	public void testGetAllItems() {
		List<Item> items = itemDAO.getAllItems();

		assertNotNull(items);
		assertTrue(items.size() > 0);		
	}
	
}
