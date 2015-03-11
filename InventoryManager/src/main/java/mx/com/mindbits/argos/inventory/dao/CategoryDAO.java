package mx.com.mindbits.argos.inventory.dao;

import java.util.List;

import mx.com.mindbits.argos.inventory.entity.Category;


public interface CategoryDAO extends BaseDAO<Integer, Category> {
	
	Category getCategory(Integer categoryId);
	
	List<Category> getAllCategories();
	
	List<Category> getMainCategories();
	
	List<Category> getCategoryDescendants(Integer categoryId);
	
	Category saveCategory(Category category);
	
	Category updateCategory(Category categoryToUpdate);
	
	void deleteCategory(Integer categoryId);
	
}
