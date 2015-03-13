package mx.com.mindbits.argos.inventory.dao;

import java.util.List;

import mx.com.mindbits.argos.inventory.entity.Production;


public interface ProductionDAO extends BaseDAO<Integer, Production> {
	
	Production getProduction(Integer productionId);
	
	List<Production> getAllProductions();
	
	Production saveProduction(Production production);
	
	Production updateProduction(Production productionToUpdate);
	
	void deleteProduction(Integer productionId);
	
}
