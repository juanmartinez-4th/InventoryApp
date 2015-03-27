package mx.com.mindbits.argos.inventory.dao.impl;

import java.util.List;

import mx.com.mindbits.argos.inventory.dao.HibernateBaseDAO;
import mx.com.mindbits.argos.inventory.dao.UnitOfMeasureDAO;
import mx.com.mindbits.argos.inventory.entity.UnitOfMeasure;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UnitOfMeasureDAOImpl extends HibernateBaseDAO<Integer, UnitOfMeasure> implements UnitOfMeasureDAO {
	private static final long serialVersionUID = 4338014302352566859L;

	@Override
	public UnitOfMeasure getUnitOfMeasure(Integer id) {
		return find(id);
	}
	
	@Override
	public List<UnitOfMeasure> getAllUnitsOfMeasure() {
		return list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<UnitOfMeasure> findByName(String unitName) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findUnitByName");
		query.setString("unitName", "%" + unitName + "%");
		
		return (List<UnitOfMeasure>)query.list();
	}
	
	@Override
	public UnitOfMeasure saveUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
		return save(unitOfMeasure);
	}

	@Override
	public UnitOfMeasure updateUnitOfMeasure(UnitOfMeasure unitToUpdate) {
		return update(unitToUpdate);
	}
	
	@Override
	public void deleteUnitOfMeasure(Integer id) {
		delete(id);
	}
	
}
