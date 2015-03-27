package mx.com.mindbits.argos.inventory.dao.impl;

import java.util.List;

import mx.com.mindbits.argos.inventory.dao.HibernateBaseDAO;
import mx.com.mindbits.argos.inventory.dao.ProductionDAO;
import mx.com.mindbits.argos.inventory.entity.Production;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ProductionDAOImpl extends HibernateBaseDAO<Integer, Production> implements ProductionDAO {

	private static final long serialVersionUID = -5769009931863886621L;

	@Override
	public Production getProduction(Integer id) {
		return find(id);
	}
	
	@Override
	public List<Production> getAllProductions() {
		return list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Production> findByName(String productionName) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findProductionByName");
		query.setString("productionName", "%" + productionName + "%");
		
		return (List<Production>)query.list();
	}
	
	@Override
	public Production saveProduction(Production production) {
		return save(production);
	}

	@Override
	public Production updateProduction(Production productionToUpdate) {
		return update(productionToUpdate);
	}
	
	@Override
	public void deleteProduction(Integer id) {
		delete(id);
	}
	
}
