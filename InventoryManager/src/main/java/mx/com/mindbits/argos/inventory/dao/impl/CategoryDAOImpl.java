package mx.com.mindbits.argos.inventory.dao.impl;

import java.util.List;

import mx.com.mindbits.argos.inventory.dao.CategoryDAO;
import mx.com.mindbits.argos.inventory.dao.HibernateBaseDAO;
import mx.com.mindbits.argos.inventory.entity.Category;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAOImpl extends HibernateBaseDAO<Integer, Category> implements CategoryDAO {

	private static final long serialVersionUID = -6028568875764160744L;

	@Override
	public Category getCategory(Integer id) {
		return find(id);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Category> getCategoryDescendants(Integer categoryId) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("getCategoryDescendants");
		query.setInteger("parentId", categoryId);
		
		return (List<Category>)query.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Category> getMainCategories() {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("getMainCategories");
		
		return (List<Category>)query.list();
	}
	
	@Override
	public List<Category> getAllCategories() {
		return list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Category> findByName(String categoryName) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findCategoryByName");
		query.setString("categoryName", "%" + categoryName + "%");
		
		return (List<Category>)query.list();
	}
	
	@Override
	public Category saveCategory(Category newCategory) {
		return save(newCategory);
	}

	@Override
	public Category updateCategory(Category categoryToUpdate) {
		Category cat = update(categoryToUpdate);
		return cat;
	}
	
	@Override
	public void deleteCategory(Integer id) {
		delete(id);
	}
	
}
