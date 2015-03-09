package mx.com.mindbits.argos.inventory.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.mindbits.argos.inventory.entity.BaseEntity;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;

public abstract class HibernateBaseDAO<K extends Serializable, E extends BaseEntity<K>> implements BaseDAO<K, E> {

	private static final long serialVersionUID = 9196752432776951968L;
	
	@Autowired
	protected SessionFactory sessionFactory;

	@Override
	public E save(E entity) {
		@SuppressWarnings("unchecked")
		final K id = (K) sessionFactory.getCurrentSession().save(entity);
		
		return find(id);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public E merge(E entity) {
		return (E) this.sessionFactory.getCurrentSession().merge(entity);
	}

	@Override
	@SuppressWarnings("unchecked")
	public E find(K id) {
		return (E) sessionFactory.getCurrentSession().get(getEntityClass(), id);
	}

	@Override
	public List<E> list() {
		@SuppressWarnings("unchecked")
		final List<E> list = sessionFactory.getCurrentSession().createQuery("from " + this.getEntityClass().getSimpleName()).list();
		
		return list;
	}

	@Override
	public E update(E entity) {
		sessionFactory.getCurrentSession().update(entity);
		
		return find(entity.getId());
	}

	@Override
	public void delete(K id) {
		E entity = find(id);
		sessionFactory.getCurrentSession().delete(entity);
	}

	private Class<?> getEntityClass() {
		final Class<?> result = GenericTypeResolver.resolveTypeArguments(getClass(), HibernateBaseDAO.class)[1];
		if (result == null) {
			
			throw new IllegalArgumentException("can not resolve type argument for DAO: " + getClass().getSimpleName());
		}
		return result;
	}
	
}
