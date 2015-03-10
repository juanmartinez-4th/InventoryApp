package mx.com.mindbits.argos.inventory.bsn.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import mx.com.mindbits.argos.inventory.bsn.CatalogManager;
import mx.com.mindbits.argos.inventory.vo.CategoryVO;
import mx.com.mindbits.argos.inventory.vo.ItemVO;
import mx.com.mindbits.argos.inventory.vo.UnitOfMeasureVO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/test-context.xml")
public class InventoryManagerTest {

	@Autowired
	private CatalogManager catalogManager;
	
	@Test
	public void testSaveItem() {
		UnitOfMeasureVO uom = new UnitOfMeasureVO();
		uom.setName("KG");
		
		ItemVO item = new ItemVO();
		item.setCode("COD0000001234ABC");
		item.setDescription("Some new item");
		item.setDetail("Some new item detail");
		item.setUnitOfMeasure(uom);
		
		try {
//			catalogManager.saveItem(item);
		}catch(Exception e) {
			fail("ITEM NOT SAVED: " + e);
		}
		
		item = new ItemVO();
		item.setCode("COD0000001235ABC");
		item.setDescription("Some new item");
		item.setDetail("Some new item detail");
		item.setUnitOfMeasure(uom);
		
		try {
//			catalogManager.saveItem(item);
		}catch(Exception e) {
			fail("ITEM NOT SAVED: " + e);
		}
	}
	
	@Test
	public void testSaveCategory() {
		CategoryVO category = new CategoryVO();
		category.setName("Una categoría");
		category.setDescription("Descripción de una categoría");
		
		
		try {
			CategoryVO cat = catalogManager.saveCategory(category);
			System.out.println(cat);
		}catch(Exception e) {
			fail("CATEGORY NOT SAVED: " + e);
		}
	}
	
	@Test
	public void testGetAllItems() {
		List<ItemVO> items = catalogManager.getAllItems();

		assertNotNull(items);
		assertTrue(items.size() == 2);
		
		for (int i = 0; i < items.size(); i++) {
			ItemVO item = items.get(i);
			
			assertNotNull(item.getId());
			assertNotNull(item.getCode());
			assertTrue(item.getId() == (i + 1));
		}
	}
	
//	@Test
//	public void testGetAllCategories() {
//		List<CategoryVO> categories = catalogManager.getAllCategories();
//
//		assertNotNull(categories);
//		assertTrue(categories.size() == 1);
//		
//		for (int i = 0; i < categories.size(); i++) {
//			CategoryVO category = categories.get(i);
//			
//			assertNotNull(category.getId());
//			assertNotNull(category.getName());
//			assertTrue(category.getId() == (i + 1));
//		}
//	}

//	@Test
//	public void testSaveClassification() {
//		List<ItemVO> items = catalogManager.getAllItems();
//		List<CategoryVO> categories = catalogManager.getAllCategories();
//		
//		ItemClassificationVO itemClassification = new ItemClassificationVO();
//		itemClassification.setCategory(categories.get(0));
//		itemClassification.setItem(items.get(0));
//		
//		try {
//			catalogManager.saveItemClassification(itemClassification);
//		}catch(Exception e) {
//			fail("CATEGORY NOT SAVED: " + e);
//		}
//	}
}
