package mx.com.mindbits.argos.inventory.dao;

import java.util.List;

import mx.com.mindbits.argos.inventory.entity.UnitOfMeasure;


public interface UnitOfMeasureDAO extends BaseDAO<Integer, UnitOfMeasure> {
	
	UnitOfMeasure getUnitOfMeasure(Integer unitId);
	
	List<UnitOfMeasure> getAllUnitsOfMeasure();
	
	List<UnitOfMeasure> findByName(String unitName);
	
	UnitOfMeasure saveUnitOfMeasure(UnitOfMeasure unitOfMeasure);
	
	UnitOfMeasure updateUnitOfMeasure(UnitOfMeasure unitToUpdate);
	
	void deleteUnitOfMeasure(Integer unitId);
	
}
